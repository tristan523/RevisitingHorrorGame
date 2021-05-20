package com.tristan.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tristan.games.revisitinghorror.RevisitingHorror;

public class MainMenuScreen implements Screen {
	private Stage _mainMenuStage;
	private final RevisitingHorror _revisitingHorrorGame;
	private FitViewport _viewport;
	
	public MainMenuScreen(RevisitingHorror game) {
		this._revisitingHorrorGame = game;
		
	}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror. SCREEN_HEIGHT);
		
		this._mainMenuStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());
		
		Gdx.input.setInputProcessor(this._mainMenuStage);
		

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		this._mainMenuStage.act(Gdx.graphics.getDeltaTime());
		
		
		this._mainMenuStage.getBatch().begin();
		this._revisitingHorrorGame.horrorTitlefont.draw(this._mainMenuStage.getBatch(), "Revisiting Horror! ! ", 500, 650);
		this._revisitingHorrorGame.horrorTitlefont.draw(this._mainMenuStage.getBatch(), "tap anywhere to start)", 100, 100);
		this._mainMenuStage.getBatch().end();
		
		this._mainMenuStage.draw();
		
		if(Gdx.input.isTouched()==true) {
			this._revisitingHorrorGame.setScreen(new GameScreen(this._revisitingHorrorGame));
			this.dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		this._viewport.update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this._mainMenuStage.dispose();

	}

}
