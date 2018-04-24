package br.com.magnojr.noescape.view;

import java.util.Scanner;

import br.com.magnojr.noescape.presenter.PlayerPresenter;

public class MenuScreen extends Screen {

	public String getMainMenu() {
		clearScreen();
		StringBuilder screen = new StringBuilder();		
		screen.append(" Welcome to No Escape \n");
		screen.append(" -------------------- \n");
		screen.append(" 1 - Start a new Game \n");
		screen.append(" 2 - Continue         \n");

		return screen.toString();
	}

	public void show() {
		System.out.println(TerminalColors.RED + getMainMenu() + TerminalColors.RESET);

		try (Scanner scanner = new Scanner(System.in)) {
			String option = "0";
			while (!option.equals("1") || !option.equals("2")) {
				option = scanner.nextLine();
				if (option.equals("1")) {
					StartScreen startScreen = new StartScreen(scanner);
					startScreen.show();
					
					PlayerPresenter player = null;
					CustomizeCharacterScreen screenCustomize = new CustomizeCharacterScreen(scanner);
					screenCustomize.show();
					player = screenCustomize.getPlayer();
					StageOneScreen stageOneScreen = new StageOneScreen(player, scanner);
					stageOneScreen.show();
				}

				if (option.equals("2")) {
					LoadGameScreen loadGameScreen = new LoadGameScreen(scanner);
					loadGameScreen.show();
					PlayerPresenter player = new PlayerPresenter(loadGameScreen.getStagePresenter());
					StageOneScreen stageOneScreen = new StageOneScreen(player, scanner);
					stageOneScreen.show();

				}

			}

		}

	}

}
