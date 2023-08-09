package jpmc.bs.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "SHOW")
public class ShowEntity {
    @Id
    private int showNumber;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SeatEntity> seats;
    private int cancellationWindow;

    public int getCancellationWindow() {
        return cancellationWindow;
    }

    public void setCancellationWindow(int cancellationWindow) {
        this.cancellationWindow = cancellationWindow;
    }

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }
}
