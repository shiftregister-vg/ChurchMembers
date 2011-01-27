import grails.util.Environment
import org.stevegood.user.Role
import org.stevegood.user.UserService

class BootStrap {
    
    def userService
    
    def init = { servletContext ->
        [
			[authority:"ROLE_ADMIN",label:"Admin"],
			[authority:"ROLE_SUPER_USER",label:"Super User"]
		].each { userService.createRole(it.label,it.authority) }
        
        if (Environment.current == Environment.DEVELOPMENT){
            def user = userService.createUser("stevegood","password")
			userService.addRoleToUser(user,Role.findByAuthority("ROLE_ADMIN"))
            userService.addRoleToUser(user,Role.findByAuthority("ROLE_SUPER_USER"))
        }
    }
    def destroy = {
    }
}
