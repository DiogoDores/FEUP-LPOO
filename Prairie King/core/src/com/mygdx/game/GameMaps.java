package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMaps {
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer orthRender;
	
	public GameMaps(){
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Mapas/LevelOne.tmx");
		orthRender = new OrthogonalTiledMapRenderer(map);
	}

}
