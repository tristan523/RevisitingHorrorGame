package com.tristan.games.revisitinghorror.screens;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tristan.games.revisitinghorror.Player;
import com.tristan.games.revisitinghorror.RevisitingHorror;
import com.tristan.games.revisitinghorror.assetManager.RevisitingHorrorAssetDescriptor;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;
import com.tristan.games.revisitinghorror.events.game.GameEventCountDown;
import com.tristan.games.revisitinghorror.events.game.GameEventPrepareToAttack;
import com.tristan.games.revisitinghorror.inventory.PlayerInventory;
import com.tristan.games.revisitinghorror.models.Opponent;
import com.tristan.games.revisitinghorror.models.ui.CountDownFive;
import com.tristan.games.revisitinghorror.models.ui.CountDownFour;
import com.tristan.games.revisitinghorror.models.ui.CountDownOne;
import com.tristan.games.revisitinghorror.models.ui.CountDownThree;
import com.tristan.games.revisitinghorror.models.ui.CountDownTwo;
import com.tristan.games.revisitinghorror.models.ui.OnDefense;
import com.tristan.games.revisitinghorror.models.ui.PrepareToAttack;

public class GameScreen implements Screen {
	private final AssetManager _assetManager;
	private boolean _startCountDown;
	private final RevisitingHorror _revisitingHorrorGame;
	private Texture _backgroundTexture;
	private BitmapFont _countDownFont;
	private Player _katniss;
	private Opponent _cato;
	
	private Stage _gameScreenStage;
	private Viewport _viewport;
	private GameEventManager _gameEventManager;
	private int _playerAttackFactor = 0;
	private GameEventOnDefense _gameEventOnDefense;
	private GameEventPrepareToAttack _gameEventPrepareToAttack;
	private PrepareToAttack _prepareToAttack;
	private OnDefense _onDefense;
	private Gson _gson;
	private PlayerInventory _playerInventory;
	private Label _lblTotalGold;
	private CountDownFive _countDownFive;
	private CountDownFour _countDownFour;
	private CountDownThree _countDownThree;
	private CountDownTwo _countDownTwo;
	private CountDownOne _countDownOne;
	private GameEventCountDown _gameEventCountDown;

	public GameScreen(final RevisitingHorror revisitingHorrorGame) {
			this._startCountDown = true;
			
			this._assetManager = new AssetManager();
			
			this._gameEventManager = new GameEventManager();
			
			this._revisitingHorrorGame = revisitingHorrorGame;
			
			this.initialize();
		
		}

	private void initialize() {
		
		Gdx.app.log("GameScreen", "In initialize(),");
		this._gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("../data/user_data.json"));
			
			if (bufferedReader != null) {
				this._playerInventory = this._gson.fromJson(bufferedReader, PlayerInventory.class);
				
				Label.LabelStyle ancientYellowStyle = new Label.LabelStyle();
				ancientYellowStyle.font = this._revisitingHorrorGame.AncientYellowFont;
				// ancientYellowStyle.fontColor = Color.RED;
				
				this._lblTotalGold = new Label(String.valueOf(this._playerInventory.getTotalGold()),
						ancientYellowStyle);
				this._lblTotalGold.setPosition(1110, 980);
				this._lblTotalGold.setAlignment(Align.right);
				this._lblTotalGold.setSize(28, 28);
				
				Gdx.app.log("GameScreen",
						"In initialize(), userData totalGold: "  + this._playerInventory.getTotalGold());
			} else {
				Gdx.app.error("GameScreen", "In initialize(), could not find user Data.");
			}
			
