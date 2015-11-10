/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Regulation.Regulator;
import Regulation.RegulatorConnector;
import java.io.OutputStream;
import Acquisition.Arduino;
import Acquisition.Mock;
import Acquisition.AcquisitionConnector;
import Acquisition.Singleton;

public class EntryPoint {
   
    private static JavaArduino application;
    private static Regulator reg;
    private static Commande com;
    public static OutputStream output;
    
    public static void main(String [] args) throws InterruptedException {
    	Commande commande = new Commande();
    	//commande.setAcquisition(new Arduino());
    	commande.setAcquisition(new Mock());
    	
    	
    	
        EntryPoint.application = new JavaArduino();
        EntryPoint.reg = Singleton.getInstance().getRegulator();
        EntryPoint.com = commande;
        
        if(commande.getAcquisition() == new Arduino())
        	com.initialize();
        
		Thread.sleep(500);
        while (!Thread.interrupted())
        {
        	AcquisitionConnector acqConnector = com.makeConnection();
        	RegulatorConnector regConnector = reg.makeConnection(acqConnector.getIndoorTemp(), acqConnector.getHumidite(), 
        			JavaArduinoModel.checkCondensation(acqConnector.getHumidite(), acqConnector.getIndoorTemp()));
        	String alim = JavaArduinoModel.checkAlimentation(JavaArduinoModel.checkTemperature(acqConnector.getIndoorTemp(),application.getSlider()));
        	//System.out.println(alim);
        	if(commande.getAcquisition() == new Arduino())
        		com.setAlimentation(alim);
        	
        	application.connectRegulator(regConnector);
        	Thread.sleep(1000);
        }
        
        com.getAcquisition().setEnable(false);
    }
}
