/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.actors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mygdx.pokemons.pokemonsActions.Pokemon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ján
 */
class Trainer extends Sprite implements Actor {
    private List<Pokemon> kapsa = new ArrayList<Pokemon>();
    private int direction;
    private Sprite test;
    ////    private static final int FRAME_COLS = 4;
    ////    private static final int FRAME_ROWS = 4;
    private TiledMapTileLayer collLayer;
    //  private float tileWidth = collLayer.getTileWidth();
    //  private float tileHeight = collLayer.getTileHeight();
    //  private final int keycode;
    //   private final Texture walkSheet;
    //   private final TextureRegion[] walkFrames;
    //   private final Animation walkAnimation;
    //    private Texture pright;
    //    private Texture pleft;
    //    private Texture pdown;
    //    private Texture pup;
    //    private boolean left;
    //    private final Texture pdownleft;
    //    private final Texture pdownright;
    //    private final Texture pleftleft;
    //    private final Texture pleftright;
    private String Name;
    private boolean active;

    public Trainer(Texture texture, String name) {

        super(texture);
        this.Name = name;
        this.active = true;
        //        pright = new Texture("pright.png");
        //        pleft = new Texture("pleft.png");
        //        pdown = new Texture("pdown.png");
        //        pup = new Texture("pup.png");
        //        pdownleft= new Texture("moving/downleft.png");
        //        pdownright= new Texture("moving/downright.png");
        //        pleftleft= new Texture("moving/leftleft.png");
        //        pleftright= new Texture("moving/leftright.png");
        //        walkSheet = texture;
        //        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        //        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        //        int index = 0;
        //        for (int i = 0; i < FRAME_ROWS; i++) {
        //            for (int j = 0; j < FRAME_COLS; j++) {
        //                walkFrames[index++] = tmp[i][j];
        //            }
        //        }
        //        walkAnimation = new Animation(0.025f, walkFrames);
        setX(128);
        setY(128);
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());

        super.draw(spriteBatch);
    }

    private void update(float delta) {

        //       this.setTexture(walkSheet);

    }

    public int getdirection() {
        return this.direction;
    }

    public void setdirection(int setter) {
        this.direction = setter;
    }

    public void moveleft() {
        setX(getX() - 1);
    }

    public void moveright() {
        setX(getX() + 1);
    }

    public void movedown() {
        setY(getY() - 1);
    }

    public void moveup() {
        setY(getY() + 1);
    }

    public void addpokemon(Pokemon pokemon) {
        if (kapsa.size() != 6) {
            kapsa.add(pokemon);
            System.out.println("Pokemon was added!");
        } else {
            System.out.println("Pokemon bag is full");
        }
    }

    public Pokemon getpokemon(int i) {
        while (kapsa.get(i) == null) {
            i++;
        }
        return kapsa.get(i);
    }

    public int getSizeofBag() {
        return kapsa.size();
    }

    public void removepokemon(int i) {
        this.kapsa.remove(i);
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void use() {
        if (this.active == true) {

        }
    }

    @Override
    public void setActivity(boolean active) {
        this.active = active;
    }
}

