package de.hvolk.hvsupplier.listview;

import de.hvolk.hvsupplier.model.Supplier;
import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<Supplier>{
    @Override
    protected void updateItem(Supplier supplierToShow, boolean empty) {
        super.updateItem(supplierToShow, empty);

        if (empty && supplierToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(supplierToShow.getMinimalInformation());
        }
    }
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 16:54PM).
 */
