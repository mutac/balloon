package balloon.flightcontroller.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.util.*;

public class AdbConsoleLogger implements Logger
{  
  //@Override
  public void info(String channel, Date timestamp, Object... messages) 
  {    
    String when = Format(timestamp);
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.i(channel, "[" + when + "] " + messages[idx].toString());
    }
  }
  
  //@Override
  public void error(String channel, Date timestamp, Object... messages) 
  {    
    String when = Format(timestamp);
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.e(channel, "[" + when + "] " + messages[idx].toString());
    }
  }
  
  private static String Format(Date date)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
    return format.format(date);
  }
}
