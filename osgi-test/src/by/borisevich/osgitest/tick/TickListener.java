package by.borisevich.osgitest.tick;

/**
 * Created by dima on 10/26/16.
 */
public interface TickListener
{
    /** The tick() method is called on every registered listener every time
     the Tick service ticks */
    void tick();
}
