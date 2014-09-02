package balloon.flightcontroller.services;

import balloon.flightcontroller.core.*;
import java.lang.reflect.*;

public class Log implements Service
{
  public static Log Create(Logger logger)
  {
    if (sInstance == null)
      sInstance = new Log(logger);
    
    return sInstance;
  }
  
  public static void SetLogOriginClassname(boolean enable)
  {
    if (sInstance != null)
      sInstance.mLogOrigin = enable;
  }
  
  public static void Info(Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(GetOrigin(), messages);
  }
  
  public static void Info(long timestampOverride, Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(GetOrigin(), timestampOverride, messages);
  }
  
  private Log(Logger logger)
  {
    mLogger = logger;
  }
  
  private static String GetOrigin()
  {
    if (sInstance != null && sInstance.mLogOrigin)
    {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
      StackTraceElement caller;
      if (stack != null && stack.length > 3)
      {
        // The top two stack elements are to call into getStackTrace()
        caller = stack[2];
        return caller.getClassName();
      }
    }

    return "baloon.flightcontroller.Anonymous";
  }
  
  public boolean start()
  {
    Info("Log started");
    return true;
  }
  
  public String getName()
  {
    return "Log";
  }
  
  boolean mLogOrigin;
  Logger mLogger;
  
  static Log sInstance;
}
