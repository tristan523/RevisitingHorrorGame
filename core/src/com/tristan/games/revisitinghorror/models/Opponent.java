package com.tristan.games.revisitinghorror.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tristan.games.revisitinghorror.events.GameEventManager;

public class Opponent extends Actor {
	
	private Sprite _sprite;
	private GameEventManager _gameEventManager;
	
	public Opponent(Texture texture, final String actorName, GameEventManager gameEventManager) {
		this.initialize(texture, gameEventManager);

//		this.addEventHandlers(actorName);
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this.setSprite(new Sprite(texture));
		
		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());
		
		this._gameEventManager = gameEventManager;
	}

	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);
		
		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());
		
		
	}
	
	public Sprite getSprite() {
		return _sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}
}
