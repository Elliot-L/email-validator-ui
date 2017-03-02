package com.csci3132.email_validator_ui;


//this class will contain the functionality of all three classes in the email_validator project
//all those methods were completely tested using JUnit in the previous project and can be safely used

public class Validator {

	//a short method that calls all 3 of the tests below and only returns true if every test is passed
	public boolean validate(String s){
		boolean test = false;
		if(checkAt(s) == true && checkWhiteSpace(s) == true && checkCharacters(s) == true){
			test = true;
		}
		return test;
	}
	
	
//the method to ensure there are not multiple @ symbols
	public boolean checkAt(String s){
		boolean test = false;
		
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '@'){
				count++;
			} 
		}
		
		if(count == 1){
			test = true;
		}
		return test;
	}
	
	
	//the method to ensure there is no whitespace
	public boolean checkWhiteSpace(String s){
		//checks to make sure there is no white space in the email address
		boolean test = false;
		if(!s.contains(" ")){
			test = true;
		}
		return test;
	}
	
	//the method to make sure there is at least one '@' and one '.'
	public boolean checkCharacters(String s){
		boolean test = false;
		
		if(s.contains(Character.toString('.')) && s.contains(Character.toString('@'))){
			test = true;
		}
		
		return test;
		
	}//ends the character check
	
}//ends validator class
