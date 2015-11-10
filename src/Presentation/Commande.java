package Presentation;

import Acquisition.Acquisition;
import Acquisition.AcquisitionConnector;
import Acquisition.Singleton;

public class Commande {
	private Acquisition acquisition;
	
	public Commande(){}
	
	public void setAcquisition(Acquisition acquisition)
	{
		this.acquisition = acquisition;
	}
	
	public Acquisition getAcquisition()
	{
		return this.acquisition;
	}
	
	public AcquisitionConnector makeConnection(){
		AcquisitionConnector connector = this.acquisition.makeConnection();
		//AcquisitionConnector connector = new AcquisitionConnector(this.acquisition.getHumidite(),this.acquisition.getIndoorTemp(), this.acquisition.getOutdoorTemp());
		return connector;
	}
	
	public void initialize()
	{
		Singleton.getInstance().getArduino().initialize();
	}
	
	public void setAlimentation(String alim)
	{
		Singleton.getInstance().getArduino().setAlimentation(alim);		
	}
	
	
}
