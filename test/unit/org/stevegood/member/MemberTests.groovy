package org.stevegood.member

import grails.test.*

class MemberTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {
		def member = new Member(firstName:"Chester",lastName:"Tester",email:"chester@tester.com",dob:new Date(),gender:Gender.MALE)
		assertEquals "Chester Tester",member.toString()
    }
}
