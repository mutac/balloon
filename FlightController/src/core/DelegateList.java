package core;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

  /**
   */
  public class DelegateList
  {
    public DelegateList()
    {
      mListeners = new ArrayList<Delegate>();
    }
    
    public void invoke() throws Delegate.MethodNotFoundException, Delegate.InvocationException
    {
      Iterator<Delegate> it = mListeners.iterator();
      while (it.hasNext())
      {
        it.next().invoke();
      }
    }
    
    public void invoke(Object arg) throws Delegate.MethodNotFoundException, Delegate.InvocationException
    {
      Iterator<Delegate> it = mListeners.iterator();
      while (it.hasNext())
      {
        it.next().invoke(arg);
      }
    }
    
    public void addListener(Object obj, String methodName)
    {
      Delegate d = new Delegate(obj, methodName);
      
      if (findListener(d) < 0)
      {
        mListeners.add(d);
      }
    }
    
    public void removeListener(Object obj, String methodName)
    {
      int listenerIdx = findListener(new Delegate(obj, methodName));
      if (listenerIdx >= 0)
      {
        mListeners.remove(listenerIdx);
      }
    }
    
    private int findListener(Delegate d)
    {
      for (int idx = 0; idx < mListeners.size(); idx++)
      {
        if (mListeners.get(idx).equals(d))
        {
          return idx;
        }
      }
      
      return -1;
    }
    
    private List<Delegate> mListeners;
  }