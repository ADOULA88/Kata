package com.kata.yatzi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.kata.yatzi.exception.DiceException;
import com.kata.yatzi.services.YatziServiceImpl;

public class YatziServiceImplTest {

	private Map<String, Integer> personScore = new HashMap<>();
	
	private Map<String, List<String>> personRuleChosen = new HashMap<>();
	
	@InjectMocks
    @Spy
    private YatziServiceImpl yatziServiceImpl = new YatziServiceImpl();

	
	@Before
    public void setUp() throws Exception {
		personScore.put("person1", 100);
		personScore.put("person2", 90);
		personScore.put("person3", 80);
		yatziServiceImpl.setPersonScore(personScore);
		List<String> rulesForPerson1 = new ArrayList<>();
		rulesForPerson1.add("yatzi");
		personRuleChosen.put("person1", rulesForPerson1);
		List<String> rulesForPerson2 = new ArrayList<>();
		rulesForPerson2.add("yatzi");
		rulesForPerson2.add("chance");
		personRuleChosen.put("person2", rulesForPerson2);
		yatziServiceImpl.setPersonRuleChosen(personRuleChosen);
	}
	@Test
	public void testGetFinalscore() throws Exception {
		Integer result = yatziServiceImpl.getFinalScore("person1");
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(100));
		result = yatziServiceImpl.getFinalScore("person4");
		assertNull(result);
	}
	
	@Test
	public void testIsEligibletoRule() throws Exception {
		Boolean result = yatziServiceImpl.isEligibletoRule("person1", "yatzi");
		assertEquals(result, false);
		result = yatziServiceImpl.isEligibletoRule("person1", "chance");
		assertEquals(result, true);
		result = yatziServiceImpl.isEligibletoRule("person1", "chance");
		assertEquals(result, false);
		result = yatziServiceImpl.isEligibletoRule("person2", "chance");
		assertEquals(result, false);
		result = yatziServiceImpl.isEligibletoRule("person2", "pair");
		assertEquals(result, true);
		assertEquals(personRuleChosen.get("person2").size(), 3);
	}
	
	@Test
	public void testCalculateScoreByRule() throws Exception {
		String sideNumber= "1,1,3,3,6";
		String rule = "chance";
		String person = "person1";
		Integer result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(14));
		sideNumber = "4,5,5,6,1";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(21));
		rule = "yatzi";
		sideNumber="1,1,1,1,1";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(50));
		sideNumber="1,1,1,2,1";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(0));
		rule = "pair";
		sideNumber="3,3,3,4,4";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(8));
		sideNumber="1,1,6,2,6";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(12));
		sideNumber="3,3,3,4,1";
		result = yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
		assertNotNull(result);
		assertEquals(result, Integer.valueOf(6));
	}
	
	@Test(expected=DiceException.class)
	public void testKoCalculateScore() throws Exception {
		String sideNumber= "1,1,3";
		String rule = "chance";
		String person = "person1";
		yatziServiceImpl.calculateScoreByRule(sideNumber, rule, person);
	}

}

