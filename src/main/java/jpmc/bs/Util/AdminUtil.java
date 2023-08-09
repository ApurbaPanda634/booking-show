package jpmc.bs.Util;

import jpmc.bs.Builder.SeatEntityBuilder;
import jpmc.bs.Builder.ShowEntityBuilder;
import jpmc.bs.entity.SeatEntity;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.exception.BookingShowExecption;
import jpmc.bs.exception.ExceptionMessage;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.service.impl.AdminServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminUtil {
    // logger
    //private static final Logger log = LogManager.getLogger(AdminUtil.class);
    final int MAX_ROW = 26;

    /*
     * Method: createShow
     * @param: setupShowModel
     * @return: showEntity
     * Method to set up the show using admin input
     */
    public ShowEntity createShow(SetupShowModel setupShowModel) {
        if(setupShowModel.getNumberOfRows() > MAX_ROW)
        {
            throw new BookingShowExecption(ExceptionMessage.ROW_LIMIT_EXCEEDED);
        }

        //log.info("Setting up the showEntity");
        Set<String> seats = populateSeats(setupShowModel.getNumberOfRows(),
                setupShowModel.getNumOfSeatsPerRow());

        List<SeatEntity> seatEntityList = new ArrayList<>();
        for(String seat : seats) {
            SeatEntity seatEntity = new SeatEntityBuilder().seatId(seat).isAvailable(true)
                    .build();
            seatEntityList.add(seatEntity);
        }

        return new ShowEntityBuilder().showNumber(setupShowModel.getShowNumber())
                .seats(seatEntityList).cancellationWindow(setupShowModel.getCancellationWindow())
                .build();
    }

    /*
     * Method: populateSeat
     * @param: numberOfRows,numOfSeatsPerRow
     * @return: Set<String> (unique set of seats id)
     * Method to generate all seats (like A1,A2...)
     */
    private Set<String> populateSeats(int numberOfRows, int numOfSeatsPerRow) {
        //log.info("populating seat details");
        Set<String> seats = new HashSet<>();
        for(int row = 0; row< numberOfRows; row++)
        {
            for(int seat = 1; seat<= numOfSeatsPerRow; seat++)
            {
                String seatName = String.valueOf(((char)(row + 'A'))) + seat;
                seats.add(seatName);
            }
        }

        return seats;
    }
}
