package Acquisition;

import java.math.BigDecimal;
import java.util.Random;

public class Mock implements Acquisition{
	
	boolean enable;
	Random random = new Random();
	private float humidite = 0, indoorTemp = 26, outdoorTemp = random.nextFloat() * (35 - 15) + 15;
	
	@Override
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public boolean getEnable() {
		return this.enable;
	}
	
	public AcquisitionConnector makeConnection()
	{
		//this.humidite = Float.parseFloat(Integer.toString(random.nextInt(100 - 10 + 1) + 10));
		//this.indoorTemp = Float.parseFloat(Integer.toString(random.nextInt(28 - 16 + 1) + 16));
		//this.outdoorTemp = Float.parseFloat(Integer.toString(random.nextInt(35 - 15 + 1) + 15));
		this.humidite = random.nextFloat() * (80 - 78) + 78;
		//this.indoorTemp = random.nextFloat() * (23 - 15) + 15;
		float tempConsigne = 15;
		//System.out.println(Singleton.getInstance().getMainWindow().getSliderValue());
		tempConsigne = Float.parseFloat(Integer.toString(Singleton.getInstance().getMainWindow().getSliderValue()));
		if(this.indoorTemp > tempConsigne)
			if(this.outdoorTemp-12 < this.indoorTemp)
				this.indoorTemp -= 0.1;
			else
				this.indoorTemp += 0.1;
		else
			this.indoorTemp += 0.1;
		
		//this.outdoorTemp = random.nextFloat() * (35 - 15) + 15;
		BigDecimal bd1 = new BigDecimal(Float.toString(humidite));
		BigDecimal bd2 = new BigDecimal(Float.toString(indoorTemp));
		BigDecimal bd3 = new BigDecimal(Float.toString(outdoorTemp));
	    bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
	    this.humidite = bd1.floatValue();
	    bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
	    this.indoorTemp = bd2.floatValue();
	    bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
	    this.outdoorTemp = bd3.floatValue();
		
		AcquisitionConnector connector = new AcquisitionConnector(this.humidite,this.indoorTemp,this.outdoorTemp);
		return connector;
	}
	
	@Override
	public float getHumidite() {
		return this.humidite;
	}

	@Override
	public float getIndoorTemp() {
		return this.indoorTemp;
	}

	@Override
	public float getOutdoorTemp() {
		BigDecimal bd3 = new BigDecimal(Float.toString(this.outdoorTemp));
	    bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
	    this.outdoorTemp = bd3.floatValue();
		return this.outdoorTemp;
	}
	
	
	

}
