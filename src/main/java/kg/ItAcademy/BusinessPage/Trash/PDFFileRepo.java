//package kg.ItAcademy.BusinessPage.Trash;
//
//import kg.ItAcademy.BusinessPage.entity.PDFFile;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface PDFFileRepo extends JpaRepository<PDFFile, Long> {
//    @Query("select pf from PDFFile pf where pf.address = :s")
//    List<PDFFile> getAllByAddress(String s);
//}
