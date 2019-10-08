package sample;

import javafx.geometry.Orientation;
import javafx.scene.control.ListView;

import java.util.Arrays;

/**
 * CS3503 Assignment 2 - part 1
 * Oct. 7 / 2019
 * Avery Briggs
 * 3471065
 *
 * Generates a ListView of 26 colours.
 * */

class ListViewColours {

    private ListView<String> listView;

    ListViewColours() {
        this.listView = genListView();
    }

    private ListView<String> genListView() {
        String[] coloursList = {"AQUA",
                "AZURE",
                "BLACK",
                "BLUE",
                "BROWN",
                "CADETBLUE",
                "CORAL",
                "CRIMSON",
                "CYAN",
                "DARKGOLDENROD",
                "GOLD",
                "GREEN",
                "LIME",
                "MAROON",
                "NAVY",
                "ORANGE",
                "ROYALBLUE",
                "SALMON",
                "SEAGREEN",
                "SILVER",
                "SLATEGRAY",
                "STEELBLUE",
                "TEAL",
                "TURQUOISE",
                "VIOLET",
                "WHITE",
                "YELLOW"};

        ListView<String> listOfItems = new ListView<>();
        listOfItems.getItems().addAll(Arrays.asList(coloursList));
        listOfItems.setOrientation(Orientation.VERTICAL);
        return listOfItems;
    }

    ListView<String> getListView() {
        return this.listView;
    }
}
