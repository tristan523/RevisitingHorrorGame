package com.tristan.games.revisitinghorror.screens;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tristan.games.revisitinghorror.Player;
import com.tristan.games.revisitinghorror.RevisitingHorror;
import com.tristan.games.revisitinghorror.assetManager.RevisitingHorrorAssetDescriptor;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.events.game.GameEventAbstract;
import com.tristan.games.revisitinghorror.events.game.GameEventPrepareToAttack;
import com.tristan.games.revisitinghorror.models.Opponent;
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
	

	public GameScreen(final RevisitingHorror revisitingHorrorGame) {
			this._startCountDown = true;
			
			this._assetManager = new AssetManager();
			
			this._gameEventManager = new GameEventManager();
			
			this._revisitingHorrorGame = revisitingHorrorGame;
			
		
		}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);
		
		this._gameScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());
		
		Gdx.input.setInputProcessor(this._gameScreenStage);
		
		this.loadAssets();
		
		this.loadActors();

	}

	private void loadActors() {
		Image battleScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene));
		
		
		this. _katniss = new Player(this._assetManager.get(RevisitingHorrorAssetDescriptor.player),
				RevisitingHorrorAssetDescriptor.player.fileName, this._gameEventManager);
		this. _katniss.spritePosition(500, 200);
		
		this._cato = new Opponent(this._assetManager.get(RevisitingHorrorAssetDescriptor.opponent),
				RevisitingHorrorAssetDescriptor.opponent.fileName, this._gameEventManager);
		_cato.spritePosition(200, 400);
		
		battleScene.setName("battleScene");
		_katniss.setName("katniss");
		this._cato.setName("cato");
		
		this._gameScreenStage.addActor(battleScene);
		this._gameScreenStage.addActor(this._katniss);
		this._gameScreenStage.setKeyboardFocus(this._katniss);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.player);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.opponent);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.battleScene);
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
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		this._gameScreenStage.act(Gdx.graphics.getDeltaTime());
		
//		this._gameScreenStage.getBatch().begin();
		
//		Texture battleScreenBackground = (Texture) this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene);
//		this._gameScreenStage.getBatch().disableBlending();
		this._gameScreenStage.draw();
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
