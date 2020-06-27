package kg.ItAcademy.BusinessPage.controller;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.service.DivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main/div")
public class DivController {
    @Autowired
    DivService divService;

    @GetMapping("/{id}")
    public Div getById(@PathVariable Long id) {
        return divService.getById(id);
    }

    @GetMapping
    public List<Div> getAll() {
        return divService.getAll();
    }

    @PostMapping
    public Div save(@RequestBody Div div) {
        return divService.save(div);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        divService.delete(id);
    }
}
