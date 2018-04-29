package br.com.magnojr.noescape.models;

import java.util.Random;

public class Enemy extends Character {

	private int currentLife;
	private boolean alive;
	private boolean underAtack;

	private final int CONST_TO_APPEAR_IN_MIDDLE = 5;

	public Enemy(Color color, Skill skill, Style style, Weapon weapon) {
		super();
		this.setColor(color);
		this.setSkill(skill);
		this.setStyle(style);
		this.setWeapon(weapon);
		this.currentLife = skill.getLife();
		this.alive = true;
		Random rand = new Random();
		int row = rand.nextInt(Stage.DEFAULT_STAGE_SIZE - CONST_TO_APPEAR_IN_MIDDLE) + 1;
		int col = rand.nextInt(Stage.DEFAULT_STAGE_SIZE - CONST_TO_APPEAR_IN_MIDDLE) + 1;
		this.setPosition(new Position(col, row));
	}

	public Enemy() {
		super();
	}

	public int getCurrentLife() {
		return currentLife;
	}

	public void setCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void killed() {
		this.alive = false;

	}

	public int getExperienceReleased() {
		Random rand = new Random();

		return rand.nextInt(10) + 1;
	}

	public void setUnderAtack(boolean b) {
		this.underAtack = b;
	}

	public boolean isUnderAtack() {
		return underAtack;
	}

	@Override	
	public void move(Stage stage, Direction direction) {
		if(this.alive){
			super.move(stage, direction);
		}
	}
}
