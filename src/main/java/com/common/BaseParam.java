package com.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.total2.Person;
import com.total2.Work;

public class BaseParam {

	private final ObjectMapper ob = new ObjectMapper();
	private JsonFactory jf = new JsonFactory();
	  //创建一个节点工厂,为我们提供所有节点  
    private JsonNodeFactory jnf = new JsonNodeFactory(false);
	
    public BaseParam(){
    	ob.configure(SerializationFeature.INDENT_OUTPUT, true);  
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
    
	public ArrayList<String> nodeNameList(String content) throws JsonProcessingException, IOException{
		
		return Lists.newArrayList(ob.readTree(content).fieldNames());
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> jsonToMap(String content) throws IOException{
//		JsonNode tree = ob.readTree(content);
//		Iterator<String> fieldNames = tree.fieldNames();
//		Map<String,Object> map = new HashMap<String,Object>();
//		while(fieldNames.hasNext()){
//			String name = fieldNames.next();
//			map.put(name, tree.findValue(name));
//		}
//		return map;
		return ob.readValue(content, Map.class);
	}
	
	public <T>T jsonToBean(String content,Class<T> javaBean) throws IOException{
		if(ob.canSerialize(javaBean)){
			return  ob.readValue(content, javaBean);
		}else{
			return null;
		}
		
	}
	
	public String mapToJson(Map<String,Object> map) throws JsonProcessingException{
//		ObjectNode node = jnf.objectNode();
//		for(Map.Entry<String, Object> entry: map.entrySet()){
//			node.putPOJO(entry.getKey(), entry.getValue());
//		}
		return ob.writeValueAsString(map);
	}
	 
	@SuppressWarnings("unchecked")
	public <T> T mapToBean(Map<String,Object> map,Class<T> javaBean) throws IOException{
		
		return (T) ob.readValue(this.mapToJson(map),javaBean);
	}
	
	public boolean containsNode(String nodeName,String content) throws JsonProcessingException, IOException{
		return ob.readTree(content).findValue(nodeName) == null;
	}
	
	

//	public Map<String,Object> JsonToMap2(String content) throws IOException{
//		//ObjectNode
//		JsonParser parser = factory.createParser(content);
//		Map<String,Object> map = new HashMap<String,Object>();
//		JsonToken token = parser.nextToken();
//		if(token != JsonToken.START_OBJECT){
//			return map;
//		}
//		token = parser.nextToken();
//		while(token != JsonToken.END_OBJECT){
//			String key = parser.getCurrentName();
//			Object value = parser.getCurrentValue();
//			map.put(key, value);
//			token = parser.nextToken();
//		}
//		parser.close();
//		return map;
//	}
	
	
	public <T> List<T> jsonToList(String content,Class<T> javaBean) throws JsonProcessingException, IOException {
		List<T> list = new ArrayList<T>();
		JsonNode nodes = ob.readTree(content);
		if(nodes.isArray()){
			Iterator<JsonNode> iterator = nodes.iterator();
			while(iterator.hasNext()){
				JsonNode next = iterator.next();
				list.add(ob.readValue(next.toString(),javaBean));
			}
		}
		return list;
	}
	
	public <T> String listToJson(List<T> list) throws JsonProcessingException{
		return ob.writeValueAsString(list);
	}
	
//	public ArrayNode getArrayNode(){
//		
//	}
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
       
        List<HashMap> list1 = b.jsonToList(str,HashMap.class);
        //Person p3 = list1.get(0);
        System.out.println(list1);
        
        Map<String,Object> m1 = new HashMap<String, Object>();
        Map<String,Object> m2 = new HashMap<String, Object>();
        m1.put("a", 1);
        m2.put("b", "b");
        List<Map<String,Object>> list2 = new LinkedList<Map<String,Object>>();
        list2.add(m1);
        list2.add(m2);
        String str2 = b.listToJson(list2);
        System.out.println(str2);
        
        JsonNode readTree = ob.readTree(str);
        System.out.println(readTree.size());
        
	}
}
