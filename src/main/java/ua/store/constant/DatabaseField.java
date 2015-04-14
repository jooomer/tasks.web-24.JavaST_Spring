/**
 * 
 */
package ua.store.constant;

/**
 * @author Sergey
 *
 */
public enum DatabaseField {
	id, price;
	
	public static DatabaseField getOrderStatus(String orderSatusString) {
		for (DatabaseField orderStatus : DatabaseField.values()) {
			if (orderStatus.toString().equals(orderSatusString)) {
				return orderStatus;
			}
		}
		throw new RuntimeException("Unknown type");
	}
}
