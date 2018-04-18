package serial;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotation;

public class ObjectWriter {

	// no duplicates
	Collection<Field> allFields = new HashSet<Field>();

	public void write(Object obj, OutputStream out) throws IOException {
		
		Class clazz=obj.getClass();
		Constructor[]constructors=clazz.getConstructors();
		 if (constructors.length==1&&constructors[0].getParameterTypes().length==1&&constructors[0].getParameterTypes()[0].equals(String.class)) {
			 System.out.println("String  class easy");
			
		}

		
		 try (PrintWriter w = new PrintWriter(out)) {
		 
			 writePrimitveTypes(obj, w);
			 
		// for (int i = 0; i < m.length; i++) { Method method = m[i];
		  //System.out.println(m[i]); writer.println(method.getName()); }
			 w.flush();w.close();
		  }
		 
		 
		 
/*
		getAllField(obj.getClass());
		System.out.println(allFields.size() + "Felderanzahl");
		for (Iterator iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = (Field) iterator.next();
			System.out.println(field.getType() + " " + field.getName() + " " + field.getDeclaringClass());

		}
		*/
	}

	public void writePrimitveTypes(Object obj,PrintWriter w){
		
		Class<?>clazz=obj.getClass();
		
		this.getAllField(clazz);
		
		for (Iterator iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = (Field) iterator.next();
			System.out.println(field.getName());
			field.setAccessible(true);
			Class<?> fieldClass=field.getType();
			
			if (fieldClass==Integer.TYPE) {
				//Zuerst scheuen ob es eine Anotierung gibt?
				try {
					w.println("int "+field.getName()+"="+field.getInt(obj));
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				
			}else if(fieldClass==Boolean.TYPE){
					try {
					
						w.println("boolean "+field.getName()+"="+field.getBoolean(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
			}else if(fieldClass==Short.TYPE){
				
					try {
					
						w.println("short "+field.getName()+"="+field.getShort(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				
				
			}else if(fieldClass==Double.TYPE){
				
				
					try {
						w.println("boolean "+field.getName()+"="+field.getBoolean(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				
			}else if(fieldClass==Short.TYPE){
				
					try {
						w.println("short "+field.getName()+"="+field.getShort(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				
				
				
			
			}else if(fieldClass==Float.TYPE){
				
					try {
						w.println("float "+field.getName()+"="+field.getFloat(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					
			}else if(fieldClass==Long.TYPE){
					try {
						w.println("long "+field.getName()+"="+field.getLong(obj));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					
			}else if(fieldClass==Byte.TYPE){
			

					try {
						w.println("byte "+field.getName()+"="+field.getByte(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
		
				
			}else if(fieldClass==Character.TYPE){
			
				
					try {
						w.println("char "+field.getName()+"="+"'"+field.getChar(obj)+"'");
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				
			}

		}
		
	}

	public Method searchForGetter(Object obj, Field field) {

		Class clazz = obj.getClass();

		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().toLowerCase().equals("get" + field.getName().toLowerCase())) {
				System.out.println("getter Gefunden");
				return method;
			}
		}

		return null;
	}

	public void getAllField(Class clazz) {

		if (clazz == null) {
			return;
		} else {
			
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) 
			{
				allFields.add(fields[i]);
			}

			clazz = clazz.getSuperclass();
			getAllField(clazz);
		}

	}

}