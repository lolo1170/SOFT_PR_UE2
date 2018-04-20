package serial;

import java.awt.print.Printable;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotation;

import javafx.beans.value.WritableObjectValue;

public class ObjectWriter {

	// no duplicates
	Collection<Field> fields = new HashSet<Field>();
	static final String beginClass = "class";
	// eigene Methode mit writeField
	// eigene Methode mit writeClassinfo

	Collection<Object> allObjects = new HashSet<Object>();
	HashMap<Integer, Object> serializedObjects = new HashMap<Integer, Object>();

	public void write(Object obj, OutputStream out) throws IOException,NoSuchMethodException,SecurityException {

		Class clazz = obj.getClass();
		
		try (PrintWriter w = new PrintWriter(out)) {
			
			w.println(beginClass);
			w.println(clazz.getName());
			
			//Has one String COnstruktor
				//clazz.getConstructor(String.class);
				//writeClassWithOneStringConstructor(obj, w);

				
				
			HashSet fields=getAllFields(clazz);

			for (Iterator iterator = fields.iterator(); iterator.hasNext();) {

				Field field = (Field) iterator.next();
				field.setAccessible(true);

				if (field.getType().isPrimitive()&&!field.isAnnotationPresent(UseSerializer.class)) {

					writePrimitveTypes(obj, field, w);

				}else if(!field.getType().isPrimitive()&&field.isAnnotationPresent(UseSerializer.class)) {
					
					System.out.println(field.getName()+" Das sind alle komplexen Felder");	
					
					final UseSerializer useSerializerAnn = ((UseSerializer)field.getAnnotation(UseSerializer.class)); 
					final Class<FieldSerializer> retrunedClass = ((Class<FieldSerializer>) useSerializerAnn.value()); 
					FieldSerializer fieldSerializer;

					w.print(field.getName());
						w.println(" = Harri value");
						w.flush();

						 Object value=null;
							
						try {
						value=field.get(obj);
							fieldSerializer = retrunedClass.newInstance();
							fieldSerializer.write(value, w);	
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					
				} else {
					
					
					Object fieldObject=null;
				
					try {
						fieldObject = field.get(obj);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					writeComplexType(fieldObject, w);
				}
				
			}//Loop

			System.out.println("Writer flushed and closed");
			w.flush();
			w.close();
		}//Try write

		
		}//Method
	
	public void writeComplexType(Object obj,PrintWriter w) {
		
		if (obj==null) {
			return ;
		}
		
		if (!serializedObjects.containsKey(obj.hashCode())) {
			
			serializedObjects.put(obj.hashCode(), obj);
			w.println(obj.getClass().getSimpleName()+" "+ obj);;
			
		}else{
			System.out.println("occoured again"+obj);
			w.println(obj.hashCode());
		}
		
		
		
		
		
	}

	public void writeClassWithOneStringConstructor(Object obj, PrintWriter w) {
		Class clazz = obj.getClass();
		w.println(beginClass + " StringConstructor");
		w.println(clazz.getName());
		// Constructor c=clazz.getConstructor(String.class);
		Method toStringMethod = null;
		try {
			toStringMethod = clazz.getDeclaredMethod("toString");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		try {
			w.println(toStringMethod.invoke(obj));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println("Ende der Methode String with one Class");

	}

	public void writePrimitveTypes(Object obj, PrintWriter w) {

		Class<?> clazz = obj.getClass();

		// Collection<Field>allFields=getAllField(clazz,new HashSet<Field>());

		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {

			Field field = (Field) iterator.next();

			final UseSerializer useSerializerAnn = ((UseSerializer) field.getAnnotation(UseSerializer.class));
			final Class<FieldSerializer> returnedClass = ((Class<FieldSerializer>) useSerializerAnn.value());

			FieldSerializer fieldSerializer;

			System.out.println(field.getName());
			field.setAccessible(true);
			Class<?> fieldClass = field.getType();

			if (fieldClass == Integer.TYPE) {
				// Zuerst scheuen ob es eine Anotierung gibt?

				if (field.isAnnotationPresent(UseSerializer.class)) {
					UseSerializer annotation = field.getDeclaredAnnotation(UseSerializer.class);
					Class annotationClass = annotation.value();
					// annotationClass=annotationClass.newInstance();

					// w.println("int "+field.getName()+"="+serializer.value());

				}
				try {
					w.println("int " + field.getName() + "=" + field.getInt(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Boolean.TYPE) {
				try {

					w.println("boolean " + field.getName() + "=" + field.getBoolean(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else if (fieldClass == Short.TYPE) {

				try {

					w.println("short " + field.getName() + "=" + field.getShort(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Double.TYPE) {

				try {
					w.println("boolean " + field.getName() + "=" + field.getBoolean(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Short.TYPE) {

				try {
					w.println("short " + field.getName() + "=" + field.getShort(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Float.TYPE) {

				try {
					w.println("float " + field.getName() + "=" + field.getFloat(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Long.TYPE) {
				try {
					w.println("long " + field.getName() + "=" + field.getLong(obj));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Byte.TYPE) {

				try {
					w.println("byte " + field.getName() + "=" + field.getByte(obj));

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else if (fieldClass == Character.TYPE) {

				try {
					w.println("char " + field.getName() + "=" + "'" + field.getChar(obj) + "'");

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

	/*
	 * public Collection<Field> getAllFields(Class
	 * clazz,Collection<Field>fields) {
	 * 
	 * 
	 * if (clazz == null) { return fields; } else {
	 * 
	 * Field[] tmpFields = clazz.getDeclaredFields(); for (int i = 0; i <
	 * tmpFields.length; i++) { fields.add(tmpFields[i]); }
	 * 
	 * clazz = clazz.getSuperclass(); return getAllFields(clazz,fields); }
	 * 
	 * }
	 */
	public HashSet getAllFields(Class clazz) {

		HashSet allFields=new HashSet<Field>();
		
		while(clazz!=null) {
			
			Field[]fields=clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				allFields.add(fields[i]);
			}
			clazz=clazz.getSuperclass();
		
		
		}
		return allFields;
		/*
		if (clazz == null) {
			return;
		} else {

			Field[] tmpFields = clazz.getDeclaredFields();
			for (int i = 0; i < tmpFields.length; i++) {
				// System.out.println(tmpFields[i].getName());
				fields.add(tmpFields[i]);
			}

			clazz = clazz.getSuperclass();
			getAllFields(clazz);
		}
*/
	}

	public void writePrimitveTypes(Object obj, Field field, PrintWriter w) {

		/*
		 * final UseSerializer useSerializerAnn =
		 * ((UseSerializer)field.getAnnotation(UseSerializer.class)); final
		 * Class<FieldSerializer> returnedClass = ((Class<FieldSerializer>)
		 * useSerializerAnn.value());
		 * 
		 * FieldSerializer fieldSerializer;
		 */
		System.out.println(field.getName());
		field.setAccessible(true);
		Class<?> fieldClass = field.getType();

		if (fieldClass == Integer.TYPE) {
			// Zuerst scheuen ob es eine Anotierung gibt?

			if (field.isAnnotationPresent(UseSerializer.class)) {
				UseSerializer annotation = field.getDeclaredAnnotation(UseSerializer.class);
				Class annotationClass = annotation.value();
				// annotationClass=annotationClass.newInstance();

				// w.println("int "+field.getName()+"="+serializer.value());

			}
			try {
				w.println("int " + field.getName() + "=" + field.getInt(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Boolean.TYPE) {
			try {

				w.println("boolean " + field.getName() + "=" + field.getBoolean(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if (fieldClass == Short.TYPE) {

			try {

				w.println("short " + field.getName() + "=" + field.getShort(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Double.TYPE) {

			try {
				w.println("boolean " + field.getName() + "=" + field.getBoolean(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Short.TYPE) {

			try {
				w.println("short " + field.getName() + "=" + field.getShort(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Float.TYPE) {

			try {
				w.println("float " + field.getName() + "=" + field.getFloat(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Long.TYPE) {
			try {
				w.println("long " + field.getName() + "=" + field.getLong(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Byte.TYPE) {

			try {
				w.println("byte " + field.getName() + "=" + field.getByte(obj));

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else if (fieldClass == Character.TYPE) {

			try {
				w.println("char " + field.getName() + "=" + "'" + field.getChar(obj) + "'");

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * private class SerializedObject{
	 * 
	 * private static int id=0; private Object obj;
	 * 
	 * public SerializedObject(Object obj) { id++; this.obj=obj; }
	 * 
	 * public int getId() {
	 * 
	 * return id;
	 * 
	 * } }
	 */
}