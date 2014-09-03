package balloon.flightcontroller.services;

import balloon.flightcontroller.Application;
import balloon.flightcontroller.core.*;
import balloon.flightcontroller.core.System;
import balloon.flightcontroller.services.*;
import android.location.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PeriodicGPSLocationLog implements Service
{
  private final int PERIODIC_SCHEDULER_POOL_SIZE = 1;
  private final int DEFAULT_LOGGING_PERIOD_MS = 1000;
  
  public PeriodicGPSLocationLog()
  {
    mLoggingPeriodMs = DEFAULT_LOGGING_PERIOD_MS;
    
    OnboardGPS onboardGps = System.GetInstance().getService(Application.WellKnownSystemPaths.OnboardGps);
    mLastKnownLocation = onboardGps.getCurrentLocation();
  }
  
  private void log()
  {
    //Log.Info(mLastKnownLocation);
  }
  
  public boolean start()
  { 
    return restartLoggingService();
  }
  
  public String getName()
  {
    return PeriodicGPSLocationLog.class.getName();
  }
  
  public void setLoggingPeriod(int loggingPeriodMs)
  {
    mLoggingPeriodMs = loggingPeriodMs;
    restartLoggingService();
  }
  
  private boolean restartLoggingService()
  {
    if (mPositionLoggerHandler != null)
      mPositionLoggerHandler.cancel(true);
    
    final Runnable loggingTask = new Runnable() 
    {
      public void run() { log(); }
    };
    
    mPositionLoggerHandler = mScheduler.scheduleAtFixedRate(loggingTask, 
        0, mLoggingPeriodMs, TimeUnit.MILLISECONDS);
    
    return true;
  }
  
  private Location mLastKnownLocation;
  private int mLoggingPeriodMs;
  private ScheduledFuture mPositionLoggerHandler;
  private final ScheduledExecutorService mScheduler = 
      Executors.newScheduledThreadPool(PERIODIC_SCHEDULER_POOL_SIZE);
}
