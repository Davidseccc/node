/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.uhk.secda1.node01.model;

/**
 *
 * @author David
 */
public class OpenWeatherMapWind {

    float speed;
    float deg;

    public OpenWeatherMapWind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    
}


