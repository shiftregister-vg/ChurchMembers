package org.stevegood.user

import org.stevegood.member.Member

class UserMember {
    
    User user
    Member member
    
    static UserMember create(User user, Member member) {
        UserMember.findByUserAndMember(user,member) ?: new UserMember(user:user,member:member).save(flush:true,insert:true)
    }
}
