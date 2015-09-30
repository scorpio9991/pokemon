/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Ján
 */
public class StartMenu implements Screen {

    private Texture texture;
    private Batch batch;
    private boolean ended = false;
    private Sound openingsound;
    private BitmapFont font;
    private Texture texturelogo;
    private Sprite logo;

    @Override
    public void show() {
        batch = new SpriteBatch();
        texture = new Texture("Startmenu.jpg");
        openingsound = Gdx.audio.newSound(Gdx.files.internal("opening.mp3"));
        openingsound.loop((float) 0.1);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setScale((float) 1.5);
        texturelogo = new Texture("repl2.png");
        logo = new Sprite(texturelogo);
        logo.setPosition(560, 480);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(texture, 0, 0);
        font.draw(batch, "New Game", 600, 500);
        font.draw(batch, "Load", 600, 450);
        logo.draw(batch);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (logo.getY() == 480) {
                openingsound.stop();
                this.ended = true;
            }
            else{
               //TODO: LOAD
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            logo.setPosition(560, 480);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            logo.setPosition(560, 430);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        texture.dispose();
        openingsound.dispose();
    }

    public boolean ended() {

        return this.ended;
    }
}
