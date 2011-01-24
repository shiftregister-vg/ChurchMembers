import grails.util.Environment
import org.stevegood.user.Role
import org.stevegood.user.UserService

class BootStrap {
    
    def userService
    
    def init = { servletContext ->
        ["ROLE_ADMIN"].each { userService.createRole(it) }
        
        if (Environment.current == Environment.DEVELOPMENT){
            userService.addRoleToUser(userService.createUser("stevegood","password"),Role.findByAuthority("ROLE_ADMIN"))
            
        }
    }
    def destroy = {
    }
}
