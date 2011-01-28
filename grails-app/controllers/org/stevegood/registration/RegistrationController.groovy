package org.stevegood.registration

import org.apache.commons.validator.EmailValidator
import org.stevegood.member.Member
import org.stevegood.member.MemberService
import org.stevegood.user.User
import org.stevegood.user.UserService

class RegistrationController {
	
	def memberService
	def userService

    def index = { redirect(action:'register') }
    
    def register = {
    	
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
    	
    	// create a new disabled user
    	User user = userService.createUser(params.username,params.password1)
    	
    	// if a member was selected in the previous form then get them, otherwise set a null value
    	Member member
    	if (params?.memberid > 0){
    		member = memberService.getMember(params.memberid as int)
    	}
    	
    	// create a new RegistrationRequest
    	RegistrationRequest.create(params.email,user,member)
    		
    	// let the user know their request has been received and redirect them to the login page
    	flash.message = "Your registration request has been recorded. You will be notified as soon as your request is approved or denied."
    	redirect(controller:"login")
    }
}
