package com.jpmc.uk.trading.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jpmc.uk.trading.bo.Instruction;

public class TradingReportUtil {

	private static final String INPUT_DATE_FORMATTER_PATTERN = "MM/dd/yyyy";

	public static LocalDate getFormattedDate(String dateValue) {
		return LocalDate.parse(dateValue,
				DateTimeFormatter.ofPattern(INPUT_DATE_FORMATTER_PATTERN));

	}

	public static int getDayOfWeekValue(String dateValue) {
		return LocalDate
				.parse(dateValue,
						DateTimeFormatter
								.ofPattern(INPUT_DATE_FORMATTER_PATTERN))
				.getDayOfWeek().getValue();

	}

	public static Double getTotalTranscation(Instruction instructionObj) {
		return instructionObj.getPricePerUnit() * instructionObj.getUnits()
				* instructionObj.getAgreedFx();

	}

}
