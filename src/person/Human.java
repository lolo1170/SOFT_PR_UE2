package person;

import serial.UseSerializer;

public class Human {

	private int age=22;
	private char buchstabe='c';
	
	@UseSerializer(StringListSerializer.class)
	String[]names= {"Stefan","Plavsic"};
	public Human() {
		
	}

public Human(String name){
	
	
}

public String toString() {
	
	return age+"";
	
}

}
