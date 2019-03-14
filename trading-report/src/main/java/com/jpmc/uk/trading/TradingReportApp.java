package com.jpmc.uk.trading;

import java.io.IOException;

import com.jpmc.uk.trading.errorhandling.ExceptionHandler;
import com.jpmc.uk.trading.parser.TradingDataCSVParser;
import com.jpmc.uk.trading.service.TradingReportService;


public class TradingReportApp {
	public static void main(String args[]) throws ExceptionHandler {

		TradingReportService tradingReportService = new TradingReportService();
		TradingDataCSVParser tradingDataCSVParser = new TradingDataCSVParser();

		try {
			tradingReportService
					.generateFXTransactionReport(tradingDataCSVParser
							.processInputCSVFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
