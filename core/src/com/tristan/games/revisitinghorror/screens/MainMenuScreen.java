package com.tristan.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tristan.games.revisitinghorror.RevisitingHorror;
import com.tristan.games.revisitinghorror.assetManager.RevisitingHorrorAssetDescriptor;

public class MainMenuScreen implements Screen {
	private Stage _mainMenuStage;
	private final RevisitingHorror _revisitingHorrorGame;
	private final AssetManager _assetManager;
	private FitViewport _viewport;
	
	public MainMenuScreen(RevisitingHorror game) {
		this._revisitingHorrorGame = game;
		
		this._assetManager = new AssetManager();
		
	}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror. SCREEN_HEIGHT);
		
		this._mainMenuStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());
		
		Gdx.input.setInputProcessor(this._mainMenuStage);
		
		this.loadAssets();
		
		this.loadActors();
	
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
		
		// if(Gdx.input.isTouched()==true) {
			//this._revisitingHorrorGame.setScreen(new GameScreen(this._revisitingHorrorGame));
			// this.dispose();
		}
//	}

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
	
	private void loadActors() {
		Gdx.app.log("MainMenuScreen", "In loadActors(),");
		
		Image shopButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.shopButton));
		shopButton.setPosition(700, 200);
		shopButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_revisitingHorrorGame.gotoShopScreen();
				
				return true;
			}
		});
		Image playButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.playButton));
		playButton.setPosition(450, 202);
		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_revisitingHorrorGame.gotoGameScreen();
				
				return true;
			}
		}
		);

		
		this._mainMenuStage.addActor(shopButton);
		this._mainMenuStage.addActor(playButton);
	}
		
		private void loadAssets() {
			this._assetManager.load(RevisitingHorrorAssetDescriptor.shopButton);
			this._assetManager.load(RevisitingHorrorAssetDescriptor.shopScreen);
			this._assetManager.load(RevisitingHorrorAssetDescriptor.playButton);
			
			this._assetManager.finishLoading();
		}
	}