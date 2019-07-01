package com.kata.yatzi.services;

/**
 * 
 * Services
 *
 */
public interface YatziService {

	/**
	 * Is eligible to rule
	 * @param person
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	Boolean isEligibletoRule(String person, String rule) throws Exception;

	/**
	 * Score by a rule
	 * @param sideNumber
	 * @param rule
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Integer calculateScoreByRule(String sideNumber, String rule, String person) throws Exception;

	/**
	 * Get final score by person
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Integer getFinalScore(String person) throws Exception;
}
