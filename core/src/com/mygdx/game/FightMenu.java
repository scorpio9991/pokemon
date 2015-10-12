/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.items.ItemParser;

/**
 * @author Ján
 */
class FightMenu extends ApplicationAdapter {
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final Texture texturelogo;
    private final Sprite logo;
    private final Texture combatLogo;
    private final Sprite tabcombat;
    private final int right = 620, left = 510, bot = 170, top = 220;
    private final int logoX = 440, logoY = 120;
    private int cursorX, cursorY;
    private boolean infight;
    private String message;
    private int dlzkamess;
    private boolean choosingPoke;
    private final Texture choosepokemonlogo;
    private final Sprite chooselogo;
    private final Player player;
    private final BitmapFont text;
    private int numberid;
    private int posledny;
    private int atpokemon;
    private final Combat combatscreen;
    private boolean turnover;
    private boolean continuous;
    private boolean waschoosing;
    private boolean haveToChoose;
    private boolean choosingBag;

    public FightMenu(SpriteBatch batch, Player player, Combat combatscreen) {
        this.combatscreen = combatscreen;
        this.player = player;
        this.batch = batch;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.setScale((float) 1.5);
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        text.setScale((float) 1);
        combatLogo = new Texture("tabcombat.png");
        texturelogo = new Texture("repl2.png");
        choosepokemonlogo = new Texture("Listofpokemontab.png");
        chooselogo = new Sprite(choosepokemonlogo);
        chooselogo.setPosition(525, 250);
        logo = new Sprite(texturelogo);
        tabcombat = new Sprite(combatLogo);
        //    this.firsttime=true;
        logo.setPosition(480, 202);
        cursorX = 480;
        cursorY = 202;
    }

    @Override
    public void render() {

        tabcombat.draw(batch);
        tabcombat.setPosition(logoX, logoY);
        if (infight && !this.choosingPoke) {
            fightmenu();
        } else if (this.choosingPoke) {
            mainmenu();
            choosePokemon();
        } else if (this.choosingBag) {
            mainmenu();
            chooseItem();
        } else {
            mainmenu();
        }
        logo.setPosition(this.cursorX, this.cursorY);
        logo.draw(batch);
        if (!this.turnover) {
            if (!this.choosingPoke && !this.choosingBag) {
                cursormoving();
                cursorControl();
                this.waschoosing = false;
            }
        }
        message(this.message, 0);
    }

    private void mainmenu() {
        font.draw(batch, "Fight", left, top);
        font.draw(batch, "Run", right, top);
        font.draw(batch, "Bag", left, bot);
        font.draw(batch, "Pokemon", right, bot);
        //   if(firsttime==true)
    }

    private void fightmenu() {
        if (this.combatscreen.getAlliePokemon().move1() != null) {
            font.draw(batch, this.combatscreen.getAlliePokemon().move1(), left, top);
        }
        if (this.combatscreen.getAlliePokemon().move2() != null) {
            font.draw(batch, this.combatscreen.getAlliePokemon().move2(), right, top);
        }
        if (this.combatscreen.getAlliePokemon().move4() != null) {
            font.draw(batch, this.combatscreen.getAlliePokemon().move4(), left, bot);
        }
        if (this.combatscreen.getAlliePokemon().move3() != null) {
            font.draw(batch, this.combatscreen.getAlliePokemon().move3(), right, bot);
        }
    }

