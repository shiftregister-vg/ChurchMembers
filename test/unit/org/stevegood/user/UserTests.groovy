package org.stevegood.user

import grails.test.*

class UserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {
		def user = new User(username:"chester",password:"tester",enabled:true)
		assertEquals("chester",user.toString())
    }
}
