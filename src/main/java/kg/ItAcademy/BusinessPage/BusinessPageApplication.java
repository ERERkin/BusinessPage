package kg.ItAcademy.BusinessPage;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
import com.itextpdf.html2pdf.HtmlConverter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.tidy.Tidy;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class BusinessPageApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BusinessPageApplication.class, args);
		//div();
	}

	public static void div() throws IOException{
//		String body = "";
//		body += readS("div" + 1);
//		body += readS("div" + 2);
//		body += readS("div" + 3);
//		String name = "i6.html";
////		FileWriter fileWriter = writeS(name, body);
//		String fileWriter = writeS(name, body);
//		System.out.println(fileWriter);
//
//		String fileWriter = "C:\\Users\\User\\IdeaProjects\\" +
//				"BusinessPage\\src\\main\\resources\\static\\" + "hi.html";
//		Document document = new Document();
//		// Создаем writer для записи в pdf
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
//				"C:\\Users\\User\\IdeaProjects\\" +
//						"BusinessPage\\src\\main\\resources\\static\\pdfHi.pdf"));
//		// Открываем для чтения html страничку
//		document.open();
//		// Парсим её и записываем в PDF
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(fileWriter));
//		document.close();
//		System.out.println( "Ваш PDF файл - Создан!" );
//		//Document document = getHtmlDocument(body);
		String html = "C:\\Users\\User" +
				"\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\" + "hi.html";
		HtmlConverter.convertToPdf(new FileInputStream(html),
				new FileOutputStream("C:\\Users\\User" +
						"\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\final2.pdf"));

		System.out.println( "PDF Created!" );
	}

//	private static Document getHtmlDocument(String htmlContent) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("<!DOCTYPE html>");
//		sb.append("<html lang=\"en\">");
//		sb.append("<head><meta charset=\"UTF-8\">");
//		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
//		sb.append("b<title>index3</title>");
//		sb.append("<link href=\"styleforlesson3.css\" rel=\"stylesheet\">");
//		sb.append("</head>");
//		sb.append("<body>");
//		sb.append(htmlContent);
//		sb.append("</body>");
//		sb.append("</html>");
//
//		Tidy tidy = new Tidy();
//		tidy.setXHTML(true);
//		tidy.setQuiet(true);
//		tidy.setShowWarnings(false);
//
//		return (Document) tidy.parseDOM(new ByteArrayInputStream(sb.toString().getBytes()), null);
//	}

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
		return ans;
	}

	public static String writeS(String s, String body){
//		FileWriter writer1 = null;
		String address = "C:\\Users\\User" +
				"\\IdeaProjects\\BusinessPage\\src\\main\\resources\\static\\"
				+ s;
		try(FileWriter writer = new FileWriter( address, true))
		{
			// запись всей строки
			//String text = "Hello Gold!";
			writer.write(sh +'\n');
			writer.write(body + '\n');
			writer.write(se);
			// запись по символам
			writer.append('\n');
//			writer.append('E');

			writer.flush();
		}
		catch(IOException ex){

			System.out.println(ex.getMessage());
		}
		System.out.println("Hi");
		return address;
	}

	static String sh = "<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n" +
			"    <meta charset=\"UTF-8\"></meta>\n" +
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></meta>\n" +
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"></meta>\n" +
			"    <title>index3</title>\n" +
			"    <link href=\"styleforlesson3.css\" rel=\"stylesheet\"></link>\n" +
			"</head>\n" +
			"<body>";
	static String se = "\n" +
			"</body>\n" +
			"</html>";

}


