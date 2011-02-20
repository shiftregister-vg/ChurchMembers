package org.stevegood

import grails.test.*

class AddressTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testToString(){
		def address = new Address(label:"Test Address",street1:"123 Maple",city:"Here",state:"TX",zip:"75000")
		assertEquals("Test Address",address.toString())
	}
	
    void testFormat() {
		def address = new Address(label:"Test Address",street1:"123 Maple",city:"Here",state:"TX",zip:"75000")
		def tValue = "123 Maple, Here, TX 75000"
		
		assertEquals(tValue,address.format())
		
		address.street2 = "Apt 1A"
		tValue = "123 Maple Apt 1A, Here, TX 75000"
		
		assertEquals(tValue,address.format())
    }
}
