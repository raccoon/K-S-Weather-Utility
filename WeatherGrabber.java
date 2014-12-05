import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
*
*
**/
//http://api.worldweatheronline.com/free/v2/weather.ashx?q=54467&format=xml&num_of_days=0&key=d0e2e3ec8ebef1d4493a1da6fd5fc
public class WeatherGrabber
{
   private Document xmlDoc;
   public WeatherGrabber()
   {
   }
   public NodeList getCurrentWeatherData(String location)
   {
      NodeList children = null;
      location.replaceAll("\\s+", "%20");
      try
      {
         boolean hasError = false;
         Node node, node2;
         NodeList nodeList, nodeListCheck;
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
         xmlDoc = docBuilder.parse("http://api.worldweatheronline.com/free/v2/weather.ashx?q=" + location + "&format=xml&num_of_days=0&key=d0e2e3ec8ebef1d4493a1da6fd5fc");       
         xmlDoc.normalize();
         nodeList = xmlDoc.getElementsByTagName("current_condition");
         nodeListCheck = xmlDoc.getElementsByTagName("error");
         node2 = nodeList.item(0);
         children = node2.getChildNodes();
         if(nodeListCheck.getLength() > 0)
         {
            JOptionPane.showMessageDialog(null, "Please enter a valid location or zip code", "An error has occured", JOptionPane.ERROR_MESSAGE);
         }
      } catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "An error has occured in attempting to connect to the weather API. Are you connected to the internet?", "An error has occured", JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();
      }
      return children;
   }
   public String getTemperatureF(NodeList children)
   {
      String temperature = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("temp_F"))
         {
             temperature += children.item(i).getTextContent();
         }
      }
      return temperature;
   }
   public String getTemperatureC(NodeList children)
   {
      String temperature = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("temp_C"))
         {
             temperature += children.item(i).getTextContent();
         }
      }
      return temperature;
   }
   public String getWindSpeedMPH(NodeList children)
   {
      String windSpeed = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("windspeedMiles"))
         {
             windSpeed += children.item(i).getTextContent();
         }
      }
      return windSpeed;
   }
   public String getWindSpeedKPH(NodeList children)
   {
      String windSpeed = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("windspeedKmph"))
         {
             windSpeed += children.item(i).getTextContent();
         }
      }
      return windSpeed;
   }
   public String getWindDirection(NodeList children)
   {
      String windDir = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("winddir16Point"))
         {
             windDir += children.item(i).getTextContent();
         }
      }
      return windDir;
   }   
   public String getPrecipitationPercent(NodeList children)
   {
      String pop = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("precipMM"))
         {
             pop += children.item(i).getTextContent();
         }
      }
      return pop;
   }
   public String getHumidityPercent(NodeList children)
   {
      String humidity = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("humidity"))
         {
             humidity += children.item(i).getTextContent();
         }
      }
      return humidity;
   }
   public String getVisibility(NodeList children)
   {
      String visibility = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("visibility"))
         {
             visibility += children.item(i).getTextContent();
         }
      }
      return visibility;
   }
   public String getPressure(NodeList children)
   {
      String pressure = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("pressure"))
         {
            pressure += children.item(i).getTextContent();
         }
      }
      return pressure;
   }
   public String getCloudCover(NodeList children)
   {
      String cloudCover = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("cloudcover"))
         {
            cloudCover += children.item(i).getTextContent();
         }
      }
      return cloudCover;
   }
   public String getFeelsLikeF(NodeList children)
   {
      String realFeelF = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("FeelsLikeF"))
         {
            realFeelF += children.item(i).getTextContent();
         }
      }
      return realFeelF;
   }
   public String getFeelsLikeC(NodeList children)
   {
      String realFeelC = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("FeelsLikeC"))
         {
            realFeelC += children.item(i).getTextContent();
         }
      }
      return realFeelC;
   }
   public String getWeatherImage(NodeList children)
   {
      String Url = "";
      Node node;
      for(int i = 0; i < children.getLength(); i++)
      {
         node = children.item(i);
         if(children.item(i).getNodeName().equals("weatherIconUrl"))
         {
             Url += children.item(i).getTextContent();
         }
      }
      return Url;
   }
}