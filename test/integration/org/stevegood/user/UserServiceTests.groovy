package org.stevegood.user

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

    void testCreatePassword() {
		
		def encodedPassword = springSecurityService.encodePassword("test")
		assertEquals encodedPassword, userService.createPassword("test")
		
    }
}
