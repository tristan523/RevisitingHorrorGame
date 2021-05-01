package com.tristan.games.revisitinghorror;

public class Weapon {
	private String _name;
	private String _size;
	private float _strength;
	private int _level;
	
	public String getName() {
		return _name;
	}
	
	public void setName(String value) {
		this._name = value;
	}
	
	public String getsize() {
		return this._size;
	}
	
	public void setsize(String value) {
		this._size = value;
	}
	
	public float getstrength() {
		return this._strength;
	}
	
	public void setstrength(float value) {
		this._strength = value;
	}
	
	public int getlevel() {
		return this._level;
	}
	
	public void setlevel(int value) {
		this._level = value;
	}

}
