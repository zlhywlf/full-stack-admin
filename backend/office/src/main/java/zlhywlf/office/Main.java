package zlhywlf.office;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zlhywlf.office.model.Product;
import zlhywlf.office.reader.ExcelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

@Log4j2
public class Main {
    public static void main(String[] args) {
        String parentPath = System.getProperty("user.home") + "/Downloads/";
        try (FileInputStream in = new FileInputStream(parentPath + "2021.xls"); FileInputStream in2 = new FileInputStream(parentPath + "2022.xls")) {
            ArrayList<Product> products = new ArrayList<>();
            ArrayList<FileInputStream> ins = new ArrayList<>();
            ins.add(in);
            ins.add(in2);
            ins.forEach(e -> {
                Workbook wb;
                try {
                    wb = WorkbookFactory.create(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                for (int i = 0; i < 12; i++) {
                    Sheet ws = wb.getSheetAt(i);
                    String sheetName = ws.getSheetName();

                    // 工序
                    int sr = 15;
                    for (int j = 0; j < (sheetName.startsWith("2022") ? 13 : 12); sr += 3, j++) {
                        String p = ws.getRow(sr).getCell(1).getStringCellValue();
                        Date date = ws.getRow(55).getCell(0).getDateCellValue();
                        products.add(ExcelReader.handleProduct(p, date, ws, sr));
                        int sc = 4;
                        for (int k = 0; k < 17; sc++, k++) {
                            products.add(ExcelReader.handleEnergy(p, date, ws, sr, sc));
                            if (k == 9) {
                                sc += 4;
                            }
                        }
                    }

                    // 采购
                    int[] rows = {2, 6, 8};
                    for (int row : rows) {
                        String name = ws.getRow(row).getCell(1).getStringCellValue();
                        int sc = 4;
                        for (int k = 0; k < 17; sc++, k++) {
                            products.add(ExcelReader.handleEnergy(name, null, ws, row, sc, "能源"));
                            if (k == 9) {
                                sc += 4;
                            }
                        }
                    }
                }
            });
//            log.info(new ObjectMapper().writeValueAsString(products));
            Workbook wb = new XSSFWorkbook();
            Sheet ws = wb.createSheet("data");
            Row row = ws.createRow(0);
            int col = 0;
            row.createCell(col++, CellType.STRING).setCellValue("name");
            row.createCell(col++, CellType.STRING).setCellValue("value");
            row.createCell(col++, CellType.STRING).setCellValue("coefficient");
            row.createCell(col++, CellType.STRING).setCellValue("procedure");
            row.createCell(col++, CellType.STRING).setCellValue("type");
            row.createCell(col, CellType.STRING).setCellValue("date");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                row = ws.createRow(i + 1);
                col = 0;
                row.createCell(col++, CellType.STRING).setCellValue(product.getName());
                row.createCell(col++, CellType.NUMERIC).setCellValue(product.getValue());
                row.createCell(col++, CellType.NUMERIC).setCellValue(product.getCoefficient());
                row.createCell(col++, CellType.STRING).setCellValue(product.getProcedure());
                row.createCell(col++, CellType.STRING).setCellValue(product.getType());
                row.createCell(col, CellType.STRING).setCellValue(product.getDateStr());
            }
            try (OutputStream fileOut = new FileOutputStream(parentPath + "workbook.xlsx")) {
                wb.write(fileOut);
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}