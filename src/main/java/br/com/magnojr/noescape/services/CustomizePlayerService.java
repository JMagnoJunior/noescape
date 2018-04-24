package br.com.magnojr.noescape.services;

import br.com.magnojr.noescape.exceptions.InvalidCharacterException;
import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;

public class CustomizePlayerService {

	public Player createPlayerCharacter(Color color, String name, Skill skill, Style style, Weapon weapon) throws InvalidCharacterException {
		Player p1 = new Player(color, "player1", skill, style, weapon);
		return p1;
	}
	
	// TODO : Edit a existent player

}
