package serial;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
		/*
		 * PrintWriter writer = new PrintWriter(new
		 * File("C:\\Users\\Stefan\\Desktop\\testSerialize.txt"));
		 * 
		 * Method[] m = obj.getClass().getMethods();
		 * 
		 * PrintWriter w = new PrintWriter(out);
		 * 
		 * writePrimitveTypes(obj, w);
		 * 
		 * 
		 * try (PrintWriter w = new PrintWriter(out)) {
		 * 
		 * 
		 * for (int i = 0; i < m.length; i++) { Method method = m[i];
		 * System.out.println(m[i]); writer.println(method.getName()); }
		 * 
		 * }
		 * 
		 * writer.flush(); writer.close();
		 */

		getAllField(obj.getClass());
		System.out.println(allFields.size() + "Felderanzahl");
		for (Iterator iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = (Field) iterator.next();
			System.out.println(field.getType() + " " + field.getName() + " " + field.getDeclaringClass());

		}
	}

	public void writePrimitveTypes(Object obj,PrintWriter w){
		
		Class<?>clazz=obj.getClass();
		
		Field[]fields=clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) 
		{
			Field field = fields[i];
			Class<?> fieldClass=field.getType();
			
			
			if (fieldClass==Integer.TYPE) {
				
				//Zuerst scheuen ob es eine Anotierung gibt?
				
				
				if(Modifier.isPublic(field.getModifiers())){
				try {
					w.println("int "+field.getName()+"="+field.getInt(obj));
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				}else{
					System.out.println(field.getName());
					
			Method method=	searchForGetter(obj, field);
			int number=-1;
					if (method!=null&&method.getReturnType()==Integer.TYPE) {
					try {
						number=	(int) method.invoke(obj);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					
					w.println("int "+field.getName()+"="+number);
					
					}
				}
				
			}else if(fieldClass==Boolean.TYPE){
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("boolean "+field.getName()+"="+field.getBoolean(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				boolean value=false;
						if (method!=null&&method.getReturnType()==Boolean.TYPE) {
						try {
							value=(boolean) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("boolean "+field.getName()+"="+value);
						}
					}
			}else if(fieldClass==Short.TYPE){
				
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("short "+field.getName()+"="+field.getShort(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				short value=-1;
						if (method!=null&&method.getReturnType()==Short.TYPE) {
						try {
							value=(short) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("short "+field.getName()+"="+value);
						
						}
					}
				
				
			}else if(fieldClass==Double.TYPE){
				
				
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("boolean "+field.getName()+"="+field.getBoolean(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				boolean value=false;
						if (method!=null&&method.getReturnType()==Boolean.TYPE) {
						try {
							value=(boolean) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("boolean "+field.getName()+"="+value);
						}
					}
			}else if(fieldClass==Short.TYPE){
				
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("short "+field.getName()+"="+field.getShort(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				short value=-1;
						if (method!=null&&method.getReturnType()==Short.TYPE) {
						try {
							value=(short) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("short "+field.getName()+"="+value);
						
						}
					}
				
				
			
			}else if(fieldClass==Float.TYPE){
			
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("float "+field.getName()+"="+field.getFloat(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				float value=-1f;
						if (method!=null&&method.getReturnType()==Float.TYPE) {
						try {
							value=(float) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("float "+field.getName()+"="+value);
						
						}
					}
			}else if(fieldClass==Long.TYPE){
				w.println("long "+field.getName());
				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("long "+field.getName()+"="+field.getLong(obj));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				long value=-1;
						if (method!=null&&method.getReturnType()==Long.TYPE) {
						try {
							value=(long) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("long "+field.getName()+"="+value);
						}
					}
			}else if(fieldClass==Byte.TYPE){
			

				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("byte "+field.getName()+"="+field.getByte(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				byte value=-1;
						if (method!=null&&method.getReturnType()==Byte.TYPE) {
						try {
							value=(byte) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("byte "+field.getName()+"="+value);
						}
			
					}	
				
			}else if(fieldClass==Character.TYPE){
			

				if(Modifier.isPublic(field.getModifiers())){
					try {
						w.println("char "+field.getName()+"="+field.getFloat(obj));
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					}else{
						
				Method method=	searchForGetter(obj, field);
				char value=' ';
						if (method!=null&&method.getReturnType()==Character.TYPE) {
						try {
							value=(char) method.invoke(obj);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						w.println("char "+field.getName()+"="+"'"+value+"'");
						
						}
			
					}	
			}

		}
		w.flush();
		
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