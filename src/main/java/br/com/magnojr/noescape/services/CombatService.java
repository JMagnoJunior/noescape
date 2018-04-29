package br.com.magnojr.noescape.services;

import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Stage;

public class CombatService {

	public int attack(Stage stage, Direction direction) {
		Player player = stage.getPlayer();
		return player.atack(stage, direction);
		
	}
	
	public int checkIfPlayerGetHit(Stage stage){
		Player player = stage.getPlayer();
		stage.getEnemies().forEach((e) -> {
			if(e.getPosition().equals(player.getPosition())){
				player.getHit();
			}
		});
		return player.getCurrentLife();
	}

	
	
}
