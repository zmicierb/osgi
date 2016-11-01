package by.borisevich.osgitest.tick;

/**
 * Created by dima on 10/26/16.
 */
public interface TickService
{
    void addListener (TickListener listener);
    void removeListener (TickListener listener);
}
