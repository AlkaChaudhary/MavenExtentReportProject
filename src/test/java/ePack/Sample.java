package ePack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Sample {

	public static void main(String[] args) throws IOException {
		ArrayList<String> alist = getDataFromExcel("RegisterTest");
		
		for(String a:alist) {
			System.out.println(a);
		}
	}
	
	public static ArrayList<String> getDataFromExcel(String testName) throws IOException {
		ArrayList<String> list= new ArrayList<>();
		FileInputStream fis = new FileInputStream("sample.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		int sheetCount= workbook.getNumberOfSheets();
		System.out.println("No of shrrt count in a workbook:"+sheetCount);
		for (int i=0; i<sheetCount;i++) {
			if (workbook.getSheetName(i).equals("Data")) {
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row> rows= sheet.rowIterator();
				Row firstRow=rows.next();
				
				Iterator<Cell> firstRowCells=firstRow.iterator();
				int c=0;
				int TestColpos=c;
				while(firstRowCells.hasNext()) {
					//System.out.println(firstRowCells.next().getStringCellValue());
				Cell firstRowCell= firstRowCells.next();	
				if(firstRowCell.getStringCellValue().equals("Test")) {
					TestColpos=c;
					
				}
				c++;
				}
				//System.out.println(TestColpos);
				while(rows.hasNext()) {
					Row row=rows.next();
					Cell cell=row.getCell(TestColpos);
					if(cell.getStringCellValue().equals("RegisterTest")) {
						Iterator<Cell> cells = row.iterator();
						cells.next();
						while(cells.hasNext()) {
							Cell currentCell = cells.next();
							if(currentCell.getCellType()==CellType.STRING) {
								list.add(currentCell.getStringCellValue());
							}
							else if(currentCell.getCellType()==CellType.NUMERIC) {
								list.add(NumberToTextConverter.toText(currentCell.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return list;
	}

}