package diet;

import diet.Order.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;

public class Restaurant {

	private String name;
    private Map<String, Menu> menus = new HashMap<>();
    private List<String[]> hours = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

	public Restaurant(String name, Food food) {
        this.name = name;
    }
	
	public String getName() {
		return name;
	}

	public void setHours(String ... hm) {
		 if (hm.length % 2 == 0) {
            for (int i = 0; i < hm.length; i += 2) {
                hours.add(new String[]{hm[i], hm[i + 1]});
            }
        } else {
            throw new IllegalArgumentException("The hours array must have an even number of elements.");
        }
	}

	public boolean isOpenAt(String time){
		for (String[] timeRange : hours) {
            String openTime = timeRange[0];
            String closeTime = timeRange[1];

            if (isTimeBetween(time, openTime, closeTime)) {
                return true;
            }
        }
		return false;
	}

	 private boolean isTimeBetween(String time, String startTime, String endTime) {
        return time.compareTo(startTime) >= 0 && time.compareTo(endTime) < 0;
    }

	public void addMenu(Menu menu) {
		menus.put(menu.getName(), menu);
	}

	public Menu getMenu(String name) {
		return menus.get(name);
	}

	public String ordersWithStatus(OrderStatus status) {
		 List<Order> filteredOrders = orders.stream()
            .filter(order -> order.getStatus() == status)
            .sorted(Comparator.comparing((Order o) -> o.getRestaurant().getName())
                    .thenComparing(o -> o.getCustomer().toString())
                    .thenComparing(Order::getDeliveryTime))
            .collect(Collectors.toList());
        
        StringBuilder result = new StringBuilder();
        for (Order order : filteredOrders) {
            result.append(order).append("\n");
        }
        return result.toString().trim();
	}

	public void addOrder(Order order) {
        orders.add(order);
    }

	public List<String[]> getHours() {
        return hours;
    }

}
