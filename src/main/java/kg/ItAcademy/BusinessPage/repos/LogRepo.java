package kg.ItAcademy.BusinessPage.repos;

import kg.ItAcademy.BusinessPage.entity.Log;
import kg.ItAcademy.BusinessPage.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepo extends JpaRepository<Log, Long> {
    @Query("SELECT count(l) from Log l where l.date = :d and l.page = :p")
    Long isViewThisDate(Date d, Page p);

    @Query("SELECT sum(l.views) from Log l where l.date = :d and l.page = :p")
    Long viewsThisDate(Date d, Page p);

    @Transactional
    @Modifying
    @Query("update Log set views = (views + 1) where date = :d and page = :p")
    void updateThisDate(Date d, Page p);

//    @Query(value = "select page_id, sum() from final_log_d fld",
//            nativeQuery = true)
    @Query("select l.page.id, sum(l.views) from Log l group by l.page.id")
    List<Object> getPopular();

//    @Query("select l.page.id, sum(l.views) from Log l where datediff(day,l.date,:d) group by l.page.id")
//    List<Object> getPopularDay(Date d);
}
