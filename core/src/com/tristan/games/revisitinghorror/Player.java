package com.tristan.games.revisitinghorror;

public class Player {
	private String _name;
	private String _keyAbility;
	private String _hairColor;
	
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

}
