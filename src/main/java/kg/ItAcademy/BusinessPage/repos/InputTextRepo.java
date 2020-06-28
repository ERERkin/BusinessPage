package kg.ItAcademy.BusinessPage.repos;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InputTextRepo extends JpaRepository<InputText, Long>{
    @Query("select it from InputText it where it.divText.id = :id")
    List<InputText> getAllInputTextsByDivId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from InputText it where it.divText.id = :divId")
    void deleteAllByDivId(Long divId);

//    List<InputText> findAllByDivText(Div div);
}
