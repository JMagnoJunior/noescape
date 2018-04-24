package br.com.magnojr.noescape.models;

import java.util.ArrayList;
import java.util.List;

import br.com.magnojr.noescape.exceptions.InvalidDirectionException;

public class Position {

	private int col;
	private int row;
	
	public static final int MAX_DISTANCE = 999;

	public Position(int col, int row) {
		super();
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int left() {
		return this.getCol() - 1;
	}

	public int right() {
		return this.getCol() + 1;
	}

	public int up() {
		return this.getRow() - 1;
	}

	public int down() {
		return this.getRow() + 1;
	}


	public int getDistance(Position enemyPosition, Direction direction) throws InvalidDirectionException {
		if(direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)  ){
			if(this.row == enemyPosition.getRow() ){
				return Math.abs(this.col - enemyPosition.getCol());	
			}
			
		}
		if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN)){
			if(this.col == enemyPosition.getCol() ){
				return Math.abs(this.row - enemyPosition.getRow());	
			}
			
		}
		
		return MAX_DISTANCE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
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
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public List<Direction> getRelativeDirectionTo(Position enemyPosition) {
		List<Direction> relativeDirections = new ArrayList<>();
		if( this.getCol() < enemyPosition.getCol() ){
			relativeDirections.add(Direction.RIGHT);
		}
		if( this.getCol() > enemyPosition.getCol() ){
			relativeDirections.add(Direction.LEFT);
		}
		if(this.getRow() > enemyPosition.getRow()){
			relativeDirections.add(Direction.UP);
		}
		if(this.getRow() < enemyPosition.getRow()){
			relativeDirections.add(Direction.DOWN);
		}
		return relativeDirections;
	}
	
	
}
