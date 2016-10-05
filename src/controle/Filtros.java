package controle;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.math.BigDecimal;

public class Filtros {
	/**
	 * 
	 */
	//Filtros filtro = new Filtros();

	public BufferedImage tomCinza(BufferedImage img) {
		/*
		 * for (int i = 0; i < img.getWidth(); i++) { for (int j = 0; j <
		 * img.getHeight(); j++) { Color c = new Color(img.getRGB(i, j)); int
		 * tomCinza = (c.getRed() + c.getGreen() + c.getBlue()) / 3; c = new
		 * Color(tomCinza, tomCinza, tomCinza); img.setRGB(i, j, c.getRGB()); }
		 * 
		 * }
		 */
		for (int x = 0; x < img.getWidth(); ++x) {
			for (int y = 0; y < img.getHeight(); ++y) {
				int rgb = img.getRGB(x, y);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int valorCinza = (r + g + b) / 3;
				int cinza = (valorCinza << 16) + (valorCinza << 8) + valorCinza;
				img.setRGB(x, y, cinza);
			}
		}
		return img;
	}

	public int[][] acharPixel(BufferedImage img) {
		int altura = descobreAltura(img);
		int largura = descobreLargura(img);
		Raster raster = img.getData();
		int[][] pixel = new int[largura][altura];
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				pixel[i][j] = raster.getSample(i, j, 0);
			}
		}
		return pixel;
	}

	// Funcão para imprimir os pixeis - recebe a matriz e imprime
	public void imprimePixel(int[][] pixel) {

		for (int i = 0; i < pixel.length; i++) {
			for (int j = 0; j < pixel[0].length; j++) {
				System.out.print(pixel[i][j] + " | ");

			}
			System.out.println();

		}
	}
	
	//funçao para imprimir os valores apos a aplicação da formula
	public void imprimeMatriz(double[][] pixel) {

		for (int i = 0; i < pixel.length; i++) {
			for (int j = 0; j < pixel[0].length; j++) {
				System.out.print(pixel[i][j] + " " + i + " - x - " + j + " | ");
 
			}
			System.out.println();

		}
	}

	public static int descobreAltura(BufferedImage img) {
		int altura = img.getHeight();
		System.out.printf("Altura " + altura + "\n");
		return altura;
	}

	public static int descobreLargura(BufferedImage img) {
		int largura = img.getWidth();
		System.out.printf("Largura " + largura + "\n");
		return largura;
	}

	// metodo inverte matriz
	public int[][] matrizTransposta(int[][] matriz) {

		int transposta[][] = new int[matriz[0].length][matriz.length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				transposta[j][i] = matriz[i][j];
			}
		}
		return transposta;

	}

	// metodo ....
	
	public String[][] formulaAlturaY(int [][] matriz) {
		double dx[][] = new double[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (i == 0) {
					dx[i][j] = ((-3.0 * matriz[i][j]) + (4.0 * matriz[i + 1][j]) - (matriz[i + 2][j])) / 2;
					
				
				}  else if (i == matriz.length -1){
					dx[i][j] = (3 * matriz[i][j]) - (4 * matriz[i - 1][j]) + (matriz[i - 2][j]) / 2;
					
				
				}  else {
					dx[i][j] = (((matriz[i - 1][j] - matriz[i + 1][j]) / 2));
					
					
				}
			}
		}
		imprimeMatriz(dx);
		return null;
	}

}
