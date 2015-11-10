

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Regulation.RegulatorConnector;
import gnu.io.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

import Acquisition.Singleton;

public class JavaArduino implements SerialPortEventListener {
    
      private MainWindow mainWindow = Singleton.getInstance().getMainWindow();
      private ArrayList<String> listPorts = new ArrayList<>();
      private ArrayList<Point2D> listPoint = new ArrayList<>();
     
    /**
     * @param args the command line arguments
     */
        JavaArduino() throws InterruptedException  {
  
            detectPorts();
            mainWindow.initGraphs();
            
    }
    
    public void connectRegulator(RegulatorConnector r)
    {
        mainWindow.updateLiveValues(r.getTemperature(), r.getHumidite(), r.getCondense());
        mainWindow.updateGraphs(r.getTemperature(), r.getHumidite());
        mainWindow.updateSliderLabel(mainWindow.getSliderValue());
    }
    private void detectPorts() {
        //for containing the ports that will be found
        Enumeration ports;
        BufferedReader input;
        /** The output stream to the port */
        OutputStream output;
        /** Milliseconds to block while waiting for port open */
        int TIME_OUT = 2000;
        /** Default bits per second for COM port. */
        int DATA_RATE = 9600;
        
        //this is the object that contains the opened port
        CommPortIdentifier selectedPortIdentifier = null;
        SerialPort serialPort = null;
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            // Identification des ports 
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
          
            
            // Ajout des ports COM à notre liste de ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
                listPorts.add(curPort.getName());
        }
        
            mainWindow.addCOMPorts(listPorts);
    } 
    
    public int getSlider()
    {
        return mainWindow.getSliderValue();
    }
    
    @Override
    public void serialEvent(SerialPortEvent spe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
