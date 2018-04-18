package person;

import java.util.ArrayList;
import java.util.List;

import datetime.Date;
import serial.UseSerializer;

public class Person  {
	
	
	private String name; 
	private Person child1, child2; 
	private Date born; 
	private Person parent;
	
	@UseSerializer(StringListSerializer.class)
	private List<String> hobbies = new ArrayList<String>(); 
	
	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBorn() {
		return born;
	}


	public void setBorn(Date born) {
		this.born = born;
	}
	
	public Person getChild1() {
		return child1;
	}

	public void setChild1(Person child1) {
		this.child1 = child1;
	}

	public Person getChild2() {
		return child2;
	}

	public void setChild2(Person child2) {
		this.child2 = child2;
	}

	public Person getParent() {
		return parent;
	}

	public void setParent(Person parent) {
		this.parent = parent;
	}
	
	public void addHobby(String hobby) {
		hobbies.add(hobby);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", born=" + born + "]";
	}
	
}
