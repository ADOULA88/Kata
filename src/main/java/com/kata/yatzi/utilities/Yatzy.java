package com.kata.yatzi.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {
	
	public static int chance(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> sideNumbers = Arrays.asList(d1, d2, d3, d4, d5);
        return sideNumbers.stream().reduce(0, (a, b) -> a + b);
    }

    public static int yatzy(int... dice) {
    	Map<Integer, Long> groupDiceNumbers = getGroupedDiceNumber(dice[0], 
    			dice[1], dice[2], dice[3], dice[4]);
    	if(groupDiceNumbers != null && groupDiceNumbers.size() == 1){
    		return 50;
    	}
    	return 0;
    }

    public static int ones_twos_threes_fours_fives_sixes(int d1, int d2, int d3, int d4, int d5, int nb) {
        List<Integer> sideNumbers = Arrays.asList(d1, d2, d3, d4, d5);
        return (int) sideNumbers.stream().filter(number -> number == nb).count() * nb;
    }
    
    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        int max = getGroupedDiceNumber(d1, d2, d3, d4, d5).entrySet().stream()
        		.filter(e -> e.getValue() > 1).max(Map.Entry.comparingByKey())
                .get().getKey();
        return max * 2;
    }

    private static Map<Integer, Long> getGroupedDiceNumber(int d1, int d2, int d3, int d4, int d5){
    	List<Integer> sideNumbers = Arrays.asList(d1, d2, d3, d4, d5);
        return sideNumbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static int two_pair_three_four_of_a_kind(int d1, int d2, int d3, int d4, int d5, int nb) {
        int result = getGroupedDiceNumber(d1, d2, d3, d4, d5).entrySet().stream()
        		.filter(e -> e.getValue() >= nb).map(e -> e.getKey())
        		.reduce(0, Integer::sum);
        return result * nb;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
    	List<Integer> sortedSideNumbers = getSortedSideNumbers(d1, d2, d3, d4, d5);
        if (sortedSideNumbers != null && ! sortedSideNumbers.isEmpty() 
        		&& sortedSideNumbers.get(0) == 1 && sortedSideNumbers.get(1) == 2 
        		&& sortedSideNumbers.get(2) == 3 && sortedSideNumbers.get(3) == 4 && sortedSideNumbers.get(4) == 5){
        	return 15;
        }   
        return 0;
    }

    private static List<Integer> getSortedSideNumbers(int d1, int d2, int d3, int d4, int d5){
    	List<Integer> sideNumbers = Arrays.asList(d1, d2, d3, d4, d5);
    	return sideNumbers.stream().sorted().collect(Collectors.toList());
    }
    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
    	List<Integer> sortedSideNumbers = getSortedSideNumbers(d1, d2, d3, d4, d5);
        if (sortedSideNumbers != null && ! sortedSideNumbers.isEmpty() 
        		&& sortedSideNumbers.get(0) == 2 && sortedSideNumbers.get(1) == 3 
        		&& sortedSideNumbers.get(2) == 4 && sortedSideNumbers.get(3) == 5 
        		&& sortedSideNumbers.get(4) == 6){
        	return 20;
        }  
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
    	Map<Integer, Long> groupDiceNumbers = getGroupedDiceNumber(d1, d2, d3, d4, d5);
    	if(groupDiceNumbers != null && groupDiceNumbers.size() == 2){
    		Long result = groupDiceNumbers.entrySet().stream()
    				.filter(e-> e.getValue() == 2 || e.getValue() == 3).map(e -> e.getValue()).findAny().get();
    		if(result != null && result == 2){
    			return d1+d2+d3+d4+d5;
    		}
    	}
    	return 0;
    }
}