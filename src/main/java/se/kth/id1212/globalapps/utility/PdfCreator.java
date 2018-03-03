/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.globalapps.utility;


import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import javax.swing.JFileChooser;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Johan
 * Creates a PDF of an application.
 */
public class PdfCreator {
    private String FILE;
    private final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    /**
     * Creates a PDF of an <code>ApplicationDTO</code> and saves it at a chosen location.
     * @param application The <code>ApplicationDTO</code> to be saved as a PDF.
     */
    public void createPDF(ApplicationDTO application) {
        try {
            Document document = new Document();
            FILE = chooseFileDirectory() + "\\" + application.getUsername() + "_application.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addTitlePage(document, application);
            addContent(document, application);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            //Do something, maybe throw a CodedException.
        }
    }
    
    /**
     * Shows a window where the user can choose a directory to store the file in.
     * @return The directory path where the file will be stored.
     */
    private String chooseFileDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser.getSelectedFile().toString();
    }

    /**
     * Adds a title page to the PDF.
     * @param document The PDF to be saved.
     * @param application The <code>ApplicationDTO</code> that the PDF will be based on.
     * @throws DocumentException If the title couldn't be added to the PDF.
     */
    private void addTitlePage(Document document, ApplicationDTO application) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        String titleName = "Job Application, " + 
                application.getUsername() + ", " + 
                application.getFirstName() + " " + 
                application.getLastName();
        preface.add(new Paragraph(titleName, catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Application PDF generated : " + new Date(),
                smallBold));
        document.add(preface);
    }

    /**
     * Adds content such as expertises and periods of availability from the <code>ApplicationDTO</code> to the PDF.
     * @param document The PDF to be saved.
     * @param application The <code>ApplicationDTO</code> that the PDF will be based on.
     * @throws DocumentException If the content couldn't be added to the PDF.
     */
    private void addContent(Document document, ApplicationDTO application) throws DocumentException {
        Anchor anchor = new Anchor("Qualifications", catFont);
        anchor.setName("Qualifications");
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph("Expertises", subFont);
        Section subCatPart = catPart.addSection(subPara);
        for(YearsWithExpertiseDTO ywe : application.getExpertises()) {
            subCatPart.add(new Paragraph("\tExpertise: " + ywe.getExpertise() + " \n" +
                    "\tYears of experience: " + ywe.getYears() + "\n"));
        }
        subPara = new Paragraph("Periods of availability", subFont);
        subCatPart = catPart.addSection(subPara);
        for(TimePeriodDTO timePeriod : application.getAvailabilityPeriods()) {
            subCatPart.add(new Paragraph("\tStart Date: " + timePeriod.getStartdate() + "\n" +
                    "\tEnd Date: " + timePeriod.getEnddate() + "\n"));
        }
        document.add(catPart);
    }

    /**
     * Adds an amount of empty lines to a specific paragraph.
     * @param paragraph The <code>Paragrapgh</code> where empty lines should be added.
     * @param number The amount of empty lines that should be added.
     */
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

