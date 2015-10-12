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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pokemons.Charmander;
import com.mygdx.pokemons.Psyduck;
import java.util.Random;

/**
 * @author Ján
 */
class Play implements Screen {
    private TiledMap mainmap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private final Player player;
    private TiledMapTileLayer blockedmap;
    private boolean isBlocked;
    private float OldX;
    private float OldY;
    private boolean danger;
    private final MyGdxGame game;
    private Texture pright;
    private Texture pleft;
    private Texture pdown;
    private Texture pup;
    private boolean movingup = false;
    private boolean movingleft = false;
    private boolean movingright = false;
    private boolean movingdown = false;
    private int pocetk = 0;
    private boolean ismoving;
    private Texture pdownleft;
    private Texture pdownright;
    private Texture pleftleft;
    private Texture pleftright;
    private Texture pupleft;
    private Texture pupright;
    private Texture prightleft;
    private Texture prightright;
    private final Sound combatsound;
    private boolean foot;
    private boolean danger2;
    private boolean menurender = false;
    private final long start;
    private long elapsedTimeMillis;
    private final PokeFactory pokefactory;
    private TiledMap homemap;
    private MapObjects BlockedObjects;
    private MapObjects DangerObjects;
    private MapObjects Danger2Objects;
    private MapObjects BlockedObjectshome;
    private OrthogonalTiledMapRenderer Renderedhome;
    private OrthogonalTiledMapRenderer Renderedmap;
    private boolean homeSwitch;

    public Play(MyGdxGame game, Player player) {
        this.game = game;
        this.player = player;
        combatsound = Gdx.audio.newSound(Gdx.files.internal("BattleSound.mp3"));
        start = System.currentTimeMillis();
        pokefactory = new PokeFactory();
    }

    @Override
    public void show() {
        mainmap = new TmxMapLoader().load("maps/poketest.tmx");
        homemap = new TmxMapLoader().load("maps/home.tmx");
        blockedmap = (TiledMapTileLayer) mainmap.getLayers().get("walls");
        Renderedhome = new OrthogonalTiledMapRenderer(homemap);
        Renderedmap = new OrthogonalTiledMapRenderer(mainmap);
        if (!this.homeSwitch) {
            renderer = Renderedmap;
        } else {
            renderer = Renderedhome;
        }
        camera = new OrthographicCamera();
        pright = new Texture("pright.png");
        pleft = new Texture("pleft.png");
        pdown = new Texture("pdown.png");
        pup = new Texture("pup.png");
        pdownleft = new Texture("moving/downleft.png");
        pdownright = new Texture("moving/downright.png");
        pleftleft = new Texture("moving/leftleft.png");
        pleftright = new Texture("moving/leftright.png");
        pupleft = new Texture("moving/upleft.png");
        pupright = new Texture("moving/upright.png");
        prightleft = new Texture("moving/rightleft.png");
        prightright = new Texture("moving/rightright.png");
        BlockedObjects = mainmap.getLayers().get("Block").getObjects();
        BlockedObjectshome = homemap.getLayers().get("block").getObjects();
        DangerObjects = mainmap.getLayers().get("danger").getObjects();
        Danger2Objects = mainmap.getLayers().get("danger2").getObjects();
    }

    @Override
    public void render(float delta) {
        //   System.out.println(this.player.getX() + "  and Y:" + this.player.getY());
        combatsound.stop();
        Random random = new Random();
        if (player.getX() == 32 && player.getY() == 192) {
            player.setX(32);
            player.setY(96);
            renderer = Renderedhome;
            homeSwitch = true;
        }
        int chance = 0;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTimeMillis = System.currentTimeMillis() - start;
        isBlocked = false;
        danger = false;
        danger2 = false;
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        if (menurender) {
            game.setScreen(new Menu(game, this, player, null));
        }
        player.setPosition(player.getX(), player.getY());
        chance = movement(chance, random);
        renderer.getBatch().end();
        if (!homeSwitch) {
            CollisionOutside(chance);
        } else {
            for (RectangleMapObject rectangleObject : BlockedObjectshome.getByType(RectangleMapObject.class)) {
                Rectangle rectangle = rectangleObject.getRectangle();
                if (Intersector.overlaps(rectangle, player.getBoundingRectangle()) && !this.ismoving) {
                    isBlocked = true;
                }
            }
            if (isBlocked) {
                player.setX(OldX);
                player.setY(OldY);
            }
            if (player.getX() == 16 && player.getY() == 96) {
                this.renderer = Renderedmap;
                this.homeSwitch = false;
                this.player.setX(32);
                this.player.setY(176);
                this.player.setTexture(pdown);
            }
        }
        camera.zoom = (float) 0.5;
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update(true);
    }

