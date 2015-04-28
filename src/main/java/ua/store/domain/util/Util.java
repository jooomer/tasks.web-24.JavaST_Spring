package ua.store.domain.util;

public class Util {
	
	/**
	 * parse url parameter and check it
	 */
	public static long parsePathVariable(String string) {
		long number;
		try {
			number = Long.valueOf(string);
		} catch (NumberFormatException e) {
			// throw new WrongUrlException(e);
			// just show first
			number = -1;
		}
		return number;
	}


}
