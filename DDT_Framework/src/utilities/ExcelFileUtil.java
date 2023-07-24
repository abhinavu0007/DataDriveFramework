package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {

	XSSFWorkbook wb;

	public ExcelFileUtil(String excelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelPath);
		wb = new XSSFWorkbook(fi);

	}
	//create number of rows

	public  int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}

	// create number of column from rows

	public  int cellCount(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}

	// read cell data from sheet

	public  String getCellData(String sheetName , int row , int column ) 
	{
		String data = "";

		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int cellData = (int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(cellData);
		}
		else
		{
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}

	// set cell data when testcase pass fails based on that

	public void setCellData(String sheetName , int row , int column , String status , String writeExcel) throws Throwable
	{
		// get sheet fromn workbook

		XSSFSheet ws = wb.getSheet(sheetName);
		// get row from sheet
		XSSFRow rowNum = ws.getRow(row);

		// create cell
		XSSFCell cell = rowNum.createCell(column);
		//set cellStatus
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);

		}

		else if(status.equalsIgnoreCase("Fail"))
				{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
				}

		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}

		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);


	}


}
