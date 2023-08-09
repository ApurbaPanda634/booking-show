package jpmc.bs.service;

import jakarta.validation.constraints.AssertTrue;
import jpmc.bs.Builder.BuyerEntityBuilder;
import jpmc.bs.Builder.BuyerModelBuilder;
import jpmc.bs.Builder.SeatEntityBuilder;
import jpmc.bs.Builder.ShowEntityBuilder;
import jpmc.bs.Util.UserUtil;
import jpmc.bs.entity.BuyerEntity;
import jpmc.bs.entity.SeatEntity;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.model.BookTicketModel;
import jpmc.bs.model.CancelTicketModel;
import jpmc.bs.repository.BuyerRepository;
import jpmc.bs.repository.SeatRepository;
import jpmc.bs.repository.ShowRepository;
import jpmc.bs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private SeatRepository seatRepository;

    @Test
    public void test_getAvailableSeats() {
        //Given
        ShowEntity showEntity = getMockShowEntity();
        when(showRepository.findById(showEntity.getShowNumber()))
                .thenReturn(Optional.of(showEntity));

        String res = userService.getAvailableSeats(showEntity.getShowNumber());
        assertTrue(res.contains("A1"));
    }

    @Test
    public void test_book() {
        //Given
        ShowEntity showEntity = getMockShowEntity();
        when(showRepository.findById(showEntity.getShowNumber()))
                .thenReturn(Optional.of(showEntity));
        when(buyerRepository.findById(any())).thenReturn(Optional.empty());
        when(buyerRepository.save(any())).thenReturn(new BuyerEntity());

        BookTicketModel bookTicketModel = new BookTicketModel();
        bookTicketModel.setPhoneNumber("9564053406");
        bookTicketModel.setShowNumber(12);
        bookTicketModel.setSeatNumbers(List.of("A1"));
        String res = userService.book(bookTicketModel);

        assertTrue(res.contains("TKT"));
    }

    @Test
    public void test_cancel() {
        //Given
        BuyerEntity buyerEntity = getMockBuyerEntity();;
        when(buyerRepository.findById(any())).thenReturn(Optional.of(buyerEntity));
        when(buyerRepository.save(any())).thenReturn(null);
        when(seatRepository.saveAll(any())).thenReturn(null);

        CancelTicketModel cancelTicketModel = new CancelTicketModel();
        cancelTicketModel.setTicket("TKT1");
        cancelTicketModel.setPhoneNumber("1234");
        String res = userService.cancel(cancelTicketModel);

        assertTrue(res.contains("TKT"));
    }

    BuyerEntity getMockBuyerEntity()
    {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
        return new BuyerEntityBuilder().phoneNumber("1234").ticketId("TKT1")
                .bookingExpiryTime(dateTime.toString()).seats(Collections
                        .singletonList(getMockSeatEntity())).build();
    }

    SeatEntity getMockSeatEntity()
    {
        return new SeatEntityBuilder().seatId("A1").isAvailable(true).build();
    }
    ShowEntity getMockShowEntity()
    {
        SeatEntity seatEntity = getMockSeatEntity();
        return new ShowEntityBuilder().showNumber(12).cancellationWindow(2)
                .seats(Collections.singletonList(seatEntity)).build();
    }
}
