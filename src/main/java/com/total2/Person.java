package com.total2;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name;

	private Integer age;

	private Map<String, Person> child;

	private List<String> phone;

	private String[] skills;

	private Work work;

	private Date born;

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Map<String, Person> getChild() {
		return child;
	}

	public void setChild(Map<String, Person> child) {
		this.child = child;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	// public String toString(){
	// return "name = "+getName()+", child = "+getChild()+", phone =
	// "+getPhone();
	// }
}
