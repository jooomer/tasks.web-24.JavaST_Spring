/**
 * 
 */
package ua.store.domain.constant;

/**
 * @author Sergey
 *
 */
public enum OrderStatus {
	WAITING_FOR_PAIMENT("Waiting for payment"), 
	PAID("Paid"), 
	DELIVERED("Delivered"), 
	CANCELED("Canceled");
	
	private String name;
	
	OrderStatus(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static OrderStatus getOrderStatus(String orderSatusString) {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.toString().equals(orderSatusString)) {
				return orderStatus;
			}
		}
		throw new RuntimeException("Unknown type");
	}
}
