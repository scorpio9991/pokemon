/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.pokemons.pokemonsActions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Ján
 */
public interface Pokemon {
    /**
     * Get HP
     *
     * @return HP
     */
    int getHP();

    /**
     * Set HP
     *
     * @param hp
     */
    void setHP(int hp);

    /**
     * Get name
     *
     * @return name
     */
    String getName();

    /**
     * Set name
     *
     * @param name
     */
    void setName(int name);

    /**
     * Get status
     *
     * @return status
     */
    String getStatus();

    /**
     * Set status
     *
     * @param status
     */
    void setStatus(String status);

    /**
     * Get Att Stat
     *
     * @return attstat
     */
    int getAttStat();

    /**
     * Set Att Stat
     *
     * @param attStat
     */
    void setAttStat(int attStat);

    /**
     * Get Def Stat
     *
     * @return defstat
     */
    int getDefStat();

    /**
     * Set Def Stat
     *
     * @param defstat
     */
    void setDefStat(int defstat);

    /**
     * Get Attribute
     *
     * @return attribute
     */
    String getAttribute();

    /**
     * Set Attribute
     *
     * @param attribute
     */
    void setAttribute(String attribute);

    /**
     * Get Endurance
     *
     * @return endurance
     */
    int getEndurance();

    /**
     * Set Endurance
     *
     * @param endurance
     */
    void setEndurance(int endurance);

    /**
     * Get Opponent
     *
     * @return opponent
     */
    Pokemon getOpponent();

    /**
     * Set Opponent pokemon
     *
     * @param oponentPokemon
     */
    void setOpponent(Pokemon oponentPokemon);

    /**
     * Get position
     *
     * @return position x,y
     */
    int[] getPosition();

    /**
     * Set position x,y
     *
     * @param x
     * @param y
     */
    void setPosition(float x, float y);

    /**
     * Pokemon move 1 (left/top)
     *
     * @return move1
     */
    String move1();

    /**
     * Pokemon move 2 (right/top)
     *
     * @return move2
     */
    String move2();

    /**
     * Pokemon move 3 (left/bottom)
     *
     * @return move1
     */
    String move3();

    /**
     * Pokemon move 4 (right/bottom)
     *
     * @return move4
     */
    String move4();

    //TODO: dokomentovat, tie move to je utok 1,2,3,4 / nie ako move ale skor ako attack

    /**
     * @param trainer
     */
    void setTrainer(boolean trainer);

    boolean levelup(int experience);

    int getLevel();

    void draw(SpriteBatch spriteBatch);

    void blickingTexture();

    void death();
}
