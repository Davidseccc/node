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
public class OpenWeatherMap {

    private String name;
    private OpenWeatherMapTemp temperature;
    private OpenWeatherMapWind wind;
    private OpenWeatherMapSky sky;

    public OpenWeatherMap(String name, OpenWeatherMapTemp temperature, OpenWeatherMapWind wind, OpenWeatherMapSky sky) {
        this.name = name;
        this.temperature = temperature;
        this.wind = wind;
        this.sky = sky;
    }

    public OpenWeatherMap() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSky(OpenWeatherMapSky sky) {
        this.sky = sky;
    }

    public OpenWeatherMapSky getSky() {
        return sky;
    }

    public void setTemperature(OpenWeatherMapTemp temperature) {
        this.temperature = temperature;
    }

    public OpenWeatherMapTemp getTemperature() {
        return temperature;
    }

    public void setWind(OpenWeatherMapWind wind) {
        this.wind = wind;
    }

    public OpenWeatherMapWind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return "[WIND]:" + wind.speed + "[LOC]:" + name + "[TEMP]:" + temperature.temp + "[SKY]:" + sky.main;//To change body of generated methods, choose Tools | Templates.
    }

    public boolean canOpenWindow() {
        if (sky.main.equals("Clear") && temperature.temp_min >= 20) {
            return true;
        } else {
            return false;
        }
    }

}
