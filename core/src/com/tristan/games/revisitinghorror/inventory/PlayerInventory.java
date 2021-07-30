package com.tristan.games.revisitinghorror.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.tristan.games.revisitinghorror.events.GameEventManager;
import com.tristan.games.revisitinghorror.models.ImageBase;

public class PlayerInventory extends ImageBase{
	private int _totalGold;
	private GameEventManager _gameEventManager;
	//private GameEventPlayerInventory _playerInventoryEvent;
	public char[] getTotalGold;
	
	public PlayerInventory(Texture texture, GameEventManager gameEventManager) {
		super(texture);
		this.initialize(texture, gameEventManager);
		this.addEventHandlers();
	}

	private void addEventHandlers() {
		// TODO Auto-generated method stub
		
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		// TODO Auto-generated method stub
		
	}

	public int getTotalGold() {
		return _totalGold;
	}
	
	public void setTotalGold(int totalGold) {
		_totalGold = totalGold;
	}
	

}
