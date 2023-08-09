package jpmc.bs.model;

import org.springframework.stereotype.Component;

@Component
public class SetupShowModel {
    int showNumber;
    int numberOfRows;

    int numOfSeatsPerRow;
    int cancellationWindow;

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumOfSeatsPerRow() {
        return numOfSeatsPerRow;
    }

    public void setNumOfSeatsPerRow(int numOfSeatsPerRow) {
        this.numOfSeatsPerRow = numOfSeatsPerRow;
    }

    public int getCancellationWindow() {
        return cancellationWindow;
    }

    public void setCancellationWindow(int cancellationWindow) {
        this.cancellationWindow = cancellationWindow;
    }
}
