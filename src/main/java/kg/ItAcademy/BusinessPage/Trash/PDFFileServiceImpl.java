//package kg.ItAcademy.BusinessPage.Trash;
//
//import com.itextpdf.html2pdf.HtmlConverter;
//import kg.ItAcademy.BusinessPage.entity.*;
//import kg.ItAcademy.BusinessPage.Trash.PDFFileRepo;
//import kg.ItAcademy.BusinessPage.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.*;
//import java.util.List;
//
//public class PDFFileServiceImpl implements PDFFileService {
//    @Autowired
//    PDFFileRepo pdfFileRepo;
//    @Autowired
//    PageService pageService;
//    @Autowired
//    DivService divService;
//    @Autowired
//    InputTextService inputTextService;
//    @Autowired
//    StyleClassService styleClassService;
//    @Override
//    public PDFFile getById(Long id) {
//        return pdfFileRepo.findById(id).get();
//    }
//
//    @Override
//    public List<PDFFile> getAll() {
//        return pdfFileRepo.findAll();
//    }
//
//    @Override
//    public PDFFile save(PDFFile item) {
//        return pdfFileRepo.save(item);
//    }
//
//    @Override
//    public void delete(Long id) {
//        pdfFileRepo.deleteById(id);
//    }
//
//    @Override
//    public List<PDFFile> getAllByAddress(String s) {
//        return pdfFileRepo.getAllByAddress(s);
//    }
//
//    public void doPDF(Long pageId, String pdfName){
//        Page page = pageService.getById(pageId);
//        List<Div> divs = divService.getAllDivsByPageId(pageId);
//        String fullDivs = "";
//        int f1 = sh.indexOf("TITLE");
//        int f2 = f1 + 4;
//        fullDivs = getChange(fullDivs, f1, f2, page.getTitle());
//        for(Div div : divs){
//            if(div.getType().equals("Logo")){
//                fullDivs += getCode(div);
//            }
//        }
//        for(Div div : divs){
//            if(div.getType().equals("Header")){
//                fullDivs += getCode(div);
//            }
//        }
//        for(Div div : divs){
//            if(!div.getType().equals("Logo") &&
//                !div.getType().equals("Header") &&
//                !div.getType().equals("Footer"))
//                    fullDivs += getCode(div);
//        }
//        for(Div div : divs){
//            if(div.getType().equals("Footer")){
//                fullDivs += getCode(div);
//            }
//        }
//        fullDivs += se;
//        String htmlAddress = writeS(page.getName(), fullDivs);
//        pdfConvert(page.getName());
//    }
//
//    public void pdfConvert(String address){
//        String html = "C:\\Users\\User" +
//                "\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\" + address +"h.html";
//        try {
//            HtmlConverter.convertToPdf(new FileInputStream(html),
//                    new FileOutputStream("C:\\Users\\User" +
//                            "\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\"+ address +".pdf"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println( "PDF Created!" );
//    }
//
//    public String getCode(Div div){
//        List<InputText> inputTexts = inputTextService.getAllInputTextsByDivId(div.getId());
//        List<StyleClass> styleClasses = styleClassService.getAllStyleClassesByDivId(div.getId());
//        String dibBody = readS(div.getAddress());
//        for(InputText it : inputTexts){
//            int f1 = dibBody.indexOf("@@@" + it.getInputName() + "@@@");
//            int f2 = dibBody.lastIndexOf("@@@" + it.getInputName() + "@@@");
//            dibBody = getChange(dibBody, f1, f2, it.getInputText());
//        }
//        for(StyleClass sc : styleClasses){
//            int f1 = dibBody.indexOf("###" + sc.getType() + "###");
//            int f2 = dibBody.lastIndexOf("###" + sc.getType() + "###");
//            dibBody = getChange(dibBody, f1, f2, sc.getName());
//        }
//        return dibBody;
//    }
//
//    public String getChange(String s, int a, int b, String ans){
//        String s1 = s.substring(0, a);
//        String s2 = s.substring(b + 1);
//        return s1 + ans + s2;
//    }
//
//    public static String writeS(String s, String body){
////		FileWriter writer1 = null;
//        String address = "C:\\Users\\User" +
//                "\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\"
//                + s  +"h.html";
//        try(FileWriter writer = new FileWriter( address, true))
//        {
//            // запись всей строки
//            //String text = "Hello Gold!";
//            writer.write(body);
//            // запись по символам
//            writer.append('\n');
////			writer.append('E');
//
//            writer.flush();
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
//        System.out.println("Hi");
//        return address;
//    }
//
//    public static String readS(String s){
//        String ans = "";
//        try(FileReader reader = new FileReader("C:\\Users\\User\\" +
//                "IdeaProjects\\BusinessPage\\src\\main" +
//                "\\java\\kg\\ItAcademy\\BusinessPage\\Fcode\\" + s))
//        {
//            // читаем посимвольно
//            int c;
//            while((c=reader.read())!=-1){
//                ans += (char)c;
//            }
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
//        return ans + '\n';
//    }
//
//    static String sh = "<!DOCTYPE html>\n" +
//            "<html lang=\"en\">\n" +
//            "<head>\n" +
//            "    <meta charset=\"UTF-8\"></meta>\n" +
//            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></meta>\n" +
//            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"></meta>\n" +
//            "    <title>TITLE</title>\n" +
//            "    <link href=\"styleforlesson3.css\" rel=\"stylesheet\"></link>\n" +
//            "    <link href=\"styleColor.css\" rel=\"stylesheet\"></link>\n" +
//            "</head>\n" +
//            "<body>";
//    static String se = "\n" +
//            "</body>\n" +
//            "</html>";
//}
