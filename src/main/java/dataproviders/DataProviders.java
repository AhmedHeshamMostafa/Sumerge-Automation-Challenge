package dataproviders;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataProviders {
    @DataProvider(name = "SuiteData")
    public Object[][] getSuiteData() throws IOException {
        String path = "C:\\Users\\Ahmed Hesham Hany\\IdeaProjects\\SumergeAutomationChallenge\\src\\test\\resources\\testdata\\SuiteData.xlsx";
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Data1");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][1];

        // read headers
        String[] headers = new String[colCount];
        for (int j = 0; j < colCount; j++) {
            headers[j] = sheet.getRow(0).getCell(j).toString();
        }

        for (int i = 1; i < rowCount; i++) {
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < colCount; j++) {
                map.put(headers[j], sheet.getRow(i).getCell(j).toString());
            }
            data[i - 1][0] = map;
        }

        workbook.close();
        fis.close();
        return data;
    }
}
