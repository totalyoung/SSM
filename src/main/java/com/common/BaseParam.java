package com.common;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.total2.Person;
import com.total2.Work;

public class BaseParam {

	private final ObjectMapper ob = new ObjectMapper();
	
	public ArrayList<String> nodeNameList(String content) throws JsonProcessingException, IOException{
		
		return Lists.newArrayList(ob.readTree(content).fieldNames());
	}
	
	public Map<String,Object> nodeValue(String content) throws IOException{
		//ObjectNode
		
		JsonNode tree = ob.readTree(content);
		Iterator<String> fieldNames = tree.fieldNames();
		Map<String,Object> map = new HashMap<String,Object>();
		while(fieldNames.hasNext()){
			String name = fieldNames.next();
			map.put(name, tree.findValue(name));
		}
		return map;
	}
	 
	
	public boolean containsNode(String nodeName,String content) throws JsonProcessingException, IOException{
		return ob.readTree(content).findValue(nodeName) == null;
		
	}
	public static void main(String[] args) throws JsonParseException, IOException, ParseException {
		
		ObjectMapper ob = new ObjectMapper();
		BaseParam b = new BaseParam();
		JsonFactory jf = new JsonFactory();
		// 为了使JSON视觉上的可读性，增加一行如下代码，注意，在生产中不需要这样，因为这样会增大Json的内容  
		//ob.configure(SerializationFeature.INDENT_OUTPUT, true);  
        // 配置mapper忽略空属性  
		ob.setSerializationInclusion(Include.NON_EMPTY);  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        //ob.setDateFormat(dateFormat);  
		 //创建一个节点工厂,为我们提供所有节点  
        JsonNodeFactory factory = new JsonNodeFactory(false);  
        ObjectNode on = new ObjectNode(factory);
        factory.
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
        String str = ob.writeValueAsString(yang);
        System.out.println(str);
        Map<String,Object> map = b.nodeValue(str);
        Person p = ob.readValue(str, Person.class);
        System.out.println();
	}
}
