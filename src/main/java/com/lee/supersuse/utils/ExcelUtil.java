package com.lee.supersuse.utils;

import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.ResultException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * 解析Excel文件使用的工具类
 */
public class ExcelUtil {
    /**
     *
     * @Title: readXls
     * @Description: 处理xls文件
     * @param @param path
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<List<String>>    返回类型
     * @throws
     *
     * 1.先用InputStream获取excel文件的io流
     * 2.然后穿件一个内存中的excel文件HSSFWorkbook类型对象，这个对象表示了整个excel文件。
     * 3.对这个excel文件的每页做循环处理
     * 4.对每页中每行做循环处理
     * 5.对每行中的每个单元格做处理，获取这个单元格的值
     * 6.把这行的结果添加到一个List数组中
     * 7.把每行的结果添加到最后的总结果中
     * 8.解析完以后就获取了一个List<List<String>>类型的对象了
     *
     */
    private static List<List<String>> readXls(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        // HSSFWorkbook 标识整个excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        // 循环每一页，并处理当前循环页
        for (int numSheet = 0; numSheet < size; numSheet++) {
            // HSSFSheet 标识某一页
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 处理当前页，循环读取每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                // HSSFRow表示行
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                // 遍历改行，获取处理每个cell元素
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    // HSSFCell 表示单元格
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal(cell));
                }
                result.add(rowList);
            }
        }
        return result;
    }


    public static List<List<String>> readXlsx(MultipartFile file){
        InputStream is;
        XSSFWorkbook hssfWorkbook = null;
        try {
            is = file.getInputStream();
            hssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // HSSFWorkbook 标识整个excel
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        // 循环每一页，并处理当前循环页
        for (int numSheet = 0; numSheet < size; numSheet++) {
            // HSSFSheet 标识某一页
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 处理当前页，循环读取每一行
            try {
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    // HSSFRow表示行
                    XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    int minColIx = hssfRow.getFirstCellNum();
                    int maxColIx = hssfRow.getLastCellNum();
                    List<String> rowList = new ArrayList<String>();
                    // 遍历改行，获取处理每个cell元素
                    for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                        // HSSFCell 表示单元格
                        XSSFCell cell = hssfRow.getCell(colIx);
                        if (cell == null) {
                            continue;
                        }
                        rowList.add(getStringVal(cell));
                    }
                    result.add(rowList);
                }
            }catch (Exception e){
                throw new JsonException(ErrorEnum.FILE_ERROR);
            }
        }
        return result;
    }

    public static String getStringVal(Cell cell) {
        switch(cell.getCellType()) {
            case NUMERIC:
                cell.setCellType(STRING);
                return cell.getStringCellValue();
            case STRING:
                return cell.getRichStringCellValue().toString();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case ERROR:
                return ErrorEval.getText(cell.getErrorCellValue());
            default:
                return "Unknown Cell Type: " + cell.getCellType();
        }
    }
}
