package com.ranyk.www.demo.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import com.ranyk.www.demo.enums.DataTypeEnum;
import com.ranyk.www.demo.enums.MethodNameEnum;
import com.ranyk.www.demo.exception.CustomException;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * @class_name: ObjectHandler
 * @description: 对象处理类
 * 
 * @author ranyk
 */
@Slf4j
public class ObjectHandler {

    private ObjectHandler() {

    }

    /**
     * 强制类型转换方法
     *
     * @param obj 需要进行类型转换的对象
     * @param <T> 需要转换成的类型
     * @return 返回转换后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    /**
     * 根据对应的类型Class对象,将对应的Object对象实例转换成对应的类型<br/>
     * ,该方法只能用于非集合类型的转换.方法要求: <br/>
     * 1. 传入的实例对象需要是对应的 Class 对象的实例或有关子类对象;<br/>
     * 2. 对数字的String类型可转成Integer类型;<br/>
     * 3. 其他类型转换暂不支持;<br/>
     *
     * @param clz 对应的Class对象
     * @param o   需要转换的实例对象
     * @param <T> 需要转换的类型,使用泛型替代
     * @return 返回转换后的对应类型对象,默认返回null;
     */
    @SuppressWarnings("unchecked")
    public static <T, V> V get(Class<?> clz, T o) {
        // 1. 传入的值是对应的class类型,则直接进行强制类型转换
        if (clz.isInstance(o)) {
            return (V) clz.cast(o);
        }
        // 2. 当传入的为 String 值,而类型为 Integer 时,需要进行类型处理
        if (DataTypeEnum.INTEGER_ENUM.getValue().equals(clz.getName())) {
            String value;
            try {
                if (o instanceof String) {
                    value = get(String.class, (String) o);
                } else {
                    value = "0";
                }
            } catch (Exception e) {
                log.error("在对获取指定类型的值时发生异常,异常信息为  {} ,将进行类型的强制转换.", e.getMessage());
                value = (String) o;
            }
            Assert.notNull(value, "在通过类型进行值类型整形转换时,获取到的值为空!");
            Integer result;
            try {
                result = Integer.valueOf(value);
            } catch (Exception e) {
                log.error("在将String转换为Integer时发生异常,异常信息为 {} , 当前传入的 String 类型数据的值为 {},将返回结果设置为 0.", e.getMessage(),
                        value);
                result = 0;
            }
            return (V) result;
        }
        // 3. 如果是其他类型需要进行处理的则在此处

        // 4. 最后再返回空值
        return null;
    }

    /**
     * 验证对象是否为空静态方法
     *
     * @param obj 需要判断的对象
     * @return 如果判断对象为空则, 返回true;不为空则返回,false;
     */
    @SuppressWarnings("rawtypes")
    public static Boolean objectIsEmpty(Object obj) {
        // 1. 判断对象本身是否为 null
        if (null == obj) {
            return true;
        }
        // 2. 当对象是字符串时,使用字符串长度是否为0,判断
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        // 3. 当对象是 Collection 集合时,先将对象转成 Collection 集合,再判断是否为空
        if (obj instanceof Collection) {
            Collection castCollectionResult = cast(obj);
            return (castCollectionResult).isEmpty();
        }
        // 4. 当对象是 Map 集合时,先将对象转成 Map 集合,再判断是否为空
        if (obj instanceof Map) {
            Map castMapResult = cast(obj);
            return (castMapResult).isEmpty();
        }
        // 5. 当对象是数组时,先将对象转成数组,再判断是否为空
        if (obj instanceof Object[]) {
            Object[] objects = (Object[]) obj;
            if (objects.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : objects) {
                if (!(boolean) objectIsEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        // 6. 默认返回 false,即验证对象不为空
        return false;
    }

    /**
     * 执行对应的指定对象的指定方法,目前仅支持 set/get 方法
     *
     * @param clz           需要执行的方法的class对象
     * @param o             需要执行方法的实例对象
     * @param methodName    需要执行的方法名
     * @param value         执行方法的入参参数值
     * @param parameterType 执行方法的入参参数类型
     */
    @SuppressWarnings("all")
    public static Object invokeSpecifyMethod(Class<?> clz, Object o, String methodName, Object value,
            Class<?>[] parameterType) {
        if (ObjectHandler.objectIsEmpty(value) && !methodName.startsWith(MethodNameEnum.GET_METHOD.getValue())) {
            log.info("利用反射执行指定方法时,未传入有关值,当前执行的方法名为 {}", methodName);
            return null;
        }
        try {
            if (ObjectHandler.objectIsEmpty(clz)) {
                clz = o.getClass();
            }
            Method method = clz.getMethod(methodName, parameterType);
            if (methodName.startsWith(MethodNameEnum.GET_METHOD.getValue())) {
                return method.invoke(o);
            }
            if (methodName.startsWith(MethodNameEnum.SET_METHOD.getValue())) {
                return method.invoke(o, value);
            }
            return null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("利用反射执行对应的方法是发生错误,错误信息为: {}, 错误行数为: {}", e.getMessage(), e.getStackTrace()[0].getLineNumber());
            throw new RuntimeException("利用反射执行对应的方法是发生错误,错误信息为: " + e.getMessage());
        }
    }

    /**
     * 获取对应属性的getter或者setter方法名,使用此方法需注意: 对应的属性名的定义不能为 is开头
     *
     * @param property 需要获取对应getter或setter方法名的属性名,属性名必须是和get方法中去掉get后第一个字母小写的格式一样,
     *                 如: sex 对应的get方法应该是 getSex() ;userName 对应的get 方法应该是
     *                 getUserName();
     * @param key      获取set/get方法key
     * @return 返回对应属性的get或set方法名
     * @throws CustomException
     */
    public static String getSetterOrGetterMethodName(String property, String key) throws CustomException {
        if ((boolean) ObjectHandler.objectIsEmpty(property)) {
            throw new CustomException("属性名为空!");
        }
        var sb = new StringBuilder(property);
        boolean changeCapitalLetters = Character.isLowerCase(sb.charAt(0))
                && (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1)));
        if (changeCapitalLetters) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        sb.insert(0, key);
        return sb.toString();
    }

    /**
     * 利用反射执指定对象指定属性的get方法
     * 
     * @param clazz    需要执行get方法的类的 Class 对象
     * @param o        需要执行get方法的对象
     * @param property 需要获取的属性
     * @return 返回执行get方法后的返回值
     */
    public static Object invokeGetMethod(Class<? extends Object> clazz, Object o, String property)
            throws CustomException {
        try {
            var method = clazz.getDeclaredMethod(
                    getSetterOrGetterMethodName(property, MethodNameEnum.GET_METHOD.getValue()),
                    (Class<?>[]) new Class[0]);
            return method.invoke(o);
        } catch (NullPointerException | NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            throw new CustomException("执行指定对象的指定属性 " + property + " 的 get 方法出错,错误信息为: " + e.getMessage() + " 错误异常行号为: "
                    + e.getStackTrace()[0].getLineNumber());
        }
    }

}
