package diet;

import java.util.*;

public class Takeaway {

	private Map<String, Restaurant> restaurants = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();

    private Food food;

	public Takeaway() {
        this(new Food());
    }

    private String normalizeTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return String.format("%02d:%02d", hours, minutes);
    }

	public Takeaway(Food food) {
        this.food = food;
    }

	public Restaurant addRestaurant(String restaurantName) {
		Restaurant restaurant = new Restaurant(restaurantName, food);
        restaurants.put(restaurantName, restaurant);
        return restaurant;
	}

	public Collection<String> restaurants() {
		return restaurants.keySet();
	}

	public Customer registerCustomer(String firstName, String lastName, String email, String phone) {
    
		Customer customer = new Customer(firstName, lastName, email, phone);
        
		customers.put(email + System.nanoTime(), customer); 
		
		return customer;
	}

	public Collection<Customer> customers(){
		TreeSet<Customer> sortedCustomers = new TreeSet<>(new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                // First compare by last name, then by first name
                int lastNameComparison = c1.getLastName().compareTo(c2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                }
                return c1.getFirstName().compareTo(c2.getFirstName());
            }
        });
        sortedCustomers.addAll(customers.values());
        return sortedCustomers;
	}

	public Order createOrder(Customer customer, String restaurantName, String time) {
		 Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant != null) {
            String adjustedTime = normalizeTime(time);
            if (!restaurant.isOpenAt(adjustedTime)) {
                adjustedTime = normalizeTime(getNextOpeningTime(restaurant, adjustedTime));
            }
            Order order = new Order(customer, restaurant, adjustedTime);
            restaurant.addOrder(order);
            return order;
        }
        return null;
	}

	 private String getNextOpeningTime(Restaurant restaurant, String time) {
        int requestedMinutes = toMinutes(time);
        String nextOpening = null;
        List<String[]> intervals = restaurant.getHours();
        for (String[] interval : intervals) {
            int startMinutes = toMinutes(interval[0]);
            if (startMinutes >= requestedMinutes) {
                nextOpening = interval[0];
                break;
            }
        }
        if (nextOpening == null && !intervals.isEmpty()) {
            nextOpening = intervals.get(0)[0];
        }
        return nextOpening;
    }

	 private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

	public Collection<Restaurant> openRestaurants(String time){
		  List<Restaurant> openRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants.values()) {
            if (restaurant.isOpenAt(time)) {
                openRestaurants.add(restaurant);
            }
        }
        openRestaurants.sort(Comparator.comparing(Restaurant::getName));
        return openRestaurants;
	}
}
