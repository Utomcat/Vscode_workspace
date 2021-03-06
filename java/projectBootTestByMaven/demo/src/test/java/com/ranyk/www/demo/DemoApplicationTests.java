package com.ranyk.www.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import com.ranyk.www.demo.enums.CheckHouAtOtherBizType;
import com.ranyk.www.demo.model.CorpDTO;
import com.ranyk.www.demo.model.Personel;
import com.ranyk.www.demo.util.FileUtil;
import com.ranyk.www.demo.util.NumberHandler;
import com.ranyk.www.demo.util.ObjectHandler;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	/**
	 * Lists.asList 方法测试,创建生成只有一个值的 List 对象
	 */
	@Test
	void contextLoads() {
		List<Integer> list = Lists.asList(Integer.valueOf("1"), new Integer[0]);
		log.info("{}", list);
	}

	/**
	 * 特殊字符的切割测试
	 */
	@Test
	void test0() {
		String st = "26f59e82-de48-4012-aa1b-be8bb08fc9f1~$510100";
		log.error("是否包含对应的指定字符: {}", st.contains("~$"));
		String result = st.replace("~$", "_");
		String[] sts = result.split("_");
		for (String s : sts) {
			log.error("当前的值为 ==> {}", s);
		}
	}

	/**
	 * ==、equals、Objects.equals 对象相等的判断,相等判断结果如下: <br/>
	 * 
	 */
	@Test
	void test1() {
		Personel personel1 = new Personel("张三", 23);
		Personel personel2 = new Personel("张三", 23);
		Personel personel3 = personel1;
		personel3.setAge(25);

		log.info("personel1 ==> {}", JSONObject.toJSONString(personel1));
		log.info("personel2 ==> {}", JSONObject.toJSONString(personel2));
		log.info("personel3 ==> {}", JSONObject.toJSONString(personel3));
		log.info("使用 == 方式判断两个对象是否相等 ==> {}", personel1 == personel2);
		log.info("使用 == 方式判断两个对象是否相等 ==> {}", personel1 == personel3);
		log.info("使用 equals 方式判断两个对象是否相等 ==> {}", personel1.equals(personel2));
		log.info("使用 Objects.equals 方式判断两个对象是否相等 ==> {}", Objects.equals(personel1, personel2));

	}

	/**
	 * 测试 == equals Objects.equals : 测试判断的有关相等的方式
	 */
	@Test
	void test2() {
		String a = null;
		String b = null;
		String c = a;
		Boolean bol = Boolean.TRUE;
		String d = new String("aaaa");
		String e = "aaaa";
		Personel personel = new Personel("aaaa", 12);

		log.error("使用 == 判定相同类型的数据是否相等 {}", a == b);
		log.error("使用 == 判定相同对象是否相等 {}", a == c);
		log.error("{}", bol);
		log.error("{}", d);
		log.error("{}", e);
		log.error("{}", personel);
	}

	/**
	 * 字符串trim()方法
	 */
	@Test
	void test3() {
		String str = "  初  七 ";
		log.error("使用trim()方法对str原字符串为==>{}<==,进行空格处理,其结果为 ==>{}<==", str, str.trim());
		Personel person = new Personel("  张三  ", 10);
		Personel person1 = new Personel();
		log.error("输入的对象的原姓名为 ==>{}<==", person.getName());
		person.setName(person.getName().trim());
		log.error("输入的对象的处理后的姓名为 ==>{}<==", person.getName());
		log.error("输入的对象的原姓名为 ==>{}<==", person1.getName());
		if (StringUtils.hasLength(person1.getName())) {
			person1.setName(person1.getName().trim());
			log.error("输入的对象的原姓名为 ==>{}<==", person1.getName());
		} else {
			log.error("姓名为空,不予进行空格处理");
		}

	}

	/**
	 * 字符串拆分测试
	 */
	@Test
	void test4() {
		byte[] dwx = new byte[1024];
		String fileId = "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.dwg" + ","
				+ new BigDecimal(dwx.length).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "dwg" + ","
				+ "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.pdf" + ","
				+ new BigDecimal(dwx.length).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "PDF";
		String[] fileIdData = fileId.split(",");
		int length = fileIdData.length;
		log.error("字符拆分后的数组长度为 ==> {}", length);
		for (int i = 0; i < length; i++) {
			log.error("index = {} 的值 ==> {}", i, fileIdData[i]);
		}
		log.error(" 字符拆分的结果为 ==>  {}", JSONObject.toJSON(fileIdData));
	}

	/**
	 * 字符串拆分测试
	 */
	@Test
	void test5() {
		byte[] dwx = new byte[1024];
		String fileId = "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.dwg" + ","
				+ new BigDecimal(dwx.length).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "dwg" + "," + ""
				+ "," + new BigDecimal(0).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "PDF";
		String[] fileIdData = fileId.split(",");
		int length = fileIdData.length;
		log.error("字符拆分后的数组长度为 ==> {}", length);
		for (int i = 0; i < length; i++) {
			if (i == 3) {
				log.error("index = {} 的值 ==> {}", i, StringUtils.hasText(fileIdData[i]) ? fileIdData[i] : null);
			} else {
				log.error("index = {} 的值 ==> {}", i, fileIdData[i]);
			}
		}
		log.error(" 字符拆分的结果为 ==>  {}", JSONObject.toJSON(fileIdData));
	}

	/**
	 * 数组测试
	 */
	@Test
	void test6() {
		int[] arr = { 0, 0, 0, 0 };
		int[] initStrs = new int[10];
		for (int i = 0; i < arr.length; i++) {
			initStrs[arr[i]]++;
		}
		for (int i = 0; i < initStrs.length; i++) {
			System.out.println(i + " ==> " + initStrs[i]);
		}
	}

	/**
	 * 对象状态翻译测试方法
	 */
	@Test
	void test7() {
		Integer a = Integer.valueOf(0);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("key", a);
		String dicStatusCode = translationDicStatusCode(String.valueOf(result.get("key")));
		log.error("获取到Map中的Integer类型数据后强转为String测试结果为 {}", dicStatusCode);
	}

	/**
	 * 强制类型转换方法
	 * 
	 * @param <T> 泛型类型,用来对类型转换的返回类型进行定义
	 * @param obj 传入的需要进行强制类型转换的对象
	 * @return 返回装换后的数据类型值
	 */
	@SuppressWarnings("unchecked")
	public <T> T cast(Object obj) {
		return (T) obj;
	}

	/**
	 * 翻译对象状态的方法,依据传入的值进行有关值的返回
	 * 
	 * @param code 需要翻译的对象状态code
	 * @return 返回翻译后的值
	 */
	public String translationDicStatusCode(String code) {
		switch (code) {
			case "0":
				return "待受理";
			case "1":
				return "待办结";
			case "2":
				return "待确认";
			case "3":
				return "待归档";
			case "4":
				return "已归档";
			default:
				return "未知";
		}
	}

	/**
	 * 验证 instanceof 关键字的<br/>
	 * 只能是同中类型或其子类进行判断<br/>
	 * 如: Integer 不能和 String 类型的数据进行判断<br/>
	 */
	@Test
	void test8() {
		String str = new String();
		Integer i = Integer.valueOf(0);
		log.error("str 和 String 的关系是 ==> {}", str instanceof String);
		log.error("i 和 Integer 的关系是 ==> {}", i instanceof Integer);

	}

	/**
	 * 读取zip文件压缩包中的信息,获取其中指定的数据的dwg文件和pdf文件
	 */
	@Test
	void test9() {
		try {
			String path = this.getClass().getResource("/").getPath();
			File file = new File(path + "/20021001.zip");
			FileInputStream zipFile = new FileInputStream(file);
			ZipInputStream zis = new ZipInputStream(zipFile);
			log.info("zip的文件大小为: ==> {}  byte", file.length());
			if (zis.getNextEntry() != null) {
				byte[] bytes = FileUtil.getContent(zis);
				String str = new String(bytes);
				JSONObject json = JSONObject.parseObject(str);
				JSONArray array = JSONObject.parseArray(JSON.toJSONString(json.get("data")));
				Decoder decoder = Base64.getDecoder();
				for (int i = 0; i < array.size(); i++) {
					JSONObject sub = JSONObject.parseObject(JSON.toJSONString(array.get(i)));
					if ("101".equalsIgnoreCase(String.valueOf(sub.get("buildidpart")))) {
						String dwghlob = String.valueOf(sub.get("dwghlob"));
						String dwfhlob = String.valueOf(sub.get("dwfhlob"));
						byte[] dwg = decoder.decode(dwghlob);
						byte[] dwf = decoder.decode(dwfhlob);
						ByteArrayInputStream dwgInputStream = new ByteArrayInputStream(dwg);
						ByteArrayInputStream dwfInputStream = new ByteArrayInputStream(dwf);
						FileOutputStream dwgFileOutPut = new FileOutputStream(
								new File(path + "/" + String.valueOf(sub.get("buildidpart")) + ".dwg"));
						FileOutputStream pdfFileOutPut = new FileOutputStream(
								new File(path + "/" + String.valueOf(sub.get("buildidpart")) + ".pdf"));
						FileUtil.writeFile(dwgInputStream, dwgFileOutPut);
						FileUtil.writeFile(dwfInputStream, pdfFileOutPut);
					}
					log.info("buildidpart ==> {}", sub.get("buildidpart"));
				}
			}
			log.info("压缩文件的文件路径为: getPath ==> {} , getAbsolutePath ==>  {} , getCanonicalPath ==> {} ", file.getPath(),
					file.getAbsolutePath(), file.getCanonicalPath());
			zis.close();
		} catch (Exception e) {
			log.error("发生异常,异常信息为: {} !", e.getMessage());
		}
	}

	/**
	 * 遍历枚举对象获取定义的此类型的枚举值
	 */
	@Test
	void test10() {
		CheckHouAtOtherBizType checkType = CheckHouAtOtherBizType.getTypeByName("");
		log.info("获取到的 checkType 为 ==> {name:{},checkChsBlue:{},checkChsCont:{},checkEstate:{},checkShsCont:{}}",
				checkType.getName(), checkType.getCheckChsBlue(), checkType.getCheckChsCont(),
				checkType.getCheckEstate(), checkType.getCheckShsCont());
	}

	/**
	 * 测试Boolean类型的数据是否满足判断逻辑
	 */
	@Test
	void test11() {
		Boolean bl = false;
		if (bl == null || bl) {
			log.info("需要抛出异常");
			return;
		}
		log.info("执行为false的结果");
	}

	/**
	 * 模拟解析请求响应后指定字符串结果
	 */
	@Test
	@SuppressWarnings("all")
	void test12() {
		String realImgUrl = null;
		String result = null;
		String reponseResult = "<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">D:\\Mapping\\HouseImgJson\\YC\\202101081201.zip</string>";
		if (reponseResult.contains("Mapping")) {
			result = reponseResult.substring(reponseResult.indexOf("Mapping") + "Mapping".length(),
					reponseResult.length() - "</string>".length()).replace("\\", "/");
			log.error("截取的返回地址结果为 ==> {}", result);
			String calMainUrl = "http://10.209.1.8:8022/MappingWebService.asmx";
			realImgUrl = calMainUrl.substring(0, calMainUrl.lastIndexOf("/")) + result;
		}

		if (StringUtils.hasLength(realImgUrl)) {
			log.error("获取到的真实图片本地地址为 ==> {}", realImgUrl);
		}
	}

	/**
	 * 获取某个类中的所有方法名和对应的参数<br/>
	 * 属性的get方法入参参数为一个空的数组;<br/>
	 * 属性的set方法入参就是对应入参的class对象数组<br/>
	 */
	@Test
	void test13() {
		Personel personel = new Personel("张三", 20, 1);
		Class<? extends Personel> clazz = personel.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		log.info("获取到的方法如下: ");
		List<Method> methodList = Arrays.stream(methods).collect(Collectors.toList());
		methodList.forEach(method -> {
			log.info("当前通过反射获取的方法名为 ==> {}, 方法参数类型为 ==> {}", method.getName(), method.getParameterTypes());
		});
	}

	/**
	 * 执行反射的get方法
	 */
	@Test
	void test14() {
		Personel personel = new Personel("张三", 20, 1);
		Class<? extends Personel> clazz = personel.getClass();
		try {
			Integer resutl = ObjectHandler.cast(ObjectHandler.invokeGetMethod(clazz, personel, "sex"));
			log.info("调用方法 invokeGetMethod 利用反射执行get属性值的结果为: {}", resutl);
		} catch (Exception e) {
			log.info("执行反射的get方法时发生异常,异常信息为: {}", e.getMessage());
		}
		try {
			Integer resutl = ObjectHandler.cast(
					ObjectHandler.invokeSpecifyMethod(clazz, personel, "getSex", null, (Class<?>[]) new Class[0]));
			log.info("调用方法 invokeSpecifyMethod 利用反射执行get属性值的结果为: {}", resutl);
		} catch (Exception e) {
			log.info("执行反射的get方法时发生异常,异常信息为: {}", e.getMessage());
		}
	}

	/**
	 * 验证数据类型转换方法能否将字符串转成整形数据
	 */
	@Test
	void test15() {
		Personel personel = new Personel("张三", 20, 1);
		Map<String, Object> map = new HashMap<>(6);
		map.put("sex", "2");
		map.put("string", "AAAAA");
		try {
			Field field = personel.getClass().getDeclaredField("sex");
			Integer result = ObjectHandler.get(field.getType(), map.get("string"));
			log.info("查询结果为 ==> {}", result);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件测试方法
	 */
	@Test
	void test16() {
		String zipPath = this.getClass().getResource("/").getPath() + LocalDateTime.now().getNano() + ".zip";
		String filePath = this.getClass().getResource("/").getPath() + "templates/101.pdf";
		log.info("需压缩的文件的路径为 {}, 压缩文件的路径为: {}", filePath, zipPath);
		File zipFile = new File(zipPath);
		File file = new File(filePath);
		try {
			if (!zipFile.exists() && zipFile.createNewFile()) {
				log.info("创建压缩文件成功!");
			}
			if (!file.exists()) {
				log.error("需被压缩文件不存在!");
				return;
			}
		} catch (IOException e) {
			log.error("创建或获取压缩文件对象失败!异常信息为: {}", e.getMessage());
			return;
		}
		try (var out = new FileOutputStream(zipFile)) {
			FileUtil.compressSingleFile(file, zipFile);
		} catch (Exception e) {
			log.error("压缩失败,异常信息为: {}", e.getMessage());
			return;
		}
		log.info("压缩成功!");
	}

	/**
	 * 测试使用 URL 对象获取文件内容,获取失败
	 */
	@Test
	void test17() {
		String path = "E:\\Work\\Vscode_workspace\\java\\projectBootTestByMaven\\demo\\target\\classes\\535708700.zip";
		try {
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			if (httpURLConnection.getResponseCode() == 200) {
				InputStream is = httpURLConnection.getInputStream();
				ZipInputStream zis = new ZipInputStream(is);
				ZipEntry zipEntry;
				if ((zipEntry = zis.getNextEntry()) != null) {
					if (!zipEntry.isDirectory()) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] bt = new byte[1024];
						int len;
						while ((len = zis.read(bt, 0, bt.length)) != -1) {
							baos.write(bt, 0, len);
						}
						String result = new String(baos.toByteArray());
						log.info("获取的结果为 ==> {}", result.length());
					}
				}
			}
		} catch (Exception e) {
			log.error("错误信息 ==> {}", e.getMessage());
		}
	}

	/**
	 * 访问Ftp地址获取有关文件
	 */
	@Test
	void test18() {
		String path = "ftp://172.16.24.225/361307000.zip";
		try {
			URL url = new URL(path);
			URLConnection connection = url.openConnection();
			if (connection != null) {
				InputStream is = connection.getInputStream();
				ZipInputStream zis = new ZipInputStream(is);
				ZipEntry zipEntry;
				if ((zipEntry = zis.getNextEntry()) != null) {
					if (!zipEntry.isDirectory()) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] bt = new byte[1024];
						int len;
						while ((len = zis.read(bt, 0, bt.length)) != -1) {
							baos.write(bt, 0, len);
						}
						String result = new String(baos.toByteArray());
						log.info("获取的结果为 ==> {}", result.length());
					}
				}
			}
		} catch (Exception e) {
			log.error("错误信息 ==> {}", e.getMessage());
		}
	}

	/**
	 * List remove 指定对象测试
	 */
	@Test
	void test19() {
		List<Personel> list = new ArrayList<>();
		Personel personel1 = new Personel("张三", 2);
		Personel personel2 = new Personel("李四", 2);
		Personel personel3 = new Personel("王五", 2);
		list.add(personel1);
		list.add(personel2);
		list.add(personel3);
		log.info("移除元素之前List集合的值 ==> {}", JSON.toJSONString(list));
		list.remove(personel1);
		log.info("移除元素之后List集合的值 ==> {}", JSON.toJSONString(list));
	}

	/**
	 * 字符串截取测试
	 */
	@Test
	void test20() {
		StringBuilder sb = new StringBuilder("123,456,2345,123466,112456,");
		log.info("字符截取之前的结果 ==> {}", sb.toString());
		String result = sb.toString().substring(0, sb.lastIndexOf(","));
		log.info("字符截取之后的结果 ==> {}", result);

	}

	/**
	 * org.springframework.util.StringUtils.hasText 和
	 * org.springframework.util.StringUtils.hasLength 方法对不同数据类型的数据进行判空处理的结果验证
	 */
	@Test
	void test21() {
		Integer str = 123456;
		Integer str1 = null;
		String str2 = "";
		String str3 = null;
		log.info("Integer 类型数据有正常的数字值,用 hasText 方法的结果为: {} , 用 hasLength 方法的结果为: {}",
				StringUtils.hasText(String.valueOf(str)), StringUtils.hasLength(String.valueOf(str)));
		log.info("Integer 类型数据为null,用 hasText 方法的结果为: {} , 用 hasLength 方法结果为: {}",
				StringUtils.hasText(String.valueOf(str1)), StringUtils.hasLength(String.valueOf(str1)));

		log.info("String 类型数据值为 \"\" ,用 hasText 方法的结果为: {} , hasLength 方法结果为: {}", StringUtils.hasText(str2),
				StringUtils.hasLength(str2));
		log.info("String 类型数据值为 null ,用 hasText 方法的结果为: {} , hasLength 方法结果为: {}", StringUtils.hasText(str3),
				StringUtils.hasLength(str3));

	}

	/**
	 * String.join() 方法测试
	 */
	@Test
	void test22() {
		List<String> list1 = new ArrayList<>();
		list1.add("Java");
		list1.add("is");
		list1.add("cool");
		Set<String> set1 = new HashSet<>();
		set1.add("PHP");
		set1.add("is");
		set1.add("cool");
		String result1 = String.join(",", list1);
		String result2 = String.join(",", set1);
		log.info("String.join + List ==> {}", result1);
		log.info("String.join + Set ==> {}", result2);
	}

	/**
	 * Collections.singletonList() 方法测试其创建的 List 集合的长度
	 */
	@Test
	void test23() {
		List<String> lists = Collections.singletonList(new String());
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = Collections.emptyList();
		log.info("创建的 List 的长度为: ==> {}", lists.size());
		log.info("创建的 List2 的长度为: ==> {}", list2.size());
		log.info("创建的 List3 的长度为: ==> {}", list3.size());
	}

	/**
	 * 字符串拼接后长度和结果的测试
	 */
	@Test
	void test24() {
		String sql = "(";
		sql += ")";
		log.info("拼接的SQL范围为: ==> {} , 其字符串的长度为: ==> {}", sql, sql.length());
	}

	/**
	 * 生成随机六位数测试
	 */
	@Test
	void test25() {
		for (int i = 0; i <= 100; i++) {
			String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
			Random rand = new Random();
			StringBuffer flag = new StringBuffer();
			for (int j = 0; j < 6; j++) {
				flag.append(sources.charAt(rand.nextInt(9)) + "");
			}
			log.info("生成的随机数为: ==> {}", flag.toString());
		}
	}

	@Test
	void test26() {
		String a = "1";
		String b = "3,1,2";
		String c = "";
		String d = ",";
		String e = null;
		String[] array = { a, b, c, d, e };
		for (String str : array) {
			try {
				String[] result = str.split(",");
				log.info("当前切割的字符串为 {} ;切割后的结果为 {} ;对应数组的长度为 {}", str, JSON.toJSONString(result), result.length);
				List<String> list = Arrays.stream(result).filter(StringUtils::hasText).sorted()
						.collect(Collectors.toList());
				log.info("当前切割的字符串为 {} ;切割后的结果为 {} ;对应数组的长度为 {}", str, JSON.toJSONString(list), list.size());
				log.info("{}", "------------------------------------------------------------------------------------");
			} catch (Exception exception) {
				log.info("当前发生异常,异常的对象为: {} ,异常信息为: {}", str, exception.getMessage());
			}
		}
	}

	@Test
	void test27() {
		Boolean bl = null;
		boolean bl2 = false;
		boolean bl3 = true;
		log.info("对比结果1 ==> {}", ObjectHandler.objectIsEmpty(bl));
		log.info("对比结果2 ==> {}", ObjectHandler.objectIsEmpty(bl2));
		log.info("对比结果3 ==> {}", ObjectHandler.objectIsEmpty(bl3));
	}

	/**
	 * boolean 类型数据的默认值,编译不通过 Boolean 类型数据的默认值,编译不通过
	 */
	@Test
	@SuppressWarnings("all")
	void test28() {
		boolean a;
		Boolean b;
		// log.info("默认的 boolean 类型的数据为 {}", a);
		// log.info("默认的 boolean 类型的数据为 {}", b);
	}

	/**
	 * jdk 8 使用 forEach 遍历 Map 集合
	 */
	@Test
	void test29() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("a", "valuea");
		map.put("b", "valueb");
		map.put("c", "valuec");
		map.put("d", "valued");
		map.put("e", "valuee");
		map.forEach((key, value) -> log.info("{} --> {}", key, value));
	}

	/**
	 * 获取 Integer 、Double、Float 数据类型的最大值和最小值
	 */
	@Test
	void test30() {
		log.info("Integer 的最大值为: {}", Integer.MAX_VALUE);
		log.info("Integer 的最小值为: {}", Integer.MIN_VALUE);
		log.info("Double 的最大值为: {}", Double.MAX_VALUE);
		log.info("Double 的最小值为: {}", Double.MIN_VALUE);
		log.info("Float 的最大值为: {}", Float.MAX_VALUE);
		log.info("Float 的最小值为: {}", Float.MIN_VALUE);
	}

	/**
	 * 测试数字判断方法 isNumber
	 */
	@Test
	void test31() {
		String num1 = "这是一个1234";
		String num2 = "1234";
		String num3 = "-1234";
		String num4 = "12345AA";
		String num5 = "+12345";
		String num6 = "++12345";
		String num7 = "--12345";
		// false
		log.info("对 num1 进行数字判断的结果为: {}", NumberHandler.isNumber(num1));
		// true
		log.info("对 num2 进行数字判断的结果为: {}", NumberHandler.isNumber(num2));
		// true
		log.info("对 num3 进行数字判断的结果为: {}", NumberHandler.isNumber(num3));
		// false
		log.info("对 num4 进行数字判断的结果为: {}", NumberHandler.isNumber(num4));
		// true
		log.info("对 num5 进行数字判断的结果为: {}", NumberHandler.isNumber(num5));
		// false
		log.info("对 num6 进行数字判断的结果为: {}", NumberHandler.isNumber(num6));
		// false
		log.info("对 num7 进行数字判断的结果为: {}", NumberHandler.isNumber(num7));
	}

	/**
	 * String 数据类型和 Integer 数据类型的转换
	 */
	@Test
	void test32() {
		String numberStr = "1234567";
		Integer number = 123456;
		log.info("String ==> Integer 使用 Integer.parseInt(String str) 方法: {}" , Integer.parseInt(numberStr));
		log.info("String ==> Integer 使用 Integer.parseInt(String str, int radix) 方法: {}" , Integer.parseInt(numberStr,16));
		log.info("Integer ==> String 使用 String.valueOf(Object obj) 方法: {}" , String.valueOf(number));
	}

	@Test
	void test33() {
		SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String format1 = sdf.format(date);
		try {
			Date date1 = sdf.parse(format1);
			log.info("format1 ==> {} , date1 ==> {}",format1, sdf2.format(date1));
		} catch (Exception e) {
			log.info("发生异常，异常信息: {}", e.getMessage());
		}
	}

	@Test
	void test34() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String format1 = format.format(new Date(date.getTime() - 86400000));
		try {
			Date date1 = format.parse(format1 + " 22:00 ");
			log.info("format1 ==> {} , date1 ==> {}", format1, sdf2.format(date1));
		} catch (Exception e) {
			log.info("发生异常，异常信息: {}", e.getMessage());
		}
	}

	@Test
	void test35() {
		List<String> lists = new ArrayList<>(6);
		lists.add("a");
		lists.add("b");
		lists.add("c");
		lists.add("d");
		lists.add("e");
		StringBuilder builder = new StringBuilder("检测到");
		if(lists.contains("a")){
			builder.append("包含 a");
		}
		log.info(builder.toString());
	}

	@Test
	void test36() {
		String a =  String.valueOf("");
		log.info("a 的值为 ==> {} , a 是否为空 {}", a, !StringUtils.hasText(a));
	}

	@Test
	@SuppressWarnings("unchecked")
	void test37() {
		Map<String,Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		String jsonStr = JSON.toJSONString(map);
		log.info("当前json字符串为: {}",jsonStr);
		Map<String,Object> result =  JSONObject.parseObject(jsonStr, HashMap.class);
		log.info("当前 result 值为: {}", JSON.toJSONString(result));
	}

	@Test
	void test38() {
		List<String> list1 = new ArrayList<>(16);
		List<String> list2 = new ArrayList<>(16);
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list2.add("1");
		list2.add("4");
		list2.add("3");
		list2.add("5");
		list2.add("2");
		list2.add("6");
		
	}

	@Test
	void test39() {
		try{
			long ms = System.currentTimeMillis();
			Date date = new Date(ms);
			Date date2 = new Date();
			log.info("date before date2 ==> {}", date.before(date2) );
		}catch(Exception exception){
			log.error("异常信息: ", exception);
		}
		
	}

	/**
	 * 判断时间对象的 compareTo 方法的返回值
	 */
	@Test
	void test40() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = new Date();
		try {
			Date before = sdf.parse("2022-04-28 16:24:54.6");
			// A.compareTo(B) A 在 B 之前 返回值小于 0;
			log.info("之前时间和当前时间做比较,其结果为: {}", before.compareTo(now));
			// A.compareTo(B) A 在 B 之后 返回值大于 0;
			log.info("当前时间和之前时间做比较,其结果为: {}", now.compareTo(before));
			// A.compareTo(B) A 等于 B 返回值等于 0;
			log.info("当前时间和当前时间做比较,其结果为: {}", now.compareTo(now));
		} catch (Exception e) {
			log.error("之前时间解析异常,异常信息为: {}", e.getMessage());
		}
	}

	/**
	 * 对象.getClass().getComponentType().getName() 调用结果验证
	 */
	@Test
	void test41() {
		long[] longs = new long[10];
		String name = longs.getClass().getComponentType().getName();
		log.info("longs getName() ==> {}", name);
	}

	/**
	 * Collections.sort 方法执行结果测试
	 */
	@Test
	void test42() {
		CorpDTO corp1 = new CorpDTO(4);
		CorpDTO corp2 = new CorpDTO(1);
		CorpDTO corp3 = new CorpDTO(3);
		CorpDTO corp4 = new CorpDTO(2);
		List<CorpDTO> corps = new ArrayList<>();
		corps.add(corp1);
		corps.add(corp2);
		corps.add(corp3);
		corps.add(corp4);
		log.info("排序前的 List 结果");
		corps.forEach(corp -> log.info(corp.toString()));
		Collections.sort(corps,new Comparator<CorpDTO>() {
			@Override
			public int compare(CorpDTO o1, CorpDTO o2) {
				return o1.getSort().compareTo(o2.getSort());
			}
		});
		log.info("排序后的 List 结果");
		corps.forEach(corp -> log.info(corp.toString()));
	}
}
