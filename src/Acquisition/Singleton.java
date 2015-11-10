package Acquisition;
import javax.swing.JFrame;

import Presentation.Commande;
import Presentation.MainWindow;
import Regulation.Regulator;

public class Singleton {
	
	private static Singleton mInstance = null;
	
	private Arduino arduino;
	private MainWindow mainWindow;
	private Regulator regulator;
	private Commande commande;
	private Mock mock;
	
	private Singleton(){
		arduino = new Arduino();
		mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		regulator = new Regulator();
		commande = new Commande();
		mock = new Mock();
	
	}
	
	public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }
	
	public Arduino getArduino(){ return this.arduino; }
	public MainWindow getMainWindow(){ return this.mainWindow; }
	public Regulator getRegulator(){ return this.regulator; }
	public Commande getCommande() { return this.commande; }
	public Mock getMock() { return this.mock; }


}
