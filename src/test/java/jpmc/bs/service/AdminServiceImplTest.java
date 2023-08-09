package jpmc.bs.service;


import jpmc.bs.Builder.AdminViewModelBuilder;
import jpmc.bs.Builder.BuyerEntityBuilder;
import jpmc.bs.Builder.SeatEntityBuilder;
import jpmc.bs.entity.BuyerEntity;
import jpmc.bs.entity.SeatEntity;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.model.AdminViewModel;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.repository.BuyerRepository;
import jpmc.bs.repository.ShowRepository;
import jpmc.bs.service.impl.AdminServiceImpl;
import jpmc.bs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AdminServiceImplTest {
    @Mock
    private ShowRepository showRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void test_setup() {
        //Given
        when(showRepository.save(any())).thenReturn(new ShowEntity());
        SetupShowModel setupShowModel = new SetupShowModel();
        setupShowModel.setShowNumber(12);
        setupShowModel.setCancellationWindow(2);
        setupShowModel.setNumberOfRows(5);
        setupShowModel.setNumOfSeatsPerRow(2);
        ShowEntity res = adminService.setup(setupShowModel);
        assertEquals(10, res.getSeats().size());
    }

    @Test
    public void test_viewShow() {
        //Given
        when(buyerRepository.findByShowNumber(12)).thenReturn(Collections
                .singletonList(getMockBuyerEntity()));
        AdminViewModel res = adminService.viewShow(12);
        assertEquals(res.getShowNumber(), 12);
        assertEquals(res.getBuyers().size(), 1);
        assertTrue(res.getBuyers().get(0).getTicket().contains("TKT1"));
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
}
