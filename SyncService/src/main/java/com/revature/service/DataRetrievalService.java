package com.revature.service;

import java.util.List;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public interface DataRetrievalService {
	
	/**
	 * @return
	 */
	public List<List<Object>> retrieveRawSheetData();
}
