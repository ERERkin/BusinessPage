package kg.ItAcademy.BusinessPage.service.Impl;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import kg.ItAcademy.BusinessPage.repos.DivRepo;
import kg.ItAcademy.BusinessPage.repos.InputTextRepo;
import kg.ItAcademy.BusinessPage.service.DivService;
import kg.ItAcademy.BusinessPage.service.InputTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InputTextServiceImpl implements InputTextService {
    @Autowired
    InputTextRepo inputTextRepo;
    @Autowired
    DivService divService;

    @Override
    public InputText getById(Long id) {
        return inputTextRepo.findById(id).get();
    }

    @Override
    public List<InputText> getAll() {
        return inputTextRepo.findAll();
    }

    @Override
    public InputText save(InputText item) {
        return inputTextRepo.save(item);
    }

    @Override
    public void delete(Long id) {
        inputTextRepo.deleteById(id);
    }

    @Override
    public List<InputText> getAllInputTextsByDivId(Long id) {
        Div div = divService.getById(id);
        return inputTextRepo.findAllByDivText(div);
    }
}