			bufferedReader.close();
		} catch (Exception ex) {
			Gdx.app.log("GameScreen", "In initialize(), system ran into exception:" + ex.getMessage());
			}
		}
		
	

	@Override
	public void show() {
		
		Gdx.app.log("GameScreen", "In show(),");
	
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);
		
		this._gameScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());
		
		Gdx.input.setInputProcessor(this._gameScreenStage);
		
		this.loadAssets();
		
		this.loadActors();

	}

	private void loadActors() {
		Image battleScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene));
		
		Image totalGoldBackground = new Image(
				this._assetManager.get(RevisitingHorrorAssetDescriptor.goldTotalBackground));
		totalGoldBackground.setPosition(1000, 970);
		
		this. _katniss = new Player(this._assetManager.get(RevisitingHorrorAssetDescriptor.player),
				RevisitingHorrorAssetDescriptor.player.fileName, this._gameEventManager);
		this. _katniss.spritePosition(500, 200);
		
		this._cato = new Opponent(this._assetManager.get(RevisitingHorrorAssetDescriptor.opponent),
				RevisitingHorrorAssetDescriptor.opponent.fileName, this._gameEventManager);
		_cato.spritePosition(200, 400);
		
		battleScene.setName("battleScene");
		_katniss.setName("katniss");
		this._cato.setName("cato");
		
		this._countDownOne = new CountDownOne(this._assetManager.get(RevisitingHorrorAssetDescriptor.one),
				this._gameEventManager);
		this._countDownOne.setVisible(false);
		
		this._countDownTwo = new CountDownTwo(this._assetManager.get(RevisitingHorrorAssetDescriptor.two),
				this._gameEventManager);
		this._countDownTwo.setVisible(false);
		
		this._countDownThree = new CountDownThree(this._assetManager.get(RevisitingHorrorAssetDescriptor.three),
				this._gameEventManager);
		this._countDownThree.setVisible(false);
		
		this._countDownFour = new CountDownFour(this._assetManager.get(RevisitingHorrorAssetDescriptor.four),
				this._gameEventManager);
		this._countDownFour.setVisible(false);
	
		this._countDownFive = new CountDownFive(this._assetManager.get(RevisitingHorrorAssetDescriptor.five),
				this._gameEventManager);
		this._countDownFive.setVisible(false);
		
		this._gameScreenStage.addActor(battleScene);
		this._gameScreenStage.addActor(this._katniss);
		this._gameScreenStage.addActor(totalGoldBackground);
		this._gameScreenStage.addActor(this._lblTotalGold);
		this._gameScreenStage.setKeyboardFocus(this._katniss);
		this._gameScreenStage.addActor(this._countDownOne);
		this._gameScreenStage.addActor(this._countDownTwo);
		this._gameScreenStage.addActor(this._countDownThree);
		this._gameScreenStage.addActor(this._countDownFour);
		this._gameScreenStage.addActor(this._countDownFive);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.player);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.opponent);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.battleScene);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.goldTotalBackground);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.one);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.two);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.three);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.four);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.five);
		this._assetManager.finishLoading();
	}
	
	private void prioritizeAttack() {
		Gdx.app.log("GameScreen", "In startCountdown(), showing, 'Prepare To Attack' message.");
		
		// 1. roll dice to calculate who goes first
		int playerDice = (int) (Math.random() * 100);
		
		if (playerDice + this._playerAttackFactor  > 49) {
			this._gameEventPrepareToAttack = new GameEventPrepareToAttack(this._prepareToAttack.getGameEventType());
			
			this._gameEventManager.broadcastEvent(_gameEventPrepareToAttack);
		} else {
			
			this._gameEventOnDefense = new GameEventOnDefense(this._onDefense.getGameEventType());
			
			this._gameEventManager.broadcastEvent(_gameEventOnDefense);
		}
	}

	@Override
	public void render(float delta) {
		
		// start intro of game
		if (this._startCountDown == true) {
			this._startCountDown = false;
			
			this._startCountDown(1);
		}
		
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		this._gameScreenStage.act(Gdx.graphics.getDeltaTime());
		
//		this._gameScreenStage.getBatch().begin();
		
//		Texture battleScreenBackground = (Texture) this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene);
//		this._gameScreenStage.getBatch().disableBlending();
		this._gameScreenStage.draw();
	}

	private void _startCountDown(int level) {
		Gdx.app.log("GameScreen", "In startCountdown(), starting countdown to battle");
		
		//1. instantiate the event
		this._gameEventCountDown = new GameEventCountDown(this._countDownFive.getGameEventType());
		this._gameEventCountDown.Level = level;
		this._gameEventCountDown.setStage(this._gameScreenStage);
		
		//2. broadcast the event
		this._gameEventManager.broadcastEvent(this._gameEventCountDown);
		
	}

	private Object newGameEventCountDown(String gameEventType) {
		// TODO Auto-generated method stub
		return null;
	}

	private Graphics getDeltaTime() {
		// TODO Auto-generated method stub
		return null;
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
//		this._battleMusic.dispose();
		this._gameScreenStage.dispose();

	}
	
	public void broadcastEvent(GameEventAbstract gameEvent) {

}
	
}
