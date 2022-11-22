package com.example.restservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServiceController {

	List<MonthlyFruitsPrices> data;

	public RestServiceController() {
		data = getData();
	}

	@GetMapping("/fruit-month-price/fruit/{fruit}/month/{month}")
	public FruitMonthPriceResponse fruitMonthPrice(@PathVariable(name = "fruit") String fruit,
			@PathVariable(name = "month") String name) {

		return new FruitMonthPriceResponse();
	}

	@GetMapping("/fruit-total-price/fruit/{fruit}/month/{month}/quantity/{quantity}")
	public FruitTotalPriceResponse fruitTotalPrice(@PathVariable(name = "fruit") String fruit,
			@PathVariable(name = "month") String name, @PathVariable(name = "quantity") Integer quantity) {

		return new FruitTotalPriceResponse();
	}

	private getData() {
		try {
			File file = ResourceUtils.getFile("classpath:FMP.xlsx");
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				MonthlyFruitsPrices fp = new MonthlyFruitsPrices();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
						case 0:
							fp.setFruit((String) getCellValue(nextCell));
							break;
						case 1:
							fp.setJan((Float) getCellValue(nextCell));
							break;
						case 2:
							fp.setFeb((Float) getCellValue(nextCell));
							break;
						case 3:
							fp.setMar((Float) getCellValue(nextCell));
							break;
						case 4:
							fp.setApr((Float) getCellValue(nextCell));
							break;
						case 5:
							fp.setMay((Float) getCellValue(nextCell));
							break;
						case 6:
							fp.setJun((Float) getCellValue(nextCell));
							break;
						case 7:
							fp.setJul((Float) getCellValue(nextCell));
							break;
						case 8:
							fp.setAug((Float) getCellValue(nextCell));
							break;
						case 9:
							fp.setSep((Float) getCellValue(nextCell));
							break;
						case 10:
							fp.setOct((Float) getCellValue(nextCell));
							break;
						case 11:
							fp.setNov((Float) getCellValue(nextCell));
							break;
						case 12:
							fp.setDec((Float) getCellValue(nextCell));
							break;
					}
				}
				data.add(fp);
			}

			wb.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				return cell.getNumericCellValue();
		}

		return null;
	}
}
