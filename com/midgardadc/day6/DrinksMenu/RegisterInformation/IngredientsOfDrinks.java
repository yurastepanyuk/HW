package day6.DrinksMenu.RegisterInformation;

import day6.DrinksMenu.Reference.ReferenceDrinks;
import day6.DrinksMenu.Reference.ReferenceIngridients;

public class IngredientsOfDrinks implements RegisterInformationMethods {

    private int idDrink;
    private int idIngridient;
    private int qty;

    public IngredientsOfDrinks() {
    }

    @Override
    public void save() {
        System.out.println("Saving Ingridients of drink " + getIdDrink());
    }

    @Override
    public IngredientsOfDrinks[] getDataBy(Object key) {
        return (IngredientsOfDrinks[]) new IngredientsOfDrinks[0];
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public int getIdIngridient() {
        return idIngridient;
    }

    public void setIdIngridient(int idIngridient) {
        this.idIngridient = idIngridient;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
