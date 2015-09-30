/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.pokemons.Pokemon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.pokemons.Actions;

/**
 *
 * @author Ján
 */
class Combat implements Screen {

    private ProgressBar hpallie;
    private ProgressBar hpenemy;
    private final MyGdxGame game;
    private OrthographicCamera camera;
    private final Play play;
    private Texture texture;
    private SpriteBatch batch;
    private final Player player;
    private final Pokemon enemy;
    private Pokemon allie;
    private BitmapFont font;
    private FightMenu fightmenu;
    private Actions actions;
    private int count = 0;
    private boolean enemydead = false;
    private int i = 0;
    private final Player trainer;
    private boolean needtoswtich;
    private String move;
    private boolean alliewasdead;
    private boolean enemycatched;
    private Skin skin;
    private ProgressBarStyle barStyle;

    public Combat(MyGdxGame game, Play play, Player player, Pokemon enemy, Player trainer) {
        this.game = game;
        this.play = play;
        this.player = player;
        this.enemy = enemy;
        this.trainer = trainer;

    }

    @Override
    public void show() {
        skin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bar.png"))));
        barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;
        hpallie = new ProgressBar(0, 0, 1, false, barStyle);
        hpenemy = new ProgressBar(0, 0, 1, false, barStyle);
        hpallie.setPosition(550, 295);
        hpenemy.setPosition(160, 470);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        fightmenu = new FightMenu(batch, player, this);
        camera.zoom = (float) 0.5;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        actions = new Actions();
        texture = new Texture("battle1.png");
    }

    @Override
    public void render(float delta) {
        if (this.trainer == null) {
            wildPokemonCombat();
        } else {
            trainerPokemonCombat();
        }
    }

    private void wildPokemonCombat() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (this.enemydead == true || this.enemycatched == true) && i > 146) {
            game.setScreen(play);
            play.setDirectionTexture();

        }
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera.lookAt(300, 300, 0);
        batch.begin();
        if (allie == null) {
            this.allie = player.getpokemon(i);
            while (this.allie.gethp() == 0) {
                i++;
                this.allie = player.getpokemon(i);
            }
            fightmenu.message("Wild " + enemy.getName() + " has appeared", 1);

        }
        hpallie.setRange(0, allie.getEndurance() * 2);
        hpenemy.setRange(0, enemy.getEndurance() * 2);
        hpallie.setValue(allie.gethp());
        hpenemy.setValue(enemy.gethp());
        batch.draw(texture, 100, 120);
        font.draw(batch, "Hp: " + enemy.gethp(), 150, 465);
        font.draw(batch, enemy.getName(), 180, 495);
        font.draw(batch, "LV: " + enemy.getLevel(), 320, 495);
        font.draw(batch, allie.gethp() + " / " + allie.getEndurance() * 2, 640, 290);
        font.draw(batch, allie.getName(), 500, 340);
        font.draw(batch, "LV: " + allie.getLevel(), 650, 340);
        font.draw(batch, "What will you do?", 130, 230);
        hpallie.draw(batch, 1);
        hpenemy.draw(batch, 2);
        enemy.setPosition(525, 350);
        allie.setPosition(210, 240);
        enemy.draw(batch);
        allie.draw(batch);
        fightmenu.render();
        turn();
        if (this.needtoswtich == true && fightmenu.amIchoosing() == false) {
            fightmenu.ineedtoChoose();
        }
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(play);
            play.setDirectionTexture();
        }
    }
//jeden tah podla renderu
    private void turn() {
        if (fightmenu.isTurnOver() == true && this.enemydead == false && this.needtoswtich == false && this.enemycatched == false) {
            if (this.alliewasdead == true || this.enemycatched == true) {
                count = 150;
            }

            if (count == 0 && this.move != null) {
                actions.attack(this.move, this.getAlliePokemon(), this.getEnemyPokemon());
                fightmenu.message(this.getAlliePokemon().getName() + " used " + this.move, 1);

            }
            if (count < 75 && this.move != null) {
                enemy.blickingTexture();
            }
            if (count == 75) {
                actions.attack(this.enemy.move1(), this.enemy, this.allie);
                fightmenu.message("Enemy " + this.enemy.getName() + " used " + this.enemy.move1() + "!", 1);

            }

            if (this.enemy.gethp() == 0) {
                this.enemydead = true;
                enemy.death();

            }
            if (count > 75 && count < 151 && fightmenu.isTurnOver()) {
                allie.blickingTexture();
            }
            if (count == 150) {
                fightmenu.setTurn(false);
                count = 0;
                this.move = null;
                this.alliewasdead = false;
                if (this.allie.gethp() == 0) {
                    fightmenu.message(allie.getName() + "has fainted!", 1);
                    this.needtoswtich = true;
                    this.alliewasdead = true;
                    allie.death();

                }
            } else {
                count++;
            }

        }
        if (this.enemydead == true) {
            if (i <= 70) {
                enemy.blickingTexture();
            }
            if (i == 146) {
                if (allie.levelup(enemy.getLevel() * 3)) {
                    fightmenu.message(this.allie.getName() + " leveled up to " + this.allie.getLevel(), 2);
                } else {
                    fightmenu.message("Gained " + enemy.getLevel() * 3 + " Experience", 2);
                }
            }
            if (i >= 71 && i < 146) {
                fightmenu.message("Enemy " + enemy.getName() + " has fainted!", 1);
            }
            i++;
        }
        if (this.enemycatched == true) {

            if (i == 146) {
                fightmenu.message(enemy.getName() + " was caught!", 2);
            }
            if (i >= 71 && i < 146) {
                fightmenu.message("Catching", 1);
            }
            i++;
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

//        batch.dispose();
//        texture.dispose();
//        font.dispose();
//        skin.dispose();
//     //   fightmenu.dispose();

    }

    public void setAlliePokemon(Pokemon allie) {
        this.allie = allie;

    }

    public Pokemon getAlliePokemon() {
        return this.allie;
    }

    public Pokemon getEnemyPokemon() {
        return this.enemy;
    }

    private void trainerPokemonCombat() {

    }

    public void noLongerNeedtoSwitch() {
        this.needtoswtich = false;
    }

    public void setAttackmove(String move) {
        this.move = move;
    }
        //I chatched a pokemon
    public void pokeCatched() {

        this.enemycatched = true;
        this.enemy.settrainer(true);
    }

}
