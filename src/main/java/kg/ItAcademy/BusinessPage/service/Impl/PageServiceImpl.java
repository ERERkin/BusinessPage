package kg.ItAcademy.BusinessPage.service.Impl;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import kg.ItAcademy.BusinessPage.entity.Log;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PageModel;
import kg.ItAcademy.BusinessPage.repos.PageRepo;
import kg.ItAcademy.BusinessPage.service.DivService;
import kg.ItAcademy.BusinessPage.service.InputTextService;
import kg.ItAcademy.BusinessPage.service.LogService;
import kg.ItAcademy.BusinessPage.service.PageService;
//import kg.ItAcademy.BusinessPage.Trash.StyleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
//import java.sql.Date;
import java.util.*;

import static java.util.Collections.sort;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageRepo pageRepo;
    @Autowired
    DivService divService;
    @Autowired
    InputTextService inputTextService;
    @Autowired
    LogService logService;
    Boolean cheker = true;

    String styleFiles[] = {"styleColor", "styleLayout", "styleSize"};

    @Override
    public Page getById(Long id) {
        System.out.println(id);
        Page page = pageRepo.findById(id).orElse(new Page());
        System.out.println(page);
        return page;
    }

    @Override
    public PageModel getPageById(Long id) {
        Page page = getById(id);
        List<Div> divs = divService.getAllDivsByPageId(id);
        return PageModel.builder()
                .id(id)
                .name(page.getName())
                .title(page.getTitle())
                .build();
    }

    @Override
    public List<Page> getAll() {
        return pageRepo.findAll();
    }

    @Override
    public Page save(Page item) {
        return pageRepo.save(item);
    }

    @Override
    public PageModel createPage(Page page) {
        page = save(page);
        return getPageById(page.getId());
    }

    @Override
    public String updatePage(Object o, Long id) {
        Page page = getById(id);
        HashMap<String, Object> hashMap = (HashMap<String, Object>)o;
        for(Map.Entry<String, Object> me : hashMap.entrySet()){
            //System.out.println(me);
            if(me.getKey().equals("header")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 1, id);
            }
            if(me.getKey().equals("body")){
                //System.out.println(me);
                ArrayList<Object> hashMapBody = (ArrayList<Object>)me.getValue();
                for(Object div : hashMapBody){
                    divService.saveDiv(div, 2, id);
                }
            }
            if(me.getKey().equals("footer")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 3, id);
            }
        }
        return page.getName();
    }

    @Override
    public void delete(Long id) {
        pageRepo.deleteById(id);
    }

    public String doPage(String pageName){
        Page page = new Page();
        for(Page p : pageRepo.getByName(pageName)){
            page = p;
        }
        logService.logAdd(new Date(), page);
        //Page page = pageService.getById(pageId);
        List<Div> divs = divService.getAllDivsByPageId(page.getId());
        StringBuilder fullDivs = new StringBuilder();
        fullDivs.append(sh).append('\n');
        int f1 = sh.indexOf("TITLE");
        int f2 = f1 + 4;
        fullDivs = new StringBuilder(getChange(fullDivs, f1, f2, page.getTitle()));
//        fullDivs += readS("style1") + '\n';
        sort(divs);
        for(Div div : divs){
            if(div.getType() == 1){
                fullDivs.append("<div class=\"header\">");
            }
            fullDivs.append(getInputs(div));
            if(div.getType() == 2){
                fullDivs.append("</div>");
            }
        }
        fullDivs.append(se);
//        String htmlAddress = writeS(page.getName(), fullDivs);
//        pdfConvert(page.getName());
        String ans = fullDivs.toString();
        return ans;
    }

    public StringBuilder getInputs(Div div){
        StringBuilder ans = new StringBuilder();
        List<InputText> inputTexts = inputTextService.getAllInputTextsByDivId(div.getId());
        StringBuilder divBody = new StringBuilder();
        divBody.append(readS(div.getAddress()));
        StringBuilder input1 = new StringBuilder();
        input1.append(divBody);
        String divBody2 = input1.reverse().toString();
        int f1;
        int f2;
        for(InputText it : inputTexts){
            f1 = divBody.indexOf("@@@" + it.getInputName() + "@@@");
//            input1 = new StringBuilder();
//            input1.append(it.getInputName());
//            f2 = divBody2.lastIndexOf("@@@" + input1.reverse().toString() + "@@@");
//            f2 = divBody.length() - f2 - 1;
            String w = divBody.toString().substring(f1);
            Integer k1 = w.indexOf("\"");
            Integer k2 = w.indexOf("<");
            if(k1 < 0) k1 = f1 + 100000;
            if(k2 < 0) k2 = f1 + 100000;
            f2 = Math.min(k1,k2) + f1 - 1;
            String s = "" + f1 + " " + f2 + " " + it.getInputName();
            divBody = new StringBuilder(getChange(divBody, f1, f2, it.getInputText()));
//            f1 = divBody.indexOf("###" + it.getStyle() + "###");
//            input1 = new StringBuilder();
//            input1.append(it.getStyle());
//            f2 = divBody2.lastIndexOf("###" + input1.reverse().toString() + "###");
//            f2 = divBody.length() - f2 - 1;
//            divBody.append(getChange(divBody, f1, f2, it.getInputText()));
        }
        return divBody;
    }

    public String getChange(StringBuilder s, int a, int b, String ans){
        System.out.println(s.length());
        System.out.println(s.substring(a,Math.min(s.length(), b)));
        String s1 = s.substring(0, a);
        String s2 = s.substring(b + 1);
        return s1 + ans + s2;
    }

    public static String readS(String s){
        String ans = "";
        try(FileReader reader = new FileReader("C:\\Users\\User\\" +
                "IdeaProjects\\BusinessPage\\src\\main" +
                "\\java\\kg\\ItAcademy\\BusinessPage\\ZFrond-code\\" + s))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                ans += (char)c;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return ans + '\n';
    }

    static String sh = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <title>TITLE</title>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
            "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
            "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" +
            "</head>\n" +
            "<body>";
    static String se = "</body>\n" +
            "</html>";
}
