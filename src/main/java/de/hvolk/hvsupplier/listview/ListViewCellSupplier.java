package de.hvolk.hvsupplier.listview;

import de.hvolk.hvsupplier.model.Supplier;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListViewCellSupplier implements Callback<ListView<Supplier>, ListCell<Supplier>> {

    @Override
    public ListCell<Supplier> call(ListView<Supplier> listViewItemCell) {
        return new ListViewCell();
    }

}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 17:00PM).
 */