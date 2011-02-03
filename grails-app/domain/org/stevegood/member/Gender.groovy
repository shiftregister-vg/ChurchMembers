package org.stevegood.member

enum Gender {
	MALE('Male'),
	FEMALE('Female')
	
	final String id
	
	Gender(String id){ this.id = id }
	
	static list(){
		[MALE,FEMALE]
	}
	
	String toString(){ id }
	
}