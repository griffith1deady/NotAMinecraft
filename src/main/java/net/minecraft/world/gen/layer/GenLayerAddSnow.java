package net.minecraft.world.gen.layer;

public class GenLayerAddSnow extends GenLayer
{
    private static final String __OBFID = "CL_00000553";

    public GenLayerAddSnow(long p_i2121_1_, GenLayer p_i2121_3_)
    {
        super(p_i2121_1_);
        this.parent = p_i2121_3_;
    }

    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int i1 = p_75904_1_ - 1;
        int j1 = p_75904_2_ - 1;
        int k1 = p_75904_3_ + 2;
        int l1 = p_75904_4_ + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

        for (int i2 = 0; i2 < p_75904_4_; ++i2)
        {
            for (int j2 = 0; j2 < p_75904_3_; ++j2)
            {
                int k2 = aint[j2 + 1 + (i2 + 1) * k1];
                this.initChunkSeed((long)(j2 + p_75904_1_), (long)(i2 + p_75904_2_));

                if (k2 == 0)
                {
                    aint1[j2 + i2 * p_75904_3_] = 0;
                }
                else
                {
                    int l2 = this.nextInt(6);
                    byte b0;

                    if (l2 == 0)
                    {
                        b0 = 4;
                    }
                    else if (l2 <= 1)
                    {
                        b0 = 3;
                    }
                    else
                    {
                        b0 = 1;
                    }

                    aint1[j2 + i2 * p_75904_3_] = b0;
                }
            }
        }

        return aint1;
    }
}