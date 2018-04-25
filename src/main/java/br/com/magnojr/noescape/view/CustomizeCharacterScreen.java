package br.com.magnojr.noescape.view;

import java.util.Scanner;

import br.com.magnojr.noescape.exceptions.InvalidCharacterException;
import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;
import br.com.magnojr.noescape.presenter.PlayerPresenter;

public class CustomizeCharacterScreen extends Screen {

	Scanner scanner;

	public CustomizeCharacterScreen() {

	}

	public CustomizeCharacterScreen(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	private PlayerPresenter player = new PlayerPresenter();

	public PlayerPresenter getPlayer() {
		return player;
	}

	public void setPlayer(PlayerPresenter player) {
		this.player = player;
	}

	private void createCharacter() {
		clearScreen();
		System.out.println(TerminalColors.BLUE + " Create you Character: " + TerminalColors.RESET);
		System.out.println("...");
		System.out.println("What is the name of you character: ");
		player.setName(scanner.nextLine());
		System.out.println("What is his color: ");
		System.out.println("------------------ ");
		System.out.println("1 - Black ");
		System.out.println("2 - Red ");
		System.out.println("3 - Green ");
		System.out.println("4 - Blue ");
		player.setColor((scanner.nextLine().substring(0, 1)));
		System.out.println("What is his weapon: ");
		System.out.println("-------------------");
		System.out.println("1 - Sword ");
		System.out.println("2 - Arrows ");
		System.out.println("3 - Staff ");
		System.out.println("4 - Knife ");
		player.setWeapon((scanner.nextLine().substring(0, 1)));

		System.out.println("What is his class (style): ");
		System.out.println("-------------------");
		System.out.println("1 - Mage ["+ PlayerPresenter.SKIN_MAGE+"]");
		System.out.println("2 - Warrior ["+ PlayerPresenter.SKIN_WARRIOR+"]");
		System.out.println("3 - Thief ["+ PlayerPresenter.SKIN_THIEF+"]");
		System.out.println("4 - Dwarf ["+ PlayerPresenter.SKIN_DWARF+"]");
		player.setStyle((scanner.nextLine().substring(0, 1)));
	}

	private void setSkillPoints() {
		clearScreen();
		System.out.println("Now you have to set 3 skills for your character. ");
		System.out.println("The sum of all skill points can't be bigger than 10 ");
		System.out.println("Life: ");
		player.setLife((scanner.nextLine()));
		System.out.println("Strenght: ");
		player.setPower((scanner.nextLine()));
		System.out.println("Velocity: ");
		player.setVelocity((scanner.nextLine()));
	}

	public void show() {

		this.createCharacter();

		this.setSkillPoints();
		clearScreen();
		while (player.getStagePresenter() == null) {
			try {
				player.create();
			} catch (InvalidCharacterException e) {
				System.out.println("Sorry, A mage can't have a sword and a warrior can't have a staff!");
				System.out.println("Press <Enter> and try again");
				scanner.nextLine();
				this.createCharacter();
			} catch (SkillNotBalancedException e) {
				System.out.println("The sum of all skill points has to be equals to 10");
				System.out.println("Press <Enter> and try again");
				scanner.nextLine();
				this.setSkillPoints();
			}
		}

		clearScreen();
		System.out.println("Well Done! You created your character");
		System.out.println("===================================== ");

		System.out.println("Press <Enter> to start a new game...");
		scanner.nextLine();
	}

}
