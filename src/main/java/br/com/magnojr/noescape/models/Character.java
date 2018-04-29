package br.com.magnojr.noescape.models;

public abstract class Character implements Colorable, Movable {

	private Position position;
	private Color color;
	private Style style;
	private Skill skill;
	private Weapon weapon;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void move(Stage stage, Direction direction) {
		
		Position currentPosition = this.getPosition();
		int maxMoveAllowedInScreen = stage.getMaxColPositionAllowed() - 2;
		
		if (direction.equals(Direction.UP) && (currentPosition.getRow() > 1)) {
			Position newPosition = new Position(currentPosition.getCol(), currentPosition.up());
			this.setPosition(newPosition);
		}

		if (direction.equals(Direction.DOWN) && (currentPosition.getRow() < maxMoveAllowedInScreen )) {
			Position newPosition = new Position(currentPosition.getCol(), currentPosition.down());
			this.setPosition(newPosition);
		}

		if (direction.equals(Direction.RIGHT) && (currentPosition.getCol() < maxMoveAllowedInScreen)) {
			Position newPosition = new Position(currentPosition.right(), currentPosition.getRow());
			this.setPosition(newPosition);
		}

		if (direction.equals(Direction.LEFT) && (currentPosition.getCol() > 1)) {
			Position newPosition = new Position(currentPosition.left(), currentPosition.getRow());
			this.setPosition(newPosition);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
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
		Character other = (Character) obj;
		if (color != other.color)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (style != other.style)
			return false;
		return true;
	}

}
