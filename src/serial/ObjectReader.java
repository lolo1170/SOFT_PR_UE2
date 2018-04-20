package serial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashSet;

public class ObjectReader {
	
	public Object read(InputStream in) throws Exception {
		
		Collection<Object>readInObjects=new HashSet<Object>();
		
		try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {

			String line=r.readLine();
			if (line.equals("class")) {

				line=r.readLine();
				Class clazz=Class.forName(line);
				System.out.println(clazz.getCanonicalName()+"Das ist kanonischer Name");
				
				Constructor c=clazz.getConstructor(String.class);
				line=r.readLine();
				//String Konstruktor
				c.newInstance(line);
				readInObjects.add(c.newInstance(line));
				
			}
			
			return null;			
		}
	}

}
