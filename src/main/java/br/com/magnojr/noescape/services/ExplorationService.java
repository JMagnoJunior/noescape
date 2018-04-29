package br.com.magnojr.noescape.services;

import br.com.magnojr.noescape.models.Direction;
import br.com.magnojr.noescape.models.Movable;
import br.com.magnojr.noescape.models.Stage;

public class ExplorationService {

	public void move(Stage stage1, Movable movable, Direction direction) {
		movable.move(stage1, direction);
	}

}
