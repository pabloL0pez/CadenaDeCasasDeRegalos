package cadena;

public class Color {

	private int color;
	private int frecuencia;
	
	public Color(int color, int frecuencia) {
		this.color = color;
		this.frecuencia = frecuencia;
	}
	
	public void incrementarColor() {
		this.frecuencia++;
	}

	public int getColor() {
		return color;
	}

	public int getFrecuencia() {
		return frecuencia;
	}
	
}
