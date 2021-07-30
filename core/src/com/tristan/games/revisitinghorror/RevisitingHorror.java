package com.tristan.games.revisitinghorror;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tristan.games.revisitinghorror.screens.GameScreen;
import com.tristan.games.revisitinghorror.screens.MainMenuScreen;
import com.tristan.games.revisitinghorror.screens.ShopScreen;

public class RevisitingHorror extends Game {
	public SpriteBatch _spriteBatch;
	public BitmapFont horrorTitlefont;
	public BitmapFont AncientYellowFont;
	
	// Constants
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 1024;
	
	// Members (getters and setters here)
	public SpriteBatch getSpriteBatch() {
		return this._spriteBatch;
	}
	
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		horrorTitlefont = new BitmapFont(Gdx.files.internal("fonts/parchment.fnt"));
		AncientYellowFont = new BitmapFont(Gdx.files.internal("fonts/ancient_yellow.fnt"));
		
		_spriteBatch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		
	}
	
	@Override
	public void dispose () {
		_spriteBatch.dispose();
		horrorTitlefont.dispose();
		AncientYellowFont.dispose();
	}

	public void gotoMainMenuScreen () {
		MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
		
		this.setScreen(mainMenuScreen);
	}
	
	public void gotoGameScreen() {
		GameScreen gameScreen = new GameScreen(this);
		
		this.setScreen(gameScreen);
	}
	
	public void gotoShopScreen() {
		ShopScreen shopScreen = new ShopScreen(this);
		
		this.setScreen(shopScreen);
	}
}
