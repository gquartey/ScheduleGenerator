import java.awt.*;
import java.awt.print.Printable;
import java.io.*;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccess;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFManager {
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc ;
    private COSDocument cosDoc ;

    public PDFManager(String fileName) throws IOException {

        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;
        File file = new File(fileName);
        try {

            this.pdDoc = PDDocument.load(new File(fileName));
            this.pdfStripper = new PDFTextStripper();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printPages(int start, int finish) throws IOException {
        this.pdfStripper.setStartPage(start);
        this.pdfStripper.setEndPage(finish);

        String parsedText = pdfStripper.getText(this.pdDoc);
        System.out.println(parsedText);
    }
    public String returnString(int start,int finish) throws IOException {
        this.pdfStripper.setStartPage(start);
        this.pdfStripper.setEndPage(finish);

        String parsedText = pdfStripper.getText(this.pdDoc);
        return parsedText;
    }


}
