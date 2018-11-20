package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 实现EXCEL数据转化成json数据
 */
public class ExcelTool {

    /**
     *  获取xlsx文件
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static JSONArray readExcel(File file) throws IOException, InvalidFormatException {
        if(file==null){
            throw new NullPointerException("the file is null.");
        }else{
            return readXLSX(file);
        }
    }


    /**
     * 读取xlsx文件
     * @param file
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static JSONArray readXLSX(File file) throws InvalidFormatException, IOException {
        Workbook book = new XSSFWorkbook(file);
        Sheet sheet = book.getSheetAt(0);
        return read(sheet, book);
    }



    /**
     * 解析数据
     * @param sheet 表格sheet对象
     * @param book 用于流关闭
     * @return
     * @throws IOException
     */
    public static JSONArray read(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        //如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new JSONArray();
        }
        //获取第一行JSON对象键
        Row firstRow = sheet.getRow(rowStart);
        int cellStart = firstRow.getFirstCellNum();
        int cellEnd = firstRow.getLastCellNum();
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        for (int j = cellStart; j < cellEnd; j++) {
            keyMap.put(j, getValue(firstRow.getCell(j), rowStart, j, book, true));
        }
       // 获取每行JSON对象的值
        JSONArray array = new JSONArray();
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            Row eachRow = sheet.getRow(i);
            JSONObject obj = new JSONObject();
            StringBuffer sb = new StringBuffer();
            for (int k = cellStart; k < cellEnd; k++) {
                if (eachRow != null) {
                    String val = getValue(eachRow.getCell(k), i, k, book, false);
                    sb.append(val);        // 所有数据添加到里面，用于判断该行是否为空
                    obj.put(keyMap.get(k), val);
                }
            }
            if (sb.toString().length() > 0) {
                array.add(obj);
            }
        }
        book.close();
        return array;
    }

    /**
     * 获取每个单元格的数据
     * @param cell
     * @param rowNum
     * @param index
     * @param book
     * @param isKey
     * @return
     * @throws IOException
     */
    public static String getValue(Cell cell, int rowNum, int index, Workbook book, boolean isKey) throws IOException {
        //空白或空
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            if (isKey) {
                book.close();
                throw new NullPointerException(String.format("the key on row %s index %s is null ", ++rowNum, ++index));
            } else {
                return "";
            }
        }
        // 0. 数字 类型
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(date);
            }
            String val = (int)cell.getNumericCellValue() + "";

            val = val.toUpperCase();
            if (val.contains("E")) {
                val = val.split("E")[0].replace(".", "");
            }
            return val;
        }
        //1. String类型
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().length() == 0) {
                if (book != null) {
                    book.close();
                }
                return "";
            }
            return val.trim();
        }
        // 2. 公式 CELL_TYPE_FORMULA
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getStringCellValue();
        }
        //4. 布尔值 CELL_TYPE_BOOLEAN
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        }
        // 5.	错误 CELL_TYPE_ERROR
        return "";
    }
}
