package serial.app;

import java.io.FileOutputStream;
import java.io.OutputStream;

import datetime.Date;
import person.Human;
import person.Person;
import serial.ObjectWriter;

public class SerialMain {

	public static void main(String[] args) {
		
		Person a = new Person(); 
		a.setName("Frank");
		a.setBorn(new Date("2.12.1970"));
		a.addHobby("Cycling");
		a.addHobby("Running");
		Person b = new Person(); 
		b.setName("Kevin");
		b.addHobby("Reading");
		b.addHobby("Playing");
		b.setBorn(new Date("22.4.2004"));
		Person c = new Person(); 
		c.setName("Ann");
		c.setBorn(new Date("12.6.2009"));
		c.addHobby("Dolls");
		a.setChild1(b);
		a.setChild2(c);
		b.setParent(a);
		c.setParent(a);

		
		ObjectWriter ow = new ObjectWriter(); 
		
		try (OutputStream out = new FileOutputStream("C:\\Users\\Stefan\\Desktop\\SerizalizedObject15.txt")) {
			//ow.write(a, out); 
			ow.write(new Human("Stefan"), out);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
/*
		ObjectReader or = new ObjectReader(); 
		try (InputStream in = new FileInputStream("SerizalizedObject.txt")) {
			Object o = or.read(in);
			System.out.println(o); 
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}

}
