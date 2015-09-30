/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.pokemons.Caterpie;
import com.mygdx.game.pokemons.Charmander;
import com.mygdx.game.pokemons.Missingno;
import com.mygdx.game.pokemons.Pidgey;
import com.mygdx.game.pokemons.Pokemon;
import com.mygdx.game.pokemons.Psyduck;
import java.util.Random;

/**
 *
 * @author Ján
 */
public class PokeFactory {
    private final Random randomlevel;
    private Pokemons pokemons;
    
    public enum Pokemons {
    Charmander, Caterpie, Psyduck,Pidgey 
    }
    
    public PokeFactory(){
        randomlevel= new Random();

    }
    
    public Pokemon getPoke(String name,boolean istrainer){
        int nahodne=randomlevel.nextInt(10)+1;
        
        
        
        if("Charmander".equals(name)){
                return new Charmander(10, istrainer);
            
        }
        else if("Caterpie".equals(name)){
            return new Caterpie(nahodne, istrainer);
        }
        else if("PsyDuck".equals(name)){
            return new Psyduck(nahodne,istrainer);
        }
        else if("Pidgey".equals(name)){
            return new Pidgey(nahodne, istrainer);
        }
        else return new Missingno(100,false);
    }
}
