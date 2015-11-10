/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Acquisition.Singleton;


/**
 *
 * Génère la fenètre d'affichage principale de l'application.
 * @param Panel mPanel (mainPanel)
 * @param Panel tPanel (temperaturePanel)
 * @param XYSeries seriesT (serie de points de température)
 * @param XYSeries seriesH (série de points d'humidité)
 */
public class MainWindow extends JFrame {
    
    private Panel mPanel = new Panel();
    private Panel tPanel= new Panel();
    private XYSeries seriesT = new XYSeries("Graphique de température");  
    private XYSeries seriesH = new XYSeries("Graphique d'humidité");
    private JSlider tSlider = new JSlider();
    private Label tLabel;
    private Label cLabel;
    
    /** Construction de la fenêtre d'affichage principale */
    public MainWindow() {
        super();
        setTitle("Interface d'administration du frigo");
        setSize(800,1000);
       
        Panel mainPanel = new Panel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        add(mainPanel);
        this.mPanel = mainPanel;
        addSlider();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
             }
          });
        
         show();
          
    } 
    
    /** Ajout à la fenêtre de la liste de ports COM détectées par l'ordinateur pour récupérer les données */
    public void addCOMPorts(ArrayList<String> Ports) {
         Panel panel = new Panel();
         Label label = new Label("Ports COM");
         
         Choice COMPorts = new Choice();
         
        for (String port : Ports)
         {
             COMPorts.add(port);
         }  
            
            panel.add(label);
            panel.add(COMPorts);
      
            mPanel.add(panel);
            
           
            show();
    }
    
    /** Ajout à la fenêtre des valeurs de température et d'humidité en temps réel */
    public void showLiveValues(float temperature, float humidite, boolean condense)
    {
        Panel panel = new Panel();
        Label labelTemp = new Label("Température du frigo : " + temperature + " C°");
        Label labelOutdoorTemp = new Label("Température extérieure : " + Singleton.getInstance().getMock().getOutdoorTemp() + " C°");
        Label labelHumid = new Label("Humidité à l'intérieur du frigo : " + humidite + " %");
    	Label condensation = new Label("Alerte de condensation");
    	
    	condensation.setForeground(Color.red);

      //  addCondensation();
        panel.setLayout(new FlowLayout());
        panel.add(labelTemp);
        panel.add(labelOutdoorTemp);
        panel.add(labelHumid);
        if(condense)
        	panel.add(condensation);
        else
        {
        	panel.add(condensation);
        	condensation.hide();
        }
      //  panel.add(cLabel);
        
        this.tPanel = panel;
        mPanel.add(panel);
        
        
        show();
    }
    
    /** Update des valeurs de température et d'humidité en temps réel */
    public void updateLiveValues(float temperature, float humidite, boolean condense)
    {

         Component[] labels = this.tPanel.getComponents();
         
         if (labels.length  > 0)
         {

            if (labels[0] instanceof Label)
            {
            Label nLabelTemp = (Label) labels[0];
            nLabelTemp.setText("Température du frigo : " + temperature + " C°");
            }
            
            if (labels[1] instanceof Label)
            {
            Label nLabelTemp = (Label) labels[1];
            nLabelTemp.setText("Température extérieure : " + Singleton.getInstance().getMock().getOutdoorTemp() + " C°");
            }


            if (labels[2] instanceof Label)
            {
            Label nLabelHumid= (Label) labels[2];
            nLabelHumid.setText("Humidité à l'intérieur du frigo : " + humidite + " %");
            }
            
            if (labels[3] instanceof Label)
            {
            Label nLabelCondense = (Label) labels[3];
            if(!condense)
            	nLabelCondense.hide();
            else
            	nLabelCondense.show();
            }
            show();
        }
        else
            showLiveValues(temperature,humidite,condense);
    }
    
     /** Ajout à la fenêtre des graphiques de température et d'humidité en temps réel */
    public void initGraphs()
    {
       XYSeriesCollection datasetT = new XYSeriesCollection();
       XYSeriesCollection datasetH = new XYSeriesCollection();
        
       datasetT.addSeries(seriesT);
       datasetH.addSeries(seriesH);
       
       XYPlot plotT = new XYPlot(datasetT, new NumberAxis(), new NumberAxis(), new DefaultXYItemRenderer() );
       XYPlot plotH = new XYPlot(datasetH, new NumberAxis(), new NumberAxis(), new DefaultXYItemRenderer() );
 
       //plot.setDataset(1, datasetT);
      // plot.setRenderer(1, new DefaultXYItemRenderer());
       plotH.getRenderer().setSeriesPaint(0, Color.BLUE);
       JFreeChart chartT = new JFreeChart("Graphique de température", JFreeChart.DEFAULT_TITLE_FONT, plotT,true);
       JFreeChart chartH = new JFreeChart("Graphique d'humidité", JFreeChart.DEFAULT_TITLE_FONT, plotH,true);
       
       ChartPanel gPanelT = new ChartPanel(chartT);
       ChartPanel gPanelH = new ChartPanel(chartH);
       
       mPanel.add(gPanelT);  
       mPanel.add(gPanelH);  
    }
    
    /** Update des graphiques de température et d'humidité en temps réel */
    public void updateGraphs(float temperature, float humidite)
    {
       XYSeriesCollection datasetT = new XYSeriesCollection();
       XYSeriesCollection datasetH = new XYSeriesCollection();
       
       
       seriesT.add((float) seriesT.getItemCount(), temperature);
       seriesH.add((float) seriesH.getItemCount(), humidite);
        
       datasetT.addSeries(seriesT);
       datasetH.addSeries(seriesH);
    }
    
    private void addSlider()
    {
        JSlider sliderTemp = new JSlider(12,26,26);
        sliderTemp.setLabelTable(sliderTemp.createStandardLabels(14));
        sliderTemp.setMinorTickSpacing(1);
        sliderTemp.setPaintLabels(true);
        sliderTemp.setPaintTicks(true);
        this.tSlider = sliderTemp;
        Label labelConsigne = new Label("Température de consigne : " + tSlider.getValue() + " C°");
        //Label labelOutdoorTemp = new Label(Singleton.getInstance().getAcquisition().getOutdoorTemp());
        this.tLabel = labelConsigne;
        mPanel.add(labelConsigne);
        mPanel.add(sliderTemp);
    }
    
    public int getSliderValue()
    {
        return this.tSlider.getValue();
    }
    
    public void updateSliderLabel(int temperature)
    {
       this.tLabel.setText("Température de consigne : " + temperature + " C°");
    }
}
