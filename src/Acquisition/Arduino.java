package Acquisition;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;
import java.util.Observable;

public class Arduino extends Observable implements SerialPortEventListener, Acquisition {
	//String test2 = Acquisition.TestClass.Yolo();
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

	public SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = {
			//"/dev/tty.usbserial-A9007UX1", // Mac OS X
			//"/dev/ttyUSB0", // Linux
			"COM7", // Windows
	};
	private float humidite;
	private float indoorTemp = 26;
	private float outdoorTemp = 0;
	public static BufferedReader input;
	public static OutputStream output;
	/** Milliseconds to block while waiting for port open */
	public static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	public static final int DATA_RATE = 9600;
	
	public String alimentation = "1";

	public void initialize() {
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();
			char ch = 1;
			output.write(ch);


			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				//Get Arduino Data
				String inputLine=input.readLine();
				String[] values = inputLine.split("_");
				Double[] valuesNum = new Double[3];
				valuesNum[0] = Double.parseDouble(values[0]);
				valuesNum[1] = Double.parseDouble(values[1]);
				valuesNum[2] = Double.parseDouble(values[2]);
				//writeData(test2);
				writeData(alimentation);
				this.humidite = Float.parseFloat(valuesNum[0].toString());
				this.indoorTemp =  Float.parseFloat(valuesNum[1].toString());
				this.outdoorTemp =  Float.parseFloat(valuesNum[2].toString());
				
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	
	

	public static synchronized void writeData(String data) {
		//System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
			//output.flush();
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}
	   

	public AcquisitionConnector makeConnection()
	{
		AcquisitionConnector connector = new AcquisitionConnector(this.humidite,this.indoorTemp,this.outdoorTemp);
		return connector;
	}
	
	public void setAlimentation(String alim)
	{
		this.alimentation = alim;
	}
	

	@Override
	public void setEnable(boolean set) {

			setAlimentation(set ? "1" : "0");
		
	}

	@Override
	public boolean getEnable() {
		return "1".equals(this.alimentation);
	}

	@Override
	public float getHumidite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getIndoorTemp() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public float getOutdoorTemp()
	{
		return this.outdoorTemp;
	}
}


