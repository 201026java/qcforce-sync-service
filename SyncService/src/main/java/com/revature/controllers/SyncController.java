package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class SyncController {

	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping("/sync")
	public Mono<Void> triggerSyncService() {
		return Mono.fromRunnable(() -> messageService.sendData()).subscribeOn(Schedulers.elastic()).then();
	}

//	@GetMapping("/sync")
//	public List<List<String>> displayCurrentCount()
//	{
//		return googleSheets.getFilteredSheetData();
//	}

}
