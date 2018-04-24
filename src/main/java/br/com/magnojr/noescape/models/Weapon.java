package br.com.magnojr.noescape.models;

public enum Weapon {
	SWORD(3, 4), ARROWS(7, 2), STAFF(6, 3), KNIFE(2, 5);

	private int shot;
	private int plusDamage;

	private Weapon(int shot, int plusDamage) {
		this.shot = shot;
		this.plusDamage = plusDamage;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getPlusDamage() {
		return plusDamage;
	}

	public void setPlusDamage(int plusDamage) {
		this.plusDamage = plusDamage;
	}

}
