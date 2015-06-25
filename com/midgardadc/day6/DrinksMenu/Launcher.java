package day6.DrinksMenu;

import day6.DrinksMenu.Forms.Menu;

/**
 * Created by stepanyuk on 24.06.2015.
 */
public class Launcher {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.getData();
        menu.draw();
    }

}
