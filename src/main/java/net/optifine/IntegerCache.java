package net.optifine;

public class IntegerCache
{
    private static final int CACHE_SIZE = 65535;
    private static final int[] cache = makeCache(CACHE_SIZE);

    private static int[] makeCache(int size)
    {
        int[] arr = new int[size];

        for (int i = 0; i < size; ++i)
        {
            arr[i] = i;
        }

        return arr;
    }

    public static int valueOf(int value)
    {
        return value >= 0 && value < 65535 ? cache[value] : value;
    }
}
