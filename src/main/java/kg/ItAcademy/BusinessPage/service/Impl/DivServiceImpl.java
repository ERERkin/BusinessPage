package kg.ItAcademy.BusinessPage.service.Impl;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.repos.DivRepo;
import kg.ItAcademy.BusinessPage.service.DivService;
import kg.ItAcademy.BusinessPage.service.InputTextService;
import kg.ItAcademy.BusinessPage.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DivServiceImpl implements DivService {
    @Autowired
    DivRepo divRepo;
    @Autowired
    InputTextService inputTextService;
    @Autowired
    PageService pageService;

    @Override
    public Div getById(Long id) {
        return divRepo.findById(id).get();
    }

    @Override
    public Div getDivByPageIdAndType(Long id, Long pageId, String type) {
        Page page = pageService.getById(id);
        List<Div> divs = divRepo.getAllDivsByPageId(id);
        for(Div d : divs){
            if(!d.getType().equals("Body") && d.getType().equals(type)){
                return d;
            }
            if(d.getId() == id) return d;
        }
        return null;
    }

    @Override
    public List<Div> getAll() {
        return divRepo.findAll();
    }

//    @Override
//    public Div getDivByPageIdAndType(Long id, Long pageId, Integer type) {
//        return null;
//    }

//    @Override
//    public Div createDiv(Object object, Integer type, Long pageId) {
//        HashMap<String, Object> hashMap = (HashMap<String, Object>)object;
//        Page page = Page.builder()
////                .id(new Long((Integer)
////                        ((HashMap<String, Object>)hashMap.get("page"))
////                                .get("id")))
//                .id(pageId)
//                .build();
//        Div div = Div.builder()
//                .address(hashMap.get("name").toString())
//                .type(type)
//                .page(page)
//                .build();
//        div = divRepo.save(div);
//        Object o = hashMap.get("inputs");
//        Object[] inputs = (Object[])o;
//        for(Object inp : inputs){
//            Map.Entry<String, O>
//            InputText inputText = InputText.builder()
//                    .divText(div)
//                    .inputName(inp.)
//                    .build();
//        }
//        return null;
//    }

//    @Override
//    public Div updateDiv(Object object, Integer type, Long pageId) {
//        return null;
//    }

    @Override
    public Div save(Div item) {
        return divRepo.save(item);
    }
//    @Column(name = "type")
//    String type;
//
//    @Column(name = "address")
//    String address;
//
//    @ManyToOne
//    @JoinColumn(name = "page_id")
//    Page page;
    @Override
    public Div saveDiv(Object object, Integer type, Long pageId) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>)object;
        String address = "";
        Object oDiv = new Object();
        for(Map.Entry<String, Object> me : hashMap.entrySet()){
            address = me.getKey();
            oDiv = me.getValue();
        }
        HashMap<String, Object> hashMapText = (HashMap<String, Object>)oDiv;
        Page page = Page.builder()
//                .id(new Long((Integer)
//                        ((HashMap<String, Object>)hashMap.get("page"))
//                                .get("id")))
                .id(pageId)
                .build();
        Div div = null;
        List<Div> divs = divRepo.getAllDivsByPageId(pageId);
        for(Div d : divs){
            if(d.getType() == type && d.getAddress() == address){
                div = d;
            }
        }
        if(div == null) {
            div = Div.builder()
                    .address(address)
                    .type(type)
                    .page(page)
                    .build();
        }else{
            div = Div.builder()
                    .id(div.getId())
                    .address(address)
                    .type(type)
                    .page(page)
                    .build();
        }
        div = divRepo.save(div);

        inputTextService.deleteByDiv(div.getId(), type);
        System.err.println(div.getId());
        for(Map.Entry<String, Object> m : hashMapText.entrySet()){
            inputTextService.save(
                    InputText.builder()
                            .divText(div)
                            .inputName(m.getKey())
                            .inputText(m.getValue().toString())
                            .build()
            );
        }
        return div;
    }

    @Override
    public void delete(Long id) {
        divRepo.deleteById(id);
    }

    @Override
    public List<Div> getAllDivsByPageId(Long id) {
        Page page = pageService.getById(id);
        return divRepo.getAllDivsByPageId(id);
    }
}
