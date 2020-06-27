package kg.ItAcademy.BusinessPage.repos;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputTextRepo extends JpaRepository<InputText, Long>{
//    @Query("select it from InputText it where it.divText.id = :id")
//    List<InputText> getAllInputTextsByDivId(Long id);

    List<InputText> findAllByDivText(Div div);
}
