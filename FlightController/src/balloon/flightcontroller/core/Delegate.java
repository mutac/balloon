package balloon.flightcontroller.core;
import java.lang.reflect.*;
import java.util.*;

/**
 */
public class Delegate
{
  public Delegate(Object obj, String methodName)
  {
    mMethodTable = new HashMap<String, Method>();
    mObj = obj;
    mMethodName = methodName;
  }
  
  public void invoke() throws MethodNotFoundException, InvocationException 
  {
    methodInvoke(new Object [] {});
  }

  public void invoke(Object arg0) throws MethodNotFoundException, InvocationException
  {
    methodInvoke(new Object [] {arg0});
  }
  
  public void invoke(Object arg0, Object arg1) throws MethodNotFoundException, InvocationException
  {
    methodInvoke(new Object [] {arg0, arg1});
  }
  
  public void invoke(Object arg0, Object arg1, Object arg2) throws MethodNotFoundException, InvocationException
  {
    methodInvoke(new Object [] {arg0, arg1, arg2});
  }
  
  public boolean equals(Delegate other)
  {
    return mObj == other.mObj && mMethodName == other.mMethodName;
  }

  private void methodInvoke(Object[] argv) throws MethodNotFoundException, InvocationException
  {
    ObjectTypes argTypes = new ObjectTypes(argv);
    Method meth = getMethod(argTypes);

    try
    {
      if (meth != null)
      {
        meth.invoke(mObj, argv);
      }
    }
    catch (Exception e)
    {
      throw new InvocationException(mObj, meth, e.toString());
    }
  }

  private Method getMethod(ObjectTypes argTypes) throws MethodNotFoundException
  {
    String argHash = argTypes.toString();
    
    if (mMethodTable.containsKey(argHash))
    {
      return mMethodTable.get(argHash);
    }
    else
    {
      Method meth = null;
      
      try
      {
        meth = mObj.getClass().getMethod(mMethodName, argTypes.getTypes());
      }
      catch (Exception e)
      {
        throw new MethodNotFoundException(mObj, mMethodName, argTypes);
      }
      
      mMethodTable.put(argHash, meth);
      return meth;
    }
  }

  //
  // Class properties
  //
  private Map<String, Method> mMethodTable;
  private Object mObj;
  private String mMethodName;
  
  //
  // Exceptions
  //
  
  class MethodNotFoundException extends Exception
  {
	  public MethodNotFoundException(Object target, String method, ObjectTypes types)
	  {
	    mMessage = "Method not found: " + target.toString() + "." + method + "(" + types.toString() + ")";
	  }
	  
	  public String toString()
	  {
	    return mMessage;
	  }
	  
	  String mMessage;
  }
  
  class InvocationException extends Exception
  {
    public InvocationException(Object target, Method meth, String reason)
    {
      mMessage = "Unable to invoke: " + target.toString() + 
          "." + meth.toGenericString() + " " + reason;
    }
    
    public String toString()
    {
      return mMessage;
    }
    
    String mMessage;
  }
  
  //
  // Helper classes
  //
  
  /*
   */
  private class ObjectTypes
  {
    public ObjectTypes(Object [] objects)
    {
      mTypes = new Class[objects.length];
      for (int idx = 0; idx < objects.length; idx++)
      {
        mTypes[idx] = TypeWrapperToPrimitive.unwrap(objects[idx].getClass());
      }
    }
    
    public String toString()
    {
      String signature = "";
      for (Class t: mTypes)
      {
        signature = signature + t.toString() + ",";
      }
      
      return signature;
    }
    
    public Class[] getTypes()
    {
      return mTypes;
    }
    
    private Class[] mTypes;
  }
  
  /**
   */
  private static class TypeWrapperToPrimitive
  {
    public static final Map<Class<?>, Class<?>> map;
    static
    {
      map = new HashMap<Class<?>, Class<?>>();
      map.put(Boolean.class, Boolean.TYPE);
      map.put(Byte.class, Byte.TYPE);
      map.put(Character.class, Character.TYPE);
      map.put(Double.class, Double.TYPE);
      map.put(Float.class, Float.TYPE);
      map.put(Integer.class, Integer.TYPE);
      map.put(Long.class, Long.TYPE);
      map.put(Short.class, Short.TYPE);
      map.put(Void.class, Void.TYPE);
    }
    
    /**
     */
    static <T> Class<T> unwrap(Class<T> c)
    {
      if (map.containsKey(c))
      {
        return (Class<T>) map.get(c);
      }
      else
      {
        return c;
      }
    }
  }
}


