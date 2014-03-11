/*
 * Copyright © 2006-2010. BSkyB Ltd All Rights reserved
 */
package com.sky.dvdstore;

import java.util.HashMap;
import java.util.Map;

public class DvdRepositoryStub implements DvdRepository {

	public static final String DVD_REFERENCE_PREFIX = "DVD-";
	
	private static final String DVD_TOPGUN_REFERENCE = DVD_REFERENCE_PREFIX + "TG423";
	private static final String DVD_DIRTYDANCING_REFERENCE = DVD_REFERENCE_PREFIX + "DD878";
	private static final String DVD_SHREK_REFERENCE = DVD_REFERENCE_PREFIX + "S765";
	private static final String DVD_LIFE_IS_BEAUTIFUL=DVD_REFERENCE_PREFIX+"C1234";
	
	private static final Map<String, Dvd> dvds;

	static {
		dvds = new HashMap<String, Dvd>();
		dvds.put(DVD_TOPGUN_REFERENCE, new Dvd(DVD_TOPGUN_REFERENCE, "Topgun", "All action film"));
		dvds.put(DVD_DIRTYDANCING_REFERENCE, new Dvd(DVD_DIRTYDANCING_REFERENCE, "Dirty Dancing", "Nobody leaves baby in the corner"));
		dvds.put(DVD_SHREK_REFERENCE, new Dvd(DVD_SHREK_REFERENCE, "Shrek", "Big green monsters, they're just all " +
					"the rage these days, what with Hulk, Yoda, and that big ugly troll " +
					"thingy out of the first Harry Potter movie. But Shrek, the flatulent " +
					"swamp-dwelling ogre with a heart of gold (well, silver at least), " +
					"is more than capable of rivalling any of them."));
		dvds.put(DVD_LIFE_IS_BEAUTIFUL, new Dvd(DVD_LIFE_IS_BEAUTIFUL,"Life is Beautiful","A deeply moving blend of cold terror and rapturous hilarity"));
	}
	
	public Dvd retrieveDvd(String reference) {
		return dvds.get(reference);
	}

}
