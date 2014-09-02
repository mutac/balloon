package balloon.flightcontroller.core;

import java.util.Calendar;

public abstract class Logger 
{
  public void info(String origin, Object... messages) {}
  public void info(String originn, long timestampOverride, Object... messages) {}
}
