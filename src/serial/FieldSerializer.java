package serial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface FieldSerializer {
	
	public abstract void write(Object v, PrintWriter writer) throws IOException;
	public abstract Object read(BufferedReader reader) throws IOException;
	
}
