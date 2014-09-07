package balloon.flightcontroller.services;

import balloon.flightcontroller.core.*;
import android.content.*;
import android.os.*;
import android.location.*;

public class OnboardGPS implements balloon.flightcontroller.core.Service, LocationListener, GpsStatus.Listener, GpsStatus.NmeaListener
{  
  private final int MIN_UPDATE_INTERVAL_MS = 100;
  private final int MIN_DISTANCE_BEFORE_UPDATE_M = 0;
  
  public OnboardGPS(Context context)
  {
    mMinIntervalMs = MIN_UPDATE_INTERVAL_MS;
    mMinDistanceBeforeUpdateM = MIN_DISTANCE_BEFORE_UPDATE_M;
    
    mLocationManager = (LocationManager) 
        context.getSystemService(Context.LOCATION_SERVICE);
  }
  
  @Override
  public boolean start()
  {    
    return resetLocationListener();
  }
  
  @Override
  public void onLocationChanged(Location loc)
  {
  }
  
  @Override
  public void onProviderDisabled(String provider) 
  {
  }

  @Override
  public void onProviderEnabled(String provider) 
  {
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) 
  {
  }
  
  @Override
  public void onGpsStatusChanged(int event)
  {
    mLocationManager.getGpsStatus(mGpsStatus);
    
    switch (event)
    {
    case GpsStatus.GPS_EVENT_STARTED:
      break;
    case GpsStatus.GPS_EVENT_STOPPED:
      break;
    case GpsStatus.GPS_EVENT_FIRST_FIX:
      // mLocationManager.getFirstFix()
      break;
    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
      // mLocationManager.getSatellites()
      break;
    default:
      break;
    }
  }
  
  public Location getCurrentLocation()
  {
    return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  }
  
  public String getName()
  {
    return OnboardGPS.class.getName();
  }
  
  @Override
  public void onNmeaReceived(long timestamp, String nmea)
  {
  }
  
  private boolean resetLocationListener()
  { 
    Log.Info("Initializing Onboard GPS provider");
    
    mLocationManager.addGpsStatusListener(this);
    mLocationManager.addNmeaListener(this);
    
    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        mMinIntervalMs, mMinDistanceBeforeUpdateM, this);
    
    boolean enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    
    if (enabled)
    {
      Log.Info("Onboard GPS provider enabled"); 
    }
    else
    {
      Log.Error("Onboard GPS provider is DISABLED!");
    }
    
    return enabled;
  }
  
  LocationManager mLocationManager;
  int mMinIntervalMs;
  int mMinDistanceBeforeUpdateM;
  GpsStatus mGpsStatus;
}
