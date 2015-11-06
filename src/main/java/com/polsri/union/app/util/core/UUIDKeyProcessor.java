package com.polsri.union.app.util.core;

import java.util.UUID;

public class UUIDKeyProcessor {

	private final static String DELIMITER = "-";

	/**
	 * generate random UUID
	 * 
	 * @return uuid string
	 */
	public static final String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	/**
	 * generate string into uuid class
	 * 
	 * @param uuidString
	 * @return UUID
	 */
	public static final UUID revertToUUID(String uuidString) {
		// UUID FORMAT : 5bbe204e-7e62-4ab1-ab16-1485dfa07897
		StringBuilder builder = new StringBuilder(uuidString);
		builder.insert(8, DELIMITER);
		builder.insert(13, DELIMITER);
		builder.insert(18, DELIMITER);
		builder.insert(23, DELIMITER);
		return UUID.fromString(builder.toString().toLowerCase());
	}

	/**
	 * generate uuid from spesified params
	 * 
	 * @param uuid
	 * @return uuid string
	 */
	public static final String generateStringFrom(UUID uuid) {
		return uuid.toString().replace("-", "").toUpperCase();
	}

}
