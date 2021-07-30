package com.tristan.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.tristan.games.revisitinghorror.RevisitingHorror;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;
import com.tristan.games.revisitinghorror.events.game.GameEventCountDown;
import com.tristan.games.revisitinghorror.models.ImageBase;

public class CountDownFive extends ImageBase{

	private GameEventManager _gameEventManager;
	private GameEventAbstract _countDownFiveEvent;
	private float _scaleCounter = 0.05f;
	
	@Override
	public String getGameEventType() {
		return "COUNT_DOWN_5";
	}

	public CountDownFive(Texture texture, GameEventManager gameEventManager ) {
		super(texture);
        
		this.initialize(texture, gameEventManager);
		
		this.addEventHandlers();
	}

	private void addEventHandlers() {
		Gdx.app.log("CountDownFive", "addEventHandlers(), registering event handlers.");
		
		this._gameEventManager.addImageEventListener(this);
		
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "COUNT_DOWN_5";
	}
	
	/*** EVENT HANDLERS ***/
	
	@Override
	public void onEvent(GameEventAbstract countDownEvent) {
		Gdx.app.log("CountDownFive",
			String.format("In onEvent(), event %s occurred.", countDownEvent.getGameEventType()));
		
		if(countDownEvent.getGameEventType() == getGameEventType()) {
			Gdx.app.log("CountDownFive", String.format(
					"In onEvent(), event %s occurred. doCountDown() will execute now.", getGameEventType()));
	
			this._countDownFiveEvent = countDownEvent;
			
			this.doCountDown(countDownEvent);
	
		}
	}
		
	@Override
	public void act(float delta) {
		if(this.getScaleX() < 5 && this.isVisible() == true) {
			Gdx.app.log("CountDownFive", "Scaling up count down number");
			
			this.scaleBy(this._scaleCounter);
			
			Gdx.app.debug("CountDownFive", String.format("Scaling up count down number. scale %f", this.getScaleX()));
			
		} else if (this.isVisible() == true) {
			this.setVisible(false);
			
			this.handle(this._countDownFiveEvent);
		}
	}
	
	@Override
	public boolean handle(Event event) {
		Gdx.app.log("CountDownFive", String.format("handled event: %s", event.getTarget()));
		
		CountDownFour.GAME_EVENT_TYPE = "COUNT_DOWN_4";
		
		//1. instantiate the event
		GameEventCountDown gameEventCountDown = new GameEventCountDown(CountDownFour.GAME_EVENT_TYPE);
		gameEventCountDown.Level = 1;
		gameEventCountDown.setStage(this.getStage());
		
		//2. broadcast the event
		this._gameEventManager.broadcastEvent(gameEventCountDown);
		
		return true;
	}
	

	private void doCountDown(GameEventAbstract countDownEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
		
	}
}
