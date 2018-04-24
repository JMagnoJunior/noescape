package br.com.magnojr.noescape.models;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.magnojr.noescape.exceptions.SkillNotBalancedException;
import br.com.magnojr.noescape.models.Skill;

public class SkillTest {

	@Test
	public void shouldCreateANewSkill_whenTheSumOfAttributesEqualsTo10() throws SkillNotBalancedException {
		Skill skill = new Skill(2,3,5);
		assertNotNull(skill);
	}
	
	@Test(expected = SkillNotBalancedException.class)
	public void shouldNotCreateANewSkill_whenTheSumOfAttributesLowerThan10() throws SkillNotBalancedException {
		Skill skill = new Skill(1,3,5);
		assertNotNull(skill);
	}
	
	@Test(expected = SkillNotBalancedException.class)
	public void shouldNotCreateANewSkill_whenTheSumOfAttributesHigherThan10() throws SkillNotBalancedException {
		Skill skill = new Skill(3,3,5);
	}

}
