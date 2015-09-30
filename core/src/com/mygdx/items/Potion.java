/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.items;

import com.mygdx.game.Player;
import com.mygdx.game.pokemons.Pokemon;

/**
 *
 * @author Ján
 */
public class Potion implements Item{
    private String name;
    private int uses;
    private final String desc;
    public Potion(){
        this.name="Potion";
        this.uses=1;
        this.desc="Restores 20 HP to a pokemon";
    }

    @Override
    public void use(Player player,Pokemon allie, Pokemon enemy) {
        allie.sethp(allie.gethp()+40);
        uses--;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add() {
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
    
}
