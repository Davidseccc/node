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
public class RelayBoard {

    private String identifier;
    private String description;
    private String name;
    private boolean state;

    public RelayBoard() {
    }

    public RelayBoard(String identifier, String description, String name, boolean state) {
        this.identifier = identifier;
        this.description = description;
        this.name = name;
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public void changeState(){
        
    }

    

}
