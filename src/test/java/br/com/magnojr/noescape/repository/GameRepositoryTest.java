package br.com.magnojr.noescape.repository;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;
import br.com.magnojr.noescape.models.Color;
import br.com.magnojr.noescape.models.Player;
import br.com.magnojr.noescape.models.Skill;
import br.com.magnojr.noescape.models.Stage;
import br.com.magnojr.noescape.models.Style;
import br.com.magnojr.noescape.models.Weapon;
import br.com.magnojr.noescape.repository.GameRepository;

public class GameRepositoryTest {

	final String SLOT_NAME = "slot1";
	
	@Before
	public void setUp() {
		try {
			Path path = FileSystems.getDefault().getPath("", SLOT_NAME);
			Files.delete(path);
		} catch (NoSuchFileException x) {

		} catch (DirectoryNotEmptyException x) {

		} catch (IOException x) {

		}
	}

	private Player getNewPlayer() throws Exception {
		Color color = Color.BLACK;
		Style style = Style.MAGE;
		Weapon weapon = Weapon.STAFF;
		Skill skill = new Skill(2, 3, 5);
		String name = "player1";

		return new Player(color, name, skill, style, weapon);
	}

	@Test
	public void shouldSaveAStage() throws  Exception {
		
		
		Stage stage1 = Stage.createStage1(getNewPlayer());

		GameRepository repository = new GameRepository();
		repository.save(stage1, SLOT_NAME);
		
		assertTrue(new File(SLOT_NAME + GameRepository.FILE_TYPE).exists());

	}

	@Test
	public void shouldGetAStageSaved() throws  Exception{

		Stage stage1 = Stage.createStage1(getNewPlayer());
		GameRepository repository = new GameRepository();

		repository.save(stage1, SLOT_NAME);

		Stage stageSaved = repository.get(SLOT_NAME);
		assertTrue(stage1.equals(stageSaved));

	}

	@After
	public  void tearDown() {
		try {
			Path path = FileSystems.getDefault().getPath("", SLOT_NAME);
			Files.delete(path);
		} catch (NoSuchFileException x) {

		} catch (DirectoryNotEmptyException x) {

		} catch (IOException x) {

		}
	}

}
