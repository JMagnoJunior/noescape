package br.com.magnojr.noescape.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.magnojr.noescape.exceptions.InvalidCharacterException;
import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.services.CustomizePlayerService;



public class CustomizePlayerServiceTest {

	@Test
	public void shouldReturnANewMage_whenCreatingANewMageWithStaff() throws  Exception{
		// GIVEN
		CustomizePlayerService service = new CustomizePlayerService();
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(2,3,5);
		String name = "player1";
			
		// WHEN
		Player p1 = service.createPlayerCharacter(color, name, skill, style, weapon);
		
		//THEN
		 assertNotNull(p1);
		 assertEquals(p1.getColor(), Color.BLACK);
		 assertEquals(p1.getStyle(), Style.MAGE);
		 assertEquals(p1.getWeapon(), Weapon.STAFF);
		 assertEquals(p1.getSkill(), skill);		
	}
	
	@Test(expected = InvalidCharacterException.class)
	public void shouldReturnAError_whenCreatingANewMageWithSword() throws  Exception{ 
		// GIVEN
		CustomizePlayerService service = new CustomizePlayerService();
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.SWORD;
		Skill skill = new Skill(2,3,5);
		String name = "player1";
			
		// WHEN
		Player p1 = service.createPlayerCharacter(color, name, skill, style, weapon);
		
		//THEN throw exception
	}
	
	@Test(expected = InvalidCharacterException.class)
	public void shouldReturnAError_whenCreatingANewWarriorWithStaff() throws  Exception{
		// GIVEN
		CustomizePlayerService service = new CustomizePlayerService();
		Color color = Color.BLACK;
		Style style = Style.WARRIOR;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(3,6,1);
		String name = "player1";
			
		// WHEN
		Player p1 = service.createPlayerCharacter(color, name, skill, style, weapon);
		
		//THEN throw exception
		
	}

}
