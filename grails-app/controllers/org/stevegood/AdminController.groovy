package org.stevegood

import grails.plugins.springsecurity.Secured
import org.stevegood.user.Role
import org.stevegood.user.User
import org.stevegood.user.UserService

@Secured(['ROLE_ADMIN'])
class AdminController {
	
	def userService
	
    def index = { }
	
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
		}
		
		[user:user,roleList:Role.list().sort{ it.label }]
	}
	
	@Secured(['ROLE_SUPER_USER'])
	def saveUser = {
		def user = userService.getUser(params?.id as int)
		if (!user){
			flash.message = "Invalid user."
			redirect(action:'index')
		} else {
			user.username = params.username
			userService.saveUser(user)
			
			flash.message = "$user saved"
			redirect(action:'editUser',params:[username:user.username])
		}
	}
	
}
