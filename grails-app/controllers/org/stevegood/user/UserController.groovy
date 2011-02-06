package org.stevegood.user

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import org.stevegood.member.MemberService

class UserController {
    
	def memberService
	def springSecurityService
    def userService

    def index = {        
        redirect(action:"show")
    }
    
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show = {
		def user = getAuthenticatedUser()
		def member = userService.getUserMember(user)
		def membersList = memberService.getAvailableMembers()
        [user:user,member:member,membersList:membersList]
    }
    
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit = {
        if (params.password1 != params.password2){
            flash.message = "Passwords do not match. Please try again."            
            redirect(action:"changePassword")
            return
        }
        
        def user = getAuthenticatedUser()
        if (params.password1.length() && params.password2.length()){
        	user.password = userService.createPassword(params.password1)
        }
        userService.saveUser(user)
        flash.message = "${user} saved!"
        redirect(action:"show")
    }
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def linkMember = {
		def member = memberService.getMember(params?.memberid as int)
		userService.addMemberToUser(springSecurityService.getCurrentUser(),member)
		flash.message = "${member} has been linked to ${springSecurityService.getCurrentUser()}"
		redirect(action:"show")
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def unlinkMember = {
		userService.removeMemberFromUser(springSecurityService.getCurrentUser())
		flash.message = "Unlinked member record from ${ springSecurityService.getCurrentUser() }"
		redirect(action:"show")
	}
    
}
