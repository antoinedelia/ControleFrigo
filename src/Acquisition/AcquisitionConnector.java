package Acquisition;

public class AcquisitionConnector {
	private float humidite;
	private float indoorTemp;
	private float outdoorTemp;
	
	public AcquisitionConnector(float _humidite, float _indoorTemp, float _outdoorTemp) {
		this.humidite = _humidite;
		this.indoorTemp = _indoorTemp;
		this.outdoorTemp = _outdoorTemp;
	}
	
	public float getHumidite()
	{
		return this.humidite;
	}
	
	public float getIndoorTemp()
	{
		return this.indoorTemp;
	}
	
	public float getOutdoorTemp()
	{
		return this.outdoorTemp;
	}
	
}
