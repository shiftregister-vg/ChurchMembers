package org.stevegood.user

import grails.plugins.springsecurity.SpringSecurityService
import org.stevegood.member.Member

class UserService {

    static transactional = true
    
    def springSecurityService
    
    UserMember addMemberToUser(User user, Member member){
        UserMember.create(user,member)
    }
    
    UserRole addRoleToUser(User user, Role role){
        UserRole.findByUserAndRole(user,role) ?: UserRole.create(user,role)
    }
    
    UserRole addRoleToUser(User user, String authority){
    	addRoleToUser(user,Role.findByAuthority(authority))
    }
    
    Role createRole(String label,String authority){
        Role.findByAuthority(authority) ?: new Role(label:label,authority:authority).save(flush:true,insert:true)
    }
    
    def createUser(String username, String password) {
        saveUser(new User(username:username,password:createPassword(password),enabled:false))
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
    
    User getNewestUser(){
    	def criteria = User.createCriteria()
    	int maxId = criteria.get{
    		eq("enabled",true)
    		projections{
    			max("id")
    		}
    	}
    	User.get(maxId)
    }
    
    def getActiveUserCount(){
    	def c = User.createCriteria()
    	def results = c.list{
    		eq("enabled",true)
    	}
    	results.size()
    }
    
	User getUser(int id){
		User.get(id)
	}

    User getUser(String username){
        User.findByUsername(username)
    }    
    
    def removeAllRolesFromUser(User user){
    	user.getAuthorities().each{ role ->
			UserRole.remove(user,role,true)
		}
    }
    
    def removeMemberFromUser(User user){
        UserMember.findByUser(user)?.delete(flush:true)
    }
    
    User saveUser(User user){
        user.save(flush:true)
    }
    
    boolean userExists(String username){
    	User.findByUsername(username) ? true : false
    }
}
