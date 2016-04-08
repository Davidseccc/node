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
public class OpenWeatherMapSky {

    private static final String ICON_ADDR = "http://openweathermap.org/img/w/";

    
    int id;
    String main;
    String description;
    String icon;

    public OpenWeatherMapSky(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
    
    	// getIconAddress concatenates the base address and the specific code for
	// the icon
	public String getIconAddress() {
		return ICON_ADDR + this.icon + ".png";
	}
}
