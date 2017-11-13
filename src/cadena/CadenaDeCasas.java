package cadena;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CadenaDeCasas {

	private boolean[][] matAdy;
	private ArrayList<Integer> casas = new ArrayList<Integer>();
	private int cantCasas;
	
	private int[] frecuenciaColor;
	private int[] casasColoreadas;
	
	private ArrayList<Integer> conjuntoCasa = new ArrayList<Integer>();
	
	public CadenaDeCasas(String path) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(path));
		int casa;
		int adyacente;
		this.cantCasas = scan.nextInt();
		this.matAdy = new boolean[this.cantCasas][this.cantCasas];
		this.casasColoreadas = new int[this.cantCasas];
		this.frecuenciaColor = new int[this.cantCasas];
		
		for (int i = 0 ; i < this.cantCasas ; i++) {
			casa = scan.nextInt() - 1;
			this.casas.add(casa);
			while ((adyacente = scan.nextInt() - 1) != -2) {
				this.matAdy[casa][adyacente] = true;
				this.matAdy[adyacente][casa] = true;
			}
		}

		scan.close();
	}
	
	public void resolver() throws IOException {
		int colorMasFrecuente = 0;
		int valorColorMasFrecuente = 0;
		
		Collections.shuffle(this.casas);
		colorear();
		
		for (int i = 0 ; i < this.frecuenciaColor.length ; i++) { 
			if (this.frecuenciaColor[i] > valorColorMasFrecuente) {
				colorMasFrecuente = i;
				valorColorMasFrecuente = this.frecuenciaColor[i];
			}
		}
		
		for (int i = 0 ; i < this.casasColoreadas.length ; i++) {
			if (this.casasColoreadas[i] == colorMasFrecuente) {
				this.conjuntoCasa.add(i+1);
			}
		}
		
		escribirSolucion();
	}

	private void colorear() {
		int color = 1;
		int casa;
		int j;
		
		for (int i = 0; i < this.cantCasas; i++)
			this.casasColoreadas[i] = 0;

		casasColoreadas[this.casas.get(0)] = color;

		for (int i = 1; i < this.cantCasas; i++) {
			casa = this.casas.get(i);
			this.casasColoreadas[casa] = color;
			j = 0;
			while(j < this.cantCasas) {
				if (casa != j) {

					if (this.matAdy[casa][j] && this.casasColoreadas[casa] == this.casasColoreadas[j]) {
						color++;
						this.casasColoreadas[casa] = color;
						this.frecuenciaColor[color]++;
						j = -1;
					}
				}
				j++;
			}
			color = 1;
		}
	}

	private void escribirSolucion() throws IOException {
		BufferedWriter buffer = new BufferedWriter(new FileWriter("regalos.out"));
		
		buffer.write(String.valueOf(this.conjuntoCasa.size()));
		buffer.newLine();
		for (int i = 0 ; i < this.conjuntoCasa.size() ; i++) {
			buffer.write(this.conjuntoCasa.get(i) + " ");
		}
		
		buffer.close();
	}
}