    private void CollisionOutside(int chance) {
        /* checks for collision with the player rectangle */
        for (RectangleMapObject rectangleObject : BlockedObjects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, player.getBoundingRectangle()) && !this.ismoving) {
                isBlocked = true;
            }
        }
        for (RectangleMapObject rectangleObject : DangerObjects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle2 = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle2, player.getBoundingRectangle())) {
                danger = true;
            }
        }
        for (RectangleMapObject rectangleObject : Danger2Objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle2 = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle2, player.getBoundingRectangle())) {
                danger2 = true;
            }
        }
        if (isBlocked) {
            player.setX(OldX);
            player.setY(OldY);
        }
        if (danger) {
            if (chance == 1) {
                combatsound.loop((float) 0.1);
                game.setScreen(new Combat(game, this, player, pokefactory.getPoke("Caterpie", false), null));
            }
            if (chance == 2) {
                combatsound.loop((float) 0.1);
                game.setScreen(new Combat(game, this, player, pokefactory.getPoke("Pidgey", false), null));
            }
        }
        if (danger2) {
            if (chance == 1) {
                combatsound.loop((float) 0.1);
                game.setScreen(new Combat(game, this, player, new Psyduck(8, false), null));
            }
            if (chance == 2) {
                combatsound.loop((float) 0.1);
                game.setScreen(new Combat(game, this, player, new Charmander(8, false), null));
            }
        }
    }

    private int movement(int chance, Random random) {
        if ((Gdx.input.isKeyPressed(Input.Keys.UP) || this.movingup) && !(this.movingdown) && !(this.movingleft) && !(this.movingright)) {
            if (pocetk == 0) {
                this.OldX = player.getX();
                this.OldY = player.getY();
            }
            player.moveup();
            this.ismoving = true;
            pocetk++;
            this.movingup = true;
            if (pocetk == 16) {
                chance = random.nextInt(10) + 1;
                this.movingup = false;
                pocetk = 0;
                ismoving = false;
                player.setTexture(pup);
            }
            if (pocetk == 8) {
                if (foot) {
                    player.setTexture(pupleft);
                    foot = false;
                } else {
                    player.setTexture(pupright);
                    foot = true;
                }
            }
        } else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || this.movingleft) && !(this.movingup) && !(this.movingright) && !(this.movingdown)) {
            if (pocetk == 0) {
                this.OldX = player.getX();
                this.OldY = player.getY();
            }
            player.moveleft();
            this.ismoving = true;
            pocetk++;
            this.movingleft = true;
            if (pocetk == 16) {
                chance = random.nextInt(10) + 1;
                this.movingleft = false;
                pocetk = 0;
                ismoving = false;
                player.setTexture(pleft);
            }
            if (pocetk == 8) {
                if (foot) {
                    player.setTexture(pleftleft);
                    foot = false;
                } else {
                    player.setTexture(pleftright);
                    foot = true;
                }
            }
        } else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || this.movingright) && !(this.movingup) && !(this.movingleft) && !(this.movingdown)) {
            if (pocetk == 0) {
                this.OldX = player.getX();
                this.OldY = player.getY();
            }
            player.moveright();
            this.ismoving = true;
            pocetk++;
            this.movingright = true;
            if (pocetk == 16) {
                chance = random.nextInt(10) + 1;
                this.movingright = false;
                pocetk = 0;
                ismoving = false;
                player.setTexture(pright);
            }
            if (pocetk == 8) {
                if (foot) {
                    player.setTexture(prightleft);
                    foot = false;
                } else {
                    player.setTexture(prightright);
                    foot = true;
                }
            }
        } else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || this.movingdown) && !(this.movingup) && !(this.movingright) && !(this.movingleft)) {
            if (pocetk == 0) {
                this.OldX = player.getX();
                this.OldY = player.getY();
            }
            player.movedown();
            pocetk++;
            this.movingdown = true;
            ismoving = true;
            if (pocetk == 16) {
                chance = random.nextInt(10) + 1;
                this.movingdown = false;
                pocetk = 0;
                ismoving = false;
                player.setTexture(pdown);
            }
            if (pocetk == 8) {
                if (foot) {
                    player.setTexture(pdownleft);
                    foot = false;
                } else {
                    player.setTexture(pdownright);
                    foot = true;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            menurender = true;
        }
        return chance;
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
        mainmap.dispose();
        renderer.dispose();
        player.getTexture().dispose();
        pdown.dispose();
        pup.dispose();
        pleft.dispose();
        pright.dispose();
        pdownleft.dispose();
        pdownright.dispose();
        pleftleft.dispose();
        pleftright.dispose();
        pdownleft.dispose();
        pdownright.dispose();
        pupleft.dispose();
        pupright.dispose();
        //    combatsound.dispose();
    }

    public void menuStop() {
        this.menurender = false;
    }

    public void setDirectionTexture() {
        if (this.player.getDirection() == 90) {
            this.player.setTexture(pright);
        }
        if (this.player.getDirection() == 180) {
            this.player.setTexture(pdown);
        }
        if (this.player.getDirection() == 270) {
            this.player.setTexture(pleft);
        }
        if (this.player.getDirection() == 0) {
            this.player.setTexture(pup);
        }
    }

    public OrthogonalTiledMapRenderer renderBackground() {
        return renderer;
    }

    public int getHours() {
        return (int) (elapsedTimeMillis / (60 * 60 * 1000F));
    }

    public int getMin() {
        return (int) (elapsedTimeMillis / (60 * 1000F));
    }
}
