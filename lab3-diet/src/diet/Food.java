package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Food {
    private Map<String, NutritionalElement> rawMaterials = new TreeMap<>();
	private Map<String, NutritionalElement> products = new TreeMap<>();
	private Map<String, Recipe> recipes = new TreeMap<>();

	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		rawMaterials.put(name, new RawMaterial(name, calories, proteins, carbs, fat));
	}

	public Collection<NutritionalElement> rawMaterials() {
		return rawMaterials.values();
	}

	public NutritionalElement getRawMaterial(String name) {
		return rawMaterials.get(name);
	}


	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		products.put(name, new Product(name, calories, proteins, carbs, fat));

	}

	public Collection<NutritionalElement> products() {
		return products.values();
	}

	public NutritionalElement getProduct(String name) {
		return products.get(name);
	}

	public Recipe createRecipe(String name) {
		Recipe recipe = recipes.get(name);  
         if (recipe == null) {
        recipe = new Recipe(name, this);
        recipes.put(name, recipe);
          }
      return recipe;
	}
	
	public Collection<NutritionalElement> recipes() {
		return new ArrayList<NutritionalElement>(new TreeMap<>(recipes).values()); 
	}

	public NutritionalElement getRecipe(String name) {
		return recipes.get(name);
	}
	public Menu createMenu(String name) {
		return new Menu(name,this);
	}
}