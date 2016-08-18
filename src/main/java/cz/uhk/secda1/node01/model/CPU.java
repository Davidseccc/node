package cz.uhk.secda1.node01.model;

import cz.uhk.secda1.node01.service.DeviceInfo;

/**
 * Node main runnable class.
 *
 * @author Šec David
 */
public class CPU {
    private int cpusensor_ID;
    private float value;
    
    
    public CPU(int cpusensor_ID) {
        this.cpusensor_ID = cpusensor_ID;
    }
    public CPU() {
    }
    
    public Number getCPUTemp(){
        DeviceInfo di = new DeviceInfo();
        this.value = di.getCPUTemp();
        return value;
    }



    public int getCpusensor_ID() {
        return cpusensor_ID;
    }

    public void setCpusensor_ID(int cpusensor_ID) {
        this.cpusensor_ID = cpusensor_ID;
    }

        public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    
    
    
}
