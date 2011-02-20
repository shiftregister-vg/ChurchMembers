package org.stevegood

import grails.test.*

class PhoneTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFormat() {
		def phone = new Phone(label:"Test Phone",npa:817,nxx:555,nxxx:1234)
		def tValue = "(817) 555-1234"
		assertEquals(tValue,phone.format())
		
		phone.extension = "x567"
		tValue += " x567"
		assertEquals(tValue,phone.format())
    }
}
