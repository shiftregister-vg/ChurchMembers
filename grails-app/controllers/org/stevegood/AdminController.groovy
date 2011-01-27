package org.stevegood

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import org.stevegood.member.Member
import org.stevegood.member.MemberService
import org.stevegood.user.Role
import org.stevegood.user.User
import org.stevegood.user.UserService

@Secured(['ROLE_ADMIN'])
class AdminController {
	
	def memberService
	def springSecurityService
	def userService
	
    def index = {
    	[memberCount:Member.count(),userCount:User.count(),newestUser:userService.getNewestUser(),newestMember:memberService.getNewestMember()]
    }
	
	@Secured(['ROLE_SUPER_USER'])
	def listUsers = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userList: User.list(params), userTotal: User.count()]
	}
	
	@Secured(['ROLE_SUPER_USER'])
	def deleteUser = { }
	
	@Secured(['ROLE_SUPER_USER'])
	def editUser = {
		def user = userService.getUser(params.username)
		if (!user){
			flash.message = "Username does not exist."
			redirect(action:'index')
			return
		}
		
		def isCurrentUser = user == springSecurityService.getCurrentUser()
		
		[user:user,roleList:Role.list().sort{ it.label },isCurrentUser:isCurrentUser]
	}
	
	@Secured(['ROLE_SUPER_USER'])
	def saveUser = {
		def user = userService.getUser(params?.id as int)
		if (user == springSecurityService.getCurrentUser()){
			user = springSecurityService.getCurrentUser()
		}
		if (!user){
			flash.message = "Invalid user."
			redirect(action:'index')
		} else {
			user.username = params.username
			
			if (params?.password1 != params?.password2){
				flash.message = "Passwords do not match. Please try again."
				redirect(action:'editUser',params:[username:user.username])
				return
			} else {
				if (params?.password1?.length() && params?.password2?.length()){
					user.password = userService.createPassword(params.password1)
				}
			}
						
			userService.saveUser(user)
			
			if ( (user.getAuthorities().sort { it.label }.collect{ it.authority } != params?.selectedRole) && (user != springSecurityService.getCurrentUser()) ){
				userService.removeAllRolesFromUser(user)
				if (params?.selectedRole instanceof java.util.List){
					params?.selectedRole?.each{
						userService.addRoleToUser(user,it)
					}
				} else {
					userService.addRoleToUser(user,params?.selectedRole)
				}
			}
			
			flash.message = "$user saved"
			redirect(action:'editUser',params:[username:user.username])
		}
	}
	
}
