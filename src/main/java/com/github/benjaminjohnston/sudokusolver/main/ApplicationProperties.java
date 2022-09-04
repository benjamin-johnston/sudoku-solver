package com.github.benjaminjohnston.sudokusolver.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
 
public class ApplicationProperties {
   private final Properties properties = new Properties();
   
   private static volatile ApplicationProperties instance = new ApplicationProperties();
    
   private ApplicationProperties() {
	  ClassLoader classLoader = this.getClass().getClassLoader();
      InputStream in = classLoader.getResourceAsStream("application.properties");
      
      try {
    	  properties.load(in);
      } catch (IOException e) {
          e.printStackTrace();
      }
   }
 
   public static ApplicationProperties getInstance()
   {
      return instance;
   }
    
   public String getProperty(String key){
      return properties.getProperty(key);
   }
    
   public Set<String> getAllPropertyNames(){
      return properties.stringPropertyNames();
   }
    
   public boolean containsKey(String key){
      return properties.containsKey(key);
   }
}