package balloon.flightcontroller.services;

import balloon.flightcontroller.core.*;
import java.util.Date;

public class Log implements Service
{
  public static Log GetInstance()
  {
    if (sInstance == null)
      sInstance = new Log();
    
    return sInstance;
  }

  public static void Info(Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.info(InferChannel(), new Date(), messages);
  }
  
  public static void Error(Object... messages)
  {
    if (sInstance != null)
      sInstance.mLogger.error(InferChannel(), new Date(), messages);
  }
  
  public void setEnableChannelInference(boolean enable)
  {
    mLogChannel = enable;
  }
  
  public void addLogger(Logger logger)
  {
    mLogger = logger;
  }
  
  public boolean start()
  {
    Info("Log started");
    return true;
  }
  
  public String getName()
  {
    return Log.class.getName();
  }
  
  private Log()
  {
    mLogger = null;
    mLogChannel = true;
  }
  
  private static String InferChannel()
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
  
  boolean mLogChannel;
  Logger mLogger;
  
  static Log sInstance;
}
