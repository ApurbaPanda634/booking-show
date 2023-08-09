package jpmc.bs.Builder;

import jpmc.bs.model.AdminViewModel;
import jpmc.bs.model.BuyerModel;

import java.util.List;

public class AdminViewModelBuilder {
    AdminViewModel adminViewModel;

    public AdminViewModelBuilder()
    {
        adminViewModel = new AdminViewModel();
    }


    public AdminViewModelBuilder showNumber(int showNumber)
    {
        adminViewModel.setShowNumber(showNumber);
        return this;
    }

    public AdminViewModelBuilder buyers(List<BuyerModel> buyers)
    {
        adminViewModel.setBuyers(buyers);
        return this;
    }

    public AdminViewModel build()
    {
        return adminViewModel;
    }
}
