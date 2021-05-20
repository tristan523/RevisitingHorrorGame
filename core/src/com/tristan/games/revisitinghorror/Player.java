package com.tristan.games.revisitinghorror;

import com.badlogic.gdx.graphics.Texture;
import com.tristan.games.revisitinghorror.events.GameEventManager;

public class Player {
	private String _name;
	private String _keyAbility;
	private String _hairColor;
	
	public String getName() {
		return _name;
	}
	
	public void setName(String value) {
		this._name = value;
	}
	
	public String getKeyAbility() {
		return this._keyAbility;
	}
	
	public void setKeyAbility(String value) {
		this._keyAbility = value;
	}
	
	public String gethairColor() {
		return this._hairColor;
	}
	
	public void sethairColor(String value) {
		this._hairColor = value;
	}

	public Player(Texture texture, final String actorName, GameEventManager gameEventManager) {
		this.initialize(texture, gameEventManager);
		
		this.addEventHandlers(actorName);
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		// TODO Auto-generated method stub
		
	}

	private void addEventHandlers(String actorName) {
		// TODO Auto-generated method stub
		
	}
}
