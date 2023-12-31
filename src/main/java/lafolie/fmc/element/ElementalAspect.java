package lafolie.fmc.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ElementalAspect
{
	NONE("none"),
	FIRE("fire"),
	ICE("ice"),
	THUNDER("thunder"),
	WIND("wind"),
	WATER("water"),
	EARTH("earth"),
	HOLY("holy"),
	DARK("dark"),
	POISON("poison"),
	GRAVITY("gravity");

	private String key;

	private static final Map<ElementalAspect, ElementalAspect> WEAK = new EnumMap<>(ElementalAspect.class);
	private static final Map<ElementalAspect, ElementalAspect> RESIST = new EnumMap<>(ElementalAspect.class);
	private static final Map<ElementalAspect, String> LANG_KEYS = new EnumMap<>(ElementalAspect.class);
	private static final Map<String, ElementalAspect> NBT_KEYS = new HashMap<>();
	private static ArrayList<ElementalAspect> randomList = new ArrayList<>();

	static
	{
		// FFXI system
		/*
		 * Fire melts Ice
		 * Ice blocks Wind
		 * Wind erodes Earth
		 * Earth grounds Thunder
		 * Thunder shocks Water
		 * Water douses Fire
		 * Light illuminates Dark
		 * Dark eclipses Light
		 */
		
		WEAK.put(ElementalAspect.NONE, ElementalAspect.NONE);
		WEAK.put(ElementalAspect.FIRE, ElementalAspect.WATER);
		WEAK.put(ElementalAspect.ICE, ElementalAspect.FIRE);
		WEAK.put(ElementalAspect.THUNDER, ElementalAspect.EARTH);
		WEAK.put(ElementalAspect.WIND, ElementalAspect.ICE);
		WEAK.put(ElementalAspect.WATER, ElementalAspect.THUNDER);
		WEAK.put(ElementalAspect.EARTH, ElementalAspect.WIND);
		WEAK.put(ElementalAspect.HOLY, ElementalAspect.DARK);
		WEAK.put(ElementalAspect.DARK, ElementalAspect.HOLY);
		WEAK.put(ElementalAspect.POISON, ElementalAspect.NONE);
		WEAK.put(ElementalAspect.GRAVITY, ElementalAspect.NONE);

		RESIST.put(ElementalAspect.NONE, ElementalAspect.NONE);
		RESIST.put(ElementalAspect.FIRE, ElementalAspect.ICE);
		RESIST.put(ElementalAspect.ICE, ElementalAspect.WIND);
		RESIST.put(ElementalAspect.THUNDER, ElementalAspect.WATER);
		RESIST.put(ElementalAspect.WIND, ElementalAspect.EARTH);
		RESIST.put(ElementalAspect.WATER, ElementalAspect.ICE);
		RESIST.put(ElementalAspect.EARTH, ElementalAspect.THUNDER);
		RESIST.put(ElementalAspect.HOLY, ElementalAspect.DARK);
		RESIST.put(ElementalAspect.DARK, ElementalAspect.HOLY);
		RESIST.put(ElementalAspect.POISON, ElementalAspect.NONE);
		RESIST.put(ElementalAspect.GRAVITY, ElementalAspect.NONE);

		LANG_KEYS.put(ElementalAspect.NONE, "fmc.element.tooltip.none");
		LANG_KEYS.put(ElementalAspect.FIRE, "fmc.element.tooltip.fire");
		LANG_KEYS.put(ElementalAspect.ICE, "fmc.element.tooltip.ice");
		LANG_KEYS.put(ElementalAspect.THUNDER, "fmc.element.tooltip.thunder");
		LANG_KEYS.put(ElementalAspect.WIND, "fmc.element.tooltip.wind");
		LANG_KEYS.put(ElementalAspect.WATER, "fmc.element.tooltip.water");
		LANG_KEYS.put(ElementalAspect.EARTH, "fmc.element.tooltip.earth");
		LANG_KEYS.put(ElementalAspect.HOLY, "fmc.element.tooltip.holy");
		LANG_KEYS.put(ElementalAspect.DARK, "fmc.element.tooltip.dark");
		LANG_KEYS.put(ElementalAspect.POISON, "fmc.element.tooltip.poison");
		LANG_KEYS.put(ElementalAspect.GRAVITY, "fmc.element.tooltip.gravity");

		for(ElementalAspect e : EnumSet.range(FIRE, GRAVITY))
		{
			randomList.add(e);
		}
	}

	private ElementalAspect(String key)
	{
		this.key = key;
	}

	/**
	 * Get a random element (excludes NONE)
	 * @return a random element
	 */
	public static ElementalAspect randomElement()
	{

		Collections.shuffle(randomList);
		return randomList.get(0);
	}

	/**
	 * Get a random element from the provided list
	 * @return random element
	 */
	public static ElementalAspect randomElement(List<ElementalAspect> list)
	{
		List<ElementalAspect> temp = new ArrayList<ElementalAspect>();
		Collections.copy(temp, list);
		Collections.shuffle(temp);
		return list.get(0);
	}

	public String getLangKey()
	{
		return LANG_KEYS.get(this);
	}

	public static String getLangKey(ElementalAspect element)
	{
		return LANG_KEYS.get(element);
	}

	public static ElementalAspect getResistantTo(ElementalAspect element)
	{
		return RESIST.get(element);
	}

	public ElementalAspect getResistantTo()
	{
		return RESIST.get(this);
	}

	public static ElementalAspect getWeakTo(ElementalAspect element)
	{
		return WEAK.get(element);
	}

	public ElementalAspect getWeakTo()
	{
		return WEAK.get(this);
	}

	public String toNbtKey()
	{
		return key;
	}

	public static String toNbtKey(ElementalAspect aspect)
	{
		return aspect.key;
	}

	public static ElementalAspect fromNbtKey(String key)
	{
		return NBT_KEYS.containsKey(key) ? NBT_KEYS.get(key) : NONE;
	}

}