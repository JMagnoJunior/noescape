package br.com.magnojr.noescape.view;

public abstract class Screen {

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public abstract void show();

}
