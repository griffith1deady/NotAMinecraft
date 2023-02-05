package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

@SideOnly(Side.CLIENT)
public class SoundList
{
	private final List<SoundEntry> field_148577_a = Lists.newArrayList();
	private boolean replaceExisting;
	private SoundCategory field_148576_c;

	public List<SoundEntry> getSoundList()
	{
		return this.field_148577_a;
	}

	public boolean canReplaceExisting()
	{
		return this.replaceExisting;
	}

	public void setReplaceExisting(boolean p_148572_1_)
	{
		this.replaceExisting = p_148572_1_;
	}

	public SoundCategory getSoundCategory()
	{
		return this.field_148576_c;
	}

	public void setSoundCategory(SoundCategory p_148571_1_)
	{
		this.field_148576_c = p_148571_1_;
	}

	@SideOnly(Side.CLIENT)
	public static class SoundEntry
		{
			private String field_148569_a;
			private float field_148567_b = 1.0F;
			private float field_148568_c = 1.0F;
			private int field_148565_d = 1;
			private SoundList.SoundEntry.Type field_148566_e;
			private boolean field_148564_f;

			public SoundEntry()
			{
				this.field_148566_e = SoundList.SoundEntry.Type.FILE;
				this.field_148564_f = false;
			}

			public String getSoundEntryName()
			{
				return this.field_148569_a;
			}

			public void setSoundEntryName(String p_148561_1_)
			{
				this.field_148569_a = p_148561_1_;
			}

			public float getSoundEntryVolume()
			{
				return this.field_148567_b;
			}

			public void setSoundEntryVolume(float p_148553_1_)
			{
				this.field_148567_b = p_148553_1_;
			}

			public float getSoundEntryPitch()
			{
				return this.field_148568_c;
			}

			public void setSoundEntryPitch(float p_148559_1_)
			{
				this.field_148568_c = p_148559_1_;
			}

			public int getSoundEntryWeight()
			{
				return this.field_148565_d;
			}

			public void setSoundEntryWeight(int p_148554_1_)
			{
				this.field_148565_d = p_148554_1_;
			}

			public SoundList.SoundEntry.Type getSoundEntryType()
			{
				return this.field_148566_e;
			}

			public void setSoundEntryType(SoundList.SoundEntry.Type p_148562_1_)
			{
				this.field_148566_e = p_148562_1_;
			}

			public boolean isStreaming()
			{
				return this.field_148564_f;
			}

			public void setStreaming(boolean p_148557_1_)
			{
				this.field_148564_f = p_148557_1_;
			}

			@SideOnly(Side.CLIENT)
			public enum Type
			{
				FILE("file"),
				SOUND_EVENT("event");
				private final String field_148583_c;

				Type(String p_i45109_3_)
				{
					this.field_148583_c = p_i45109_3_;
				}

				public static SoundList.SoundEntry.Type getType(String p_148580_0_)
				{
					SoundList.SoundEntry.Type[] atype = values();

					for (Type type : atype) {
						if (type.field_148583_c.equals(p_148580_0_)) {
							return type;
						}
					}

					return null;
				}
			}
		}
}