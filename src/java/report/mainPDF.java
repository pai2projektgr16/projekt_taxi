package report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Łukasz
 */

public class mainPDF {
	
	private static String getDystans() {
		return "128";
	}
	
	private static String getZarobek() {
		return "100";
	}
	
	private static String getNrRej() {
		return "TKI 34586";
	}
	
	private static String getCzasStart() {
		return now("HH:MM:ss");
	}
	
	private static String getCzasStop() {
		return now("HH:MM:ss");
	}
	
	private static String getCzasPracy() {
		return now("HH:MM:ss");
		
	}
	
	private static void raportAddDate(Document doc, Font f1, Font f2) throws DocumentException {
		doc.add(new Phrase("Data: ", f2));
		doc.add(new Phrase(now("dd.MM.YYYY HH:MM:ss"), f1));
		doc.add(new Paragraph(""));
	}
	
	private static void raportAddDriver(Document d, String driver, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Kierowca: ", f2));
		d.add(new Phrase(driver, f1));
		d.add(new Paragraph(""));
	}
	
	private static void raportAddTaxi(Document d, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Taks�wka: ", f2));
		d.add(new Phrase(getNrRej(), f1));
		d.add(new Paragraph(""));
	}
	
	private static void raportAddDistanceTraveled(Document d, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Przejechany dystans: ", f2));
		d.add(new Phrase(getDystans() + "km", f1));
		d.add(new Paragraph(""));
	}
	
	private static void raportAddDistanceProfit(Document d, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Zarobek: ", f2));
		d.add(new Phrase(getZarobek() + "z�", f1));
		d.add(new Paragraph(""));
	}
	
	private static void raportAddTimeInterval(Document d, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Przedzia� czasu pracy: ", f2));
		d.add(new Phrase(getCzasStart() + " - " + getCzasStop(), f1));
		d.add(new Paragraph(""));
	}
	
	private static void raportAddWorkingTime(Document d, Font f1, Font f2) throws DocumentException {
		d.add(new Phrase("Czas pracy: ", f2));
		d.add(new Phrase(getCzasPracy(), f1));
		d.add(new Paragraph(""));
	}
	
	private static void createTable(Document d, String driver) throws DocumentException {
		    PdfPTable table = new PdfPTable(3);
		    table.setWidthPercentage(100);
		    //table.setBorderColor(BaseColor.GRAY);
		    // t.setPadding(4);
		    // t.setSpacing(4);
		    // t.setBorderWidth(1);

		    PdfPCell c1 = new PdfPCell(new Phrase("Id kursu"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    c1.setBackgroundColor(BaseColor.GRAY); 
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Kierowca"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    c1.setBackgroundColor(BaseColor.GRAY); 
		    table.addCell(c1);

		    c1 = new PdfPCell(new Phrase("Czas"));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    c1.setBackgroundColor(BaseColor.GRAY); 
		    table.addCell(c1);
		    table.setHeaderRows(1);

		    table.addCell("1");
		    table.addCell(driver);
		    table.addCell(now("HH:MM:ss"));

		    d.add(new Paragraph(" "));
		    d.add(table);

		  }
	
	
	public static void createRaportPDFCourse(String fileName, String driver) throws DocumentException, IOException {
		Document raport=new Document(PageSize.A4, 50, 50, 50, 50); 
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);  
        Font f1 = new Font(bf, 14, Font.NORMAL);
        Font f2 = new Font(bf, 14, Font.BOLD);
        Font fontTitle = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
		PdfWriter.getInstance(raport,new FileOutputStream(fileName)); 	
		raport.addAuthor(driver);
		raport.open(); 
		
		raport.add(new Paragraph("Raport z kursu (ID: ...)", fontTitle));
		
		raportAddDate(raport, f1, f2);
		raportAddDriver(raport, driver, f1, f2);
		raportAddTaxi(raport, f1, f2);
		raportAddDistanceTraveled(raport, f1, f2);
		raportAddDistanceProfit(raport, f1, f2);
		raportAddTimeInterval(raport, f1, f2);
		raportAddWorkingTime(raport, f1, f2);
		
		
	
		//createTable(raport, driver);
		
		raport.close();
	}
	

	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
		}
	
	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Start");
		createRaportPDFCourse("file.pdf", "Janek");
		System.out.println("Stop");
	}
	
	}


