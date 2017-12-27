package edu.swarthmore.cs71.starfruit.scraper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfManager {
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc ;
    private int pageCount;

    public PdfManager(File file) throws IOException {

        this.pdfStripper = null;
        this.pdDoc = null;
        try {

            this.pdDoc = PDDocument.load(file);
            this.pdfStripper = new PDFTextStripper();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pageCount = this.pdDoc.getNumberOfPages();
    }

        public void printPages() throws IOException {
            this.pdfStripper.setStartPage(0);
            this.pdfStripper.setEndPage(this.pageCount);
            String parsedText = pdfStripper.getText(this.pdDoc);
            System.out.println(parsedText);
        }

        public String pdfString() throws IOException {
            this.pdfStripper.setStartPage(0);
            this.pdfStripper.setEndPage(this.pageCount);
            return pdfStripper.getText(this.pdDoc);
        }



}
