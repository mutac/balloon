package balloon.flightcontroller.core;

import java.util.*;

public class System 
{
  public static System GetInstance()
  {
    if (sInstance == null)
      sInstance = new System();
   
    return sInstance;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T getService(String path)
  {
    T service = null;
    try
    {
      service = (T) mServices.get(path);
    }
    catch (Exception ex)
    {
      service = null;
    }
    
    return service;
  }
  
  public void addService(String path, Object obj)
  {
    mServices.put(path, obj);
  }
  
  private System()
  {
  }
  
  private HashMap<String, Object> mServices = new HashMap<String, Object>();
  private static System sInstance = null;
}
