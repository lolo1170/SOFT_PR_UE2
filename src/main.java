import java.io.IOException;

import serial.ObjectWriter;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new ObjectWriter().write(new toDo("Essen", null),null);
		
		
	}

}
