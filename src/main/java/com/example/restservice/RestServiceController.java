package com.example.restservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

	List<MonthlyFruitsPrices> data = new ArrayList<MonthlyFruitsPrices>();
    DecimalFormat df = new DecimalFormat("0.00");

	public RestServiceController() {
		getData();
	}

	@GetMapping("/fruit-month-price/fruit/{fruit}/month/{month}")
	public FruitMonthPriceResponse fruitMonthPrice(@PathVariable(name = "fruit") String fruit,
			@PathVariable(name = "month") String month) {
		FruitMonthPriceResponse response = new FruitMonthPriceResponse();
		if (data != null && data.size() > 0) {
			Optional<MonthlyFruitsPrices> mfp = data.stream().filter(d -> d.getFruit().equalsIgnoreCase(fruit))
					.findFirst();
			month = month.toLowerCase();
			if (mfp.isPresent()) {
				response = getResponse(fruit, month, mfp);
			}

		}
		return response;
	}

	@GetMapping("/fruit-total-price/fruit/{fruit}/month/{month}/quantity/{quantity}")
	public FruitTotalPriceResponse fruitTotalPrice(@PathVariable(name = "fruit") String fruit,
			@PathVariable(name = "month") String month, @PathVariable(name = "quantity") Integer quantity) {
		if (quantity == null) {
			quantity = 0;
		}
		FruitTotalPriceResponse response = new FruitTotalPriceResponse();
		FruitMonthPriceResponse fmpr = this.fruitMonthPrice(fruit, month);
		if (fmpr.getFruit() != null) {
			response.setFruit(fmpr.getFruit());
			response.setFmp(fmpr.getFmp());
			response.setEnvironment(fmpr.getEnvironment());
			response.setId(fmpr.getId());
			response.setMonth(fmpr.getMonth());
			response.setQuantity(quantity);
			response.setTotalPrice(quantity * Double.valueOf(fmpr.getFmp()));
		}
		return response;

	}

	private FruitMonthPriceResponse getResponse(String fruit, String month, Optional<MonthlyFruitsPrices> mfp) {
		FruitMonthPriceResponse response = new FruitMonthPriceResponse();
		switch (month) {
			case "jan":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getJan()));
				response.setMonth("jan");
				response.setEnvironment("8000");
				break;
			case "feb":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getFeb()));
				response.setMonth("feb");
				response.setEnvironment("8000");
				break;
			case "mar":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getMar()));
				response.setMonth("mar");
				response.setEnvironment("8000");
				break;
			case "apr":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getApr()));
				response.setMonth("apr");
				response.setEnvironment("8000");
				break;
			case "may":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getMay()));
				response.setMonth("may");
				response.setEnvironment("8000");
				break;
			case "jun":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getJun()));
				response.setMonth("jun");
				response.setEnvironment("8000");
				break;
			case "jul":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getJul()));
				response.setMonth("jul");
				response.setEnvironment("8000");
				break;
			case "aug":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getAug()));
				response.setMonth("aug");
				response.setEnvironment("8000");
				break;
			case "sep":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getSep()));
				response.setMonth("sep");
				response.setEnvironment("8000");
				break;
			case "oct":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getOct()));
				response.setMonth("oct");
				response.setEnvironment("8000");
				break;
			case "nov":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getNov()));
				response.setMonth("nov");
				response.setEnvironment("8000");
				break;
			case "dec":
				response.setId(getId());
				response.setFruit(fruit.toLowerCase());
				response.setFmp(df.format(mfp.get().getDec()));
				response.setMonth("dec");
				response.setEnvironment("8000");
				break;
			default:
				break;
		}

		return response;

	}

	private void getData() {
		try {
			File file = ResourceUtils.getFile("classpath:FMP.xlsx");
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
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
							fp.setJan((Double) getCellValue(nextCell));
							break;
						case 2:
							fp.setFeb((Double) getCellValue(nextCell));
							break;
						case 3:
							fp.setMar((Double) getCellValue(nextCell));
							break;
						case 4:
							fp.setApr((Double) getCellValue(nextCell));
							break;
						case 5:
							fp.setMay((Double) getCellValue(nextCell));
							break;
						case 6:
							fp.setJun((Double) getCellValue(nextCell));
							break;
						case 7:
							fp.setJul((Double) getCellValue(nextCell));
							break;
						case 8:
							fp.setAug((Double) getCellValue(nextCell));
							break;
						case 9:
							fp.setSep((Double) getCellValue(nextCell));
							break;
						case 10:
							fp.setOct((Double) getCellValue(nextCell));
							break;
						case 11:
							fp.setNov((Double) getCellValue(nextCell));
							break;
						case 12:
							fp.setDec((Double) getCellValue(nextCell));
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

	private long getId() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
}
