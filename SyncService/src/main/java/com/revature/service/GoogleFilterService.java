package com.revature.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.FormResponse;
@Service
public class GoogleFilterService implements DataFilterService{

	private DataRetrievalService dataRetrievalService;
	
	/**
	 * @param dataRetrievalService
	 */
	@Autowired
	public void setDataRetrievalService(DataRetrievalService dataRetrievalService) {
		this.dataRetrievalService = dataRetrievalService;
	}
	
	
	/**
	 * @return
	 */
	@Override
	public List<List<String>> getFilteredSheetData() {
		return filterDup(convertRawToStringList(dataRetrievalService.retrieveRawSheetData()));
	}
	
	/**
	 * @param data
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<List<String>> convertRawToStringList(List<List<Object>> data) {
		//TODO: Comment
		List<List<String>> listOfLists = new ArrayList<List<String>>();
		/* 
		 * TODO: Comment
		 */
		for (@SuppressWarnings("rawtypes") List row : data) {
			listOfLists.add(row);
		}
		return listOfLists;
	}
	
	/**
	 * @param data
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<List<String>> filterDup(List<List<String>> data) {
		if(data.size()==0)
		{
			return new ArrayList<List<String>>();
		}
		// size test
//		for (List row : data) {
//			System.out.println("SIZE: " + row.size());
//		}
		//TODO: Comment
		List<String> questions = data.get(0);
		
		//TODO: Comment
		List<Integer> itemsToRemove = new ArrayList<Integer>();
		
		/* 
		 * Get index of all duplicate columns to be joined.
		 */
		//TODO: Comment
		for (int i = 1; i < questions.size() - 1; i++) {
			//TODO: Comment
			if (questions.get(i).toString().equals(questions.get(i - 1).toString())
					|| questions.get(i).toString().equals(questions.get(i + 1).toString())) {
				
				//TODO: Comment
				itemsToRemove.add(i);
			}
		}
		
		/* 
		 * Remove duplicate questions
		 */
		//TODO: Comment
		Set<String> set = new LinkedHashSet<String>();
		//TODO: Comment
		set.addAll(questions);
		//TODO: Comment
		questions.clear();
		//TODO: Comment
		questions.addAll(set);
		//TODO: Comment
		data.set(0, questions);
		
		/* 
		 * Joins duplicate Columns
		 */
		//TODO: Comment
		for (int i = 1; i < data.size(); i++) {
			//TODO: Comment
			for (int j = itemsToRemove.size() - 1; j >= 0; j--) {
				//TODO: Comment
				if (data.get(i).get(itemsToRemove.get(j).intValue()).toString().isEmpty()) {
					//TODO: Comment
					data.get(i).remove(itemsToRemove.get(j).intValue());
				}
			}
		}
		
		/* 
		 * Fill in missing ending columns
		 */
		//TODO: Comment
		int maxColumn = data.get(0).size();
		//TODO: Comment
		for (@SuppressWarnings("rawtypes") List row : data) {
			//TODO: Comment
			while (row.size() < maxColumn) {
				//TODO: Comment
				row.add("");
			}
		}

		return data;
	}
	
	/**
	 * @return
	 */
	@Override
	public List<FormResponse> mapFormResponses() {
		
		List<FormResponse>forms=new ArrayList<FormResponse>();
		
		List<List<String>> filteredData = getFilteredSheetData();
		System.out.println("Filtered Data:\n"+filteredData);
		if (filteredData.size()==0)
		{
			return new ArrayList<FormResponse>();
		}
		// TODO: Comment
		List<String>questions=filteredData.get(0);
		// TODO: Comment
		questions.remove(0);
		
		//Cycle through filtered data and create a new form response and add it to the returned array
		for(int i=1;i< filteredData.size();i++)
		{
			FormResponse temp =new FormResponse();
			temp.setTimestamp(filteredData.get(i).get(0));
			List<String>answers=filteredData.get(i);
			answers.remove(0);
			temp.setQuestions(questions);
			temp.setAnswers(answers);	
			forms.add(temp);
		}
		// TODO: Comment

		return forms;
	}

	
}
