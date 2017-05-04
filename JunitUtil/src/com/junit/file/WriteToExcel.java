package com.junit.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import com.junit.pojo.ReportAttributes;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteToExcel {

	private WritableCellFormat times;
	
	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}
	
	public void addDataForConnections(WritableSheet sheet,
			ArrayList<ReportAttributes> queueDetailsTOs) throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);
		times = new WritableCellFormat(times10pt);

		CellView cv = new CellView();
		cv.setFormat(times);

		cv.setAutosize(true);

		if (!queueDetailsTOs.isEmpty()) {
			int j = 1;
			for (int i = 0; i < queueDetailsTOs.size(); i++) {
				addNumber(sheet, 0, j, Integer.parseInt(String.valueOf(j)));
				addCaption(sheet, 1, j, queueDetailsTOs.get(i).getFileName());
				addCaption(sheet, 2, j, queueDetailsTOs.get(i).getActualOutput());
				addCaption(sheet, 3, j, queueDetailsTOs.get(i).getStatus());
				addCaption(sheet, 4, j, queueDetailsTOs.get(i).getException());
				j++;
			}
		}
	}
	
	public void createExcel(String reportPath, ArrayList<ReportAttributes> lstContent){
		try {
			String workDir =System.getProperty("user.dir");
			//fileOperator.generatetestCSV(csvPath, reportContent);
			WorkbookSettings wbSettings = new WorkbookSettings();
			wbSettings.setLocale(new Locale("en", "EN"));
			WritableWorkbook workbook = null;
        	Workbook templateWorkbook = Workbook.getWorkbook(new File(
        			workDir + "\\TestFiles\\config\\JunitReport.xls"));
        	workbook = Workbook.createWorkbook(new File(reportPath),
				templateWorkbook);
        	WritableSheet flowSheet = workbook.getSheet("Junit_Report");
        	System.out.println("Creating excel....");
        	addDataForConnections(flowSheet, lstContent);
		
        	workbook.write();
        	workbook.close();
        	System.out.println("Complete!");
		} catch (BiffException be) {
			be.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}
}
