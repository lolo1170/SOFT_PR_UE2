package serial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObjectReader {
	
	public Object read(InputStream in) throws Exception {
		try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
			// TODO
			return null;			
		}
	}

}
