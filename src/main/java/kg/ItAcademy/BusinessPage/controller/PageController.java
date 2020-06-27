package kg.ItAcademy.BusinessPage.controller;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PageModel;
import kg.ItAcademy.BusinessPage.service.DivService;
import kg.ItAcademy.BusinessPage.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    PageService pageService;
    @Autowired
    DivService divService;

    @GetMapping("/{id}")
    public PageModel getPageById(@PathVariable Long id) {
        return pageService.getPageById(id);
    }

    @PostMapping("/{id}")
    public String getDone(@PathVariable Long id, @RequestBody Object o) {
        return "http://localhost:8080/page/show/" + pageService.updatePage(o, id);
    }

    @GetMapping
    public List<Page> getAllPage() {
        return pageService.getAll();
    }

    @PostMapping
    public PageModel savePage(@RequestBody Page item) {
        return pageService.createPage(item);
    }

    @DeleteMapping("/{id}")
    public void deletePage(@PathVariable Long id) {
        pageService.delete(id);
    }

//    @PostMapping("/{pageId}/logo")
//    public Div saveLogo(@RequestBody Object item, @PathVariable Long pageId) {
//        return divService.saveDiv(item, "Logo", pageId);
//    }

    @GetMapping("/show/{name}")
    public String doPage(@PathVariable String name){
        return pageService.doPage(name);
    }
}
