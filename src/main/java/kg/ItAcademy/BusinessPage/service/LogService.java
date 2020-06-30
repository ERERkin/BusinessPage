package kg.ItAcademy.BusinessPage.service;

import kg.ItAcademy.BusinessPage.entity.Log;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PopularPageModel;

import java.util.Date;
import java.util.List;

public interface LogService extends BaseService<Log>{
    void logAdd(Date date, Page page);

    List<PopularPageModel> getTop();
}
