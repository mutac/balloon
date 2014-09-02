package balloon.flightcontroller.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import balloon.flightcontroller.core.*;
import android.util.*;

public class AdbConsoleLogger implements Logger
{  
  //@Override
  public void info(String channel, Object... messages) 
  {
    String timestamp = Timestamp();
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.i(channel, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  //@Override
  public void info(String channel, long timestampOverride, Object... messages) 
  {
    String timestamp = Timestamp(timestampOverride);
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.i(channel, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  //@Override
  public void error(String channel, Object... messages) 
  {
    String timestamp = Timestamp();
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.e(channel, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  //@Override
  public void error(String channel, long timestampOverride, Object... messages) 
  {
    String timestamp = Timestamp(timestampOverride);
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.e(channel, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  private static String Timestamp()
  {
    return Format(new Date());
  }
  
  private static String Timestamp(long timestampOverride)
  {
    Date then = new Date();
    then.setTime(timestampOverride / 1000);
    return Format(then);
  }
  
  private static String Format(Date date)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
    return format.format(date);
  }
}
