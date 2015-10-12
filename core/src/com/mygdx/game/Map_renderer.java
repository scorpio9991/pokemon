/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import javax.swing.Renderer;

/**
 * @author Ján
 */
public class Map_renderer {
    private OrthogonalTiledMapRenderer Renderedhome;
    private OrthogonalTiledMapRenderer Renderedmap;
    private Renderer renderer;
    private final TiledMap mainmap;
    private final TiledMap homemap;
    private final TiledMapTileLayer blockedmap;

    public Map_renderer() {
        mainmap = new TmxMapLoader().load("maps/poketest.tmx");
        homemap = new TmxMapLoader().load("maps/home.tmx");
        blockedmap = (TiledMapTileLayer) mainmap.getLayers().get("walls");
        Renderedhome = new OrthogonalTiledMapRenderer(homemap);
        Renderedmap = new OrthogonalTiledMapRenderer(mainmap);
    }

    public void set_renderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
