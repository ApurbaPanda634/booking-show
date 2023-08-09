package jpmc.bs.service.impl;

import jpmc.bs.Builder.AdminViewModelBuilder;
import jpmc.bs.Builder.BuyerModelBuilder;
import jpmc.bs.entity.BuyerEntity;
import jpmc.bs.model.AdminViewModel;
import jpmc.bs.model.BuyerModel;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.Util.AdminUtil;
import jpmc.bs.entity.ShowEntity;
import jpmc.bs.repository.BuyerRepository;
import jpmc.bs.repository.ShowRepository;
import jpmc.bs.service.AdminService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    //private static final Logger log = org.apache.logging.log4j.LogManager
    //       .getLogger(AdminServiceImpl.class);
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    AdminUtil adminUtil = new AdminUtil();

    /*
     * Method: setup
     * @param: setupShowModel
     * Method to set up the show using admin input
     */
    @Override
    public ShowEntity setup(SetupShowModel setupShowModel) {
        //log.info("setting up show: {}", setupShowModel.getShowNumber());
        ShowEntity showEntity = adminUtil.createShow(setupShowModel);
        showRepository.save(showEntity);
        return showEntity;
    }

    /*
     * Method: viewShow
     * @param: showNumber (unique show number)
     * @return: AdminViewModel (view model for admin)
     * Method to populate the AdminViewModel
     */
    @Override
    public AdminViewModel viewShow(int showNumber) {
        List<BuyerEntity> buyers = buyerRepository.findByShowNumber(showNumber);
        List<BuyerModel> buyerModels = new ArrayList<>();
        buyers.forEach(b -> {
            List<String> seats = new ArrayList<>();
            b.getSeats().forEach(e -> seats.add(e.getSeatId()));
            buyerModels.add(new BuyerModelBuilder().ticket(b.getTicketId())
                    .buyerPhoneNumber(b.getPhoneNumber()).allocatedSeats(seats).build());
        });

        return new AdminViewModelBuilder().showNumber(showNumber).buyers(buyerModels).build();
    }
}
