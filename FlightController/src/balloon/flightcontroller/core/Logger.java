package balloon.flightcontroller.core;

import java.util.Date;

public interface Logger 
{
  public void info(String channel, Date timestamp, Object... messages);
  public void error(String channel, Date timestamp, Object... messages);
}
