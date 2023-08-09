package jpmc.bs.Builder;

import jpmc.bs.model.BuyerModel;

import java.util.List;

public class BuyerModelBuilder {
    BuyerModel buyerModel;

    public BuyerModelBuilder()
    {
        buyerModel = new BuyerModel();
    }

    public BuyerModelBuilder buyerPhoneNumber(String buyerPhoneNumber)
    {
        buyerModel.setBuyerPhoneNumber(buyerPhoneNumber);
        return this;
    }

    public BuyerModelBuilder ticket(String ticket)
    {
        buyerModel.setTicket(ticket);
        return this;
    }

    public BuyerModelBuilder allocatedSeats(List<String> allocatedSeats)
    {
        buyerModel.setAllocatedSeats(allocatedSeats);
        return this;
    }

    public BuyerModel build()
    {
        return buyerModel;
    }
}
