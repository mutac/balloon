package balloon.flightcontroller.core;

import java.util.Calendar;

public interface Logger 
{
  public void info(String channel, Object... messages);
  public void info(String channel, long timestampOverride, Object... messages);
  
  public void error(String channel, Object... messages);
  public void error(String channel, long timestampOverride, Object... messages);
}
