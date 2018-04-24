package br.com.magnojr.noescape.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.magnojr.noescape.exceptions.InvalidDirectionException;
import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Position;

public class PositionTest {

	@Test
	public void shouldReturnTheDistanceBetweenTwoPositions() throws InvalidDirectionException {
		int rowP1 = 1;
		int rowP2 = 10;		
		Position p1 = new Position(1, rowP1);
		Position p2 = new Position(1, rowP2);
		assertEquals(p1.getDistance(p2, Direction.RIGHT), Position.MAX_DISTANCE);
		assertEquals(p1.getDistance(p2, Direction.DOWN), Math.abs(rowP1 - rowP2));
		
		int colP1 = 1;
		int colP2 = 10;
		p1 = new Position(colP1, 1);
		p2 = new Position(colP2, 1);
		assertEquals(p1.getDistance(p2, Direction.RIGHT), Math.abs(rowP1 - rowP2));
		assertEquals(p1.getDistance(p2, Direction.DOWN), Position.MAX_DISTANCE);
	}

}
