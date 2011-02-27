package org.stevegood.user

import org.stevegood.member.Member

class UserMemberLinkRequest {
	
	User user
	Member member
	
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
    }
	
	String toString(){
		"$user -> $member"
	}
	
	static UserMemberLinkRequest create(User user, Member member){
		UserMemberLinkRequest.findByUserAndMember(user,member) ?: new UserMemberLinkRequest(user:user,member:member).save(insert:true,flush:true)
	}
}
