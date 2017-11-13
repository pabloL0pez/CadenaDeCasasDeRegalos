package cadena;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			CadenaDeCasas regalos = new CadenaDeCasas("regalos.in");
			regalos.resolver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
