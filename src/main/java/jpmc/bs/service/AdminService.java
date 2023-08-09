package jpmc.bs.service;

import jpmc.bs.model.AdminViewModel;
import jpmc.bs.model.SetupShowModel;
import jpmc.bs.entity.ShowEntity;

public interface AdminService {

    ShowEntity setup(SetupShowModel showEntity);

    AdminViewModel viewShow(int showNumber);
}
