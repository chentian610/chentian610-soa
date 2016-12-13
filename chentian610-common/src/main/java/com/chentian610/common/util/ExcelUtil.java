package com.chentian610.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/** 
 * Excel处理通用类，将Excel导入成待处理的对象
 * 导入的jar包 
 */  
  
public class ExcelUtil  
{  
  
  
    /** 
     *  
     * @描述：是否是2003的excel，返回true是2003 
     * @参数：@param filePath　文件完整路径 
     * @返回值：boolean 
     */  
    public static boolean isExcel2003(String filePath)
    {  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
    /** 
     *  
     * @描述：是否是2007的excel，返回true是2007 
     * @参数：@param filePath　文件完整路径 
     * @返回值：boolean 
     */  
    public static boolean isExcel2007(String filePath)
    {  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
    } 

    /** 
     *  
     * @描述：验证excel文件 
     * @时间：2012-08-29 下午16:27:15 
     * @参数：@param filePath　文件完整路径 
     * @参数：@return 
     * @返回值：boolean 
     */  
    public static boolean validateExcel(String filePath)
    {  
        /** 检查文件名是否为空或者是否是Excel格式的文件 */  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath)))  
            throw new RuntimeException("您导入的不是Excel文件格式，请确认！");
        /** 检查文件是否存在 */  
        File file = new File(filePath);
        if (file == null || !file.exists())  
        	throw new RuntimeException("文件读取失败或者不存在，请确认！");
        return true;  
    }  
  
    /** 
     *  
     * @描述：根据文件名读取excel文件 
     * @参数：@param filePath 文件完整路径 
     * @参数：@return 
     * @返回值：List 
     */  
    public static <T> List<T> getData(String filePath, HashMap<String,String> fieldMap, Class<T> clazz)
    {  
    	List<T> list = new ArrayList<T>();
        InputStream is = null;
        try {  
            validateExcel(filePath);
            File file = new File(filePath);
            is = new FileInputStream(file);
            Workbook wb = null;  
            if (isExcel2007(filePath)) 
            	wb = new XSSFWorkbook(is);  
            else wb = new HSSFWorkbook(is);  
            list = read(wb,fieldMap,clazz);  
        }
        catch (Exception ex)
        {  
            ex.printStackTrace();  
        }  
        finally  
        {  
            if (is != null)  
            {  
                try  
                {  
                    is.close();  
                }  
                catch (IOException e)
                {  
                    is = null;  
                    e.printStackTrace();  
                }  
            }  
        }  
        /** 返回最后读取的结果 */  
        return list;  
    }  
  
  
    /** 
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @描述：读取数据 
     * @返回值：List<List<String>> 
     */  
    private static <T> List<T> read(Workbook wb, HashMap<String,String> fieldMap, Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
    {  
        List<T> list = new ArrayList<T>();
        /** 得到第一个shell */  
        Sheet sheet = wb.getSheetAt(0);  
        /** 得到Excel的行数 */  
        int row_num = sheet.getPhysicalNumberOfRows();  
        int col_num=0;
        /** 得到Excel的列数 */  
        if (row_num >= 1 && sheet.getRow(0) != null)  
           col_num = sheet.getRow(0).getPhysicalNumberOfCells();  
        
        for (int c = 0; c < col_num; c++) {
            if (StringUtil.isEmpty(getCellValue(sheet.getRow(0), c))) continue;
            fieldMap.put(c + "", fieldMap.get(getCellValue(sheet.getRow(0), c)));
        }
        /** 循环Excel的行 */  
        for (int r = 1; r < row_num; r++)  
        {  
            Row row = sheet.getRow(r); 
            if (row == null)  continue;  
            /** 循环Excel的列 */  
            T bean = clazz.newInstance();
            for (int c = 0; c < col_num; c++)  
            {
        		String fieldName = fieldMap.get(c+"");
                if (StringUtil.isEmpty(fieldName)) continue;
        		Field field = clazz.getDeclaredField(fieldName);
        		Method fieldSetMet = clazz.getDeclaredMethod(BeanUtil.parGetName(fieldName,"set"),field.getType());
        		String value =getValueOfCell(row, c)+"";
                if (null != value && !"".equals(value)) {  
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {  
                        fieldSetMet.invoke(bean, value);  
                    } else if ("Date".equals(fieldType)) {  
                        Date temp = DateUtil.dateTimeFormat(value);
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Integer".equals(fieldType)  
                            || "int".equals(fieldType)) {  
                        Integer intval = (int) Double.parseDouble(value);
                        fieldSetMet.invoke(bean, intval);  
                    } else if ("Long".equalsIgnoreCase(fieldType)) {  
                        Long temp = Long.parseLong(value);
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Double".equalsIgnoreCase(fieldType)) {  
                        Double temp = Double.parseDouble(value);
                        fieldSetMet.invoke(bean, temp);  
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {  
                        Boolean temp = Boolean.parseBoolean(value);
                        fieldSetMet.invoke(bean, temp);  
                    } else {  
                        System.out.println("没有可以转换的类型" + fieldType);
                    }  
                } 
            }  
            list.add(bean);
        }  
        return list;  
    }

	private static String getCellValue(Row row, int c) {
		Cell cell = row.getCell(c);  
		String cellValue = "";
		if (null != cell)  
		{  
		    // 以下是判断数据的类型  
		    switch (cell.getCellType())  
		    {  
		    case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
		        cellValue = cell.getNumericCellValue() + "";  
		        break;  
		    case HSSFCell.CELL_TYPE_STRING: // 字符串  
		        cellValue = cell.getStringCellValue();  
		        break;  
		    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
		        cellValue = cell.getBooleanCellValue() + "";  
		        break;  
		    case HSSFCell.CELL_TYPE_FORMULA: // 公式  
		        cellValue = cell.getCellFormula() + "";  
		        break;  
		    case HSSFCell.CELL_TYPE_BLANK: // 空值  
		        cellValue = "";  
		        break;  
		    case HSSFCell.CELL_TYPE_ERROR: // 故障  
		        cellValue = "非法字符";  
		        break;  
		    default:  
		        cellValue = "未知类型";  
		        break;  
		    }  
		}
		return cellValue;
	}  
	
	private static String getValueOfCell(Row row, int c) {
		Cell cell = row.getCell(c);  
		String cellValue = "";
		if (null != cell)  
		{  
		    // 以下是判断数据的类型  
		    switch (cell.getCellType())  
		    {  
		    case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
		        cellValue = cell.getNumericCellValue() + "";  
		        cellValue=cellValue.substring(0, cellValue.indexOf("."));
		        break;  
		    case HSSFCell.CELL_TYPE_STRING: // 字符串  
		        cellValue = cell.getStringCellValue();  
		        break;  
		    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
		        cellValue = cell.getBooleanCellValue() + "";  
		        break;  
		    case HSSFCell.CELL_TYPE_FORMULA: // 公式  
		        cellValue = cell.getCellFormula() + "";  
		        break;  
		    case HSSFCell.CELL_TYPE_BLANK: // 空值  
		        cellValue = "";  
		        break;  
		    case HSSFCell.CELL_TYPE_ERROR: // 故障  
		        cellValue = "非法字符";  
		        break;  
		    default:  
		        cellValue = "未知类型";  
		        break;  
		    }  
		}
		return cellValue;
	}  
  
}  