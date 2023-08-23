package zlhywlf.office.reader;

import org.apache.poi.ss.usermodel.Sheet;
import zlhywlf.office.model.Product;

public class ExcelReader {
    public static Product handleProduct(Sheet ws, int row) {
        Product product = new Product();
        product.setProcedure(ws.getRow(15).getCell(1).getStringCellValue().replace("(90m2)", "").trim());
        product.setUnit(ws.getRow(14).getCell(2).getStringCellValue().replace("（", "").replace("）", ""));
        product.setValue(ws.getRow(row).getCell(2).getNumericCellValue());
        product.setNameZh(ws.getRow(row + 1).getCell(2).getStringCellValue());
        product.setType("product");
        product.setDate(ws.getRow(55).getCell(0).getDateCellValue());
        return product;
    }
}
