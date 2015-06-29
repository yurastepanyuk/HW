package day6.DrinksMenu.Forms;

import day6.DrinksMenu.Reference.ReferenceDrinks;
import day6.DrinksMenu.Reference.ReferenceIngridients;

public interface ActionMenu {

    void newDrink(String nameDrink);//add new drink in DB
    void newIngridient(String nameIngridient);//add new ingridient in DB

    void addIngridientOfDrink(ReferenceDrinks drink, ReferenceIngridients ingridients, int qty);// menu of operators
    void delIngridientOfDrink(ReferenceDrinks drink, ReferenceIngridients ingridients, int qty);// menu of operators

}
