package Acquisition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import Acquisition.Arduino;;

public class TestClass
{
	public static BufferedReader input;
	public static OutputStream output;
	public static String test = "0";

//	public static synchronized void writeData(String data) {
//		System.out.println("Sent: " + data);
//		try {
//			output.write(data.getBytes());
//		} catch (Exception e) {
//			System.out.println("could not write to port");
//		}
//	}
	public static String Yolo()
	{
		return test;
	}

	public static void main(String[] ag)
	{
		
//		try
//		{
//			Acquisition obj = new Acquisition();
//			int c=0;
//			obj.initialize();
//			input = Acquisition.input;
//			output = Acquisition.output;
//			InputStreamReader Ir = new InputStreamReader(System.in);
//			BufferedReader Br = new BufferedReader(Ir);
//			while (c!=3)
//			{
//				output.write(0);
//				System.out.println("Allumer ou éteindre frigo ?");
//				System.out.println("1.Allumer");
//				System.out.println("2.Éteindre");
//				System.out.print("Enter your choice :");
//				c = Integer.parseInt(Br.readLine());
//
//				switch(c)
//				{
//				case 1:
//					writeData("1");
//					break;
//
//				case 2:
//					writeData("0");
//					break;
//
//				case 3:
//					System.exit(0);
//				}
//			}
//
//
//			String inputLine=input.readLine();
//			System.out.println(inputLine );
//
//			obj.close();
//
//		}
//		catch(Exception e){}

	}
}