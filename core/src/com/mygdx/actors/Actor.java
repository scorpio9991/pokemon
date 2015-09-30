/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.actors;

/**
 * @author Ján
 */
public interface Actor {
    String getName();

    boolean isActive();

    void use();

    void setActivity(boolean active);
}
