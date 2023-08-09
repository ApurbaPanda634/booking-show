package jpmc.bs.service;

import jpmc.bs.model.BookTicketModel;
import jpmc.bs.model.CancelTicketModel;

import java.util.Set;

public interface UserService {
    String getAvailableSeats(int showNumber);

    String book(BookTicketModel bookTicketModel);

    String cancel(CancelTicketModel cancelTicketModel);
}
