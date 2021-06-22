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
import com.ranyk.www.demo.model.Personel;
import com.ranyk.www.demo.util.FileUtil;
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
	 * org.springframework.util.StringUtils.hasText  和
	 * org.springframework.util.StringUtils.hasLength  方法对不同数据类型的数据进行判空处理的结果验证
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

		log.info("String 类型数据值为 \"\" ,用 hasText 方法的结果为: {} , hasLength 方法结果为: {}",
				StringUtils.hasText(str2), StringUtils.hasLength(str2));
		log.info("String 类型数据值为 null ,用 hasText 方法的结果为: {} , hasLength 方法结果为: {}",
				StringUtils.hasText(str3), StringUtils.hasLength(str3));

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
		log.info("拼接的SQL范围为: ==> {} , 其字符串的长度为: ==> {}",sql,sql.length());
	}

}
