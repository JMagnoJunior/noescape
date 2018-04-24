package br.com.magnojr.noescape.presenter;

import br.com.magnojr.noescape.exceptions.InvalidCharacterException;
import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;
import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.services.CombatService;
import br.com.magnojr.noescape.services.ExplorationService;
import br.com.magnojr.noescape.services.MenuService;

public class PlayerPresenter {

	private String name;
	private String color;
	private String style;
	private String weapon;

	private String life;
	private String power;
	private String velocity;

	private int damage = 0;

	private Player player;
	private StagePresenter stagePresenter;

	MenuService menuService = new MenuService();
	ExplorationService explorationService = new ExplorationService();
	CombatService combatService = new CombatService();

	public PlayerPresenter(StagePresenter stagePresenter) {
		this.stagePresenter = stagePresenter;
		Stage stage = stagePresenter.getStage();
		player = stage.getPlayer();
	}

	public PlayerPresenter() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public StagePresenter getStagePresenter() {
		return stagePresenter;
	}

	public void setStagePresenter(StagePresenter stagePresenter) {
		this.stagePresenter = stagePresenter;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	private Color selectedColor() {
		Color c = null;
		if (color.equals("1")) {
			c = Color.BLACK;
		}
		if (color.equals("2")) {
			c = Color.RED;
		}
		if (color.equals("3")) {
			c = Color.GREEN;
		}
		if (color.equals("4")) {
			c = Color.BLUE;
		}
		return c;
	}

	private Weapon selectedWeapon() {
		Weapon w = null;
		if (weapon.equals("1")) {
			w = Weapon.SWORD;
		}
		if (weapon.equals("2")) {
			w = Weapon.ARROWS;
		}
		if (weapon.equals("3")) {
			w = Weapon.STAFF;
		}
		if (weapon.equals("4")) {
			w = Weapon.KNIFE;
		}
		return w;
	}

	private Style selectedStyle() {
		Style s = null;
		if (style.equals("1")) {
			s = Style.MAGE;
		}
		if (style.equals("2")) {
			s = Style.WARRIOR;
		}
		if (style.equals("3")) {
			s = Style.THIEF;
		}
		if (style.equals("4")) {
			s = Style.DWARF;
		}
		return s;

	}

	public void create() throws InvalidCharacterException, SkillNotBalancedException {

		Color color = selectedColor();
		Weapon weapon = selectedWeapon();
		Style style = selectedStyle();
		Skill skill = new Skill(new Integer(this.getLife()), new Integer(this.getPower()),
				new Integer(this.getVelocity()));
		Player player = new Player(color, this.name, skill, style, weapon);
		this.player = player;

		Stage stage = menuService.startANewGame(player);
		this.stagePresenter = new StagePresenter(stage);

	}

	public String getLifeBar() {
		int currentLife = this.getPlayer().getCurrentLife();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < currentLife; i++) {
			result.append("*");
		}
		return result.toString();
	}

	public int getExperience() {
		return this.getPlayer().getExp();
	}

	public void action(String input) {
		Stage stage = this.stagePresenter.getStage();
		if (input.equals("w")) {
			this.explorationService.move(stage, this.getPlayer(), Direction.UP);
		}
		if (input.equals("s")) {
			this.explorationService.move(stage, this.getPlayer(), Direction.DOWN);
		}
		if (input.equals("d")) {
			this.explorationService.move(stage, this.getPlayer(), Direction.RIGHT);
		}
		if (input.equals("a")) {
			this.explorationService.move(stage, this.getPlayer(), Direction.LEFT);
		}

		if (input.equals("wk")) {
			damage = this.combatService.attack(stage, Direction.UP);

		}
		if (input.equals("sk")) {
			damage = this.combatService.attack(stage, Direction.DOWN);

		}
		if (input.equals("dk")) {
			damage = this.combatService.attack(stage, Direction.RIGHT);

		}
		if (input.equals("ak")) {
			damage = this.combatService.attack(stage, Direction.LEFT);

		}
		if (input.equals("save")) {
			menuService.save(stage, this.player.getName());
		}

	}

	public String getWeaponReach() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.getPlayer().getWeapon().getShot(); i++) {
			sb.append("*");
		}

		return sb.toString();
	}

	public int getWeaponDamage() {
		return this.getPlayer().getWeapon().getPlusDamage();
	}

	public String getWeaponName() {

		return this.getPlayer().getWeapon().toString();
	}

	public void cleanDamage() {
		this.damage = 0;
	}

}
