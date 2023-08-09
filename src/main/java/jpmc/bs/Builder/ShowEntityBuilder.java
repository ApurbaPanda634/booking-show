package jpmc.bs.Builder;

import jpmc.bs.entity.SeatEntity;
import jpmc.bs.entity.ShowEntity;

import java.util.List;

public class ShowEntityBuilder {
    ShowEntity showEntity;

    public ShowEntityBuilder()
    {
        showEntity = new ShowEntity();
    }

    public ShowEntityBuilder showNumber(int showNumber)
    {
        showEntity.setShowNumber(showNumber);
        return this;
    }

    public ShowEntityBuilder seats(List<SeatEntity> seats)
    {
        showEntity.setSeats(seats);
        return this;
    }

    public ShowEntityBuilder cancellationWindow(int cancellationWindow)
    {
        showEntity.setCancellationWindow(cancellationWindow);
        return this;
    }

    public ShowEntity build()
    {
        return showEntity;
    }
}
