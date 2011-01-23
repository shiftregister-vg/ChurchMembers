package org.stevegood.user

import grails.plugins.springsecurity.SpringSecurityService
import org.stevegood.member.Member

class UserService {

    static transactional = true
    
    def springSecurityService
    
    UserMember addMemberToUser(User user, Member member){
        UserMember.create(user,member)
    }
    
    def createUser(String username, String password) {
        saveUSer(new User(username:username,password:createPassword(password),enabled:true))
    }
    
    String createPassword(String password){
        springSecurityService.encodePassword(password)
    }
    
    def enableDisableUser(User user){
        user.enabled = !user.enabled
        saveUser(user)
    }
    
    def deleteUser(User user){
        removeMemberFromUser(user)
        user.delete(flush:true)
    }
    
    User getUser(String username){
        User.findByUsername(username)
    }    
    
    def removeMemberFromUser(User user){
        UserMember.findByUser(user)?.delete(flush:true)
    }
    
    User saveUser(User user){
        user.save(flush:true)
    }
}
