package diet;
import java.util.*;

public class Menu implements NutritionalElement {
	private String name;
	private Food food;
	
	private Map<String, Double> recipes = new HashMap<>();
	private List<String> products = new ArrayList<>();

	public Menu(String name, Food food) {
		this.name = name;
		this.food = food;
	}

    public Menu addRecipe(String recipe, double quantity) {
		recipes.put(recipe, recipes.getOrDefault(recipe, 0.0) + quantity);
		return this;
	}
    public Menu addProduct(String product) {
		products.add(product);
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		double total = 0.0;
		for (Map.Entry<String, Double> entry : recipes.entrySet()) {
			NutritionalElement recipe = food.getRecipe(entry.getKey());
			total += recipe.getCalories() * entry.getValue() / 100.0;
		}
		for (String product : products) {
			NutritionalElement prod = food.getProduct(product);
			total += prod.getCalories();
		}
		return total;
	}

	@Override
	public double getProteins() {
		double total = 0.0;
		for (Map.Entry<String, Double> entry : recipes.entrySet()) {
			NutritionalElement recipe = food.getRecipe(entry.getKey());
			total += recipe.getProteins() * entry.getValue() / 100.0;
		}
		for (String product : products) {
			NutritionalElement prod = food.getProduct(product);
			total += prod.getProteins();
		}
		return total;
	}

	@Override
	public double getCarbs() {
		double total = 0.0;
		for (Map.Entry<String, Double> entry : recipes.entrySet()) {
			NutritionalElement recipe = food.getRecipe(entry.getKey());
			total += recipe.getCarbs() * entry.getValue() / 100.0;
		}
		for (String product : products) {
			NutritionalElement prod = food.getProduct(product);
			total += prod.getCarbs();
		}
		return total;
	}

	@Override
	public double getFat() {
			double total = 0.0;
		for (Map.Entry<String, Double> entry : recipes.entrySet()) {
			NutritionalElement recipe = food.getRecipe(entry.getKey());
			total += recipe.getFat() * entry.getValue() / 100.0;
		}
		for (String product : products) {
			NutritionalElement prod = food.getProduct(product);
			total += prod.getFat();
		}
		return total;
	}

	@Override
	public boolean per100g() {
		return false;
	}
}