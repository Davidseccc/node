/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.uhk.secda1.node01.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import cz.uhk.secda1.node01.model.OpenWeatherMap;
import cz.uhk.secda1.node01.model.OpenWeatherMapSky;
import cz.uhk.secda1.node01.model.OpenWeatherMapTemp;
import cz.uhk.secda1.node01.model.OpenWeatherMapWind;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class OpenWeatherMapParser {

    public static final String API_KEY = "745cb134b32dc06fcd2457d8d62b6c1d";
    public static final String LOCATION = "Pravy";

    /**
 * Parse data from OpenWeatherMap
 * @return OpenWeatherMap
 */
    
    public OpenWeatherMap parse() {
        OpenWeatherMap ow = new OpenWeatherMap();

        try {
            String sURL = "http://api.openweathermap.org/data/2.5/find?q=" 
                            + LOCATION + "&units=metric&appid=" + API_KEY;

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            ow = parseJSON(root);
 
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            Logger.getLogger(OpenWeatherMapParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ow;
    }

    /**
     * Parse Json String to specified Objects 
     * @params root element
     * @return OpenWeatherMap
    */
    private OpenWeatherMap parseJSON(JsonElement root) throws JsonSyntaxException {
        OpenWeatherMap ow;
        JsonObject rootobj = root.getAsJsonObject();
        JsonArray jarray = rootobj.getAsJsonArray("list");
        JsonObject listObject = jarray.get(0).getAsJsonObject();
        String name = listObject.get("name").toString();
        JsonObject mo = jarray.get(0).getAsJsonObject();
        
        OpenWeatherMapTemp temp = new Gson().fromJson(mo.getAsJsonObject("main"),
                OpenWeatherMapTemp.class);
        OpenWeatherMapWind wind = new Gson().fromJson(mo.getAsJsonObject("wind"),
                OpenWeatherMapWind.class);
        OpenWeatherMapSky sky = new Gson().fromJson(mo.getAsJsonArray("weather").get(0).getAsJsonObject(),
                OpenWeatherMapSky.class);
        
        ow = new OpenWeatherMap(name, temp, wind, sky);
        return ow;
    }

}
