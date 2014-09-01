package services;

import android.content.*;
import android.os.*;
import android.location.*;

public class OnboardGPS implements core.Service, LocationListener, GpsStatus.Listener //, GpsStatus.NmeaListener
{
  public OnboardGPS Create(Context context)
  {
    if (sInstance != null)
      sInstance = new OnboardGPS(context);
    
    return sInstance;
  }
  
  private OnboardGPS(Context context)
  {
    mMinIntervalMs = 1000;
    mMinDistanceBeforeUpdateM = 0;
    
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
  
  public String getName()
  {
    return "OnboardGPS";
  }
  
  /*
  @Override
  public void onNmeaReceived(long timestamp, String nmea)
  {
  }
  */
  
  private boolean resetLocationListener()
  { 
    mLocationManager.addGpsStatusListener(this);
    //mLocationManager.addNmeaListener(this);
    
    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        mMinIntervalMs, mMinDistanceBeforeUpdateM, this);
    
    return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }
  
  LocationManager mLocationManager;
  int mMinIntervalMs;
  int mMinDistanceBeforeUpdateM;
  GpsStatus mGpsStatus;
  
  OnboardGPS sInstance;
}