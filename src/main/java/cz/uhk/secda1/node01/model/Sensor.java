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
public class Sensor implements ISensor{

    private String name;
    private String identifier;
    private String description;
    private double value;
    
    /**
     *
     * @return Number
     */
    @Override
    public Number loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUnitString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
