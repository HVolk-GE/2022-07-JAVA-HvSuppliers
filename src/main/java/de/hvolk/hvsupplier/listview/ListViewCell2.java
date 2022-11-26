package de.hvolk.hvsupplier.listview;

import de.hvolk.hvsupplier.model.Delivery;
import javafx.scene.control.ListCell;

public class ListViewCell2 extends ListCell<Delivery> {
    @Override
    protected void updateItem(Delivery deliveryToShow, boolean empty) {
        super.updateItem(deliveryToShow, empty);

        if (empty && deliveryToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(deliveryToShow.getMinimalDeliveriesInformation());
        }
    }
}
/**
 * tested and auto - formatted (SHIFT+ALT+L) by H.Volk with IDE (06/09/2022 16:58PM).
 */
