import grails.util.Environment

import org.compass.core.engine.SearchEngineQueryParseException
import org.stevegood.member.Gender
import org.stevegood.member.MemberService
import org.stevegood.user.Role
import org.stevegood.user.User
import org.stevegood.user.UserService

class BootStrap {
    
	def memberService
	def searchableService
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
            
//            def chester = memberService.createMember("Chester","Tester",new Date().parse("MM/dd/yyyy","01/01/1979"),"chester@tester.com",Gender.MALE)
//			def hester = memberService.createMember("Hester","Tester",new Date().parse("MM/dd/yyyy","01/01/1981"),"hester@tester.com",Gender.FEMALE)
//			memberService.addSpouseToMember(chester,hester)
//            def lester = memberService.createMember("Lester","Tester",new Date().parse("MM/dd/yyyy","01/01/2005"),"lester@tester.com",Gender.MALE)
//			memberService.addChildToParent(chester,lester)
//			memberService.addChildToParent(hester,lester)

        }
		
		searchableService.rebuildSpellingSuggestions()
    }
    def destroy = {
    }
}
