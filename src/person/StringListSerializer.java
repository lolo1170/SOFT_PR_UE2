package person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import serial.FieldSerializer;

public class StringListSerializer implements FieldSerializer {
	
	@Override
	public void write(Object o, PrintWriter writer) throws IOException {
		@SuppressWarnings("unchecked")
		List<String> al = (List<String>) o; 
		String elems = al.stream().collect(Collectors.joining(" ")); 
		writer.println(elems);
	}

	@Override
	public Object read(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String[] words = line.split(" ");
		ArrayList<String> al = new ArrayList<String>();
		for (String e : words) {
			al.add(e);
		}
		return al;
	}
}
