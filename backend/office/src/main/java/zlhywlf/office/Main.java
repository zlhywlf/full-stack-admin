package zlhywlf.office;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import zlhywlf.office.model.Product;
import zlhywlf.office.reader.ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;

@Log4j2
public class Main {
    public static void main(String[] args) {
        String parentPath = System.getProperty("user.dir") + "/backend/office/target/";
        try (FileInputStream in = new FileInputStream(parentPath + "2021.xls")) {
            Workbook wb = WorkbookFactory.create(in);
            int numberOfSheets = wb.getNumberOfSheets();
            for (int i = 0; i < 12; i++) {
                Sheet ws = wb.getSheetAt(i);
                int s = 16;
                for (int j = 0; j < 6; s += 3, j++) {
                    log.info(ExcelReader.handleProduct(ws, s));
                }
//                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}