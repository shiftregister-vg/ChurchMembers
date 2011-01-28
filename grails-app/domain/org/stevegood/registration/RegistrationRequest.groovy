package org.stevegood.registration

import org.stevegood.member.Member
import org.stevegood.user.User

class RegistrationRequest {
	
	String email
	User user
	Member member
	boolean approved = false
	
	Date dateCreated
	Date dateUpdated
	
    static constraints = {
    	email blank:false, email:true
    	member nullable:true
    	dateUpdated nullable:true
    }
    
    static RegistrationRequest create(String email,User user, Member member){
    	RegistrationRequest.findWhere(user:user,member:member,email:email) ?: new RegistrationRequest(user:user,member:member,email:email).save(flush:true,insert:true)
    }
}
