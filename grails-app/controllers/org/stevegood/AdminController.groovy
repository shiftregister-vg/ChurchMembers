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
			def checkUser = userService.getUser(params.username)
			if (checkUser && user.username != params.username){
				flash.message = "Username is already taken, please choose another."
				redirect(action:'editUser',params:[username:user.username])
				return
			}
			
			user.username = params.username
			user.enabled = params.enabled ?: false
			
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
			
			if ( (user.getAuthorities().sort { it.label }.collect{ it.authority } != params?.selectedRole) && (user != springSecurityService.getCurrentUser()) && params?.selectedRole ){
				
				userService.removeAllRolesFromUser(user)
				
				if (params.selectedRole.class.isArray()){
					params.selectedRole.each{
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
	
	def addUser = {
		
	}
	
	def createUser = {
		if (params.password1 != params.password2){
			flash.message = "Passwords do not match. Please try again."
			redirect(action:'addUser',params:[username:params.username])
			return
		}
		
		def user = userService.getUser(params.username)
		if (user){
			flash.message = "Username is already taken, please choose another."
			redirect(action:'addUser',params:[username:params.username])
			return
		}
		user = userService.createUser(params.username,params.password1)
		if (!user){
			flash.message = "Uh oh! Something went wrong when trying to save the user. Try your request again or contact the administrator."
			redirect(action:'addUser',params:[username:params.username])
			return
		}
		flash.message = "$user has been created!"
		redirect(action:'editUser',params:[username:params.username])
	}
	
}
