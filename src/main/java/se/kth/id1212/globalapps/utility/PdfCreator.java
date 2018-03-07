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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
    //private String FILE = "recruiter/";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * Creates a PDF of an <code>ApplicationDTO</code> and saves it at a chosen location.
     * @param application The <code>ApplicationDTO</code> to be saved as a PDF.
     */
    public void createPDF(ApplicationDTO application) {
        try {
            String FILE = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            FILE += "recruiter/";
            Document document = new Document();
            FILE += application.getUsername() + "_application.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

//          adds the title page
            Paragraph preface = new Paragraph();
            String titleName = "Job Application, " + 
                    application.getUsername() + ", " + 
                    application.getFirstName() + " " + 
                    application.getLastName();
            preface.add(new Paragraph(titleName));
            preface.add(new Paragraph(
                    "Application PDF generated : " + new Date()));
            document.add(preface);

    //      adds the content
            Anchor anchor = new Anchor("Qualifications");
            anchor.setName("Qualifications");
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);
            Paragraph subPara = new Paragraph("Years with expertises");
            Section subCatPart = catPart.addSection(subPara);
            for(YearsWithExpertiseDTO ywe : application.getExpertises()) {
                subCatPart.add(new Paragraph("\tExpertise: " + ywe.getExpertise() + " \n" +
                        "\tYears of experience: " + ywe.getYears() + "\n"));
            }
            subPara = new Paragraph("Periods of availability");
            subCatPart = catPart.addSection(subPara);
            for(TimePeriodDTO timePeriod : application.getAvailabilityPeriods()) {
                subCatPart.add(new Paragraph("\tStart Date: " + timePeriod.getStartdate() + "\n" +
                        "\tEnd Date: " + timePeriod.getEnddate() + "\n"));
            }
            document.add(catPart);
            document.close();
        } catch (DocumentException | FileNotFoundException documentException) {
            LOGGER.log(Level.SEVERE, documentException.toString());
        }
    }
}

