package br.com.magnojr.noescape.models;

import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;

public class Skill {

	private final int TOTAL_SKILL_POINTS = 10; 
	private int life;
	private int power;
	private int velocity;

	public Skill() {
		super();
	}

	public Skill(int life, int power, int velocity) throws SkillNotBalancedException {
		super();
		this.life = life;
		this.power = power;
		this.velocity = velocity;
		this.checkIfSkillsAreBalanced();
	}
	
	private void checkIfSkillsAreBalanced() throws SkillNotBalancedException{
		if(this.life + this.power + this.velocity != TOTAL_SKILL_POINTS){
			throw new SkillNotBalancedException();
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

}
