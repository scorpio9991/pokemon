/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.items.Item;
import com.mygdx.items.ItemParser;

/**
 * @author Ján
 */
public class Menu implements Screen {
    private final Player player;
    private BitmapFont font;
    private Batch batch;
    private Texture texturelogo;
    private Sprite logo;
    private Texture combatLogo;
    private BitmapFont text;
    private Texture tab;
    private Sprite tabLogo;
    private final int left = 160, bot = 450, top = 500;
    private final MyGdxGame game;
    private final Play play;
    private int cursorX, cursorY;
    private Sprite tabLogo2;
    private boolean pokemonmenu;
    private boolean itemmenu;
    private int poz;
    private boolean moving;
    private int maxcursorY;
    private Texture texture;
    private Item tempitem;
    private boolean usingItem;
    private int dlzkamess;
    private String message;
    private boolean continuous;
    private Texture messageTab;
    private Sprite tabMessage;
    private boolean playermenu;

    /**
     * @param game
     * @param play
     * @param player
     * @param trainer
     */
    public Menu(MyGdxGame game, Play play, Player player, Player trainer) {
        this.play = play;
        this.game = game;
        this.player = player;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale((float) 1.5);
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        text.setScale((float) 1);
        combatLogo = new Texture("tabcombat.png");
        texturelogo = new Texture("repl2.png");
        tab = new Texture("Listofpokemontab.png");
        tabLogo = new Sprite(tab);
        tabLogo.setPosition(100, 360);
        tabLogo2 = new Sprite(tab);
        tabLogo2.setPosition(320, 360);
        logo = new Sprite(texturelogo);
        messageTab = new Texture("MessageTab.png");
        tabMessage = new Sprite(messageTab);
        tabMessage.setPosition(0, 0);
        //    this.firsttime=true;
        logo.setPosition(100, 300);
        cursorX = 120;
        cursorY = 582;
        // renderer = play.renderBackground();
        texture = new Texture("background.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(texture, 0, 0);
        //   player.draw(batch);
        //  renderer.render();
        mainmenu();
        if (pokemonmenu) {
            pokemonmenu();
            cursorMovement();
        } else if (itemmenu) {
            itemmenu();
            cursorMovement();
        } else if (playermenu) {
            playermenu();
        } else {
            cursorMovement();
            cursorControl();
        }
        this.message(message, 0);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            play.menuStop();
            game.setScreen(play);
            play.setDirectionTexture();
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
        font.dispose();
        text.dispose();
        tab.dispose();
        batch.dispose();
        combatLogo.dispose();
        texturelogo.dispose();
        texture.dispose();
    }

    private void cursorMovement() {
        if (!this.pokemonmenu && !this.itemmenu) {
            if (this.cursorY > 582) {
                this.cursorY = 582;
            }
            if (this.cursorY < 382) {
                this.cursorY = 382;
            }

            logo.setPosition(this.cursorX, this.cursorY);
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                this.cursorY -= 50;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                this.cursorY += 50;
            }
            logo.draw(batch);
        } else {
            if (this.pokemonmenu) {
                this.maxcursorY = 582 - 40 * player.getSizeofBag() + 40;
            }
            if (this.itemmenu) {
                this.maxcursorY = 582 - 40 * player.getSizeofITEMbag() + 40;
            }
            if (this.cursorY > 582) {
                this.cursorY = 582;
            }
            if (this.cursorY < this.maxcursorY) {
                this.cursorY = this.maxcursorY;
            }

            logo.setPosition(this.cursorX, this.cursorY);
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                //   poz--;
                this.cursorY -= 40;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                //  poz++;
                this.cursorY += 40;
            }
            logo.draw(batch);
        }
    }

    private void cursorControl() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            switch (cursorY) {
                case 382: {
                    Gdx.app.exit();
                    break;
                }
                case 432: {
                    //potom tu pojde save
                    break;
                }
                case 482: {
                    playermenu = true;

                    break;
                }
                case 532: {
                    itemmenu = true;
                    poz = 0;
                    cursorX += 220;
                    cursorY = 582;

                    break;
                }
                case 582: {
                    poz = 0;
                    pokemonmenu = true;
                    cursorX += 220;

                    break;
                }
            }
        }
    }

    private void mainmenu() {
        tabLogo.draw(batch);
        font.draw(batch, "Pokemon", left, top + 100);
        font.draw(batch, "Items", left, top + 50);
        font.draw(batch, "Player", left, top);
        font.draw(batch, "Save", left, bot);
        font.draw(batch, "Exit Game", left, bot - 50);
    }

    private void itemmenu() {
        tabLogo2.draw(batch);
        int i = 0;
        int y = 598;
        while (player.getSizeofITEMbag() != i) {
            text.draw(batch, player.getitem(i).getName() + ": x" + player.getitem(i).getUses(), 360, y);

            //   text.draw(batch, "Hp:" + player.getitem(i).getHP() + " Lv:" + player.getpokemon(i).getLevel(), 450, y);
            i++;
            y -= 40;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            this.itemmenu = false;
            this.cursorX -= 220;
            this.cursorY = 532;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            this.message(player.getitem(poz).getDescription(), 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if ((ItemParser.parse(player.getitem(poz)) == (2)) || (ItemParser.parse(player.getitem(poz)) == (0))) {
                if (!moving) {
                    tempitem = player.getitem(poz);
                    usingItem = true;
                    pokemonmenu = true;
                    itemmenu = false;
                }
            } else {
                this.message("This item cannot be used in the menu", 1);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (poz - 1 >= 0) {
                poz--;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            if (poz + 1 < player.getSizeofITEMbag()) {
                poz++;
            }
        }
    }

    private void pokemonmenu() {
        tabLogo2.draw(batch);
        int i = 0;
        int y = 598;
        while (player.getSizeofBag() != i) {
            text.draw(batch, player.getpokemon(i).getName(), 360, y);
            if (player.getpokemon(i).getHP() == 0) {
                text.draw(batch, "DEAD", 450, y);
            } else {
                text.draw(batch, "Hp:" + player.getpokemon(i).getHP() + " Lv:" + player.getpokemon(i).getLevel(), 450, y);
            }
            i++;
            y -= 40;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            this.message("Name: " + player.getpokemon(poz).getName() + " Atribbute: " + player.getpokemon(poz).getAttribute(), 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            this.pokemonmenu = false;
            this.cursorX -= 220;
            this.cursorY = 582;
            this.usingItem = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!usingItem) {
                if (!moving) {
                    moving = true;
                    cursorX += 20;
                } else {
                    moving = false;
                    cursorX -= 20;
                }
            } else {
                tempitem.use(player, player.getpokemon(poz), null);
                this.message("Player used " + tempitem.getName() + " on " + player.getpokemon(poz).getName(), 1);
                player.checkAndEmpty();
                this.pokemonmenu = false;
                this.usingItem = false;
                this.cursorX -= 220;
                this.cursorY = 582;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (moving) {
                if (poz - 1 >= 0) {
                    player.swapminus(poz);
                }
            }
            if (poz - 1 >= 0) {
                poz--;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (moving) {
                if (poz + 1 < player.getSizeofBag()) {
                    player.swapplus(poz);
                }
            }
            if (poz + 1 < player.getSizeofBag()) {
                poz++;
            }
        }

        //        font.draw(batch, null, right, 382);
        //        font.draw(batch, null, right, 422);
        //        font.draw(batch, null, right, 462);
        //        font.draw(batch, null, right, 502);
        //        font.draw(batch, null, right, 542);
        //        font.draw(batch, null, right, 582);
    }

    public void message(String message, int nova) {
        // 1 for 75 renders message
        // 2 for non-stop message
        if (nova == 1) {
            this.dlzkamess = 0;
            tabMessage.draw(batch);
        }
        if (nova == 2) {
            this.continuous = true;
            this.dlzkamess = 0;
            tabMessage.draw(batch);
        }
        if (message != null) {
            this.message = message;

            if (this.dlzkamess <= 75 || this.continuous) {
                tabMessage.draw(batch);
                font.draw(batch, this.message, 50, 80);
                dlzkamess++;
            } else if (!this.continuous) {
                this.message = null;
            }
        }
    }

    private void playermenu() {
        tabLogo2.draw(batch);
        int i = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            this.playermenu = false;
        }
        font.draw(batch, player.getName(), 340, 598);
        font.draw(batch, "This session: " + play.getHours() + ":" + play.getMin(), 340, 548);
        font.draw(batch, "Poke number: " + player.getSizeofBag(), 340, 508);
    }
}
