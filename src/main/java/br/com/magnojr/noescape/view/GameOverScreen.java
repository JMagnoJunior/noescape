package br.com.magnojr.noescape.view;

import java.util.Scanner;

public class GameOverScreen extends Screen{

	private Scanner scanner;
	
	public GameOverScreen(Scanner scanner){
		this.scanner = scanner;
	}
	
	@Override
	public void show() {
		super.clearScreen();
		System.out.println("==========");
		System.out.println("You Lose!");
		System.out.println("The middle earth was destroyed");
		System.out.println("==========");
		scanner.nextLine();
		MenuScreen menuScreen = new MenuScreen();
		menuScreen.show();
		
	}

}
