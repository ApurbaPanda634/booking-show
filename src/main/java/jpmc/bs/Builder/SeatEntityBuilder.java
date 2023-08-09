package jpmc.bs.Builder;

import jpmc.bs.entity.SeatEntity;

public class SeatEntityBuilder {
    SeatEntity seat;
    public SeatEntityBuilder()
    {
        seat = new SeatEntity();
    }

    public SeatEntityBuilder seatId(String seatId)
    {
        seat.setSeatId(seatId);
        return this;
    }

    public SeatEntityBuilder isAvailable(boolean isAvailable)
    {
        seat.setAvailable(isAvailable);
        return this;
    }

    public SeatEntity build()
    {
        return seat;
    }
}
