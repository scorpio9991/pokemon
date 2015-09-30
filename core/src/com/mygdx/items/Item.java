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
public interface Item {
    public void use(Player player,Pokemon allie, Pokemon enemy);
    public String getName();
    public void add(); 
    public int getUses();
    public String getDescription();
}
