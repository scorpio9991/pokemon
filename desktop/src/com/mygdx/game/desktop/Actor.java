/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Animation;


/**
 *
 * @author Ján
 */
public class Actor implements ApplicationListener {

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Animation animation;

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("Red.png");
 //       batch = new SpriteBatch();
        //    texture = new Texture(Gdx.files.internal("Red.png"));
        sprite = new Sprite(texture);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
     //   Gdx.gl.glClearColor(1, 1, 1, 1);
        //   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.setY(200);
        sprite.setX(200);
        sprite.draw(batch);

        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }


}
