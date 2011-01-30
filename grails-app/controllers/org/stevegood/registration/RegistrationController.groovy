package org.stevegood.registration

import grails.plugins.springsecurity.Secured
import org.apache.commons.validator.EmailValidator
import org.stevegood.member.Member
import org.stevegood.member.MemberService
import org.stevegood.user.User
import org.stevegood.user.UserMember
import org.stevegood.user.UserService

class RegistrationController {
	
	def memberService
	def userService

    def index = { redirect(action:'register') }
    
    def register = {
    	// create a new collection of members who are NOT associated to a User
		def availableMembers = Member.list() - UserMember.list().collect { it.member }
		[availableMembers:availableMembers]
    }
    
    def submitRegistrationRequest = {
    	
    	// make sure a username was passed
    	if (!params.username.size()){
    		flash.message = "Please provide a username."
    		redirect(action:"register")
    		return
    	}
    	
    	// validate that the username is unique
    	if (userService.userExists(params.username)){
    		flash.message = "Username is already taken. Please choose another."
    		redirect(action:'register',params:[username:params.username])
    		return
    	}
    	
    	// validate that a valid email address was passed
    	def emailValidator = EmailValidator.getInstance()
    	if (!params?.email?.size() || !emailValidator.isValid(params?.email)){
    		flash.message = "A valid email address is required."
    		redirect(action:'register',params:[username:params.username,email:params.email])
    		return
    	}
    	
    	// validate that a password was supplied
    	if (!params?.password1?.size() || !params?.password2?.size()){
    		flash.message = "Please supply a password."
    		redirect(action:'register',params:[username:params.username,email:params.email])
    		return
    	}
    	
    	// validate that the passwords match
    	if (params?.password1 != params?.password2){
    		flash.message = "Passwords do not match. Please try again."
    		redirect(action:'register',params:[username:params.username,email:params.email])
    		return
    	}
    	
    	// we should be ready to process the request now
    	
    	// if a member was selected in the previous form then get them, otherwise set a null value
    	Member member
    	if (params?.memberid > 0){
    		member = memberService.getMember(params.memberid as int)
    	}
    	
    	// create a new RegistrationRequest and a disabled User
    	RegistrationRequest.create(params.email,userService.createUser(params.username,params.password1),member)
    		
    	// let the user know their request has been received and redirect them to the login page
    	flash.message = "Your registration request has been recorded. You will be notified as soon as your request is approved or denied."
    	redirect(controller:"login")
    }
	
	@Secured(['ROLE_ADMIN'])
	def processRequest = {
		def registrationRequest = RegistrationRequest.get(params?.id)
		[registrationRequest:registrationRequest]
	}
	
	@Secured(['ROLE_ADMIN'])
	def removeMemberFromRequest = {
		def registrationRequest = RegistrationRequest.get(params?.id)
		if (registrationRequest){
			registrationRequest.member = null
			registrationRequest.save()
		}
		redirect(action:'processRequest',params:[id:params?.id])
	}
	
	@Secured(['ROLE_ADMIN'])
	def approveRequest = {
		def registrationRequest = RegistrationRequest.get(params?.id)
		if (!registrationRequest || registrationRequest.approved){
			flash.message = "Registration request either did not exist or was already approved."
			redirect(controller:"admin",action:"index")
			return
		}
		
		registrationRequest.approved  = true
		registrationRequest.rejected = false
		registrationRequest.save()
		
		def user = registrationRequest.user
		user.enabled = true
		userService.saveUser(user)
		flash.message = "$user registration request has been approved"
		
		def member = registrationRequest.member
		if (member){
			userService.addMemberToUser(user,member)
			flash.message = flash.message + "<br/>$user has been linked to $member"
		}
		
		redirect(controller:"admin",action:"index")
	}
	
	@Secured(['ROLE_ADMIN'])
	def rejectRequest = {
		
		def registrationRequest = RegistrationRequest.get(params?.id)
		if (registrationRequest){
			registrationRequest.approved = false
			registrationRequest.rejected = true
			registrationRequest.save()
		}
		
		redirect(controller:'admin',action:'index')
		
	}
}
