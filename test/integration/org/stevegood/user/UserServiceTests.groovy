package org.stevegood.user

import org.stevegood.member.Gender
import org.stevegood.member.Member
import org.stevegood.registration.RegistrationRequest

import grails.test.*
import grails.plugins.springsecurity.SpringSecurityService;

class UserServiceTests extends GroovyTestCase {
    def userService
	def springSecurityService
	
	protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testAddMemberToUser(){
		def member = new Member(firstName:"Chester",lastName:"Tester",dob:new Date(),email:"chester@tester.com",gender:Gender.MALE).save()
		assert member
		
		def user = new User(username:"chestertester",password:springSecurityService.encodePassword("password"),enabled:true).save()
		assert user
		
		def userMember = userService.addMemberToUser(user,member)
		assert userMember
		
		assertEquals userMember.user, user
		assertEquals userMember.member, member
		
		userMember.delete()
		member.delete()
		user.delete()
	}
	
	void testAddRoleToUser(){
		def user = new User(username:"chestertester",password:springSecurityService.encodePassword("password"),enabled:true).save(failOnError:true)
		assert user
		
		def role = Role.findByAuthority("ROLE_TEST_USER") ?: new Role(authority:"ROLE_TEST_USER",label:"Test User").save(failOnError:true)
		assert role
		
		def userRole = userService.addRoleToUser(user,role)
		assert userRole
		
		assertEquals user, userRole.user
		assertEquals role, userRole.role
		
		userRole.remove(user,role,true)
		
		userRole = userService.addRoleToUser(user,"ROLE_TEST_USER")
		assert userRole
		
		assertEquals user, userRole.user
		assertEquals role, userRole.role
		
		userRole.remove(user,role,true)
		
		user.delete()
		role.delete()
	}
	
	void testCreateRole(){
		def role = userService.createRole("Test User","ROLE_TEST_USER")
		assert role
		
		role.delete()
	}
	
	void testCreateUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		user.delete()
	}
	
    void testCreatePassword() {
		def encodedPassword = springSecurityService.encodePassword("test")
		assertEquals encodedPassword, userService.createPassword("test")
    }
	
	void testEnableDisableUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		assertFalse user.enabled
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		userService.enableDisableUser(user)
		assertFalse user.enabled
		
		user.delete()
	}
	
	void testDeleteUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		userService.deleteUser(user)
		assertNull User.findByUsername("chestertester")
	}
	
	void testGetNewestUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		assertFalse userService.getNewestUser().equals( user )
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		assertEquals userService.getNewestUser(), user
		
		userService.deleteUser(user)
	}
	
	void testGetActiveUserCount(){
		def userCount = userService.getActiveUserCount()
		def user = userService.createUser("chestertester","password")
		
		assertEquals userCount, userService.getActiveUserCount()
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		assertEquals userCount+1, userService.getActiveUserCount()
		
		userService.deleteUser(user)
		
		assertEquals userCount, userService.getActiveUserCount()
	}
	
	void testGetPendingRequests(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		def registrationRequest = RegistrationRequest.create("chester@tester.com",user,null)
		assert registrationRequest
		
		def pendingRegistrationRequests = userService.getPendingRegistrationRequests()
		assertNotNull pendingRegistrationRequests
		
		assertEquals 1,RegistrationRequest.countByApprovedAndRejected(false,false)
		
		registrationRequest.approved = true
		registrationRequest.save()
		
		assertEquals 0,RegistrationRequest.countByApprovedAndRejected(false,false)
		
		registrationRequest.delete(flush:true)
		userService.deleteUser(user)
	}
	
	void testGetUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		def user2 = userService.getUser(user.id as int)
		assert user2
		
		assertEquals user,user2
		
		user2 = userService.getUser("chestertester")
		assert user2
		
		assertEquals user,user2
		
		userService.deleteUser(user)
	}
	
	void testGetUserMember(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		def member = new Member(firstName:"Chester",lastName:"Tester",dob:new Date(),email:"chester@tester.com",gender:Gender.MALE).save()
		assert member
		
		def userMember = userService.addMemberToUser(user,member)
		assert userMember
		
		assertEquals userMember.member, userService.getUserMember(user)
		
		userMember.delete()
		userService.deleteUser(user)
		member.delete()
	}
	
	void testRemoveAllRolesFromUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		def role = userService.createRole("Test Role","ROLE_TEST")
		assert role
		
		assertEquals 0,UserRole.countByUser(user)
		
		userService.addRoleToUser(user,role)
		assertEquals 1,UserRole.countByUser(user)
		
		userService.removeAllRolesFromUser(user)
		assertEquals 0,UserRole.countByUser(user)
		
		userService.addRoleToUser(user,role)
		assertEquals 1,UserRole.countByUser(user)
		
		def testUserRole = userService.createRole("Test User","ROLE_TEST_USER")
		assert testUserRole
		
		userService.addRoleToUser(user,"ROLE_TEST_USER")
		assertEquals 2,UserRole.countByUser(user)
		
		userService.removeAllRolesFromUser(user)
		assertEquals 0,UserRole.countByUser(user)
		
		testUserRole.delete()
		role.delete()
		userService.deleteUser(user)
	}
	
	void testRemoveMemberFromUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		userService.enableDisableUser(user)
		assert user.enabled
		
		def member = new Member(firstName:"Chester",lastName:"Tester",dob:new Date(),email:"chester@tester.com",gender:Gender.MALE).save()
		assert member
		
		def userMember = userService.addMemberToUser(user,member)
		assert userMember
		
		assertEquals userMember.member, userService.getUserMember(user)
		
		userService.removeMemberFromUser(user)
		assertNull UserMember.findByUserAndMember(user,member)
		
		userService.deleteUser(user)
		member.delete()
	}
	
	void testSaveUser(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		assertNotNull userService.saveUser(user)
		
		userService.deleteUser(user)
	}
	
	void testUserExists(){
		def user = userService.createUser("chestertester","password")
		assert user
		
		assert userService.userExists("chestertester")
		assertFalse userService.userExists("thisusernameshouldneverexistinthesystem")
		
		userService.deleteUser(user)
	}
}
