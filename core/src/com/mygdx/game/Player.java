/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.pokemons.pokemonsActions.Pokemon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.items.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ján
 */
public class Player extends Sprite {
    private final List<Pokemon> kapsa = new ArrayList<Pokemon>();
    private final List<Item> bag = new ArrayList<Item>();
    private int direction;
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 4;
    private final Texture walkSheet;
    private final TextureRegion[] walkFrames;
    private final String name;
    private int Idoriginal;

    public Player(Texture texture) {
        super(texture);
        this.name = "Johnny";
        walkSheet = texture;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        setX(128);
        setY(128);
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    private void update(float delta) {
    }

    public int getDirection() {
        return this.direction;
    }

    public void setdirection(int setter) {
        this.direction = setter;
    }

    public void moveleft() {
        setX(getX() - 1);
        this.direction = 270;
    }

    public void moveright() {
        setX(getX() + 1);
        this.direction = 90;
    }

    public void movedown() {
        setY(getY() - 1);
        this.direction = 180;
    }

    public void moveup() {
        setY(getY() + 1);
        this.direction = 0;
    }

    public void addpokemon(Pokemon pokemon) {
        if (kapsa.size() != 6) {
            kapsa.add(pokemon);
        } else {
            //TODO: BAG FULL CONDITION
        }
    }

    public void swapminus(int poz) {
        //kapsa.remove(poz);
        Collections.swap(kapsa, poz, poz - 1);
    }

    public void swapplus(int poz) {
        //kapsa.remove(poz);
        Collections.swap(kapsa, poz, poz + 1);
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

    public int getSizeofITEMbag() {
        return bag.size();
    }

    public void additem(Item item) {
        if (bag.contains(item)) {
            bag.get(bag.indexOf(item)).add();
        } else {
            bag.add(item);
        }
    }

    public Item getitem(int i) {
        return bag.get(i);
    }

    void checkAndEmpty() {
        int i = 0;
        while (i < this.getSizeofITEMbag()) {
            if (this.getitem(i).getUses() == 0) {
                this.bag.remove(i);
            }
            i++;
        }
    }

    public String getName() {
        return this.name;
    }

    public void replace(Pokemon original, Pokemon evolved) {
        this.Idoriginal = this.kapsa.indexOf(original);
        kapsa.remove(original);
        kapsa.add(this.Idoriginal, evolved);
    }
}
