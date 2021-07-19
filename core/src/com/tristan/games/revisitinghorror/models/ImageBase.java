package com.tristan.games.revisitinghorror.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tristan.games.revisitinghorror.events.GameEventListener;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;

public class ImageBase extends Image implements GameEventListener {
	private String _name;
	
	public String getGameEventType() {
		return "";
	}
	
	public void setName(String name) {
		this._name = name;
	}
	
	public String getName() {
		return this._name;
	}
	
	public static String GAME_EVENT_TYPE = "";
	
	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		
	}
	
	@Override
	public boolean handle(Event event) {
		return false;
	}
	
	public ImageBase(Texture texture) {
		super(texture);
	}
}
