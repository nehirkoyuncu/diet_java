package diet;

import java.util.*;

public class Order {

	public enum OrderStatus {
		ORDERED, READY, DELIVERED
	}

	public enum PaymentMethod {
		PAID, CASH, CARD
	}
	
	private Customer customer;
    private Restaurant restaurant;
    private String deliveryTime;
    private OrderStatus status;
    private PaymentMethod paymentMethod;

	private Map<String, Integer> menus;

	  public Order(Customer customer, Restaurant restaurant, String deliveryTime) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.deliveryTime = deliveryTime;
        this.status = OrderStatus.ORDERED; 
        this.paymentMethod = PaymentMethod.CASH;
        this.menus = new HashMap<>();
    }

	public void setPaymentMethod(PaymentMethod pm) {
		 this.paymentMethod = pm;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setStatus(OrderStatus os) {
		this.status = os;
	}

	public OrderStatus getStatus() {
		return this.status;
	}


	public Order addMenus(String menu, int quantity) {
		menus.put(menu, quantity);
        return this;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(restaurant.getName())
          .append(", ")
          .append(customer.getFirstName())
          .append(" ")
          .append(customer.getLastName())
          .append(" : (")
          .append(deliveryTime)
          .append("):\n");

        List<String> menuNames = new ArrayList<>(menus.keySet());
        Collections.sort(menuNames);
        for (String menu : menuNames) {
            sb.append("\t")
              .append(menu)
              .append("->")
              .append(menus.get(menu))
              .append("\n");
        }
        return sb.toString().trim();
    }
    
    public String getDeliveryTime() {
        return this.deliveryTime;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }
	
}
