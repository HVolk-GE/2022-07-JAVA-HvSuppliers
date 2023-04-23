package de.hvolk.hvsupplier.listview;

import de.hvolk.hvsupplier.model.Delivery;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListVewCellDelivery implements Callback<ListView<Delivery>, ListCell<Delivery>> {

    @Override
    public ListCell<Delivery> call(ListView<Delivery> listViewItemCell) {
        return new ListViewCell2();
    }
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 16:50PM).
 */

