package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.game.pokemons.Caterpie;
import com.mygdx.game.pokemons.Charmander;
import com.mygdx.game.pokemons.Pidgey;
import com.mygdx.game.pokemons.Psyduck;
import com.mygdx.items.Pokeball;
import com.mygdx.items.Potion;
import com.mygdx.items.RareCandy;

public class MyGdxGame extends Game {

    private SpriteBatch batch;
    private Texture img;
    private FPSLogger fpsLogger;
    private Player player;
    private Pokeball pokeball;
    private RareCandy rarecandy;
    private PokeFactory pokefactory;
    private StartMenu startmenu;
    private Play playmenu;

    public void change() {

    }
    
    @Override
    public void create() {
        pokefactory = new PokeFactory();
        pokeball = new Pokeball();
        rarecandy = new RareCandy();
        player = new Player(new Texture("pdown.png"));
        player.additem(pokeball);
        player.additem(pokeball);
        player.additem(new Potion());
        player.additem(rarecandy);
        player.additem(rarecandy);

        player.addpokemon(pokefactory.getPoke("Charmander", true));
        player.addpokemon(pokefactory.getPoke("Caterpie", true));
        player.addpokemon(pokefactory.getPoke("PsyDuck", true));
   //     player.addpokemon(pokefactory.getPoke("Pidgey", true));

        fpsLogger = new FPSLogger();
        startmenu = new StartMenu();
        playmenu = new Play(this, player);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void render() {
        super.render();
        if (startmenu.ended() == true) {
            if(getScreen()==startmenu)
            setScreen(playmenu);
        } else {
            if(getScreen()==null)
            setScreen(startmenu);
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();

    }
}
