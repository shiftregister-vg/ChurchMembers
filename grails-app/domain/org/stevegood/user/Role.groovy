package org.stevegood.user

class Role {

	String authority
	String label

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true 
		label blank:false
	}
	
	String toString(){
		label
	}
}
