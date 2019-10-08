package sample;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * CS3503 Assignment 2 - part 1
 * Oct. 7 / 2019
 * Avery Briggs
 * 3471065
 *
 * Generates a MenuBar with MenuItems for each button.
 * */

class GenMenuBar {

    private MenuBar menuBar;

    GenMenuBar(HashMap<String, ArrayList<String>> menus) {
        MenuBar mb = new MenuBar();
        Set<Map.Entry<String, ArrayList<String>>> set = menus.entrySet();
        for (Object o : set) {
            Map.Entry entry = (Map.Entry) o;
            String btnName = (String) entry.getKey();
            ArrayList<String> menuItems = (ArrayList<String>) entry.getValue();
            Menu newMenu = genNewMenu(btnName, menuItems);
            mb.getMenus().add(newMenu);
        }
        this.menuBar = mb;
    }

    private Menu genNewMenu(String menuLabel, ArrayList<String> menuItems) {
        Menu m = new Menu(menuLabel);
        for (String label : menuItems) {
            MenuItem mItem = new MenuItem(label);
            m.getItems().add(mItem);
        }
        return m;
    }

    MenuBar getMenuBar() {
        return this.menuBar;
    }
}
