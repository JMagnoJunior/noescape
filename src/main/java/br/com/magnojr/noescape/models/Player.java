package br.com.magnojr.noescape.models;

import java.util.List;
import java.util.Optional;

import br.com.magnojr.noescape.exceptions.InvalidCharacterException;

public class Player extends Character {

	private String name;
	private int exp = 0;
	private int currentLife;

	public Player(Color color, String name, Skill skill, Style style, Weapon weapon) throws InvalidCharacterException {
		super();

		this.setName(name);
		this.setColor(color);
		this.setSkill(skill);
		this.setStyle(style);
		this.setWeapon(weapon);

		Position initialPosition = new Position(1, 1);

		this.setPosition(initialPosition);

		this.currentLife = skill.getLife();

		this.checkIfItsAValidPlayer();
	}

	public int getCurrentLife() {

		return currentLife;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	private void checkIfItsAValidPlayer() throws InvalidCharacterException {
		// TODO: this rule should be improved in the future, but that is just a
		// MVP... and we have tests
		if (this.getStyle().equals(Style.MAGE) && this.getWeapon().equals(Weapon.SWORD)) {
			throw new InvalidCharacterException();
		}

		if (this.getStyle().equals(Style.WARRIOR) && this.getWeapon().equals(Weapon.STAFF)) {
			throw new InvalidCharacterException();
		}

	}

//	public void move(Stage stage, Direction direction) {
//		Position currentPosition = this.getPosition();
//		if (direction.equals(Direction.UP) && (currentPosition.getRow() > 1)) {
//			Position newPosition = new Position(currentPosition.getCol(), currentPosition.up());
//			this.setPosition(newPosition);
//		}
//
//		if (direction.equals(Direction.DOWN) && (currentPosition.getRow() < (stage.getMaxColPositionAllowed() - 1))) {
//			Position newPosition = new Position(currentPosition.getCol(), currentPosition.down());
//			this.setPosition(newPosition);
//		}
//
//		if (direction.equals(Direction.RIGHT) && (currentPosition.getCol() < (stage.getMaxRowPositionAllowed() - 1))) {
//			Position newPosition = new Position(currentPosition.right(), currentPosition.getRow());
//			this.setPosition(newPosition);
//		}
//
//		if (direction.equals(Direction.LEFT) && (currentPosition.getRow() > 1)) {
//			Position newPosition = new Position(currentPosition.left(), currentPosition.getRow());
//			this.setPosition(newPosition);
//		}
//
//	}

	public int atack(Stage stage, Direction direction) {
		int damage = 0;
		int powerAtack = this.getWeapon().getPlusDamage() + this.getSkill().getPower();

		Optional<Enemy> enemyOptional = stage.getEnemies().stream().filter((e) -> {

			Position playerPosition = this.getPosition();
			Position enemyPosition = e.getPosition();

			int distance;
			List<Direction> enemyRelativeDirections;

			try {
				distance = playerPosition.getDistance(enemyPosition, direction);
				enemyRelativeDirections = playerPosition.getRelativeDirectionTo(enemyPosition);
				if ((this.getWeapon().getShot() >= distance) && e.isAlive()) {
					if (enemyRelativeDirections.contains(direction)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} catch (Exception e1) {
				return false;
			}

		}).findFirst();

		if (enemyOptional.isPresent()) {

			Enemy enemy = enemyOptional.get();
			if (powerAtack >= enemy.getCurrentLife()) {
				enemy.killed();
				this.addExp(enemy.getExperienceReleased());
				damage = enemy.getCurrentLife();
			} else {
				enemy.setCurrentLife(enemy.getCurrentLife() - powerAtack);
				enemy.setUnderAtack(true);
				damage = powerAtack;
			}
			enemy.setUnderAtack(false);

		} else {
			damage = 0;
		}
		return damage;

	}
	
	public void getHit(){
		this.currentLife -= 1;
	}

	private void addExp(int experienceReleased) {
		this.exp += experienceReleased;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exp;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (exp != other.exp)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
