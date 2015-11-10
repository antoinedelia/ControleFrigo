package Acquisition;

public interface Acquisition {
	
	public void setEnable(boolean set);
	
	public boolean getEnable();
	
	public float getHumidite();
	
	public float getIndoorTemp();
	
	public float getOutdoorTemp();
	
	public AcquisitionConnector makeConnection();
	
	
	
	
}
