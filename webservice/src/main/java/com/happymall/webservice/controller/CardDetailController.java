package com.happymall.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.service.CardDetailService;

@RestController
@RequestMapping({ "/cards" })
public class CardDetailController {

	@Autowired
	CardDetailService cardDetailService;

	@RequestMapping("/all/{id}")
	public List<CardDetail> list(Model model, @PathVariable("id") int userId) {

		return cardDetailService.findCardByUser(userId);
	}

	@GetMapping("/{id}")
	public @ResponseBody CardDetail getCardById(@PathVariable("id") int cardDetailtId) {

		return cardDetailService.getCardDetail(cardDetailtId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewCard(@RequestBody CardDetail cardToBeAdded) {

		cardDetailService.addCardDetail(cardToBeAdded);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public CardDetail processUpdateCard(@RequestBody CardDetail cardToBeUpdated) {

		return cardDetailService.updateCardDetail(cardToBeUpdated);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public CardDetail deleteCard(@RequestBody CardDetail cardToBeDeleted) {

		cardToBeDeleted.setActive_Ind('D');
		return cardDetailService.updateCardDetail(cardToBeDeleted);
	}

	@RequestMapping("/user/{id}")
	public List<CardDetail> findCardByUser(@PathVariable("id") int userId) {

		return cardDetailService.findCardByUser(userId);
	}

	@RequestMapping("/default/{id}")
	public CardDetail findDefaultCardByUser(@PathVariable("id") int userId) {

		return cardDetailService.findDefaultCardByUser(userId);
	}

	@RequestMapping(value = "/is-valid", method = RequestMethod.POST)
	public CardDetail isCardValid(@RequestBody CardDetail cardDetail) {

		Boolean flag = cardDetailService.isCardValid(cardDetail);
		if (flag)
			return cardDetail;

		return null;
	}

}
