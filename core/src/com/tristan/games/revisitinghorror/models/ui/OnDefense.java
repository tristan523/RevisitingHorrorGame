package com.tristan.games.revisitinghorror.models.ui;

import com.badlogic.gdx.graphics.Texture;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.models.ImageBase;

public class OnDefense extends ImageBase {
	public OnDefense(Texture texture) {
		super(texture);
		// TODO Auto-generated constructor stub
	}

	private GameEventManager _gameEventManager;
	private float _visibilityCounter = 0.0f;
	
	public String getGameEventType() {
		
		return "ON_DEFENSE";
	}

}
