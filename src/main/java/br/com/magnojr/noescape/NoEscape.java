package br.com.magnojr.noescape;

import java.io.IOException;

import br.com.magnojr.noescape.view.MenuScreen;

public class NoEscape {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("This game is only compatible with Linux");

		MenuScreen menu = new MenuScreen();

		menu.show();

	}

}
