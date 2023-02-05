package net.optifine;

import java.util.HashMap;
import java.util.Map;

public class TimedEvent
{
    private static final Map<String, Long> mapEventTimes = new HashMap();

    public static boolean isActive(String name, long timeIntervalMs)
    {
        synchronized (mapEventTimes)
        {
            long timeNowMs = System.currentTimeMillis();

            long timeLastMs = mapEventTimes.computeIfAbsent(name, k -> timeNowMs);

            if (timeNowMs < timeLastMs + timeIntervalMs)
            {
                return false;
            }
            else
            {
                mapEventTimes.put(name, timeNowMs);
                return true;
            }
        }
    }
}
