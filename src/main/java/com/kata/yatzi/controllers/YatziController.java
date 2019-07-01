package com.kata.yatzi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kata.yatzi.exception.DiceException;
import com.kata.yatzi.services.YatziService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller
 *
 */
@RestController
@RequestMapping("/yatzi")
@Api(value = "Yatzi", description = "The game of Yatzi")
public class YatziController {

	@Autowired
	private YatziService yatziService;

	@ApiOperation(value = "Calculate Score By Chance", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully calculated the score"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/score/{sideNumber}/{rule}/{person}", method = RequestMethod.GET, produces = "application/json")
	public Integer calculateScore(@PathVariable String sideNumber, @PathVariable String rule, @PathVariable String person) {

		try {
			if (sideNumber != null) {
				return yatziService.calculateScoreByRule(sideNumber, rule, person);
			}
		} catch (Exception e) {
			if (e instanceof DiceException) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, 
						"You must have 5 numbers seperated by coma.", e);
			}
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
					"Internal error when calculating score", e);
		}
		return null;
	}
	
	@ApiOperation(value = "Is the player eligible to a rule.", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The player is Eligible to the rule."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/isEligibleToRule/{person}/{rule}", method = RequestMethod.GET, produces = "application/json")
	public Boolean isEligibleToRule(@PathVariable String person, @PathVariable String rule) {

		try {
			if (person != null && rule != null) {
				return yatziService.isEligibletoRule(person, rule);
			}
		} catch (Exception e) {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
				"Internal error when checking if a player is eligible to rule : " + rule, e);
		}
		return null;
	}
	
	@ApiOperation(value = "Get final score for player.", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The player's score"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/isEligibleToRule/{person}", method = RequestMethod.GET, produces = "application/json")
	public Integer getScore(@PathVariable String person) {

		try {
			if (person != null) {
				return yatziService.getFinalScore(person);
			}
		} catch (Exception e) {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
				"Internal error when getting score for : " + person, e);
		}
		return null;
	}
}
