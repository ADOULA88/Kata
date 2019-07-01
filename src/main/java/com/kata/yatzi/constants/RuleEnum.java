package com.kata.yatzi.constants;

/**
 * 
 * Rules Enumeration
 *
 */
public enum RuleEnum {
	CHANCE("chance"), YATZY("yatzi"), PAIR("pair"), TWO_PAIRS("two pairs"),
	THREE_OF_KIND("three of kind"), FOUR_OF_KIND("four of kind"), SMALL_STRAIGHT("small straight"),
	LARGE_STRAIGHT("large straight"), FULL_HOUSE("full house"), ONES("ones"), TWOS("twos"), 
	THREES("threes"), FOURS("fours"), FIVES("fives"), SIXES("sixes");
	
	private String rule; 
	
	RuleEnum(String rule) {
		this.rule = rule;
	}

	public String getRule() {
		return rule;
	}
	
}
