package diet;

import java.util.LinkedHashMap;
import java.util.Map;

public class Recipe implements NutritionalElement {
	private String name;
    private Food food;
    private Map<NutritionalElement, Double> ingredients = new LinkedHashMap<>();
    
	public Recipe(String name, Food food) {
        this.name = name;
        this.food = food;
    }
	public Recipe addIngredient(String material, double quantity) {
		 NutritionalElement element = food.getRawMaterial(material);
        if (element != null) {
            ingredients.put(element, quantity);
        }
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public double getCalories() {
		return computePer100g(NutritionalValue.CALORIES);
	}
	

	@Override
	public double getProteins() {
		return computePer100g(NutritionalValue.PROTEINS);
	}

	@Override
	public double getCarbs() {
        return computePer100g(NutritionalValue.CARBS);

	}

	@Override
	public double getFat() {
        return computePer100g(NutritionalValue.FAT);
	}

	@Override
	public boolean per100g() {
		return true;
	}

	 private double computePer100g(NutritionalValue type) {
        double totalWeight = 0.0;
        double totalValue = 0.0;

        for (Map.Entry<NutritionalElement, Double> entry : ingredients.entrySet()) {
            NutritionalElement ingredient = entry.getKey();
            double weight = entry.getValue();
            totalWeight += weight;

            switch (type) {
                case CALORIES:
                    totalValue += ingredient.getCalories() * weight / 100.0;
                    break;
                case PROTEINS:
                    totalValue += ingredient.getProteins() * weight / 100.0;
                    break;
                case CARBS:
                    totalValue += ingredient.getCarbs() * weight / 100.0;
                    break;
                case FAT:
                    totalValue += ingredient.getFat() * weight / 100.0;
                    break;
            }
        }

        if (totalWeight == 0.0) return 0.0;
        return (totalValue / totalWeight) * 100.0;
    }

    private enum NutritionalValue {
        CALORIES,
        PROTEINS,
        CARBS,
        FAT
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<NutritionalElement, Double> entry : ingredients.entrySet()) {
            sb.append(entry.getKey().getName())
              .append(" - ")
              .append(entry.getValue())
              .append("g\n");
        }
        return sb.toString().trim();
    }
	
}
