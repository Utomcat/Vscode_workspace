package com.ranyk.www.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.stream.Collectors;
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
	void test0(){
		String st = "26f59e82-de48-4012-aa1b-be8bb08fc9f1~$510100";
		log.error("是否包含对应的指定字符: {}", st.contains("~$"));
		String result = st.replace("~$", "_");
		String[] sts = result.split("_");
		for (String s : sts) {
			log.error("当前的值为 ==> {}", s);
		}
	}


	/**
	 * ==、equals、Objects.equals  对象相等的判断,相等判断结果如下: <br/>
	 * 
	 */
	@Test
	void test1(){
		Personel personel1 = new Personel("张三",23);
		Personel personel2 = new Personel("张三",23);
		Personel personel3 = personel1;
		personel3.setAge(25);

		log.info("personel1 ==> {}", JSONObject.toJSONString(personel1));
		log.info("personel2 ==> {}", JSONObject.toJSONString(personel2));
		log.info("personel3 ==> {}", JSONObject.toJSONString(personel3));
		log.info("使用 == 方式判断两个对象是否相等 ==> {}",personel1 == personel2);
		log.info("使用 == 方式判断两个对象是否相等 ==> {}",personel1 == personel3);
		log.info("使用 equals 方式判断两个对象是否相等 ==> {}",personel1.equals(personel2));
		log.info("使用 Objects.equals 方式判断两个对象是否相等 ==> {}",Objects.equals(personel1, personel2));
		
	}


	/**
	 * 测试 == equals Objects.equals : 测试判断的有关相等的方式
	 */
	@Test
	void test2(){
		String a = null;
		String b = null;
		String c = a;
		Boolean bol = Boolean.TRUE;
		String d = new String("aaaa");
		String e = "aaaa";
		Personel personel = new Personel("aaaa",12);

		log.error("使用 == 判定相同类型的数据是否相等 {}", a == b);
		log.error("使用 == 判定相同对象是否相等 {}", a == c);
		log.error("{}", bol);
		log.error("{}", d);
		log.error("{}", e);
		log.error("{}", personel);
	}


	@Test
	void test3() {
		String str = "  初  七 ";
		log.error("使用trim()方法对str原字符串为==>{}<==,进行空格处理,其结果为 ==>{}<==", str,str.trim());
		Personel person =  new Personel("  张三  ",10);
		Personel person1 =  new Personel();
		log.error("输入的对象的原姓名为 ==>{}<==", person.getName());
		person.setName(person.getName().trim());
		log.error("输入的对象的处理后的姓名为 ==>{}<==", person.getName());
		log.error("输入的对象的原姓名为 ==>{}<==", person1.getName());
		if(StringUtils.hasLength(person1.getName())){
			person1.setName(person1.getName().trim());
			log.error("输入的对象的原姓名为 ==>{}<==", person1.getName());
		}else{
			log.error("姓名为空,不予进行空格处理");
		}

	}


	@Test
	void test4() {
		byte[] dwx = new byte[1024];
		String fileId = "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.dwg" + ","
		+ new BigDecimal(dwx.length).divide(new BigDecimal(1024),0,RoundingMode.UP) + "," 
		+ "dwg" + "," 
		+ "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.pdf" + "," 
		+ new BigDecimal(dwx.length).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," 
		+ "PDF";
		String[] fileIdData = fileId.split(",");
		int length = fileIdData.length;
		log.error("字符拆分后的数组长度为 ==> {}", length);
		for (int i = 0; i < length; i++) {
			log.error("index = {} 的值 ==> {}", i,fileIdData[i]);
		}
		log.error(" 字符拆分的结果为 ==>  {}",JSONObject.toJSON(fileIdData));
	}


	@Test
	void test5() {
		byte[] dwx = new byte[1024];
		String fileId = "fastdfs://group1/M00/00/21/CgBkBV_cF_SAP0uTAADlt9RrGrs531.dwg" + ","
				+ new BigDecimal(dwx.length).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "dwg" + ","
				+ "" + ","
				+ new BigDecimal(0).divide(new BigDecimal(1024), 0, RoundingMode.UP) + "," + "PDF";
		String[] fileIdData = fileId.split(",");
		int length = fileIdData.length;
		log.error("字符拆分后的数组长度为 ==> {}", length);
		for (int i = 0; i < length; i++) {
			if(i == 3){
				log.error("index = {} 的值 ==> {}", i, StringUtils.hasText(fileIdData[i])?fileIdData[i]:null);
			}else{
				log.error("index = {} 的值 ==> {}", i, fileIdData[i]);
			}
		}
		log.error(" 字符拆分的结果为 ==>  {}", JSONObject.toJSON(fileIdData));
	}


	@Test
	void test6() {
		int[] arr = {0,0,0,0};
		int[] initStrs = new int[10];
		for (int i = 0; i < arr.length; i++) {
			initStrs[arr[i]]++;
		}
		for (int i = 0; i < initStrs.length; i++) {
			System.out.println(i + " ==> " + initStrs[i]);
		}
	}


	@Test
	void test7() {
		Integer a = Integer.valueOf(0);
		Map<String,Object> result = new HashMap<String,Object>(2);
		result.put("key", a);
		String dicStatusCode = translationDicStatusCode(String.valueOf(result.get("key")));
		log.error("获取到Map中的Integer类型数据后强转为String测试结果为 {}", dicStatusCode);
	}

	
	@SuppressWarnings("unchecked")
	public <T> T cast(Object obj) {
		return (T) obj;
	}


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



	@Test
	void test8() {
		String str = new String();
		Integer i = Integer.valueOf(0);
		log.error("str 和 String 的关系是 ==> {}", str instanceof String);
		log.error("i 和 Integer 的关系是 ==> {}", i instanceof Integer);
		
	}


	@Test
	void test9() {
		try {
			String path = this.getClass().getResource("/").getPath();
			File file = new File(path + "/202101081231.zip");
			FileInputStream zipFile = new FileInputStream(file);
			ZipInputStream zis = new ZipInputStream(zipFile);
			log.info("zip的文件大小为: ==> {}  byte", file.length());
			if (zis.getNextEntry() != null){
				byte[] bytes =  FileUtil.getContent(zis);
				String str = new String(bytes);
				JSONObject  json = JSONObject.parseObject(str);
				JSONArray array = JSONObject.parseArray( JSON.toJSONString(json.get("data")));
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
						FileOutputStream dwgFileOutPut = new FileOutputStream(new File(path + "/" + String.valueOf(sub.get("buildidpart")) + ".dwg"));
						FileOutputStream pdfFileOutPut = new FileOutputStream(new File(path + "/" + String.valueOf(sub.get("buildidpart")) + ".pdf"));
						FileUtil.writeFile(dwgInputStream, dwgFileOutPut);
						FileUtil.writeFile(dwfInputStream, pdfFileOutPut);
					}
					log.info("buildidpart ==> {}", sub.get("buildidpart"));
				}
			}
			log.info("压缩文件的文件路径为: getPath ==> {} , getAbsolutePath ==>  {} , getCanonicalPath ==> {} ", file.getPath(),file.getAbsolutePath(),file.getCanonicalPath());
			zis.close();
		} catch(Exception e) {
			log.error("发生异常,异常信息为: {} !", e.getMessage());
		}
	}


	@Test
	void test10() {
		CheckHouAtOtherBizType checkType = CheckHouAtOtherBizType.getTypeByName("");
		log.info("获取到的 checkType 为 ==> {name:{},checkChsBlue:{},checkChsCont:{},checkEstate:{},checkShsCont:{}}", checkType.getName(),checkType.getCheckChsBlue(),checkType.getCheckChsCont(),checkType.getCheckEstate(),checkType.getCheckShsCont());
	}


	@Test
	void test11() {
		Boolean bl = false;
		if(bl == null || bl) {
			log.info("需要抛出异常");
			return;
		}
	}


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

		if(StringUtils.hasLength(realImgUrl)) {
			log.error("获取到的真实图片本地地址为 ==> {}", realImgUrl);
		}
	}


	@Test
	void test13() {
		Personel personel = new Personel("张三",20,1);
		Class<? extends Personel> clazz =  personel.getClass();
		Method[] methods =  clazz.getDeclaredMethods();
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
		Integer resutl = ObjectHandler.cast(ObjectHandler.invokeGetMethod(clazz, personel, "sex"));
		log.info("利用反射执行get属性值的结果为: {}",resutl);
	}

}
