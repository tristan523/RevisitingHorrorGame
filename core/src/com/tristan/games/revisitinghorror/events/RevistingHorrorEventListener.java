package com.tristan.games.revisitinghorror.events;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;

public interface RevistingHorrorEventListener extends EventListener {
	
	public void onEvent(GameEventAbstract gameEvent);
	
}
