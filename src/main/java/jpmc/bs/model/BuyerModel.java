package jpmc.bs.model;

import java.util.List;
public class BuyerModel {
    String BuyerPhoneNumber;
    String ticket;
    List<String> allocatedSeats;

    public List<String> getAllocatedSeats() {
        return allocatedSeats;
    }

    public void setAllocatedSeats(List<String> allocatedSeats) {
        this.allocatedSeats = allocatedSeats;
    }

    public String getBuyerPhoneNumber() {
        return BuyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        BuyerPhoneNumber = buyerPhoneNumber;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
