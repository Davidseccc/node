package cz.uhk.secda1.node01.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class OpenWeatherMapTemp {

    float humidity;

    float pressure;

    float temp;

    float temp_max;

    float temp_min;

    public OpenWeatherMapTemp(float humidity, float pressure, float temp, float temp_max, float temp_min) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }
    
    	// A method that converts temperature from Celsius degrees to Fahrenheit
	String getTemperatureInFahrenheit() {
		float temp = 32 + (this.temp * 9 / 5);
		return String.format("%.2f", temp);
	}
 

    
}