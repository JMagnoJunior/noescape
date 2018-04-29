package br.com.magnojr.noescape.services;

import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.repository.GameRepository;
import br.com.magnojr.noescape.repository.GameRepositoryFile;

public class MenuService {

	private GameRepository repository = (GameRepository) new GameRepositoryFile();

	public MenuService() {
	}

	public MenuService(GameRepository gameRepository) {
		this.repository = gameRepository;
	}
	
	public Stage startANewGame(Player player) throws SkillNotBalancedException{
		return Stage.createStage1(player);
	}

	public void save(Stage stage, String slot) {
		repository.save(stage, slot);
	}

	public Stage recovery(String slot) {
		return repository.get(slot);
	}

}
