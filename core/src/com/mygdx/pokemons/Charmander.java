/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.pokemons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pokemons.pokemonsActions.Actions;
import com.mygdx.pokemons.pokemonsActions.Pokemon;

/**
 * @author Ján
 */
public class Charmander extends Sprite implements Pokemon {
    private int hp;
    private String status;
    private Pokemon opponent;
    private int attackStat;
    private int defStat;
    private String attribute;
    private int endurance;
    private int level;
    private final Actions actions;
    private String name;
    private String move1;
    private String move2;
    private String move3;
    private String move4;
    private int pocet;
    private Color oricolor;
    private int experience;

    public Charmander(int level, boolean istrainer) {
        super(new Texture("FCharmander.png"));
        if (istrainer == true) {
            super.setTexture(new Texture("BCharmander.png"));
        }
        this.level = level;
        this.endurance = this.getLevel() * 1;
        this.hp = this.getEndurance() * 2;
        this.actions = new Actions();
        this.move1 = "Bite";
        this.move2 = "Ember";
        this.attackStat = this.getLevel();
        this.defStat = this.getLevel() / 2;
        this.attribute = "fire";
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    private void update(float delta) {

    }

    @Override
    public int getHP() {
        return this.hp;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setHP(int hp) {
        this.hp = hp;
        if (this.hp < 0) {
            this.hp = 0;
        }
        if (this.hp > this.getEndurance() * 2) {
            this.hp = this.getEndurance() * 2;
        }
    }

    @Override
    public void setOpponent(Pokemon pokemon) {
        this.opponent = pokemon;
    }

    @Override
    public int[] getPosition() {
        return new int[0];
    }

    @Override
    public String move1() {
        return this.move1;
    }

    @Override
    public String move2() {
        return this.move2;
    }

    @Override
    public String move3() {
        return this.move3;
    }

    @Override
    public String move4() {
        return this.move4;
    }

    @Override
    public int getAttStat() {
        return this.attackStat;
    }

    @Override
    public void setAttStat(int att) {
    }

    @Override
    public int getDefStat() {
        return this.defStat;
    }

    @Override
    public void setDefStat(int defstat) {

    }

    @Override
    public String getAttribute() {
        return this.attribute;
    }

    @Override
    public void setAttribute(String attribute) {

    }

    @Override
    public int getEndurance() {
        return this.endurance;
    }

    @Override
    public void setEndurance(int end) {
        this.endurance = end;
    }

    @Override
    public Pokemon getOpponent() {
        return null;
    }

    @Override
    public boolean levelup(int exp) {

        this.experience += exp;
        if (this.experience >= this.getLevel() * 10) {
            this.level += 1;
            if (this.level == 12) {
                this.move3 = "QuickAttack";
            }
            this.experience = 0;
            this.attackStat += 2;
            this.defStat += 1;
            this.endurance += 2;
            this.setHP(this.getEndurance() * 2);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getName() {
        return this.name = "Charmander";
    }

    @Override
    public void setName(int name) {

    }

    @Override
    public void blickingTexture() {
        if (pocet == 0) {
            oricolor = super.getColor();
        }
        if (pocet % 10 == 0) {
            super.setColor(Color.DARK_GRAY);
        } else if (pocet % 5 == 0) {
            super.setColor(oricolor);
        }

        if (pocet == 74) {
            pocet = 0;
            super.setColor(oricolor);
        }
        pocet++;
    }

    @Override
    public void death() {
        super.setRotation(90);
    }

    @Override
    public void setTrainer(boolean trainer) {
        super.setTexture(new Texture("BCharmander.png"));
    }
}
