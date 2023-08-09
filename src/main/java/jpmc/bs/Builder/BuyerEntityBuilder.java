package jpmc.bs.Builder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jpmc.bs.entity.BuyerEntity;
import jpmc.bs.entity.SeatEntity;

import java.util.List;

public class BuyerEntityBuilder {
    BuyerEntity buyerEntity;
    public BuyerEntityBuilder()
    {
        buyerEntity = new BuyerEntity();
    }

    public BuyerEntityBuilder phoneNumber(String phoneNumber)
    {
        buyerEntity.setPhoneNumber(phoneNumber);
        return this;
    }

    public BuyerEntityBuilder showNumber(int showNumber)
    {
        buyerEntity.setShowNumber(showNumber);
        return this;
    }

    public BuyerEntityBuilder name(String name)
    {
        buyerEntity.setName(name);
        return this;
    }

    public BuyerEntityBuilder ticketId(String ticketId)
    {
        buyerEntity.setTicketId(ticketId);
        return this;
    }

    public BuyerEntityBuilder seats(List<SeatEntity> seats)
    {
        buyerEntity.setSeats(seats);
        return this;
    }

    public BuyerEntityBuilder bookingExpiryTime(String bookingExpiryTime)
    {
        buyerEntity.setBookingExpiryTime(bookingExpiryTime);
        return this;
    }

    public BuyerEntity build()
    {
        return buyerEntity;
    }
}
