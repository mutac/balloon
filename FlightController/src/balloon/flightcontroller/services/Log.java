package balloon.flightcontroller.services;

import balloon.flightcontroller.core.*;

public class Log implements Service
{
  public static Log Create(Logger logger)
  {
    if (sInstance == null)
      sInstance = new Log(logger);
    
    return sInstance;
  }
  
  public boolean start()
  {
    Info(getName(), "Log started");
    return true;
  }
  
  public String getName()
  {
    return "Log";
  }
  
  public static void Info(String origin, Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(origin, messages);
  }
  
  public static void Info(String origin, long timestampOverride, Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(origin, timestampOverride, messages);
  }
  
  private Log(Logger logger)
  {
    mLogger = logger;
  }

  static Log sInstance;
  static Logger mLogger;
}
