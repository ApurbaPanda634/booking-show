package jpmc.bs.service.impl;

import jpmc.bs.Builder.BuyerEntityBuilder;
import jpmc.bs.Util.UserUtil;
import jpmc.bs.entity.BuyerEntity;
import jpmc.bs.entity.SeatEntity;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.exception.BookingShowExecption;
import jpmc.bs.exception.ExceptionMessage;
import jpmc.bs.exception.NotFoundException;
import jpmc.bs.model.BookTicketModel;
import jpmc.bs.model.CancelTicketModel;
import jpmc.bs.repository.BuyerRepository;
import jpmc.bs.repository.SeatRepository;
import jpmc.bs.repository.ShowRepository;
import jpmc.bs.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService
{

    //private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    ShowRepository showRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SeatRepository seatRepository;

    UserUtil userUtil = new UserUtil();

    /*
     * Method: getAvailableSeats
     * @param: showNumber (input by user)
     * @return: All available seat comma separated
     * Method to find all available seats
     */
    @Override
    public String getAvailableSeats(int showNumber) {
        //log.info("get all available seats for a specific show {}", showNumber);
        ShowEntity show = fetchShowEntity(showNumber);
        Set<String> availableSeat = new HashSet<>();

        // filtering seat details based on availability
        show.getSeats().stream().filter(SeatEntity::isAvailable).forEach(seatEntity -> {
            availableSeat.add(seatEntity.getSeatId());
        });
        return availableSeat.toString();
    }

    /*
     * Method: book
     * @param: bookTicketModel (input by user)
     * @return: ticketId which got created
     * Method to book the ticket and return the booked ticket id
     */
    @Override
    public String book(BookTicketModel bookTicketModel) {
        int showNumber = bookTicketModel.getShowNumber();
        //log.info("book a ticket for show {}", showNumber);
        ShowEntity show = fetchShowEntity(showNumber);

        List<SeatEntity> seatsToBook = new ArrayList<>();

        Set<String> userProvidedSeatNumbers = new HashSet<>(bookTicketModel
                .getSeatNumbers());

        String ticketNumber = userUtil.generateTicketNumber();
        String currentExpiryTime = userUtil.getExpiryTime(show.getCancellationWindow());

        // check if seats are already booked
        show.getSeats().forEach(seat -> {
            if(userProvidedSeatNumbers.contains(seat.getSeatId())
                    && seat.isAvailable())
            {
                seat.setAvailable(false);
                seat.setTicketId(ticketNumber);
                seatsToBook.add(seat);
            }
            else if(userProvidedSeatNumbers.contains(seat.getSeatId())
                    && !seat.isAvailable())
            {
                // seat is already booked
                throw new BookingShowExecption(ExceptionMessage.SEAT_BOOKED);
            }
        });

        // check if phone-number already present
        buyerRepository.findById(bookTicketModel.getPhoneNumber())
                .ifPresent(data -> {throw new BookingShowExecption(ExceptionMessage
                        .DUPLICATE_PHONE_NUMBER);});

        // update the seats
        BuyerEntity buyerEntity = new BuyerEntityBuilder().showNumber(showNumber)
                .phoneNumber(bookTicketModel.getPhoneNumber()).ticketId(ticketNumber)
                        .seats(seatsToBook).bookingExpiryTime(currentExpiryTime).build();

        buyerRepository.save(buyerEntity);

        return ticketNumber;
    }

    /*
     * Method: cancel
     * @param: cancelTicketModel (input by user)
     * @return: ticketId which got cancelled
     * Method to cancel the ticket booking and return the cancelled ticket id
     */
    @Override
    public String cancel(CancelTicketModel cancelTicketModel) {
        //log.info("book a ticket for buyer {}", cancelTicketModel.getPhoneNumber());
        // check if buyer present
        BuyerEntity buyerEntity = fetchBuyerEntity(cancelTicketModel
                .getPhoneNumber());

        // validation, if ticket id not matching
        if(!buyerEntity.getTicketId().equals(cancelTicketModel.getTicket()))
        {
            throw new BookingShowExecption(ExceptionMessage.INVALID_TICKET_NUMBER);
        }

        if(!userUtil.checkIfExpired(buyerEntity.getBookingExpiryTime()))
        {
            throw new BookingShowExecption(ExceptionMessage.CANCEL_WINDOW_EXPIRED);
        }

        buyerEntity.getSeats().forEach(seatEntity -> {
            seatEntity.setTicketId(null);
            seatEntity.setAvailable(true);
        });

        seatRepository.saveAll(buyerEntity.getSeats());
        buyerEntity.setSeats(new ArrayList<>());
        buyerRepository.save(buyerEntity);
        buyerRepository.deleteById(cancelTicketModel.getPhoneNumber());

        return  cancelTicketModel.getTicket();
    }

    /*
     * Method: fetchShowEntity
     * @param: showNumber
     * @return: ShowEntity
     * Method to fetch show entity
     */
    private ShowEntity fetchShowEntity(int showNumber)
    {
        //log.info("Fetching Show Entity");
        return showRepository.findById(showNumber)
                .orElseThrow(NotFoundException::new);
    }

    /*
     * Method: fetchBuyerEntity
     * @param: phoneNumber
     * @return: BuyerEntity
     * Method to fetch buyer entity
     */
    private BuyerEntity fetchBuyerEntity(String phoneNumber)
    {
        //log.info("Fetching Buyer Entity");
        return buyerRepository.findById(phoneNumber)
                .orElseThrow(NotFoundException::new);
    }
}
