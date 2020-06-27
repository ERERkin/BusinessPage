package kg.ItAcademy.BusinessPage.service;

import kg.ItAcademy.BusinessPage.entity.Div;

import java.util.List;

public interface DivService extends BaseService<Div> {
    Div getDivByPageIdAndType(Long id, Long pageId, String type);

    List<Div> getAllDivsByPageId(Long id);

    Div saveDiv(Object object, Integer type, Long pageId);

//    Div updateDiv(Object object, Integer type, Long pageId);
}
