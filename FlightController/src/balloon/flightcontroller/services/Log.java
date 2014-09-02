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
  
  public static void SetLogchannelClassname(boolean enable)
  {
    if (sInstance != null)
      sInstance.mLogChannel = enable;
  }
  
  public static void Info(Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(GetChannel(), messages);
  }
  
  public static void Info(long timestampOverride, Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(GetChannel(), timestampOverride, messages);
  }
  
  public static void Error(Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.error(GetChannel(), messages);
  }
  
  public static void Error(long timestampOverride, Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.error(GetChannel(), timestampOverride, messages);
  }
  
  private Log(Logger logger)
  {
    mLogger = logger;
    mLogChannel = true;
  }
  
  private static String GetChannel()
  {
    if (sInstance != null && sInstance.mLogChannel)
    {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
      if (stack != null && stack.length >= 5)
      {
        // The top two stack elements are to call into getStackTrace()
        StackTraceElement caller = stack[4];
        return caller.getClassName();
      }
    }

    return "balloon.flightcontroller";
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
  
  boolean mLogChannel;
  Logger mLogger;
  
  static Log sInstance;
}
