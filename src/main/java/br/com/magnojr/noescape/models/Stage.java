package br.com.magnojr.noescape.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;

public class Stage implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_STAGE_SIZE = 30;

	private Player player;
	private List<Enemy> enemies;
	private String[][] frame = new String[DEFAULT_STAGE_SIZE][DEFAULT_STAGE_SIZE];

	public Stage(Player player, List<Enemy> enemies) {
		super();
		this.player = player;
		this.enemies = enemies;
	}

	public static Stage createStage1(Player player) throws SkillNotBalancedException {
		List<Enemy> enemies = new ArrayList<Enemy>();
		Enemy e1 = new Enemy(Color.RED,new Skill(7,2,1),Style.ENEMY_VOGON, Weapon.KNIFE);
		Enemy e2 = new Enemy(Color.RED,new Skill(8,1,1),Style.ENEMY_VOGON, Weapon.KNIFE);
		Enemy e3 = new Enemy(Color.RED,new Skill(5,2,3),Style.ENEMY_VOGON, Weapon.KNIFE);
		Enemy e4 = new Enemy(Color.RED,new Skill(5,2,3),Style.ENEMY_VOGON, Weapon.KNIFE);
		Enemy e5 = new Enemy(Color.RED,new Skill(9,1,0),Style.ENEMY_VOGON, Weapon.KNIFE);
		Enemy e6 = new Enemy(Color.RED,new Skill(5,2,3),Style.ENEMY_VOGON, Weapon.KNIFE);
		enemies.add(e1);
		enemies.add(e2);
		enemies.add(e3);
		enemies.add(e4);
		enemies.add(e5);
		enemies.add(e6);
		return new Stage(player, enemies);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public Position getMaxPositionAllowed(){
		return new Position( this.DEFAULT_STAGE_SIZE - 1,  this.DEFAULT_STAGE_SIZE - 1);
	}
	
	public Position getMinPositionAllowed(){
		return new Position(  1,   1);
	}
	
	public int getMinColPositionAllowed() {
		return 1;
	}
	
	public int getMinRowPositionAllowed() {
		return 1;
	}
	
	public int getMaxColPositionAllowed() {
		return this.DEFAULT_STAGE_SIZE - 1;
	}

	public int getMaxRowPositionAllowed() {
		return this.DEFAULT_STAGE_SIZE - 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enemies == null) ? 0 : enemies.hashCode());
		result = prime * result + Arrays.deepHashCode(frame);
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		Stage other = (Stage) obj;
		if (enemies == null) {
			if (other.enemies != null)
				return false;
		} else if (!enemies.equals(other.enemies))
			return false;
		if (!Arrays.deepEquals(frame, other.frame))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

}
