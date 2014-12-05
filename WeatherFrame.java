import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
// TO DO: add text line that accepts zip code,
// Weather app using: worldweatheronline.com
// APPID (API key) d0e2e3ec8ebef1d4493a1da6fd5fc
public class WeatherFrame extends JFrame
{
   private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
   private JLabel image = new JLabel();
   private JTextField location = new JTextField("Enter a zip code or location");
   private JCheckBox tempF = new JCheckBox("Temperature (F)");
   private JCheckBox tempC = new JCheckBox("Temperature (C)");
   private JCheckBox windSpeedMPH = new JCheckBox("Wind Speed (MPH)");
   private JCheckBox windSpeedKPH = new JCheckBox("Wind Speed (KPH)");
   private JCheckBox windDir = new JCheckBox("Wind Direction");
   private JCheckBox precipChance = new JCheckBox("Precipitation Chance");
   private JCheckBox humidity = new JCheckBox("Humidity");
   private JCheckBox visibility = new JCheckBox("Visibility");
   private JCheckBox pressure = new JCheckBox("Pressure");
   private JCheckBox cloudCover = new JCheckBox("Cloud Clover");
   private JCheckBox feelsLikeF = new JCheckBox("Feels Like Temperature (F)");
   private JCheckBox feelsLikeC = new JCheckBox("Feels Like Temperature (C)");
   private JPanel mainPanel = new JPanel();
   private JButton getData = new JButton("Find Weather Data");
   private JTextArea temperatureData = new JTextArea("Weather information will be printed here");
   private WeatherGrabber weather = new WeatherGrabber();
   class getWeatherData implements ActionListener
   { 
      public void actionPerformed(ActionEvent e)
      {
         try {
            String data = "";
            NodeList list = weather.getCurrentWeatherData(location.getText());
            Image img = null;
            URL urlToImg = new URL(weather.getWeatherImage(list));
            img = ImageIO.read(urlToImg);
            ImageIcon icon = new ImageIcon(img);
            if(tempF.isSelected())
               data += "Temperature (F): " + weather.getTemperatureF(list) + " degrees Fahrenheit\n";
            if(tempC.isSelected())
               data += "Temperature (C): " + weather.getTemperatureC(list) + " degrees Celsius\n";
            if(windSpeedMPH.isSelected())
               data += "Wind Speed (MPH): " + weather.getWindSpeedMPH(list) + " miles per hour\n";
            if(windSpeedKPH.isSelected())
               data += "Wind Speed (KPH): " + weather.getWindSpeedKPH(list) + " kilometers per hour\n";
            if(windDir.isSelected())
               data += "Wind Direction: " + weather.getWindDirection(list) + "\n";
            if(precipChance.isSelected())
               data += "Percent chance of precipitation: " + weather.getPrecipitationPercent(list) + "%\n";
            if(humidity.isSelected())
               data += "Humidity Percentage: " + weather.getHumidityPercent(list) + "%\n";
            if(pressure.isSelected())
               data += "Pressure: " + weather.getPressure(list) + "mb\n";
            if(cloudCover.isSelected())
               data += "Cloud Coverage: " + weather.getCloudCover(list) + "%\n";
            if(feelsLikeF.isSelected())
               data += "Feels Like (F): " + weather.getFeelsLikeF(list) + " degrees Fahrenheit\n";
            if(feelsLikeC.isSelected())
               data += "Feels Like (C): " + weather.getFeelsLikeC(list) + " degrees Celsius\n";
            temperatureData.setText(data);
            image.setIcon(icon);
         } catch(Exception ex)
         {
            ex.printStackTrace();
         }
      }
   }
   public WeatherFrame(String title, int width, int height)
   {
      location.setPreferredSize(new Dimension(350, 20));
      getData.setPreferredSize(new Dimension(350, 30));
      mainPanel.add(location);
      checkBoxes.add(tempF);
      checkBoxes.add(tempC);
      checkBoxes.add(windSpeedMPH);
      checkBoxes.add(windSpeedKPH);
      checkBoxes.add(windDir);
      checkBoxes.add(precipChance);
      checkBoxes.add(humidity);
      checkBoxes.add(visibility);
      checkBoxes.add(pressure);
      checkBoxes.add(cloudCover);
      checkBoxes.add(feelsLikeF);
      checkBoxes.add(feelsLikeC);
      mainPanel.setLayout(new FlowLayout());
      setTitle(title);
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      temperatureData.setBackground(null);
      temperatureData.setEditable(false);
      getData.addActionListener(new getWeatherData());
      for(int i = 0; i < checkBoxes.size(); i++)
      {
         mainPanel.add(checkBoxes.get(i), BorderLayout.NORTH);
      }
      mainPanel.add(getData, BorderLayout.CENTER);
      mainPanel.add(temperatureData, BorderLayout.SOUTH);
      mainPanel.add(image);
      add(mainPanel);
      setVisible(true);
   }
}