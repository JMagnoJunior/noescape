package br.com.magnojr.noescape.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Enemy;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Position;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.services.CombatService;

public class CombatServiceTest {

	private Player getNewPlayer() throws  Exception {
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(2, 3, 5);
		String name = "player1";

		return new Player(color, name, skill, style, weapon);
	}

	private Enemy getNewEnemy() throws  Exception{
		Color color = Color.RED;
		Style style = Style.ENEMY_VOGON;
		Weapon weapon = Weapon.KNIFE;
		Skill skill = new Skill(2, 3, 5);

		return new Enemy(color, skill, style, weapon);
	}
	
	private Enemy getNewStrongEnemy() throws  Exception{
		Color color = Color.RED;
		Style style = Style.ENEMY_VOGON;
		Weapon weapon = Weapon.KNIFE;
		Skill skill = new Skill(8, 1, 1);

		return new Enemy(color, skill, style, weapon);
	}

	@Test
	public void shouldAtackAEnemyAndDestroyIfLifeIsLowerThanAtack_whenItCanBeReachedAtRight() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(1, 2));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() + player.getWeapon().getShot() - 1, player.getPosition().getRow());
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.RIGHT);
		
		// THEN
		assertFalse(enemy.isAlive());
		
	}

	@Test
	public void shouldNotAtackAEnemy_whenItCanNotBeReachedAtRight() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(1, 1));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() + player.getWeapon().getShot() + 1, player.getPosition().getRow());
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.RIGHT);
		
		// THEN
		assertTrue(enemy.isAlive());
	}
	
	@Test
	public void shouldAtackAEnemyAndDestroyIfLifeIsLowerThanAtack_whenItCanBeReachedAtLeft() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(Stage.DEFAULT_STAGE_SIZE - 1, 1));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() - player.getWeapon().getShot() + 1, player.getPosition().getRow());
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.LEFT);
		
		// THEN
		assertFalse(enemy.isAlive());
		
	}
	
	@Test
	public void shouldNotAtackAEnemy_whenItCanNotBeReachedAtLeft() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(Stage.DEFAULT_STAGE_SIZE - 1, Stage.DEFAULT_STAGE_SIZE - 1));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() - player.getWeapon().getShot() - 1, player.getPosition().getRow() );
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.LEFT);
		
		// THEN
		assertTrue(enemy.isAlive());
	}
	
	@Test
	public void shouldAtackAEnemyAndDestroyIfLifeIsLowerThanAtack_whenItCanBeReachedAtBottom() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(1, 1));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() , player.getPosition().getRow() + player.getWeapon().getShot() - 1);
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.DOWN);
		
		// THEN
		assertFalse(enemy.isAlive());
		
	}
	
	public void shouldNotAtackAEnemy_whenItCanNotBeReachedAtBottom() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(1, 1));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol(), player.getPosition().getRow() + player.getWeapon().getShot() +  1 );
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.DOWN);
		
		// THEN
		assertTrue(enemy.isAlive());
	}
	
	@Test
	public void shouldAtackAEnemyAndDestroyIfLifeIsLowerThanAtack_whenItCanBeReachedAbove() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(0, Stage.DEFAULT_STAGE_SIZE));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol() , player.getPosition().getRow()- player.getWeapon().getShot() + 1 );
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.UP);
		
		// THEN
		assertFalse(enemy.isAlive());
		
	}
	
	public void shouldNotAtackAEnemy_whenItCanNotBeReachedAbove() throws  Exception{
		CombatService service = new CombatService();
		// GIVEN
		Player player = this.getNewPlayer();		
		player.setPosition(new Position(0, Stage.DEFAULT_STAGE_SIZE));
		
		Stage stage1 = Stage.createStage1(player);		
		Enemy enemy = this.getNewEnemy();
		Position enemyPosition = new Position(player.getPosition().getCol(), player.getPosition().getRow() - player.getWeapon().getShot() -  1 );
		enemy.setPosition( enemyPosition );		
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		stage1.setEnemies(enemies);		
		
		
		// WHEN
		service.attack(stage1, Direction.DOWN);
		
		// THEN
		assertTrue(enemy.isAlive());
	}
	

	 @Test
	 public void shouldNotDestroyAEnemy_whenAtackPowerIsWeakerThanEnemysLife() throws  Exception{
		 CombatService service = new CombatService();
			// GIVEN
			Player player = this.getNewPlayer();		
			player.setPosition(new Position(2, 1));
			
			Stage stage1 = Stage.createStage1(player);		
			Enemy enemy = this.getNewStrongEnemy();
			Position enemyPosition = new Position(player.getPosition().getCol()+ player.getWeapon().getShot() - 1, player.getPosition().getRow() );
			enemy.setPosition( enemyPosition );		
			List<Enemy> enemies = new ArrayList<Enemy>();
			enemies.add(enemy);
			stage1.setEnemies(enemies);		
			
			
			// WHEN
			service.attack(stage1, Direction.RIGHT);
			
			// THEN
			assertTrue(enemy.isAlive());
	 }
	 
	 @Test
	 public void shouldDestroyAEnemy_whenAtackTwiceAndTheTotalAtackIsStrongerThanEnemysLife() throws  Exception{
		 CombatService service = new CombatService();
			// GIVEN
			Player player = this.getNewPlayer();		
			player.setPosition(new Position(2, 1));
			
			Stage stage1 = Stage.createStage1(player);		
			Enemy enemy = this.getNewStrongEnemy();
			Position enemyPosition = new Position(player.getPosition().getCol() + player.getWeapon().getShot() - 1, player.getPosition().getRow());
			enemy.setPosition( enemyPosition );		
			List<Enemy> enemies = new ArrayList<Enemy>();
			enemies.add(enemy);
			stage1.setEnemies(enemies);		
			
			
			// WHEN
			service.attack(stage1, Direction.RIGHT);						
			
			// THEN
			assertTrue(enemy.isAlive());
			
			// WHEN
			service.attack(stage1, Direction.RIGHT);
			
			// THEN
			assertFalse(enemy.isAlive());
	 }
	 
	 @Test
	 public void shouldDestroyTheEnemyAndGetExperienceOnce_whenAtackTwiceAndTheTotalAtackIsStrongerThanEnemysLife() throws  Exception{
		 CombatService service = new CombatService();
			// GIVEN
			Player player = this.getNewPlayer();		
			player.setPosition(new Position(2, 1));
			
			Stage stage1 = Stage.createStage1(player);		
			Enemy enemy = this.getNewStrongEnemy();
			Position enemyPosition = new Position(player.getPosition().getCol() + player.getWeapon().getShot() - 1, player.getPosition().getRow());
			enemy.setPosition( enemyPosition );		
			List<Enemy> enemies = new ArrayList<Enemy>();
			enemies.add(enemy);
			stage1.setEnemies(enemies);
			
			int oldExp = player.getExp();
			
			// WHEN
			service.attack(stage1, Direction.RIGHT);						
			
			// THEN
			assertTrue(enemy.isAlive());
			assertEquals(player.getExp(), oldExp);
			// WHEN
			service.attack(stage1, Direction.RIGHT);
			
			// THEN
			assertFalse(enemy.isAlive());
			assertTrue(oldExp < player.getExp() );
	 }

}
