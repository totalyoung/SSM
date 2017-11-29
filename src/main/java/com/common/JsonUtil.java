package com.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.total2.Person;
import com.total2.Work;

/**
 * 
 * @author total
 * @version 1.0
 */
public class JsonUtil {

    private static JsonUtil defaultJsonUtil = null;
    private static JsonUtil nonEmptyJsonUtil = null;
    private static JsonUtil nonDefaultJsonUtil = null;
    private static JsonUtil defaultUnwrapRootJsonUtil = null;

    private ObjectMapper mapper;
    
	//创建一个节点工厂,为我们提供所有节点  
    private static final JsonNodeFactory NODE_FACTOTY = new JsonNodeFactory(false);

    public JsonUtil() {
        this(null, false);
    }

    public JsonUtil(boolean unwrapRoot) {
        this(null, unwrapRoot);
    }

    public JsonUtil(JsonInclude.Include include, boolean unwrapRoot) {
    	mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
        	mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 对于空的对象转json的时候不抛出错误
        //mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 视空字符传为null
        //mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 禁用序列化日期为timestamps
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 低层级配置
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 取消对非ASCII字符的转码
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
       
        if (unwrapRoot) {
        	mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        }
    }

    public ObjectMapper getMapper() {
		return mapper;
	}


	/**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public synchronized static JsonUtil nonEmptyMapper() {
        if (nonEmptyJsonUtil == null) {
            nonEmptyJsonUtil = new JsonUtil(JsonInclude.Include.NON_EMPTY, false);
        }
        return nonEmptyJsonUtil;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public synchronized static JsonUtil nonDefaultMapper() {
        if (nonDefaultJsonUtil == null) {
            nonDefaultJsonUtil = new JsonUtil(JsonInclude.Include.NON_DEFAULT, false);
        }
        return nonDefaultJsonUtil;
    }

    public synchronized static JsonUtil defaultUnwrapRootMapper() {
        if (defaultUnwrapRootJsonUtil == null) {
            defaultUnwrapRootJsonUtil = new JsonUtil(true);
        }
        return defaultUnwrapRootJsonUtil;
    }

    /**
     * 创建默认Mapper
     */
    public synchronized static JsonUtil defaultMapper() {
        if (defaultJsonUtil == null) {
            defaultJsonUtil = new JsonUtil();
        }
        return defaultJsonUtil;
    }

	public ArrayList<String> nodeNameList(String content) throws JsonProcessingException, IOException{
		
		return Lists.newArrayList(mapper.readTree(content).fieldNames());
	}
	
	/**
	 * 转换成json字符串
	 * @param object
	 * @return json字符串
	 */
	public String toJson(Object object) {
		if(object == null){
			return null;
		}
		try {
			return  mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param content
	 * @param javaBean
	 * @return
	 */
	public <T>T toBean(String content,Class<T> javaBean){
		if(StringUtils.isBlank(content)){
			return null;
		}
		try {
			 return  mapper.readValue(content, javaBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> jsonToMap(String content){
		
		return toBean(content, Map.class);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> beanToMap(Object object){
		return convertValue(object, Map.class);
	}


	public <T> T mapToBean(Map<String,Object> map,Class<T> javaBean){
		
		return (T) convertValue(map,javaBean);
	}
	
	
	
	public boolean containsNode(String nodeName,String content) throws JsonProcessingException, IOException{
		return mapper.readTree(content).findValue(nodeName) == null;
	}
	
	public ObjectNode jsonToNode(String content) {
		try {
			
			return toBean(content, ObjectNode.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayNode jsonToArrayNode(String content) {
		try {
			return toBean(content, ArrayNode.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ObjectNode beanToNode(Object object){
		
		return convertValue(object,ObjectNode.class);
	}
	
	public<T> T convertValue(Object fromValue,Class<T> toValueType){
		if(fromValue == null){
			return null;
		}
		return mapper.convertValue(fromValue, toValueType);
	}
	
	public <T> List<T> jsonToList(String content,Class<T> javaBean) throws JsonProcessingException, IOException {
		if(StringUtils.isBlank(content)){
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<T>();
		JsonNode nodes = mapper.readTree(content);
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
	
	public static ObjectNode getObjectNode(){
		return NODE_FACTOTY.objectNode();
	}

	public static ArrayNode getArrayNode(){
		return NODE_FACTOTY.arrayNode();
	}
	
	public static Person initBean() throws ParseException{
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
		JsonUtil jsonUtil = JsonUtil.nonDefaultMapper();
		Person p1 = JsonUtil.initBean();
		Person p2 = JsonUtil.initBean();
		List<Person> list = new LinkedList<Person>();
		list.add(p1);
		list.add(p2);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name","mapToBean");
		//map.put("child",p1);
		map.put("phone", "123456");
		
		System.out.println("\n-------------测试toJson-------------");
        System.out.println(jsonUtil.toJson(list));
        System.out.println(jsonUtil.toJson(p1));
        
        
        System.out.println("\n-------------测试toBean-------------");
        //System.out.println(jsonUtil.toBean(jsonUtil.toJson(p1), Person.class).getChild().get("one").getName());
        System.out.println(jsonUtil.toBean(jsonUtil.toJson(p1), Person.class));
        
        System.out.println("\n-------------测试jsonToMap-------------");
        System.out.println(jsonUtil.jsonToMap(jsonUtil.toJson(p1)));
        
        System.out.println("\n-------------测试beanToMap-------------");
        System.out.println(jsonUtil.beanToMap(p1));
        
        System.out.println("\n-------------测试mapToBean-------------");
        System.out.println(jsonUtil.mapToBean(jsonUtil.beanToMap(p1), Person.class));
        
        System.out.println("\n-------------测试jsonToNode-------------");
        System.out.println(jsonUtil.jsonToNode(jsonUtil.toJson(p1)));
        
        System.out.println("\n-------------测试jsonToArrayNode-------------");
        System.out.println(jsonUtil.jsonToArrayNode(jsonUtil.toJson(list)));
        
        System.out.println("\n-------------测试getObjectNode-------------");
        ObjectNode node = JsonUtil.getObjectNode();
        node.put("name", "hahah ");
        System.out.println(node);
        
        System.out.println("\n-------------测试getArrayNode-------------");
        ArrayNode aNode = JsonUtil.getArrayNode();
        aNode.add(node);
        aNode.add(jsonUtil.jsonToNode(jsonUtil.toJson(p1)));
        System.out.println(aNode);
        
        System.out.println("\n-------------测试beanToNode-------------");
        ObjectNode node2 = jsonUtil.beanToNode(p1);
        node2.put("test", "beanToNode");
        System.out.println(node2);
        node2 = jsonUtil.beanToNode(jsonUtil.beanToMap(p1).put("test2", "beanToNode"));
        System.out.println(node2);
        
        System.out.println("\n-------------测试jsonToList-------------");
        List<Person> list2 = jsonUtil.jsonToList(jsonUtil.toJson(list), Person.class);
        System.out.println(list2);
	}
}
