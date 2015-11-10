/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

public class JavaArduinoModel {
    
    public static boolean checkTemperature(float indoorTemp, float selectedTemp) {
    	//Si temp frigo plus chaude que temp voulu
       if(indoorTemp > selectedTemp){
//    	   System.out.println("Temp : true");
    	   return true;		//On continu de refroidir
    	 
       }
       else{
//    	   System.out.println("Temp : false");
    	   return false;	//On stop de refroidir
       }
       
    }
    
    public static boolean checkCondensation(float humidite, float currentTemp) {
        
        //boolean alertCondensation = false;
        float rosee = 0.0f;
       // rosee = (float) Math.pow((double) humidite, (double) (1/8) ) * (112.0f + 0.9f * currentTemp) + 0.1f * currentTemp - 112;
        float alpha = (float) (((17.27f * currentTemp) / (237.7f + currentTemp)) + Math.log(humidite));
        rosee = (237.7f * alpha) / (17.27f - alpha);
//        System.out.println("rosee :" + rosee);
//        System.out.println("temp :" + currentTemp);
//        System.out.println("humidite :" + humidite);
        if (rosee > currentTemp){
//         	System.out.println("Humid : true");
        	return true; //Pas de condensation, on continu de refroidir
        }
        else{
//        	System.out.println("Humid : false");
        	return false; //Condensation, on stop
        }
    }
    
    public static String checkAlimentation(boolean temp){
    	if(temp == false){
    		return "0";
    	}
    	else{
    		return "1";
    	}
    }
    
    
}
    
