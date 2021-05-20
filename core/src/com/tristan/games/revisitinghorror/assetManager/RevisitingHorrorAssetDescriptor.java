package com.tristan.games.revisitinghorror.assetManager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class RevisitingHorrorAssetDescriptor {

	public static final AssetDescriptor<Texture> battleScene = new AssetDescriptor<Texture>(Asset.BATTLE_SCENE,
			Texture.class);
	public static final AssetDescriptor<Texture> opponent = new AssetDescriptor<Texture>(Asset.OPPONENT,
			Texture.class);
	public static final AssetDescriptor<Texture> player = new AssetDescriptor<Texture>(Asset.PLAYER,
			Texture.class);

}
