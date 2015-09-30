/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.pokemons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Ján
 */
public interface Pokemon{
    int gethp();
    String getName();
    String getStatus();
    void setStatus(String status);
    void sethp(int hp);
    int getAttStat();
    void setAttStat(int att);
    int getDefStat();
    void setDefStat();
    String getattribute();
    int getEndurance();
    void setEndurance(int end);
    boolean levelup(int experience);
    int getLevel();
    void setopponent(Pokemon pokemon);
    void draw(SpriteBatch spriteBatch);
    void setPosition(float x,float y);
    String move1();
    String move2();
    String move3();
    String move4();
    void blickingTexture();
    void death();
    void settrainer(boolean trainer);
}
