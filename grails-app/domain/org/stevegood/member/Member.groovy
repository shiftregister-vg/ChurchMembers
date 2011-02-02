package org.stevegood.member

import org.stevegood.Address
import org.stevegood.Phone

class Member {
    
    String firstName
    String lastName
    String email
    Date dob
    Date dateCreated
    Date dateUpdated
    
    static hasMany = [addresses:Address,phones:Phone]
    
    static constraints = {
        firstName   blank:false
        lastName    blank:false
        email       blank:true,email:true
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
        /*def memberSpouse = MemberSpouse.findByMember(this)
        if (!memberSpouse){
            memberSpouse = MemberSpouse.findBySpouse(this)
            spouse = memberSpouse.member
        } else {
            spouse = memberSpouse.spouse
        }*/
    }
    
}
