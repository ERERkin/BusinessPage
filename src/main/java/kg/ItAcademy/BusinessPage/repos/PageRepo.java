package kg.ItAcademy.BusinessPage.repos;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepo extends JpaRepository<Page, Long>{
//    @Query("Select p from Page p where p.name = :n")
//    List<Page> getByName(String n);

    List<Page> findAllByName(String name);
}
