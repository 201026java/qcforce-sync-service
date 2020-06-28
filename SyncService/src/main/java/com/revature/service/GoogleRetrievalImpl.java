package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import com.revature.config.SheetsServiceConfig;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Service
public class GoogleRetrievalImpl implements DataRetrievalService{
	
	/** * */
	private Sheets sheetsService;

	private FormService formService;

	/** * */
	public static int currentRow;

	/**
	 * @param sheetsService
	 */
	@Autowired
	public void setSheetsService(Sheets sheetsService) {
		this.sheetsService = sheetsService;
	}

	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	/*
	public GoogleRetrievalImpl(Sheets sheetsService, FormService formService) {
		super();
		this.sheetsService = sheetsService;
		this.formService = formService;
	}
	*/

	@Override
	public List<List<Object>> retrieveRawSheetData() {
		// TODO: Comment
		String spreadsheetId = SheetsServiceConfig.SPREAD_SHEET_ID;
		// TODO: Comment

		currentRow = formService.getFormById(1).getFormId();

		currentRow += 1;
		String range = "A" + (currentRow+1) + ":ZZZ";
		// TODO: Comment
		ValueRange response, questions;

		// TODO: Comment
		try {
			questions = sheetsService.spreadsheets().values().get(spreadsheetId, "A1:1").execute();
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			if (formService.getFormById(1).getFormId() + 1 != currentRow) {
				return new ArrayList<List<Object>>();
			}

			List<List<Object>> values = response.getValues();
			if (values != null) {
				values.add(0, questions.getValues().get(0));
				return values;
			} else {
				return new ArrayList<List<Object>>();
			}

		} catch (Exception e) {
			// TODO: Log this exception
			e.printStackTrace();
			return new ArrayList<List<Object>>();
		}

	}

}
