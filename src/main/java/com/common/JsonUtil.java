package com.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.riversoft.weixin.common.util.JsonMapper;
import com.total2.Person;
import com.total2.Work;

public class JsonUtil {

    private static JsonMapper defaultJsonMapper = null;
    private static JsonMapper nonEmptyJsonMapper = null;
    private static JsonMapper nonDefaultJsonMapper = null;
    private static JsonMapper defaultUnwrapRootJsonMapper = null;

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null, false);
    }

    public JsonMapper(boolean unwrapRoot) {
        this(null, unwrapRoot);
    }

    public JsonMapper(JsonInclude.Include include, boolean unwrapRoot) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        if (unwrapRoot) {
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        }
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public synchronized static JsonMapper nonEmptyMapper() {
        if (nonEmptyJsonMapper == null) {
            nonEmptyJsonMapper = new JsonMapper(JsonInclude.Include.NON_EMPTY, false);
        }
        return nonEmptyJsonMapper;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public synchronized static JsonMapper nonDefaultMapper() {
        if (nonDefaultJsonMapper == null) {
            nonDefaultJsonMapper = new JsonMapper(JsonInclude.Include.NON_DEFAULT, false);
        }
        return nonDefaultJsonMapper;
    }

    public synchronized static JsonMapper defaultUnwrapRootMapper() {
        if (defaultUnwrapRootJsonMapper == null) {
            defaultUnwrapRootJsonMapper = new JsonMapper(true);
        }
        return defaultUnwrapRootJsonMapper;
    }

    /**
     * 创建默认Mapper
     */
    public synchronized static JsonMapper defaultMapper() {
        if (defaultJsonMapper == null) {
            defaultJsonMapper = new JsonMapper();
        }
        return defaultJsonMapper;
    }
    
	private final ObjectMapper ob = new ObjectMapper();
	private JsonFactory jf = new JsonFactory();
	  //创建一个节点工厂,为我们提供所有节点  
    private JsonNodeFactory jnf = new JsonNodeFactory(false);
	
    public JsonUtil(){
    	//
    	//ob.configure(SerializationFeature.INDENT_OUTPUT, true);  
        // 配置mapper忽略空属性  
		ob.setSerializationInclusion(Include.NON_EMPTY);  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        ob.setDateFormat(dateFormat);  
        // 对于空的对象转json的时候不抛出错误
        ob.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用序列化日期为timestamps
        ob.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 禁用遇到未知属性抛出异常
        ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 视空字符传为null
        ob.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 低层级配置
        ob.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许属性名称没有引号
        ob.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        ob.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 取消对非ASCII字符的转码
        ob.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
    }
    
    public static JsonUtil getInstance(){
    	
    }
	public ArrayList<String> nodeNameList(String content) throws JsonProcessingException, IOException{
		
		return Lists.newArrayList(ob.readTree(content).fieldNames());
	}
	
	public String toJson(Object object) {
		if(object == null){
			return null;
		}
		try {
			return  ob.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <T>T toBean(String content,Class<T> javaBean){
		if(StringUtils.isBlank(content)){
			return null;
		}
		try {
			 return  ob.readValue(content, javaBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> jsonToMap(String content){
		if(StringUtils.isBlank(content)){
			return null;
		}
		return toBean(content, Map.class);
	}
	
	public Map<String,Object> beanToMap(Object object){
		return jsonToMap(toJson(object));
	}


	public <T> T mapToBean(Map<String,Object> map,Class<T> javaBean){
		if(map == null || map.size() == 0){
			return null;
		}
		return (T) toBean(toJson(map),javaBean);
	}
	
	
	
	public boolean containsNode(String nodeName,String content) throws JsonProcessingException, IOException{
		return ob.readTree(content).findValue(nodeName) == null;
	}
	
	public ObjectNode jsonToNode(String content) {
		if(StringUtils.isBlank(content)){
			return null;
		}
		try {
			return (ObjectNode)ob.readTree(content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayNode strToArrayNode(String content) {
		if(StringUtils.isBlank(content)){
			return null;
		}
		try {
			return (ArrayNode)ob.readTree(content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ObjectNode beanToNode(Object object){
		if(object == null){
			return null;
		}
		return jsonToNode(toJson(object));
	}
	
	public <T> List<T> jsonToList(String content,Class<T> javaBean) throws JsonProcessingException, IOException {
		if(StringUtils.isBlank(content)){
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<T>();
		JsonNode nodes = ob.readTree(content);
		if(nodes.isArray()){
			Iterator<JsonNode> iterator = nodes.iterator();
			while(iterator.hasNext()){
				JsonNode next = iterator.next();
				list.add(toBean(next.toString(),javaBean));
			}
		}else{
			list.add(toBean(content,javaBean));
		}
		return list;
	}
	
	public <T> String listToJson(List<T> list) {
		return toJson(list.getClass());
	}
	

	public Person initBean() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		Person one = new Person();
        one.setAge(1);
        one.setName("one");
        one.setSkills(new String[]{"吃奶","睡觉","拉屎"});
        List<String> oList = new LinkedList<String>();
        oList.add("555");
        oList.add("666");
        one.setPhone(oList);
        one.setBorn(dateFormat.parse("2017-02-11"));
        
        Person two = new Person();
        two.setAge(2);
        two.setName("two");
        two.setSkills(new String[]{"吃奶2","睡觉3","拉屎3"});
        List<String> tList = new LinkedList<String>();
        tList.add("777");
        tList.add("888");
        two.setPhone(oList);
        two.setBorn(dateFormat.parse("2008-02-11"));
        
        Person yang = new Person();
        yang.setName("杨诗涛");
        yang.setAge(28);
        yang.setSkills(new String[]{"java","js","mysql"});
        List<String> yList = new LinkedList<String>();
        yList.add("111");
        yList.add("222");
        yang.setPhone(yList);
        Map<String,Person> yMap = new HashMap<String, Person>();
        yMap.put("one", one);
        yMap.put("two", two);
        yang.setChild(yMap);
        Work work = new Work();
        work.setAddress("广州天河");
        work.setName("IT");
        yang.setWork(work);
        yang.setBorn(dateFormat.parse("2018-08-08"));
        
        return yang;
	        
	}
	public static void main(String[] args) throws JsonParseException, IOException, ParseException {
		
		ObjectMapper ob = new ObjectMapper();
		BaseParam b = new BaseParam();
		Person p1 = b.initBean();
		Person p2 = b.initBean();
		List<Person> list = new LinkedList<Person>();
		list.add(p1);
		list.add(p2);
		String str = ob.writeValueAsString(list);
        System.out.println(str);
       
        List list1 = b.toBean(str,List.class);
        //Person p3 = list1.get(0);
        System.out.println(list1);
        
//        Map<String,Object> m1 = new HashMap<String, Object>();
//        Map<String,Object> m2 = new HashMap<String, Object>();
//        m1.put("a", 1);
//        m1.put("name","yang");
//        m2.put("b", "b");
//        List<Map<String,Object>> list2 = new LinkedList<Map<String,Object>>();
//        list2.add(m1);
//        list2.add(m2);
//        String str2 = b.listToJson(list2);
//        System.out.println(str2);
//        
//        Person p = b.mapToBean(m1, Person.class);
//        //String s = b.mapToStr(m1);
//        JsonNode readTree = ob.readTree(str);
//        System.out.println(readTree.size());
        
	}
}
