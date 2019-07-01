package com.kata.yatzi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kata.yatzi.constants.Constants;
import com.kata.yatzi.constants.RuleEnum;
import com.kata.yatzi.exception.DiceException;
import com.kata.yatzi.utilities.Yatzy;

@Service
public class YatziServiceImpl implements YatziService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Map<String, List<String>> personRuleChosen = new HashMap<>();

	private static Map<String, Integer> personScore = new HashMap<>();

	@Override
	public Integer getFinalScore(String person) throws Exception {
		Map<String, Integer> result = personScore.entrySet().stream().filter(p -> p.getKey().equals(person))
				.collect(Collectors.toMap(per -> per.getKey(), per -> per.getValue()));
		return result.get(person);
	}

	@Override
	public Boolean isEligibletoRule(String person, String rule) throws Exception {
		Map<String, List<String>> result = personRuleChosen.entrySet().stream().filter(p -> p.getKey().equals(person))
				.collect(Collectors.toMap(per -> per.getKey(), per -> per.getValue()));
		if (result != null && !result.isEmpty()) {
			List<String> ruleAlreadyUsed = result.get(person);
			if (ruleAlreadyUsed != null && !ruleAlreadyUsed.isEmpty()) {
				if (ruleAlreadyUsed.contains(rule.toLowerCase())) {
					return false;
				} else {
					ruleAlreadyUsed.add(rule);
					return true;
				}
			}
		} else if (result != null && result.isEmpty()) {
			List<String> ruleAdded = new ArrayList<>();
			ruleAdded.add(rule);
			personRuleChosen.put(person, ruleAdded);
			return true;
		}
		return false;
	}

	@Override
	public Integer calculateScoreByRule(String sideNumber, String rule, String person) throws Exception {
		logger.debug("Calculate score");
		if (sideNumber != null) {
			List<String> values = Arrays.stream(sideNumber.split(Constants.COMA)).collect(Collectors.toList());
			if (values == null || values.size() != 5) {
				logger.error("You must have 5 numbers seperated with coma.");
				throw new DiceException("You must have 5 numbers seperated with coma.");
			}
			int d1 = Integer.valueOf(values.get(0));
			int d2 = Integer.valueOf(values.get(1));
			int d3 = Integer.valueOf(values.get(2));
			int d4 = Integer.valueOf(values.get(3));
			int d5 = Integer.valueOf(values.get(4));

			Integer score = processScore(d1, d2, d3, d4, d5, rule);

			Map<String, Integer> result = personScore.entrySet().stream().filter(p -> p.getKey().equals(person))
					.collect(Collectors.toMap(per -> per.getKey(), per -> per.getValue()));
			Integer finalScore = null;
			if (result != null && !result.isEmpty()) {
				finalScore = result.get(person);
				finalScore = finalScore + score;
			} else {
				finalScore = new Integer(score);
				result.put(person, finalScore);
			}
			return score;
		}
		return null;
	}

	private int processScore(int d1, int d2, int d3, int d4, int d5, String rule) {
		if (RuleEnum.CHANCE.getRule().equals(rule)) {
			return Yatzy.chance(d1, d2, d3, d4, d5);
		}if (RuleEnum.YATZY.getRule().equals(rule)) {
			return Yatzy.yatzy(d1, d2, d3, d4, d5);
		} else if (RuleEnum.ONES.getRule().equals(rule)) {
			return Yatzy.ones(d1, d2, d3, d4, d5);
		} else if (RuleEnum.TWOS.getRule().equals(rule)) {
			return Yatzy.twos(d1, d2, d3, d4, d5);
		} else if (RuleEnum.THREES.getRule().equals(rule)) {
			return Yatzy.threes(d1, d2, d3, d4, d5);
		} else if (RuleEnum.PAIR.getRule().equals(rule)) {
			return Yatzy.score_pair(d1, d2, d3, d4, d5);
		} else if (RuleEnum.TWO_PAIRS.getRule().equals(rule)) {
			return Yatzy.two_pair(d1, d2, d3, d4, d5);
		} else if (RuleEnum.THREE_OF_KIND.getRule().equals(rule)) {
			return Yatzy.three_of_a_kind(d1, d2, d3, d4, d5);
		} else if (RuleEnum.FOUR_OF_KIND.getRule().equals(rule)) {
			return Yatzy.four_of_a_kind(d1, d2, d3, d4, d5);
		} else if (RuleEnum.SMALL_STRAIGHT.getRule().equals(rule)) {
			return Yatzy.smallStraight(d1, d2, d3, d4, d5);
		} else if (RuleEnum.LARGE_STRAIGHT.getRule().equals(rule)) {
			return Yatzy.largeStraight(d1, d2, d3, d4, d5);
		} else if (RuleEnum.FULL_HOUSE.getRule().equals(rule)) {
			return Yatzy.fullHouse(d1, d2, d3, d4, d5);
		} else {
			Yatzy yatzy = new Yatzy(d1, d2, d3, d4, d5);
			if (RuleEnum.FOURS.getRule().equals(rule)) {
				return yatzy.fours();
			} else if (RuleEnum.FIVES.getRule().equals(rule)) {
				return yatzy.fives();
			} else if (RuleEnum.SIXES.getRule().equals(rule)) {
				return yatzy.sixes();
			}
		}
		return 0;
	}

	public static void setPersonScore(Map<String, Integer> personScore) {
		YatziServiceImpl.personScore = personScore;
	}

	public static void setPersonRuleChosen(Map<String, List<String>> personRuleChosen) {
		YatziServiceImpl.personRuleChosen = personRuleChosen;
	}

}
