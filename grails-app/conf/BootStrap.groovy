import grails.util.Environment
import org.stevegood.member.MemberService
import org.stevegood.user.Role
import org.stevegood.user.UserService

class BootStrap {
    
	def memberService
    def userService
    
    def init = { servletContext ->
        [
			[authority:"ROLE_ADMIN",label:"Admin"],
			[authority:"ROLE_SUPER_USER",label:"Super User"]
		].each { userService.createRole(it.label,it.authority) }
        
        if (Environment.current == Environment.DEVELOPMENT || User.count() == 0){
            def user = userService.createUser("admin","admin")
            userService.enableDisableUser(user)
			userService.addRoleToUser(user,Role.findByAuthority("ROLE_ADMIN"))
            userService.addRoleToUser(user,Role.findByAuthority("ROLE_SUPER_USER"))
            
            def member = memberService.createMember("Chester","Tester",new Date().parse("MM/dd/yyyy","01/01/0001"),"chester@tester.com")
        }
    }
    def destroy = {
    }
}
