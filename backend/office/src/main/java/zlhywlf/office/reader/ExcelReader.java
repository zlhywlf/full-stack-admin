package zlhywlf.office.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import zlhywlf.office.model.Product;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelReader {
    public static Product handleProduct(String p, Date date, Sheet ws, int row) {
        Product product = new Product();
        product.setProcedure(getChinese(p));
        product.setDate(date);
        product.setDateStr(ws.getSheetName());
        product.setType("产品");
        product.setValue(ws.getRow(row + 1).getCell(2).getNumericCellValue());
        switch (product.getProcedure()) {
            case "烧结机" -> product.setName("烧结矿");
            case "竖炉" -> product.setName("球团矿");
            case "炼铁合计" -> product.setName("合格铁");
            case "炼钢" -> product.setName("钢坯");
            case "轧钢" -> product.setName("窄带钢");
            case "白灰窑" -> product.setName("石灰");
            case "烧结余热发电", "发电" -> product.setName("电");
            case "其它" -> product.setName("其它");
            case "氧气" -> product.setName("氧气");
            case "氮气" -> product.setName("氮气");
            case "新水" -> product.setName("新水");
        }
        return product;
    }

    public static Product handleEnergy(String p, Date date, Sheet ws, int row, int col) {
        return handleEnergy(p, date, ws, row, col, "耗能");
    }

    public static Product handleEnergy(String p, Date date, Sheet ws, int row, int col, String type) {
        Product product = new Product();
        product.setProcedure(getChinese(p));
        product.setDate(date);
        product.setDateStr(ws.getSheetName());
        product.setType(type);
        Cell cell = ws.getRow(row).getCell(col);
        if (cell.getCellType() == CellType.NUMERIC) {
            product.setValue(cell.getNumericCellValue());
        }
        product.setName(getChinese(ws.getRow(0).getCell(col).getStringCellValue()));
        int r = 51;
        if (ws.getSheetName().startsWith("2022")) {
            r = 54;
        }
        product.setCoefficient(ws.getRow(r).getCell(col).getNumericCellValue());
        return product;
    }

    public static String getChinese(String paramValue) {
        String regex = "([一-龥]+)";
        StringBuilder str = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str.append(matcher.group(0));
        }
        return str.toString();
    }

}
