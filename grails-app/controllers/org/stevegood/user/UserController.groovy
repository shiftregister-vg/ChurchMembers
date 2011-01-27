package org.stevegood.user

import grails.plugins.springsecurity.Secured

class UserController {
    
    def userService
    
    def index = {        
        redirect(action:"show")
    }
    
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show = {
        [user:getAuthenticatedUser()]
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
    def changePassword = {
        [user:getAuthenticatedUser()]
    }
    
}
