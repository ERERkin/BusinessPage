package kg.ItAcademy.BusinessPage.service;

import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PageModel;

public interface PageService extends BaseService<Page> {
    String doPage(String pageName);

    PageModel getPageById(Long id);

    PageModel createPage(Page page);

    String updatePage(Object o, Long id);
}
