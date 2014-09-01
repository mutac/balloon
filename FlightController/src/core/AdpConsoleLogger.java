package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import core.*;
import android.util.*;

public class AdpConsoleLogger extends Logger
{  
  //@Override
  public void info(String origin, Object... messages) 
  {
    String timestamp = Timestamp();
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.i(origin, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  //@Override
  public void info(String origin, long timestampOverride, Object... messages) 
  {
    String timestamp = Timestamp(timestampOverride);
    
    for (int idx = 0; idx < messages.length; ++idx)
    {
      Log.i(origin, "[" + timestamp + "] " + messages[idx].toString());
    }
  }
  
  private static String Timestamp()
  {
    Calendar cal = Calendar.getInstance();
    return cal.toString();
  }
  
  private static String Timestamp(long timestampOverride)
  {
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
    
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(timestampOverride * 1000);
    return cal.toString();
  }
}
