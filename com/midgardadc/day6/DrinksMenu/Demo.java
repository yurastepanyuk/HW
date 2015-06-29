package day6.DrinksMenu;

import day6.DrinksMenu.Forms.Menu;
import day6.DrinksMenu.Reference.ReferenceDrinks;
import day6.DrinksMenu.Reference.ReferenceIngridients;
import day6.DrinksMenu.RegisterInformation.PricesOfIngridients;

public class Demo {

    public Demo() {

        Menu menu = new Menu();
        menu.getData();
        menu.draw();

        menu.newDrink("Coffee black");
        menu.newDrink("Coffee american");
        menu.newDrink("Coffee with milk");
        menu.newDrink("Tea");

        menu.newIngridient("Black coffee");
        menu.newIngridient("Water");
        menu.newIngridient("Sugar");
        menu.newIngridient("Milk");
        menu.newIngridient("Black tea");
        menu.newIngridient("Green tea");

        PricesOfIngridients pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(0);
        pricesOfIngridients.setPrice((float)08.50);
        pricesOfIngridients.save();
        pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(1);
        pricesOfIngridients.setPrice((float)00.50);
        pricesOfIngridients.save();
        pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(2);
        pricesOfIngridients.setPrice((float)01.00);
        pricesOfIngridients.save();
        pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(3);
        pricesOfIngridients.setPrice((float)02.50);
        pricesOfIngridients.save();
        pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(4);
        pricesOfIngridients.setPrice((float)07.50);
        pricesOfIngridients.save();
        pricesOfIngridients = new PricesOfIngridients();
        pricesOfIngridients.setIdIngridient(5);
        pricesOfIngridients.setPrice((float)07.50);
        pricesOfIngridients.save();

        menu.addIngridientOfDrink(new ReferenceDrinks(), new ReferenceIngridients(), 1);
        menu.addIngridientOfDrink(new ReferenceDrinks(), new ReferenceIngridients(), 1);
        menu.addIngridientOfDrink(new ReferenceDrinks(), new ReferenceIngridients(), 1);

        menu.delIngridientOfDrink(new ReferenceDrinks(), new ReferenceIngridients(), 1);

        menu.getData();
        menu.draw();

    }
}
