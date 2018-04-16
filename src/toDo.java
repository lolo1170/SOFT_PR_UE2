

import java.time.LocalDate;
import java.util.Random;

public class toDo {
	
	final int id=new Random(12).nextInt();
	
	String description;
	LocalDate dueTo;
	protected toDo next;
	
	public toDo(String description,LocalDate date){
		
		this.description=description;
		dueTo=date;
	}
	
	public String  getDescription(){
		
		return description;
		
	}
	
	public String toString()
	{
		
		
		StringBuffer sb=new StringBuffer();
		
		sb.append(id+": ");
		sb.append(dueTo+" - ");
		sb.append(description+" ");
	
		
		
		return sb.toString();
		
		
	}
	
}
