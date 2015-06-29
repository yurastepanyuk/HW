package day6.DrinksMenu.Forms;

import day6.DrinksMenu.Reference.ReferenceDrinks;
import day6.DrinksMenu.Reference.ReferenceIngridients;
import day6.DrinksMenu.RegisterInformation.IngredientsOfDrinks;
import day6.DrinksMenu.RegisterInformation.PricesOfIngridients;

public class Menu implements FormsDrawable, ActionMenu{

    ReferenceDrinks referenceDrinks [];
    IngredientsOfDrinks ingredientsOfDrinks [][];//ingredientsOfDrinks [0][] - ingridients drink referenceDrinks [0]
    float pricesOfDrinks[];//pricesOfDrinks[0] - price drink referenceDrinks [0]

    public Menu() {
    }

    @Override
    public void draw() {
        System.out.println("I am drawing Menu");
    }

    public void getData() {

        referenceDrinks = new ReferenceDrinks().getAllData();

        ingredientsOfDrinks = new IngredientsOfDrinks [referenceDrinks.length][];
        pricesOfDrinks = new float [referenceDrinks.length];

        int i = 0;
        for (ReferenceDrinks drink : referenceDrinks) {
            IngredientsOfDrinks [] currentIngredientsOfDrinks  = new IngredientsOfDrinks().getDataBy(drink);
            ingredientsOfDrinks[i] = currentIngredientsOfDrinks;

            float price = 0;
            int idx = 0;
            for (IngredientsOfDrinks ingredientsOfDrinks: currentIngredientsOfDrinks){
                float priceIngedient = new PricesOfIngridients().getDataBy(ingredientsOfDrinks)[0].getPrice();
                price += priceIngedient;
            }
            pricesOfDrinks[i] = price;

            i++;
        }

        System.out.println("Read data from DB");

    }

    @Override
    public void newDrink(String nameDrink) {
        System.out.println("Added new drink " + nameDrink);
        ReferenceDrinks referenceDrinks = new ReferenceDrinks();
        referenceDrinks.setName(nameDrink);
        referenceDrinks.save(referenceDrinks);
    }

    @Override
    public void newIngridient(String nameIngridient) {
        System.out.println("Added new ingridient " + nameIngridient);
        ReferenceIngridients referenceIngridient = new ReferenceIngridients();
        referenceIngridient.setName(nameIngridient);
        referenceIngridient.save(referenceIngridient);
    }

    @Override
    public void addIngridientOfDrink(ReferenceDrinks drink, ReferenceIngridients ingridients, int qty) {
        System.out.println("Added new ingridient of drink ");
    }

    @Override
    public void delIngridientOfDrink(ReferenceDrinks drink, ReferenceIngridients ingridients, int qty) {
        System.out.println("Delete ingridient of drink ");
    }
}
