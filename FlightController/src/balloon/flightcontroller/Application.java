package balloon.flightcontroller;

import java.util.*;

import android.content.*;
import balloon.flightcontroller.core.*;
import balloon.flightcontroller.services.*;

public class Application 
{
  public Application(Context context)
  {
    mStartupServices = new ArrayList<Service>();
    mAllServices = new ArrayList<Service>();
    
    addStartup(Log.Create(new AdbConsoleLogger()));
    addStartup(OnboardGPS.Create(context));
    
    startUp();
  }
  
  void startUp()
  {
    for(Service service : mStartupServices)
    {
      service.start();
    }
  }
  
  void addStartup(Service service)
  {
    mStartupServices.add(service);
    mAllServices.add(service);
  }
  
  private List<Service> mStartupServices;
  private List<Service> mAllServices;
}
