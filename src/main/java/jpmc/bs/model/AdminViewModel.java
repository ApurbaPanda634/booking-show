package jpmc.bs.model;

import java.util.List;

public class AdminViewModel {
    int showNumber;

    List<BuyerModel> buyers;

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public List<BuyerModel> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<BuyerModel> buyers) {
        this.buyers = buyers;
    }
}
