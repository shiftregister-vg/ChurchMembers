package org.stevegood.member

import grails.test.*

class MemberServiceTests extends GroovyTestCase {
    
	def memberService
	
	protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAddChildToMember() {
		def member = new Member(firstName:"Chester",lastName:"Tester",dob:new Date().parse("MM/dd/yyyy","01/01/1979"),email:"chester@tester.com",gender:Gender.MALE).save(failOnError:true)
		assert member
		
		def child = new Member(firstName:"Lester",lastName:"Tester",dob:new Date(),gender:Gender.MALE).save(failOnError:true)
		assert child
		
		def parentChild = memberService.addChildToParent(member,child)
		assert parentChild
		
		assertEquals parentChild.parent, member
		assertEquals parentChild.child, child
		
		parentChild.delete()
		member.delete()
		child.delete()
    }
	
	void testAddSpouseToMember(){
		def member = new Member(firstName:"Chester",lastName:"Tester",dob:new Date().parse("MM/dd/yyyy","01/01/1979"),email:"chester@tester.com",gender:Gender.MALE).save(failOnError:true)
		assert member
		
		def spouse = new Member(firstName:"Hester",lastName:"Tester",dob:new Date().parse("MM/dd/yyyy","01/01/1982"),email:"hester@tester.com",gender:Gender.FEMALE).save(failOnError:true)
		assert spouse
		
		def memberSpouse = memberService.addSpouseToMember(member,spouse)
		assert memberSpouse
		
		assertEquals memberSpouse.member, member
		assertEquals memberSpouse.spouse, spouse
		
		memberSpouse.delete()
		spouse.delete()
		member.delete()
	}
	
	void testCreateAddress(){
		def address = memberService.createAddress("Test Address","123 Maple","","Heresville","TX","76000")
		assert address
		
		assertEquals "Test Address",address.label
		assertEquals "123 Maple",address.street1
		assertNull address.street2
		assertEquals "Heresville",address.city
		assertEquals "TX",address.state
		assertEquals "76000",address.zip
		
		address.street2 = "Apt 123"
		address.save()
		
		assertEquals "Apt 123", address.street2
		
		address.delete()
	}
	
	void testCreateMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		assertEquals "Chester",member.firstName
		assertEquals "Tester",member.lastName
		
		member.delete()
	}
	
	void testCreatePhone(){
		def phone = memberService.createPhone("Test Phone",987,654,3210,"")
		assert phone
		
		assertEquals "Test Phone",phone.label
		assertEquals 987,phone.npa
		assertEquals 654,phone.nxx
		assertEquals 3210,phone.nxxx
		
		assertNull phone.extension
		
		phone.extension = "x741"
		phone.save()
		
		assertEquals "x741",phone.extension
		
		phone.delete()
	}
	
	void testDeleteAddress(){
		def address = memberService.createAddress("Test Address","123 Maple","","Heresville","TX","76000")
		assert address
		
		memberService.deleteAddress(address)
	}
	
	void testDeleteMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		memberService.deleteMember(member)
	}
	
	void testDeletePhone(){
		def phone = memberService.createPhone("Test Phone",987,654,3210,"")
		assert phone
		
		memberService.deletePhone(phone)
	}
}
