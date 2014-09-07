package balloon.flightcontroller;

import java.util.*;

import android.content.*;
import balloon.flightcontroller.core.*;
import balloon.flightcontroller.services.*;

public class Application 
{  
  public Application(Context context)
  {
    mContext = context;
    mCoreServices = new ArrayList<Service>();
    mAllServices = new ArrayList<Service>();
    
    createCoreServices();
    
    startCoreServices();
  }
  
  void createCoreServices()
  {
    balloon.flightcontroller.core.System system = balloon.flightcontroller.core.System.GetInstance();
    
    Log logService = Log.GetInstance();
    
    AdbConsoleLogger adbLogger = new AdbConsoleLogger();
    logService.addLogger(adbLogger);
    system.addService(WellKnownServices.AdbConsoleLogger, adbLogger);
    addStartup(logService);
    
    OnboardGPS onboardGps = new OnboardGPS(mContext);
    system.addService(WellKnownServices.OnboardGps, onboardGps);
    addStartup(onboardGps);
    
    PeriodicGPSLocationLog periodicGpsLogger = new PeriodicGPSLocationLog();
    system.addService(WellKnownServices.PeriodictGPSLogger, periodicGpsLogger);
    addStartup(periodicGpsLogger);
  }
  
  void startCoreServices()
  {
    for(Service service : mCoreServices)
    {
      service.start();
    }
  }
  
  void addStartup(Service service)
  {
    mCoreServices.add(service);
    mAllServices.add(service);
  }
  
  private List<Service> mCoreServices;
  private List<Service> mAllServices;
  private Context mContext;
}
