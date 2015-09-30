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
public class Pokeball implements Item{
    private final String name;
    private int uses;
    private final String desc;
    public Pokeball(){
    this.name="Pokeball";
    this.uses=1;
    this.desc="Catches a enemy pokemon";
}

    @Override
    public void use(Player player,Pokemon allie, Pokemon enemy) {
        player.addpokemon(enemy);
        uses--;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add() {
        uses++;
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
