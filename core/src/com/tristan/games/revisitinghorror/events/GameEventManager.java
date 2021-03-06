package com.tristan.games.revisitinghorror.events;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.tristan.games.revisitinghorror.Player;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;
import com.tristan.games.revisitinghorror.models.ImageBase;
import com.tristan.games.revisitinghorror.models.ui.PrepareToAttack;

public class GameEventManager {
	
	/***FIELDS***/
	public boolean IsCountDown = false;
	private ArrayList<ImageBase> _imageEventListeners = new ArrayList<ImageBase>();

	private Object ImageEventListener;

	public void addEventListener(Player player) {
		// TODO Auto-generated method stub
	}
	
	public void broadcastEvent1(GameEventAbstract gameEvent) {
		
	}
	public void addImageEventListener(ImageBase image) {
		//1. is this listener registered ALREADY?
		boolean alreadyExists = false;
		
		for (int counter = 0; counter < this._imageEventListeners.size(); counter++) {
			
			if (ImageBase.GAME_EVENT_TYPE == this._imageEventListeners.get(counter).getGameEventType()) {
				alreadyExists = true;
				
				break;
			}
		}
		
		if (alreadyExists == false) {
			Gdx.app.log("GameEventManager",
					"In addImageEventListener(), adding listener for event type: " + ImageBase.GAME_EVENT_TYPE);
				
				this._imageEventListeners.add(image);
			}
		}
	
	public void broadcastEvent(GameEventAbstract gameEvent) {
		
		Gdx.app.log("GameEventManager",
				String.format("In broadcastEvent(), dispatching event notifications for game event type: %s.",
						gameEvent.getGameEventType()));
		for (int counter = 0; counter < this._imageEventListeners.size(); counter++) {
			if (gameEvent.getGameEventType() == this._imageEventListeners.get(counter).getGameEventType()) {
				Gdx.app.log("GameEventManager",
						String.format("In broadcastEvent(), dispatching image event notification for: %s.",
								this._imageEventListeners.get(counter).getClass().toString()));

				this._imageEventListeners.get(counter).onEvent(gameEvent);
				
				if (gameEvent.isHandled() == true) {
					Gdx.app.log("GameEventManager", "In broadcastEvent(), event handled.  Now, stopping event");

					gameEvent.stop();
				}
			}
		}
	}
	}

