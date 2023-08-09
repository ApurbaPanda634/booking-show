package jpmc.bs.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BUYER")
public class BuyerEntity {
    @Id
    String phoneNumber;
    String name;
    String ticketId;
    @OneToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    List<SeatEntity> seats;

    String bookingExpiryTime;

    public String getBookingExpiryTime() {
        return bookingExpiryTime;
    }

    public void setBookingExpiryTime(String bookingExpiryTime) {
        this.bookingExpiryTime = bookingExpiryTime;
    }

    int showNumber;

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }

}
