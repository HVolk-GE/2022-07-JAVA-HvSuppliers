package de.hvolk.hvsupplier.logic;

public class MainController {

    private static MainController instance;

    private int currentId;

    private MainController() {
        // only private
    }

    public static synchronized MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public int getCurrentId() {
        return currentId;
    }
    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
}
