package edu.swarthmore.cs71.starfruit.scraper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class ExcelParser {
    private FileInputStream excelFile;
    private Sheet dataTypeSheet;
    private Workbook workbook;
    public ExcelParser(File file) throws IOException {
        this.excelFile = new FileInputStream(file);
        this.workbook = new XSSFWorkbook(this.excelFile);
        this.dataTypeSheet = workbook.getSheetAt(0);
    }
    private ArrayList<BuilderToken> readFile(){
        ArrayList<BuilderToken> courseBuilderTokens= new ArrayList<>();
        Iterator<Row> iterator = this.dataTypeSheet.iterator();
        // this skips the first row that is simply there for labeling
        iterator.next();
        BuilderToken department;
        BuilderToken courseNum;
        BuilderToken courseName;
        BuilderToken courseTime;
        BuilderToken courseDays;
        BuilderToken credits;
        Boolean CONTINUITY;
        while(iterator.hasNext()){

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            int acc = 0;
            CONTINUITY = true;
            while(cellIterator.hasNext() && CONTINUITY){
                Cell currentCell = cellIterator.next();
                if(currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if(acc == 2){
                        department = new BuilderToken.Dept(currentCell.getStringCellValue());
                        courseBuilderTokens.add(department);
                    }
                    if(acc == 3){
                        courseNum = new BuilderToken.CourseNumber(currentCell.getStringCellValue().substring(0,3));
                        courseBuilderTokens.add(courseNum);
                    }
                    if(acc == 5){
                        courseName = new BuilderToken.ClassNameComponent(currentCell.getStringCellValue());
                        courseBuilderTokens.add(courseName);
                    }
                    if(acc == 14){
                        courseTime = new BuilderToken.Time(currentCell.getStringCellValue());
                        courseBuilderTokens.add(courseTime);
                    }
                    if(acc == 15){
                        courseDays = new BuilderToken.CourseDays(currentCell.getStringCellValue());
                        courseBuilderTokens.add(courseDays);
                        CONTINUITY = false;
                    }

                }else if(currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    if(acc == 6){
                        Integer value = new Double(currentCell.getNumericCellValue()).intValue();
                        credits = new BuilderToken.Credits(value.toString());
                        courseBuilderTokens.add(credits);
                    }
                }else{
                    if (acc == 14) {
                        courseTime = new BuilderToken.Time(null);
                        courseBuilderTokens.add(courseTime);
                    }
                    if (acc == 15) {
                        courseDays = new BuilderToken.CourseDays(null);
                        courseBuilderTokens.add(courseDays);
                        CONTINUITY = false;
                    }
                }
                acc ++;
            }
            // we can make there course here because the above while loop with collect all the information neccesary to produce a course
        }
        return courseBuilderTokens;
    }
    public Collection<Course> ProduceCourses(){

        Collection<Course> allCourses= new ArrayList<>();
        ArrayList<BuilderToken> builderTokens = readFile();
        CourseToken currentToken = new CourseToken();
        for(BuilderToken i: builderTokens){
            if(currentToken.getComplete() == false){
                currentToken.addToken(i);
            }else{
                if(currentToken.getDept() == null){
                    //System.out.println("something is happeing");
                }
                else if(!currentToken.getDept().equals("OCST") && !currentToken.getDept().equals("DOMX") && !currentToken.getCourseNum().equals("SGM")) {
                    Course course = currentToken.convertToCourse();
                    allCourses.add(course);
                }
                currentToken = new CourseToken();
                currentToken.addToken(i);
            }
        }
        return allCourses;
    }
}
