package br.com.magnojr.noescape.presenter;

import java.util.Optional;
import java.util.Random;

import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Colorable;
import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Enemy;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Position;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.services.CombatService;
import br.com.magnojr.noescape.services.ExplorationService;
import br.com.magnojr.noescape.services.MenuService;
import br.com.magnojr.noescape.view.TerminalColors;

public class StagePresenter {

	private Stage stage;
	private ExplorationService explorationService = new ExplorationService();
	private CombatService combatService = new CombatService();

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public StagePresenter(Stage stage) {
		this.stage = stage;
	}

	public String getWallFormat() {
		return "#";
	}

	public int getCols() {
		return stage.getMaxColPositionAllowed();
	}

	public int getRows() {
		return stage.getMaxRowPositionAllowed();
	}
	
	private String paintElement(String element, Colorable elemColorable){
		if(elemColorable.getColor().equals(Color.BLACK)){
			element = TerminalColors.BLACK_BRIGHT + element + TerminalColors.RESET; 
		}
		if(elemColorable.getColor().equals(Color.BLUE)){
			element = TerminalColors.BLUE_BRIGHT + element + TerminalColors.RESET; 
		}
		if(elemColorable.getColor().equals(Color.GREEN)){
			element = TerminalColors.GREEN + element + TerminalColors.RESET; 
		}
		if(elemColorable.getColor().equals(Color.RED)){
			element = TerminalColors.RED_BRIGHT + element + TerminalColors.RESET; 
		}
		return element;
	}

	public String getElement(int i, int j) {
		String element = " ";
		Player player = stage.getPlayer();
		Position playerPosition = player.getPosition();
		if (playerPosition.getRow() == i && playerPosition.getCol() == j) {
			String imagePlayer = PlayerPresenter.SKIN_THIEF;
			if (player.getStyle().equals(Style.DWARF)) {
				imagePlayer = PlayerPresenter.SKIN_DWARF;
			}
			if (player.getStyle().equals(Style.MAGE)) {
				imagePlayer = PlayerPresenter.SKIN_MAGE;
			}
			if (player.getStyle().equals(Style.WARRIOR)) {
				imagePlayer = PlayerPresenter.SKIN_WARRIOR;
			}
			imagePlayer = paintElement(imagePlayer, player);
			element = imagePlayer;
		}

		Optional<Enemy> enemy = stage.getEnemies().stream().filter((e) -> {
			if (e.getPosition().getRow() == i && e.getPosition().getCol() == j) {
				return true;
			} else {
				return false;
			}
		}).findFirst();
		if (enemy.isPresent()) {
			element = "X";
			if (enemy.get().getStyle().equals(Style.ENEMY_VOGON) && enemy.get().isAlive()) {
				element = "V";
				element = paintElement(element, enemy.get());
			}
			
		}
		
		return element;
	}

	public static StagePresenter loadGame(String playerName) {
		return new StagePresenter(new MenuService().recovery(playerName));

	}

	public boolean isClear() {
		long totalEnemies = this.stage.getEnemies().stream().filter((e) -> {
			if (e.isAlive()) {
				return true;
			} else {
				return false;
			}
		}).count();
		if (totalEnemies <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveEnemies(){
		stage.getEnemies().forEach((e) -> {
			Random rnd = new Random();
			int result = rnd.nextInt(5);
			if(result == 1){
				explorationService.move(this.stage, e, Direction.UP);
			}
			if(result == 2){
				explorationService.move(this.stage, e, Direction.DOWN);
			}
			if(result == 3){
				explorationService.move(this.stage, e, Direction.RIGHT);
			}
			if(result == 4){
				explorationService.move(this.stage, e, Direction.LEFT);
			}
			
		});
	}
	
	public int checkIfPlayerGetHit(){
		return combatService.checkIfPlayerGetHit(this.stage);
	}

}
