package com.jpmc.uk.trading.service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmc.uk.trading.bo.Instruction;
import com.jpmc.uk.trading.bo.Ranking;
import com.jpmc.uk.trading.bo.TradeType;
import com.jpmc.uk.trading.errorhandling.ExceptionHandler;

public class TradingReportService {

	private static final NumberFormat currencyFormatter = NumberFormat
			.getCurrencyInstance(new Locale("en", "US"));

	private static final String INCOMING_ENTITIES_RANKING = "Incoming Entities Ranking";

	private static final String OUTGOING_ENTITIES_RANKING = "Outgoing Entities Ranking";

	private static final String SEPERATOR_LINE_STRING = "****************************************";

	private static final String TRADING_DATE = "Date : ";

	private static final String INCOMING = "Incoming ";

	private static final String RANKING = "Rank  ";

	private static final String ENTITY = "Entity ";

	private static final String TOTAL_AMOUNT = "  Amount : ";

	private static final String TRADE_AMOUNT_ON = "trade amount on ";

	private static final String OUTGOING_TRADE_AMOUNT_ON_DAILY = "Outgoing Trading Amount On Daily";

	private static final String INCOMING_TRADE_AMOUNT_ON_DAILY = "Incoming Trading Amount On Daily";

	public boolean generateFXTransactionReport(List<Instruction> instructionList)
			throws ExceptionHandler {

		Map<LocalDate, Double> mapdataBuy = instructionList
				.stream()
				.filter(x -> x.getTranscationType().equalsIgnoreCase(
						TradeType.BUY.name()))
				.collect(
						Collectors
								.groupingBy(
										Instruction::getSettlementDate,
										Collectors
												.summingDouble(Instruction::getTotalTransaction)));

		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(OUTGOING_TRADE_AMOUNT_ON_DAILY);
		System.out.println(SEPERATOR_LINE_STRING);
		mapdataBuy.entrySet().forEach(
				b -> {
					System.out.println(TRADING_DATE + b.getKey() + TOTAL_AMOUNT
							+ currencyFormatter.format(b.getValue()));
				});

		System.out.println();

		/* Rank the entities for all the incoming FX transactions settled */
		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(OUTGOING_ENTITIES_RANKING);
		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(RANKING + ENTITY + TOTAL_AMOUNT);
		rankingBasedOnEntity(instructionList, TradeType.BUY.name()).forEach(
				(f) -> {
					System.out.println(f.getRank() + "     " + f.getEntity()
							+ "      "
							+ currencyFormatter.format(f.getTotalAmount()));
				});

		System.out.println();
		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(INCOMING_TRADE_AMOUNT_ON_DAILY);
		System.out.println(SEPERATOR_LINE_STRING);
		Map<LocalDate, Double> mapdataSell = instructionList
				.stream()
				.filter(x -> x.getTranscationType().equalsIgnoreCase(
						TradeType.SELL.name()))
				.collect(
						Collectors
								.groupingBy(
										Instruction::getSettlementDate,
										Collectors
												.summingDouble(Instruction::getTotalTransaction)));

		mapdataSell.entrySet().forEach(
				s -> {
					System.out.println(TRADING_DATE + s.getKey() + TOTAL_AMOUNT
							+ currencyFormatter.format(s.getValue()));
				});
		System.out.println();
		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(INCOMING_ENTITIES_RANKING);
		System.out.println(SEPERATOR_LINE_STRING);
		System.out.println(RANKING + ENTITY + TOTAL_AMOUNT);
		rankingBasedOnEntity(instructionList, TradeType.SELL.name()).forEach(
				(f) -> {
					System.out.println(f.getRank() + "     " + f.getEntity()
							+ "      "
							+ currencyFormatter.format(f.getTotalAmount()));
				});

		return true;
	}

	public List<Ranking> rankingBasedOnEntity(
			List<Instruction> instructionList, String transactionType)
			throws ExceptionHandler {

		double[] score = { Double.MIN_VALUE };
		int[] no = { 0 };
		int[] rank = { 0 };

		return instructionList
				.stream()
				.filter(x -> x.getTranscationType().equalsIgnoreCase(
						transactionType))
				.collect(
						Collectors.groupingBy(
								Instruction::getEntity,
								Collectors
										.summingDouble(Instruction::getTotalTransaction)))
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.map(p -> {
					++no[0];
					if (score[0] != p.getValue())
						rank[0] = no[0];
					else if (score[0] == p.getValue())
						--no[0];
					return new Ranking(rank[0], p.getKey(), score[0] = p
							.getValue());
				}).collect(Collectors.toList());
	}

}
