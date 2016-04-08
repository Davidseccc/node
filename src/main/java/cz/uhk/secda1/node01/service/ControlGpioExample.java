/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.uhk.secda1.node01.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.script.ScriptException;

public class ControlGpioExample {

    public static final String ON_SCRIPT = "on.py";
    public static final String OFF_SCRIPT = "off.py";
    public static final String SCRIPTS_PATH = "/home/pi/";

    public ControlGpioExample() {

    }

    public void switchOn() {
        execCommand(ON_SCRIPT);
    }

    public void switchOff()  {
        execCommand(OFF_SCRIPT);
    }

    private void execCommand(String name) {
        String cmd = "python " + SCRIPTS_PATH + name;
        String ret = "";
        String output = "";

        try {
            String line;
            Process p = Runtime.getRuntime().exec(cmd.split(" "));
            p.waitFor();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                while ((line = input.readLine()) != null) {
                    output += (line + '\n');
                }
            }
            //System.out.println(output);

        } catch (IOException | InterruptedException e) {
        }
    }

}
