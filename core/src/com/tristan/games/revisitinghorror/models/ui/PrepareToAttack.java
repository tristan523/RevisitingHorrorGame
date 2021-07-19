package com.tristan.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.models.ImageBase;

public class PrepareToAttack extends ImageBase {
	private GameEventManager _gameEventManager;
	private float _visibilityCounter = 0.0f;
	
	public String getGameEventType() {
		
		return "PREPARE_TO_ATTACK";
	}

	public PrepareToAttack(Texture texture, GameEventManager gameEventManager) {
		super(texture);
		this.initialize(texture, gameEventManager);
		this.addEventHandlers();
	}
	
	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
	}

	private void addEventHandlers() {
		Gdx.app.log("PrepareToAttack", "addEventHandlers(), registering event handlers.");
		this._gameEventManager.addImageEventListener(this);
		
	}

	@Override
	public void act(float delta) {
		if (this.isVisible() == true) {
			this._visibilityCounter += delta;
			
			Gdx.app.log("PrepareToAttack", "In act(), visibility counter: " + this._visibilityCounter);
			
			if (this._visibilityCounter > 10) {
				this.setVisible(false);
				
				this.handle(null);
			}
		}
	}
	

}
