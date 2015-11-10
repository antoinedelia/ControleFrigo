/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regulation;

public class Regulator {
            
    public RegulatorConnector makeConnection(float valueT, float valueH, boolean condense) {
        RegulatorConnector connector = new RegulatorConnector(valueT, valueH, condense);
        return connector;
    }
}
