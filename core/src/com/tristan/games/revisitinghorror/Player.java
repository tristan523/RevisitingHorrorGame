package com.tristan.games.revisitinghorror;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tristan.games.revisitinghorror.events.GameEventListener;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Actor implements GameEventListener {
	private String _name;
	private String _keyAbility;
	private String _hairColor;
	private GameEventManager _gameEventManager;
	private String _gameEventType;
	private Sprite _sprite;
	private int _stamina;
	private float _deltaX;
	private boolean _leftMove;
	private boolean _rightMove;

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

	public Sprite getSprite() {
		return _sprite;
	}

	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}

	public float getDeltaX() {
		return _deltaX;
	}

	public void setDeltaX(float speed) {
		this._deltaX = speed;
	}

	public Player(Texture texture, final String actorName, GameEventManager gameEventManager) {
		this.initialize(texture, gameEventManager);

		this.addEventHandlers(actorName);
	}

	@Override
	public void act(float delta) {
		super.act(Gdx.graphics.getDeltaTime());
		
		if (this._leftMove == true) {
			this.getSprite().translateX(-this.getDeltaX() * Gdx.graphics.getDeltaTime());
		}
		
		if (this._rightMove == true) {
			this.getSprite().translateX(this.getDeltaX() * Gdx.graphics.getDeltaTime());
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// Gdx.app.log("Player", "In draw(), updating player image");
		
		this.getSprite().draw(batch);
	}
	
	
	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this.setDeltaX(100f);

		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());
		
		this._gameEventManager = gameEventManager;
	}

	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());

		this.setTouchable(Touchable.enabled);
	}

	private void addEventHandlers(String actorName) {
		Gdx.app.log("Player", "addEventHandlers(), registering event handlers.");

		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keyCode) {
				Gdx.app.log("Player", "keyDown(), key down event occurred for ");

				handleMovement(keyCode);

				return true;
			}
		});

		this.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keyCode) {
				Gdx.app.log("Player", "keyUp(), key up event occurred for ");

				// player has lifted up on movement key
				// KILL movement!
				if (_leftMove == true) {
					setLeftMove(false);
				}

				if (_rightMove == true) {
					setRightMove(false);
				}

				return true;
			}
		});

		this._gameEventManager.addEventListener(this);
	}

	public void setLeftMove(boolean isLeftMove) {
		if (this._rightMove && isLeftMove) {
			this._rightMove = false;
		}

		this._leftMove = isLeftMove;
	}

	public void setRightMove(boolean isRightMove) {
		if (this._leftMove && isRightMove) {
			this._leftMove = false;
		}

		this._rightMove = isRightMove;
	}

	/**
	 * Reference:*https://
	 * docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_Z
	 **
	 * 
	 * @param keyCode
	 */
	private void handleMovement(int keyCode) {
		// 'd'
		if (keyCode == Input.Keys.D || keyCode == Input.Keys.RIGHT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the right");

			this.setRightMove(true);
		} else if (keyCode == Input.Keys.A || keyCode == Input.Keys.LEFT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the left");

			this.setLeftMove(true);
		}

//						Gdx.app.log("Player", "Player position: " + this.getSprite().getX());
	}

	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}
}
