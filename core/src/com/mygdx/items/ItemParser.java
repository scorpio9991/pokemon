/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.items;

/**
 * @author Ján
 */
public class ItemParser {
    public static int parse(Item item) {
        //0 means usable whenever
        //1 means usable only in combat
        //2 means usable only in menu
        if ("Potion".equals(item.getName())) {
            return 0;
        }
        if ("Rare Candy".equals(item.getName())) {
            return 2;
        }
        if ("Pokeball".equals(item.getName())) {
            return 1;
        } else {
        }
        return 3;
    }
}
