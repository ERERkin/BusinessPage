package kg.ItAcademy.BusinessPage.controller;

import kg.ItAcademy.BusinessPage.model.PopularPageModel;
import kg.ItAcademy.BusinessPage.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/top")
public class TopPageController {
    @Autowired
    LogService logService;
    @GetMapping
    List<PopularPageModel> getTop(){
        return logService.getTop();
    }
}
