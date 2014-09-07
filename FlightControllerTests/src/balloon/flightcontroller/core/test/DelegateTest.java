package balloon.flightcontroller.core.test;

import balloon.flightcontroller.core.*;

import junit.framework.TestCase;

public class DelegateTest extends TestCase 
{
  class AppropriateMethods <Arg0, Arg1, Arg2>
  {
    public void method()
    {
      mCalledMethod0 = true;
    }
    public boolean calledMethod0()
    {
      return mCalledMethod0;
    }
    
    public void method(Arg0 arg0)
    {
      mCalledMethod1 = true;
    }
    public boolean calledMethod1()
    {
      return mCalledMethod1;
    }
    
    public void method(Arg0 arg0, Arg1 arg1)
    {
      mCalledMethod2 = true;
    }
    public boolean calledMethod2()
    {
      return mCalledMethod2;
    }
    
    public void method(Arg0 arg0, Arg1 arg1, Arg2 arg2)
    {
      mCalledMethod3 = true;
    }
    public boolean calledMethod3()
    {
      return mCalledMethod3;
    }
    
    boolean mCalledMethod0 = false;
    boolean mCalledMethod1 = false;
    boolean mCalledMethod2 = false;
    boolean mCalledMethod3 = false;
  }
  
  class GenericallyTypedMethods extends AppropriateMethods<Object, Object, Object>
  {
  }
  
  public void testGenericallyTypedInvoke() throws Exception
  {
    GenericallyTypedMethods subject = new GenericallyTypedMethods();
    
    Delegate delegate = new Delegate(subject, "method");

    delegate.invoke(); 
    delegate.invoke(new Object());
    delegate.invoke(new Object(), new Object());
    delegate.invoke(new Object(), new Object(), new Object());
    
    assertTrue(subject.calledMethod0());
    assertTrue(subject.calledMethod1());
    assertTrue(subject.calledMethod2());
    assertTrue(subject.calledMethod3());
  }
  
  public void testTypedInvoke() throws Exception
  {
    AppropriateMethods<Integer, String, Boolean> subject = new AppropriateMethods<Integer, String, Boolean>();
    
    Delegate delegate = new Delegate(subject, "method");
    
    delegate.invoke();
    delegate.invoke(0);
    delegate.invoke(1, "jello");
    delegate.invoke(2, "gwerold", false);
    
    assertTrue(subject.calledMethod0());
    assertTrue(subject.calledMethod1());
    assertTrue(subject.calledMethod2());
    assertTrue(subject.calledMethod3());
  }
}
