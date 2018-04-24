package br.com.magnojr.noescape.view;

import java.util.Scanner;

import br.com.magnojr.noescape.presenter.StagePresenter;

public class LoadGameScreen extends Screen {

	private Scanner scanner;
	private StagePresenter stagePresenter;

	public LoadGameScreen(Scanner scanner) {
		this.scanner = scanner;
	}

	public StagePresenter getStagePresenter() {
		return stagePresenter;
	}

	public void setStagePresenter(StagePresenter stagePresenter) {
		this.stagePresenter = stagePresenter;
	}

	public void show() {
		System.out.println("Inform your character's name: ");
		String playerName = scanner.nextLine();
		this.stagePresenter = StagePresenter.loadGame(playerName);

	}

}