    private void cursormoving() {
        logo.setPosition(this.cursorX, this.cursorY);
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            this.cursorY = 152;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            this.cursorX = 480;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.cursorX = 590;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.cursorY = 202;
        }
        //  firsttime=false;
    }

    private void cursorControl() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (this.cursorY == 202 && this.cursorX == 480) {
                if (!this.infight) {
                    infight = true;
                } else {
                    combatscreen.setAttackmove(this.combatscreen.getAlliePokemon().move1());
                    this.turnover = true;
                }
            }
            if (this.cursorY == 202 && this.cursorX == 590) {
                if (!this.infight) {
                    message("You cant run!", 1);
                    this.turnover = true;
                } else {
                    combatscreen.setAttackmove(this.combatscreen.getAlliePokemon().move2());
                    this.turnover = true;
                }
            }
            if (this.cursorY == 152 && this.cursorX == 590) {
                if (!this.infight) {
                    this.choosingPoke = true;
                    this.cursorY = 465;
                    this.cursorX = 550;
                    this.atpokemon = 0;
                } else {
                    combatscreen.setAttackmove(this.combatscreen.getAlliePokemon().move3());
                    this.turnover = true;
                }
            }
            if (this.cursorY == 152 && this.cursorX == 480 && !infight) {
                if (!this.infight) {
                    this.choosingBag = true;
                    this.cursorY = 465;
                    this.cursorX = 550;
                    this.atpokemon = 0;
                } else {
                    combatscreen.setAttackmove(this.combatscreen.getAlliePokemon().move4());
                    this.turnover = true;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            infight = false;
        }
    }

    public void message(String message, int nova) {
        // 1 for 75 renders message
        // 2 for non-stop message
        if (nova == 1) {
            this.dlzkamess = 0;
        }
        if (nova == 2) {
            this.continuous = true;
            this.dlzkamess = 0;
        }
        if (message != null) {
            this.message = message;

            if (this.dlzkamess <= 75 || this.continuous) {
                font.draw(batch, this.message, 150, 200);
                dlzkamess++;
            } else if (!this.continuous) {
                this.message = null;
            }
        }
    }

    private void choosePokemon() {
        numberid = 0;
        int y = 480;
        chooselogo.draw(batch);

        //        for(int i = 0; i <- player.getSizeofBag(); i++){
        //            text.draw(batch, player.getpokemon(i).getName(), 565, y);
        //            if(player.getpokemon(i).getHP()==0){
        //                text.draw(batch, "DEAD", 655, y);
        //            } else
        //            text.draw(batch, "Hp:" + player.getpokemon(i).getHP() + " Lv:" + player.getpokemon(i).getLevel(), 655, y);
        //            y -= 30;
        //        }

        while (player.getSizeofBag() != numberid) {
            text.draw(batch, player.getpokemon(numberid).getName(), 565, y);
            if (player.getpokemon(numberid).getHP() == 0) {
                text.draw(batch, "DEAD", 655, y);
            } else
                text.draw(batch, "Hp:" + player.getpokemon(numberid).getHP() + " Lv:" + player.getpokemon(numberid).getLevel(), 655, y);
            numberid++;
            y -= 30;
        }
        posledny = y + 30;
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) && !this.haveToChoose) {
            this.choosingPoke = false;
            this.cursorX = 590;
            this.cursorY = 152;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && this.cursorY < 480 - 15) {
            this.cursorY += 30;
            this.atpokemon -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && this.cursorY > posledny - 10) {
            this.cursorY -= 30;
            this.atpokemon += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (player.getpokemon(this.atpokemon).getHP() == 0) {
                message("This Pokemon is dead", 1);
            } else {
                this.combatscreen.setAlliePokemon(player.getpokemon(this.atpokemon));
                this.turnover = true;
                this.choosingPoke = false;
                this.waschoosing = true;
                this.haveToChoose = false;
                this.choosingBag = false;
                combatscreen.noLongerNeedtoSwitch();
                message(player.getpokemon(this.atpokemon).getName() + " ,I choose you!", 1);
                cursorX = 480;
                cursorY = 202;
            }
        }
    }

    public boolean isTurnOver() {
        return this.turnover;
    }

    public void setTurn(boolean stav) {
        this.turnover = stav;
    }

    public boolean wasIchoosing() {
        return this.waschoosing;
    }

    public boolean amIchoosing() {
        return this.choosingPoke;
    }

    public void ineedtoChoose() {
        this.choosingPoke = true;
        this.haveToChoose = true;
        this.cursorY = 465;
        this.cursorX = 550;
        this.atpokemon = 0;
    }

    private void chooseItem() {
        numberid = 0;
        int y = 480;
        chooselogo.draw(batch);
        while (player.getSizeofITEMbag() != numberid) {
            text.draw(batch, player.getitem(numberid).getName() + ": X" + player.getitem(numberid).getUses(), 565, y);
            numberid++;
            y -= 30;
        }
        posledny = y + 30;
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            this.choosingPoke = false;
            this.choosingBag = false;
            this.cursorX = 590;
            this.cursorY = 152;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && this.cursorY < 480 - 15) {
            this.cursorY += 30;
            this.atpokemon -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && this.cursorY > posledny - 10) {
            this.cursorY -= 30;
            this.atpokemon += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (ItemParser.parse(player.getitem(this.atpokemon)) == 1 || ItemParser.parse(player.getitem(this.atpokemon)) == 0) {
                if ("Pokeball".equals(player.getitem(this.atpokemon).getName())) {
                    combatscreen.pokeCatched();
                }
                this.message("Player used: " + player.getitem(this.atpokemon).getName(), 1);
                player.getitem(this.atpokemon).use(player, this.combatscreen.getAlliePokemon(), this.combatscreen.getEnemyPokemon());
                if (player.getitem(this.atpokemon).getUses() == 0) {
                    player.checkAndEmpty();
                }
                this.turnover = true;
                this.choosingPoke = false;
                this.waschoosing = true;
                this.haveToChoose = false;
                this.choosingBag = false;
                cursorX = 480;
                cursorY = 202;
            } else
                this.message("Cannot be used in combat", 1);
        }
    }
}
