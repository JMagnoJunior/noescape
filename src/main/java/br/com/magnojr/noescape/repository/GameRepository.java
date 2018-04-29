package br.com.magnojr.noescape.repository;

import br.com.magnojr.noescape.models.Stage;

public interface GameRepository {

	public void save(Stage stage, String slot);
	
	public Stage get(String slot);
}
