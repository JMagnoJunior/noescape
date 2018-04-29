package br.com.magnojr.noescape.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.repository.GameRepositoryFile;

public class MenuServiceTest {

	final String SLOT_NAME = "slot1";
	
	private Player getNewPlayer() throws  Exception{
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(2, 3, 5);
		String name = "player1";

		return new Player(color, name, skill, style, weapon);
	}
	
	GameRepositoryFile gameRepository = Mockito.mock(GameRepositoryFile.class);
	
	@Test
	public void shouldSaveTheGame() throws  Exception{
		
		Mockito.doNothing().when(gameRepository).save(Mockito.isA(Stage.class), Mockito.isA(String.class)) ;
		Player player = getNewPlayer();
		Stage stage = Stage.createStage1(player);
		gameRepository.save(stage, SLOT_NAME);
		Mockito.verify(gameRepository, Mockito.times(1)).save(stage, SLOT_NAME);
				
	}

}
