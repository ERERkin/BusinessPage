package kg.ItAcademy.BusinessPage.repos;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivRepo extends JpaRepository<Div, Long> {
//    @Query("select d from Div d where d.page.id = :id")
//    List<Div> getAllDivsByPageId(Long id);

    List<Div> findAllByPage(Page page);
}
