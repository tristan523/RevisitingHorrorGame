package com.tristan.games.revisitinghorror.screens;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tristan.games.revisitinghorror.RevisitingHorror;
import com.tristan.games.revisitinghorror.assetManager.RevisitingHorrorAssetDescriptor;
import com.tristan.games.revisitinghorror.inventory.PlayerInventory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShopScreen implements Screen {
	
private AssetManager _assetManager;
private Gson _gson;
private RevisitingHorror _revisitingHorrorGame;
private FitViewport _viewport;
private Stage _shopScreenStage;
private Image _dailyAwardButton;
private Label _lblTotalGold;
private PlayerInventory _playerinventory;

public ShopScreen(final RevisitingHorror revisitingHorrorGame) {
	this._assetManager = new AssetManager();
	
	this._revisitingHorrorGame = revisitingHorrorGame;
	
	this.initialize();
}

	private void initialize() {
		
		Gdx.app.log("ShopScreen", "In initialize(),");
	this._gson = new GsonBuilder().setPrettyPrinting().create();
	
	try {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("../data/user_data.json"));
		
		if (bufferedReader != null) {
			this._playerinventory = this._gson.fromJson(bufferedReader, PlayerInventory.class);
			
			Label.LabelStyle ancientYellowStyle = new Label.LabelStyle();
			ancientYellowStyle.font = this._revisitingHorrorGame.AncientYellowFont;
			// ancientYellowStyle.fontColor = Color.RED;
			
			this._lblTotalGold = new Label(String.valueOf(this._playerinventory.getTotalGold()),
					ancientYellowStyle);
			this._lblTotalGold.setPosition(1110, 980);
			this._lblTotalGold.setAlignment(Align.right);
			this._lblTotalGold.setSize(28, 28);
		}
		bufferedReader.close();
	} catch (Exception ex) {
		Gdx.app.log("ShopScreen", "In initialize(),system ran into an exception."  +ex.getMessage());
	}
	
}

	@Override
	public void show() {
		Gdx.app.log("ShopScreen", "In show(),");
		
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);
		
		this._shopScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());
		
		Gdx.input.setInputProcessor(this._shopScreenStage);
		
		this.loadAssets();
		
		this.loadActors();
	}

	private void loadActors() {
		Gdx.app.log("ShopScreen", "In loadActors(),");
		
		Image shopScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.shopScreen));
	    this._dailyAwardButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.dailyAwardButton));
	    this._dailyAwardButton.setPosition(500, 500);
	    this._dailyAwardButton.addListener(new InputListener() {
	    		@Override
	    		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    			collectdailyAward();
	    			
	    			return true;
	    		}
	    });
	    Image backButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.backButton));
	    backButton.setPosition(0,970);
	    backButton.addListener(new InputListener() {
	    	@Override
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		_revisitingHorrorGame.gotoMainMenuScreen();
	    		
	    		return true;
	    	}
		});
	    
	    Image totalGoldBackground = new Image(
	    		this._assetManager.get(RevisitingHorrorAssetDescriptor.goldTotalBackground));
	    totalGoldBackground.setPosition(1000, 970);
	    
	    this._shopScreenStage.addActor(shopScene);
	    this._shopScreenStage.addActor(_dailyAwardButton);
	    this._shopScreenStage.addActor(totalGoldBackground);
	    this._shopScreenStage.addActor(this._lblTotalGold);
	    this._shopScreenStage.addActor(backButton);
		
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shopScreen);
    	this._assetManager.load(RevisitingHorrorAssetDescriptor.dailyAwardButton);
        this._assetManager.load(RevisitingHorrorAssetDescriptor.goldTotalBackground);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.backButton);
		
		this._assetManager.finishLoading();
	}

	private void collectdailyAward() {
		this._dailyAwardButton.setVisible(false);
		
		int dailyAward = 15;
		
		int totalGold = this._playerinventory.getTotalGold() + dailyAward;
		
		this._playerinventory.setTotalGold(totalGold);
		
		Gdx.app.log("ShopScreen",
				"In collectDailyAward(), playerInventory - totalGold: " + this._playerinventory.getTotalGold());
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.6f, 0.4f, 0.0f, 1);
		if (this._lblTotalGold.textEquals(String.valueOf(this._playerinventory.getTotalGold())) == false) {
			this._lblTotalGold.setText(this._playerinventory.getTotalGold());
		}
		this._shopScreenStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
