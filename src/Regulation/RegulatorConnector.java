/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regulation;

import java.util.Observable;

public class RegulatorConnector extends Observable {
    
    private float temperature;
    private float humidite;
    private boolean condense;
    
    RegulatorConnector(float _temperature, float _humidite, boolean _condense)
    {
        this.temperature = _temperature;
        this.humidite = _humidite;
        this.condense = _condense;
    }
    
    public float getTemperature()
    {
        return this.temperature;
    }
    
    public float getHumidite()
    {
        return this.humidite;
    }
    
    public boolean getCondense()
    {
    	return this.condense;
    }
    
    public void dump()
    {
    	System.out.println(this.temperature);
    	System.out.println(this.humidite);
    }
}
