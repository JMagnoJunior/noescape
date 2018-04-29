package br.com.magnojr.noescape.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Position;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.services.ExplorationService;

public class ExplorationServiceTest {

	private Player getNewPlayer() throws  Exception{
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(2, 3, 5);
		String name = "player1";

		return new Player(color, name, skill, style, weapon);
	}

	@Test
	public void shouldChangeThePlayerPositionToRowMinusOne_whenPlayerMoveUpAndRowBiggerThanZero() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMinColPositionAllowed(), stage1.getMinRowPositionAllowed() + 1));
		int oldRow = player.getPosition().getRow();
		service.move(stage1,player, Direction.UP);

		// THEN
		assertEquals(player.getPosition().getRow(), oldRow - 1);
	}

	@Test
	public void shouldNotChangeThePlayerPositionToRowMinusOne_whenPlayerMoveUpAndRowLessThanOrEqualToOne() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(stage1.getMinPositionAllowed());
		int oldRow = player.getPosition().getRow();
		service.move(stage1,player, Direction.UP);

		// THEN
		assertEquals(player.getPosition().getRow(), oldRow);

	}

	@Test
	public void shouldChangeThePlayerPositionToRowPlusOne_whenPlayerMoveDownAndRowLessThanStageSize() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(stage1.getMinPositionAllowed());
		int oldRow = player.getPosition().getRow();
		service.move(stage1,player, Direction.DOWN);

		// THEN
		assertEquals(player.getPosition().getRow(), oldRow + 1);
	}

	@Test
	public void shouldNotChangeThePlayerPositionToRowPlusOne_whenPlayerMoveDownAndRowEqualsToStageSize() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMinColPositionAllowed(), stage1.getMaxRowPositionAllowed()));
		int oldRow = player.getPosition().getRow();
		service.move(stage1, player, Direction.DOWN);

		// THEN
		assertEquals(player.getPosition().getRow(), oldRow);
	}

	@Test
	public void shouldChangeThePlayerPositionToColPlusOne_whenPlayerMoveRightAndColIsLessThanMaxColPositionAllowed() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMinColPositionAllowed(), stage1.getMinRowPositionAllowed()));
		int oldCol = player.getPosition().getCol();
		service.move(stage1,player, Direction.RIGHT);

		// THEN
		assertEquals(player.getPosition().getCol(), oldCol + 1);
	}

	@Test
	public void shouldNotChangeThePlayerPositionToColPlusOne_whenPlayerMoveRightAndColIsEqualsToMaxColPositionAllowed() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMaxColPositionAllowed(), stage1.getMinRowPositionAllowed()));
		int oldCol = player.getPosition().getCol();
		service.move(stage1,player, Direction.RIGHT);

		// THEN
		assertEquals(player.getPosition().getCol(), oldCol);
	}

	@Test
	public void shouldChangeThePlayerPositionToColMinusOne_whenPlayerMoveLeftAndColIsBiggerThanMinColPositionAllowed() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMinColPositionAllowed() +1, stage1.getMinRowPositionAllowed()));
		int oldCol = player.getPosition().getCol();
		service.move(stage1,player, Direction.LEFT);

		// THEN
		assertEquals(player.getPosition().getCol(), oldCol -1);
	}
	
	@Test
	public void shouldNotChangeThePlayerPositionToColMinusOne_whenPlayerMoveLeftAndColIsEqualsToMinColPositionAllowed() throws  Exception{
		ExplorationService service = new ExplorationService();
		// GIVEN
		Player player = getNewPlayer();
		Stage stage1 = Stage.createStage1(player);

		// WHEN
		player.setPosition(new Position(stage1.getMinColPositionAllowed(),stage1.getMinRowPositionAllowed()));
		int oldRow = player.getPosition().getRow();
		service.move(stage1,player, Direction.LEFT);

		// THEN
		assertEquals(player.getPosition().getRow(), oldRow);
	}

}
