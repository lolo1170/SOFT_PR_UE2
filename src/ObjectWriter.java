import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class ObjectWriter {

	public void write(Object obj, OutputStream out) throws IOException {

		PrintWriter writer = new PrintWriter(new File("C:\\Users\\Stefan\\Desktop\\test.txt"));

		Method[] m = obj.getClass().getMethods();

		for (int i = 0; i < m.length; i++) {
			Method method = m[i];
			System.out.println(m[i]);
			writer.println(method.getName());
		}

		writer.flush();
		writer.close();

	}
}