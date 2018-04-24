package br.com.magnojr.noescape.view;

import java.util.List;
import java.util.Scanner;

import br.com.magnojr.noescape.models.Enemy;
import br.com.magnojr.noescape.presenter.PlayerPresenter;
import br.com.magnojr.noescape.presenter.StagePresenter;

public class StageOneScreen extends Screen {

	private PlayerPresenter player;

	private Scanner scanner;

	public StageOneScreen(PlayerPresenter player, Scanner scanner) {
		this.player = player;

		this.scanner = scanner;
	}
	
	private void clearStage(){
		clearScreen();
		System.out.println("  >>>  Congratulations!!! <<<");
		System.out.println("You saved the middle earth from the threat of one of the most unpleasant races in the galaxy");
		System.out.println("All Vogons are destroied. You are a true hero. Thank you!");
		System.out.println(" (...cntrl + c to quit) ");
		scanner.nextLine();
	}

	private void drawStage() {
		clearScreen();
		System.out.println("* * * * Stage 1 * * * *");
		System.out.println("Player: " + player.getName());
		System.out.println("Total Life: " + player.getLifeBar());
		System.out.println("Experience: " + player.getExperience());
		System.out.println("Weapon Selected: " + player.getWeaponName() + " / Weapon - damage: "
				+ player.getWeaponDamage() + " / Weapon - reach: " + player.getWeaponReach());

		StagePresenter stagePresenter = player.getStagePresenter();
		String wall = stagePresenter.getWallFormat();
		int cols = stagePresenter.getCols();
		int rows = stagePresenter.getRows();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || i == cols - 1) {
					System.out.print(wall);
				} else {
					if (j == 0 || j == rows - 1) {
						System.out.print(wall);
					} else {
						System.out.print(stagePresenter.getElement(i, j));
					}
				}
			}

			System.out.print("\n");
		}
		while (true) {
			
			if(stagePresenter.isClear()){				
				this.clearStage();
				return;
			}

			System.out.println("[Controller]: ");
			System.out.println("  - type a(left) s(down) d(left) w(up) and <Enter> to move your character; ");
			System.out.println(
					"  - type ak(attack left) sk(atack down) dk(atack right) wk(atack up) and <Enter> to move your character;");
			System.out.println("  - type 'save' and press <Enter> to save your game; ");
			System.out.println("  - type Cntrl + C to quit; ");
			String input = scanner.nextLine();

			player.action(input);

			if (player.getDamage() > 0) {
				System.out.println("You Hit The Bad Guy! ");
				System.out.println("((( Damage: " + player.getDamage() + " )))");
				System.out.println("Press <Enter> to continue...");
				scanner.nextLine();
				player.cleanDamage();
			}
			
		

			this.drawStage();
		}

	}

	public void show() {
		clearScreen();

		drawStage();

	}

}
