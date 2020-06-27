//package kg.ItAcademy.BusinessPage.Trash;
//
//import kg.ItAcademy.BusinessPage.Trash.StyleClass;
//import kg.ItAcademy.BusinessPage.Trash.StyleClassRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class StyleClassServiceImpl implements StyleClassService {
//    @Autowired
//    StyleClassRepo styleClassRepo;
//
//    @Override
//    public StyleClass getById(Long id) {
//        return styleClassRepo.findById(id).get();
//    }
//
//    @Override
//    public List<StyleClass> getAll() {
//        return styleClassRepo.findAll();
//    }
//
//    @Override
//    public StyleClass save(StyleClass item) {
//        return styleClassRepo.save(item);
//    }
//
//    @Override
//    public void delete(Long id) {
//        styleClassRepo.existsById(id);
//    }
//
//    @Override
//    public List<StyleClass> getAllStyleClassesByDivId(Long id) {
//        return styleClassRepo.getAllStyleClassesByDivId(id);
//    }
//}
