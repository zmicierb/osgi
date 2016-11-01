package by.borisevich.osgitest.tock;

/**
 * Created by dima on 10/26/16.
 */
import org.osgi.framework.*;
import org.osgi.util.tracker.*;
import by.borisevich.osgitest.tick.*;

public class Activator implements BundleActivator, TickListener
{
    /**
     start() method gets called when running osgi:start from the console
     */
    public void start(BundleContext bundleContext) throws Exception
    {
        System.out.println("Tock bundle started");
        // Get a service tracker for the Tick service, using its
        //  class name
        ServiceTracker tracker = new ServiceTracker
                (bundleContext, TickService.class.getName(), null);
        // Start the tracker
        tracker.open();
        // Find the service's API from the tracker. If the tick service
        //  is not running, we get a null here
        TickService tick = (TickService) tracker.getService();
        // Stop the tracker, since we're done with it
        tracker.close ();
        // If we got a reference to the tick service, then call its
        //  addListener method to register this class as a receiver of
        //  tick events. Otherwise, throw an exception
        if (tick != null)
            tick.addListener (this);
        else
            throw new Exception
                    ("Can't start tock bundle, as tick service is not running");
    }

    /**
     stop() method gets called when running osgi:start from the console
     */
    public void stop(BundleContext bundleContext) throws Exception
    {
        System.out.println("Tock bundle stopped");
        ServiceTracker tracker = new ServiceTracker
                (bundleContext, TickService.class.getName(), null);
        tracker.open();
        TickService tick = (TickService) tracker.getService();
        tracker.close ();
        // If the Tick service is running, then tick will be non-null, and
        //  we can remove ourself from the list of listeners on the service
        if (tick != null)
            tick.removeListener (this);
    }

    /**
     tick() implements the tick() method in TickListener. Once we are
     registered with the Tick service, this method will get called on
     every tick
     */
    @Override
    public void tick()
    {
        System.out.println ("tock");
    }
}