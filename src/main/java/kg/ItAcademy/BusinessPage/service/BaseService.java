package kg.ItAcademy.BusinessPage.service;

import java.util.List;

public interface BaseService<T> {
    T getById(Long id);

    List<T> getAll();

    T save (T item);

    void delete(Long id);
}
