package kg.ItAcademy.BusinessPage.service;

import kg.ItAcademy.BusinessPage.entity.InputText;

import java.util.List;

public interface InputTextService extends BaseService<InputText> {
    List<InputText> getAllInputTextsByDivId(Long id);
}
