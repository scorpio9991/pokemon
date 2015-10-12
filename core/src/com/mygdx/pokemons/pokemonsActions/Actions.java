/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.pokemons.pokemonsActions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ján
 */
public class Actions {
    private final Map<String, AbstractAttack> attacks;

    public Actions() {
        this.attacks = new HashMap<String, AbstractAttack>();
        this.attacks.put("Scratch", new ScratchAttack());
        this.attacks.put("Bite", new BiteAttack());
        this.attacks.put("Ember", new EmberAttack());
        this.attacks.put("Bubble", new BubbleAttack());
        this.attacks.put("Growl", new GrowlAttack());
        this.attacks.put("Splash", new SplashAttack());
        this.attacks.put("QuickAttack", new QuickAttack());
        this.attacks.put("Tackle", new Tackle());
    }

    public void attack(String name, Pokemon allie, Pokemon enemy) {
        AbstractAttack attack = this.attacks.get(name);
        if (attack != null) {
            attack.execute(allie, enemy);
        }
    }

    private abstract class AbstractAttack {
        public abstract void execute(Pokemon allie, Pokemon enemy);
    }

    private class ScratchAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP() - allie.getAttStat());
        }
    }

    private class BiteAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP() - allie.getAttStat());
        }
    }

    private class EmberAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            if ("Grass".equals(enemy.getAttribute()))
                enemy.setHP(enemy.getHP() - 2 * allie.getAttStat());
            else
                enemy.setHP((int) (enemy.getHP() - 1.5 * allie.getAttStat()));
        }
    }

    private class BubbleAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            if ("Fire".equals(enemy.getAttribute()))
                enemy.setHP(enemy.getHP() - 2 * allie.getAttStat());
            else
                enemy.setHP((int) (enemy.getHP() - 1.5 * allie.getAttStat()));
        }
    }

    private class GrowlAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP() - 20);
        }
    }

    private class SplashAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP());
        }
    }

    private class QuickAttack extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP() - allie.getAttStat());
        }
    }

    private class Tackle extends AbstractAttack {
        @Override
        public void execute(Pokemon allie, Pokemon enemy) {
            enemy.setHP(enemy.getHP() - allie.getAttStat());
        }
    }
}
