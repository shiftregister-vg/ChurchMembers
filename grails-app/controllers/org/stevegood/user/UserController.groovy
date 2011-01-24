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
        if (params.password != params.password2){
            flash.message = "Passwords do not match. Please try again."            
            redirect(action:"changePassword")
        } else {            
            def user = getAuthenticatedUser()
            user.password = userService.createPassword(params.password)
            userService.saveUser(user)
            flash.message = "Password set!"
            redirect(action:"show")
        }
    }
    
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def changePassword = {
        [user:getAuthenticatedUser()]
    }
    
}
