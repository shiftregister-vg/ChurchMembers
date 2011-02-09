package org.stevegood.member

import org.stevegood.Address
import org.stevegood.Phone

class Member {
    
    String firstName
    String lastName
    String email
    Date dob
	Gender gender
    Date dateCreated
    Date lastUpdated
    
    static hasMany = [addresses:Address,phones:Phone]
    
    static constraints = {
        firstName   blank:false
        lastName    blank:false
        email       blank:true,email:true
		gender		nullable:false
    }
    
	static searchable = {
		spellCheck "include"
	}

    String toString(){
        "$firstName $lastName"
    }
    
    Set<Member> parents(){
        ParentChild.findAllByChild(this)*.parent as Set
    }
    
    Set<Member> children(){
        ParentChild.findAllByParent(this)*.child as Set
    }
    
    Member spouse(){
        MemberSpouse.findByMember(this)?.spouse ?: MemberSpouse.findBySpouse(this)?.member
    }
    
}
