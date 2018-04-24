package br.com.magnojr.noescape.view;

import java.util.Scanner;

public class StartScreen {

	private Scanner scanner;

	public StartScreen(Scanner scanner) {
		this.scanner = scanner;
	}

	public void show() {
		System.out.println(TerminalColors.BLUE +"===================================================");
		System.out.println("The Vogons are invading the middle earth and now only a hero can save us");
		System.out.println(
				"...as you may know, the Vogons are very bureaucratic and that's why you have to press enter before each command O:) ");
		System.out.println(
				"...but it doesn't matter.Our hero is stronger than that and now is time to restore the peace.");
		System.out.println("===================================================");
		System.out.println("(press <Enter> to continue...)" + TerminalColors.RESET);
		scanner.nextLine();

	}

}
