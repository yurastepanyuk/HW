package day6.DrinksMenu.Forms;

import day6.DrinksMenu.Reference.ReferenceDrinks;
import day6.DrinksMenu.RegisterInformation.IngredientsOfDrinks;
import day6.DrinksMenu.RegisterInformation.PricesOfIngridients;

/**
 * Created by stepanyuk on 24.06.2015.
 */
public class Menu implements FormsDrawable{

    ReferenceDrinks referenceDrinks [];
    IngredientsOfDrinks ingredientsOfDrinks [][];
    float pricesOfDrinks[];

    public Menu() {
        getData();
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

    }
}
