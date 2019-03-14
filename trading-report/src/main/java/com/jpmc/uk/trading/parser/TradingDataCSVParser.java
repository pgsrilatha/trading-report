package com.jpmc.uk.trading.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jpmc.uk.trading.bo.Currency;
import com.jpmc.uk.trading.bo.Instruction;
import com.jpmc.uk.trading.util.TradingReportUtil;

public class TradingDataCSVParser {
	
	private static final int[] SETTLEMENT_DATE_ADJUSTMENT = { 6, 7 };

    private static final int[] AED_SETTLEMENT_DATE_ADJUSTMENT = { 5, 6 };

    private static final String CSV_DATA_FILE_NAME = "trading_data.csv"; 

	 public List<Instruction> processInputCSVFile() throws IOException {
	        List<Instruction> instructionList = new ArrayList<Instruction>();
	        BufferedReader br = new BufferedReader(
	                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CSV_DATA_FILE_NAME)));
	        instructionList = br.lines().skip(1).map(csvtoEntity).collect(Collectors.toList());
	        br.close();
	        return instructionList;
	    }

	    public static Function<String, Instruction> csvtoEntity = (line) -> {
	        String[] p = line.split(",");
	        Instruction instructionObj = new Instruction();
	        instructionObj.setEntity(p[0]);
	        instructionObj.setTranscationType(p[1]);
	        instructionObj.setAgreedFx(Double.parseDouble(p[2]));
	        instructionObj.setCurrenyType(p[3]);
	        instructionObj.setInstructionDate(TradingReportUtil.getFormattedDate(p[4]));
	        instructionObj.setSettlementDate(TradingReportUtil.getFormattedDate(p[5]));
	        instructionObj.setUnits(Integer.parseInt(p[6]));
	        instructionObj.setPricePerUnit(Double.parseDouble(p[7]));
	        if (instructionObj.getCurrenyType().equals(Currency.SAR.name())
	                || instructionObj.getCurrenyType().equals(Currency.AED.name())) {
	            if (IntStream.of(AED_SETTLEMENT_DATE_ADJUSTMENT)
	                    .anyMatch(x -> x == TradingReportUtil.getDayOfWeekValue(p[5]))) {
	                instructionObj.setSettlementDate(TradingReportUtil.getFormattedDate(p[5])
	                        .plusDays(7 % TradingReportUtil.getDayOfWeekValue(p[5])));

	            }
	        } else {
	            if (IntStream.of(SETTLEMENT_DATE_ADJUSTMENT)
	                    .anyMatch(x -> x == TradingReportUtil.getDayOfWeekValue(p[5]))) {
	                int i = 7 % TradingReportUtil.getDayOfWeekValue(p[5]) + 1;
	                instructionObj.setSettlementDate(TradingReportUtil.getFormattedDate(p[5]).plusDays(i));
	            }
	        }

	        instructionObj.setTotalTransaction(TradingReportUtil.getTotalTranscation(instructionObj));

	        return instructionObj;
	    }; 

}
