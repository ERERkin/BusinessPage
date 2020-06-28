package kg.ItAcademy.BusinessPage.service.Impl;

import kg.ItAcademy.BusinessPage.entity.Div;
import kg.ItAcademy.BusinessPage.entity.InputText;
import kg.ItAcademy.BusinessPage.entity.Page;
import kg.ItAcademy.BusinessPage.model.PageModel;
import kg.ItAcademy.BusinessPage.repos.PageRepo;
import kg.ItAcademy.BusinessPage.service.DivService;
import kg.ItAcademy.BusinessPage.service.InputTextService;
import kg.ItAcademy.BusinessPage.service.PageService;
//import kg.ItAcademy.BusinessPage.Trash.StyleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
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
        Object sendObject = hashMap.get("sendingObj");
        HashMap<String, Object> hashMapSendObject = (HashMap<String, Object>)sendObject;
        Object allObject = hashMapSendObject.get("all");
        HashMap<String, Object> hashMapAllObject = (HashMap<String, Object>)allObject;
        for(Map.Entry<String, Object> me : hashMapAllObject.entrySet()){
            //System.out.println(me);
            if(me.getKey().equals("logo")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 1, id);
            }
            if(me.getKey().equals("nav")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 2, id);
            }
            if(me.getKey().equals("body")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 3, id);
            }
            if(me.getKey().equals("footer")){
                //System.out.println(me);
                divService.saveDiv(me.getValue(), 4, id);
            }
        }
        return page.getName();
    }

    @Override
    public void delete(Long id) {
        pageRepo.deleteById(id);
    }

    public String doPage(String pageName){
        Page page = pageRepo.getByName(pageName).get(0);
        //Page page = pageService.getById(pageId);
        List<Div> divs = divService.getAllDivsByPageId(page.getId());
        StringBuilder fullDivs = new StringBuilder();
        fullDivs.append(sh).append('\n');
        int f1 = sh.indexOf("TITLE");
        int f2 = f1 + 4;
        fullDivs = new StringBuilder(getChange(fullDivs, f1, f2, page.getTitle()));
        fullDivs.append(getStyles(divs));
//        fullDivs += readS("style1") + '\n';
        fullDivs.append(sc).append('\n');
        System.out.println(fullDivs);
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
        Integer border = ans.indexOf("</style>");
        String ans1 = ans.substring(0,border);
        String ans2 = ans.substring(border);
        ans2 = ans2.replace("normal" , "llllllllll");
        ans2 = ans2.replace("reverse", "normal");
        ans2 = ans2.replace("llllllllll", "reverse");
        ans = ans1 + ans2;
        return ans;
    }

    public String getStyles(List<Div> divs){
        StringBuilder s = new StringBuilder();
        for(String styleClass : styleFiles){
            s.append(readS(styleClass)).append(" \n");
        }
        Set<String> hashSet = new HashSet<>();
        for(Div div : divs) {
            String[] styles = div.getStyle().split(" ");
            for(String style : styles){
                int i1 = s.indexOf("." + style);
                if(i1 < 0) continue;
                String subS = s.substring(i1);
                int i2 = subS.indexOf('}') + 1;
                hashSet.add(subS.substring(0,i2));
            }
        }
        String ans = "";
        for(String s1 : hashSet){
            ans += s1 + '\n';
        }
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
        int f1 = divBody.indexOf("###class###");
        input1 = new StringBuilder();
        input1.append("###class###");
        int f2 = divBody2.lastIndexOf(input1.reverse().toString());
        f2 = divBody.length() - f2 - 1;
        //ans.append(getChange(divBody, f1, f2, div.getStyle()));
        String[] styles = div.getStyle().split(" ");
        String style = "";
        for(String s : styles){
            if(!s.equals("normal") && !s.equals("reverse")){
                style += s + " ";
            }else if(s.equals("reverse")){
                cheker = false;
            }
        }
        divBody = new StringBuilder(getChange(divBody, f1, f2, style));
        for(InputText it : inputTexts){
            f1 = divBody.indexOf("@@@" + it.getInputName() + "@@@");
            input1 = new StringBuilder();
            input1.append(it.getInputName());
            f2 = divBody2.lastIndexOf("@@@" + input1.reverse().toString() + "@@@");
            f2 = divBody.length() - f2 - 1;
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
                "\\java\\kg\\ItAcademy\\BusinessPage\\Fcode\\" + s))
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
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\"\n" +
            "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
            "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@500;600;800&display=swap\" rel=\"stylesheet\">\n" +
            "    <title>TITLE</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            min-height: 100vh;\n" +
            "            margin: 0;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: center;\n" +
            "            font-family: 'Montserrat', sans-serif;\n" +
            "        }\n" +
            "        a{/*это ко всем ссылкам по умолчанию указываю стиль, чтобы он синим не горел*/\n" +
            "            text-decoration: none;\n" +
            "            outline: none;\n" +
            "            color: inherit;\n" +
            "            margin: 0 10px;\n" +
            "        }";
    static String sc =  ".normal{\n" +
            "            width: 38%;\n" +
            "            background: #444444;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: center;\n" +
            "        }\n" +
            "\n" +
            "        .reverse{\n" +
            "            width: 59%;\n" +
            "            background: #fdffff;\n" +
            "            border: 2px solid #444444;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: center;\n" +
            "        }" +
            ".mainCont{\n" +
            "            height: 600px;\n" +
            "            width: 1000px;\n" +
            "            display: flex;\n" +
            "            flex-direction: column;\n" +
            "            justify-content: space-between;\n" +
            "        }\n" +
            "        .header{\n" +
            "            display: flex;\n" +
            "            justify-content: space-between;\n" +
            "        }\n" +
            "        .nav{\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "        }\n" +
            "        .body-cell{\n" +
            "            height: 100px;\n" +
            "            display: flex;\n" +
            "            justify-content: space-between;\n" +
            "        }\n" +
            "        .p{\n" +
            "            padding: 5px;\n" +
            "        }\n" +
            "        .footer{\n" +
            "            display: flex;\n" +
            "            justify-content: space-between;\n" +
            "        }\n" +
            "\n" +
            "        @media (max-width: 1076px) {\n" +
            "            body{\n" +
            "                padding: 20px 50px;\n" +
            "            }\n" +
            "        }\n" +
            "        @media (max-width: 756px) {\n" +
            "            body{\n" +
            "                padding: 10px 20px;\n" +
            "            }\n" +
            "            .small{\n" +
            "                font-size: 16px;\n" +
            "            }\n" +
            "\n" +
            "            .medium{\n" +
            "                font-size: 18px;\n" +
            "            }\n" +
            "\n" +
            "            .large{\n" +
            "                font-size: 24px;\n" +
            "            }\n" +
            "            a{\n" +
            "                margin: 0 3px;\n" +
            "            }\n" +
            "        }\n" +
            "        @media (max-width: 568px) {\n" +
            "            body{\n" +
            "                padding: 0 20px;\n" +
            "            }\n" +
            "            .small{\n" +
            "                font-size: 12px;\n" +
            "            }\n" +
            "\n" +
            "            .medium{\n" +
            "                font-size: 16px;\n" +
            "            }\n" +
            "\n" +
            "            .large{\n" +
            "                font-size: 22px;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"mainCont\">";
    static String se = "</div>\n" +
            "</body>\n" +
            "</html>";
}
