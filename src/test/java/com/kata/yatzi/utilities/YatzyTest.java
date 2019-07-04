package com.kata.yatzi.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class YatzyTest {

	@Test
	public void chance_scores_sum_of_all_dice() {
		int expected = 15;
		int actual = Yatzy.chance(2, 3, 4, 5, 1);
		assertEquals(expected, actual);
		assertEquals(16, Yatzy.chance(3, 3, 4, 5, 1));
	}

	@Test
	public void yatzy_scores_50() {
		int expected = 50;
		int actual = Yatzy.yatzy(4, 4, 4, 4, 4);
		assertEquals(expected, actual);
		assertEquals(50, Yatzy.yatzy(6, 6, 6, 6, 6));
		assertEquals(0, Yatzy.yatzy(6, 6, 6, 6, 3));
	}

	@Test
	public void test_1s() {
		assertTrue(Yatzy.ones_twos_threes_fours_fives_sixes(1, 2, 3, 4, 5, 1) == 1);
		assertEquals(2, Yatzy.ones_twos_threes_fours_fives_sixes(1, 2, 1, 4, 5, 1));
		assertEquals(0, Yatzy.ones_twos_threes_fours_fives_sixes(6, 2, 2, 4, 5, 1));
		assertEquals(4, Yatzy.ones_twos_threes_fours_fives_sixes(1, 2, 1, 1, 1, 1));
	}

	@Test
	public void test_2s() {
		assertEquals(4, Yatzy.ones_twos_threes_fours_fives_sixes(1, 2, 3, 2, 6, 2));
		assertEquals(10, Yatzy.ones_twos_threes_fours_fives_sixes(2, 2, 2, 2, 2, 2));
	}

	@Test
	public void test_threes() {
		assertEquals(6, Yatzy.ones_twos_threes_fours_fives_sixes(1, 2, 3, 2, 3, 3));
		assertEquals(12, Yatzy.ones_twos_threes_fours_fives_sixes(2, 3, 3, 3, 3, 3));
	}

	@Test
	public void fours_test() {
		assertEquals(12, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 4, 5, 5, 4));
		assertEquals(8, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 5, 5, 5, 4));
		assertEquals(4, Yatzy.ones_twos_threes_fours_fives_sixes(4, 5, 5, 5, 5, 4));
	}

	@Test
	public void fives() {
		assertEquals(10, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 4, 5, 5, 5));
		assertEquals(15, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 5, 5, 5, 5));
		assertEquals(20, Yatzy.ones_twos_threes_fours_fives_sixes(4, 5, 5, 5, 5, 5));
	}

	@Test
	public void sixes_test() {
		assertEquals(0, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 4, 5, 5, 6));
		assertEquals(6, Yatzy.ones_twos_threes_fours_fives_sixes(4, 4, 6, 5, 5, 6));
		assertEquals(18, Yatzy.ones_twos_threes_fours_fives_sixes(6, 5, 6, 6, 5, 6));
	}

	@Test
	public void one_pair() {
		assertEquals(6, Yatzy.score_pair(3, 4, 3, 5, 6));
		assertEquals(10, Yatzy.score_pair(5, 3, 3, 3, 5));
		assertEquals(12, Yatzy.score_pair(5, 3, 6, 6, 5));
	}

	@Test
	public void two_Pair() {
		assertEquals(16, Yatzy.two_pair_three_four_of_a_kind(3, 3, 5, 4, 5, 2));
		assertEquals(16, Yatzy.two_pair_three_four_of_a_kind(3, 3, 5, 5, 5, 2));
	}

	@Test
	public void three_of_a_kind() {
		assertEquals(9, Yatzy.two_pair_three_four_of_a_kind(3, 3, 3, 4, 5, 3));
		assertEquals(15, Yatzy.two_pair_three_four_of_a_kind(5, 3, 5, 4, 5, 3));
		assertEquals(9, Yatzy.two_pair_three_four_of_a_kind(3, 3, 3, 3, 5, 3));
	}

	@Test
	public void four_of_a_knd() {
		assertEquals(12, Yatzy.two_pair_three_four_of_a_kind(3, 3, 3, 3, 5, 4));
		assertEquals(20, Yatzy.two_pair_three_four_of_a_kind(5, 5, 5, 4, 5, 4));
		assertEquals(12, Yatzy.two_pair_three_four_of_a_kind(3, 3, 3, 3, 3, 4));
	}

	@Test
	public void smallStraight() {
		assertEquals(15, Yatzy.smallStraight(1, 2, 3, 4, 5));
		assertEquals(15, Yatzy.smallStraight(2, 3, 4, 5, 1));
		assertEquals(0, Yatzy.smallStraight(1, 2, 2, 4, 5));
	}

	@Test
	public void largeStraight() {
		assertEquals(20, Yatzy.largeStraight(6, 2, 3, 4, 5));
		assertEquals(20, Yatzy.largeStraight(2, 3, 4, 5, 6));
		assertEquals(0, Yatzy.largeStraight(1, 2, 2, 4, 5));
	}

	@Test
	public void fullHouse() {
		assertEquals(8, Yatzy.fullHouse(1, 1, 2, 2, 2));
		assertEquals(0, Yatzy.fullHouse(2, 2, 3, 3, 4));
		assertEquals(0, Yatzy.fullHouse(4, 4, 4, 4, 4));
	}
}
