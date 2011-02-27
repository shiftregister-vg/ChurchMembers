package org.stevegood.member

import grails.test.*
import org.stevegood.user.UserService

class MemberServiceTests extends GroovyTestCase {
    
	def memberService
	def userService
	
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
	
	void testGetAvailableMembers(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		assert memberService.getAvailableMembers().contains(member)
		
		def user = userService.createUser("chester","password")
		assert user
		
		userService.addMemberToUser(user,member)
		
		assertFalse memberService.getAvailableMembers().contains(member)
		
		userService.removeMemberFromUser(user)
		userService.deleteUser(user)
		memberService.deleteMember(member)
	}
	
	void testGetEligableSpouses(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def spouse = memberService.createMember("Hester","Tester",new Date().parse('yyyy-MM-dd','1981-01-01'),"hester@tester.com",Gender.FEMALE)
		assert spouse
		
		def parent = memberService.createMember("Nester","Tester",new Date().parse('yyyy-MM-dd','1934-01-01'),"nester@tester.com",Gender.MALE)
		assert parent
		
		def child = memberService.createMember("Lester","Tester",new Date(),"lester@tester.com",Gender.MALE)
		assert child
		
		def unRelatedMember = memberService.createMember("Foo","Bar",new Date(),"foo@bar.com",Gender.MALE)
		assert unRelatedMember
		
		memberService.addSpouseToMember(member,spouse)
		memberService.addChildToParent(member,child)
		memberService.addChildToParent(spouse,child)
		memberService.addChildToParent(parent,member)
		
		def eligableSpouses = memberService.getEligableSpouses(member)
		
		assert eligableSpouses.contains(unRelatedMember)
		assert eligableSpouses.contains(spouse)
		assertFalse eligableSpouses.contains(member)
		assertFalse eligableSpouses.contains(parent)
		assertFalse eligableSpouses.contains(child)
		
		memberService.removeSpouseFromMember(member)
		memberService.removeAllChildrenFromMember(member)
		memberService.removeAllChildrenFromMember(spouse)
		memberService.removeAllChildrenFromMember(parent)
		
		member.delete()
		spouse.delete()
		parent.delete()
		child.delete()
		unRelatedMember.delete()
	}
	
	void testGetMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		assertEquals member, memberService.getMember(member.id as int)
		
		def user = userService.createUser("chester","password")
		assert user
		
		userService.addMemberToUser(user,member)
		
		assertEquals member,memberService.getMember(user)
		
		userService.removeMemberFromUser(user)
		userService.deleteUser(user)
		memberService.deleteMember(member)
	}
	
	void testGetNewestMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		assertEquals member, memberService.getNewestMember()
		
		def newMember = memberService.createMember("Hester","Tester",new Date().parse('yyyy-MM-dd','1981-12-01'),"hester@tester.com",Gender.FEMALE)
		assert newMember
		
		assertEquals newMember,memberService.getNewestMember()
		assert member != memberService.getNewestMember()
		
		memberService.deleteMember(member)
		memberService.deleteMember(newMember)
	}
	
	void testGetValidChildren(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def sibling = memberService.createMember("Hank","Tester",new Date().parse('yyyy-MM-dd','1987-12-24'),"hank@tester.com",Gender.MALE)
		assert sibling
		
		def spouse = memberService.createMember("Hester","Tester",new Date().parse('yyyy-MM-dd','1981-01-01'),"hester@tester.com",Gender.FEMALE)
		assert spouse
		
		def parent = memberService.createMember("Nester","Tester",new Date().parse('yyyy-MM-dd','1934-01-01'),"nester@tester.com",Gender.MALE)
		assert parent
		
		def child = memberService.createMember("Lester","Tester",new Date(),"lester@tester.com",Gender.MALE)
		assert child
		
		memberService.addSpouseToMember(member,spouse)
		memberService.addChildToParent(parent,member)
		memberService.addChildToParent(parent,sibling)
		
		def validChildren = memberService.getValidChildren(member)
		
		assert validChildren.contains(child)
		assertFalse validChildren.contains(spouse)
		assertFalse validChildren.contains(sibling)
		assertFalse validChildren.contains(parent)
		assertFalse validChildren.contains(member)
		
		memberService.removeSpouseFromMember(member)
		memberService.removeAllChildrenFromMember(member)
		memberService.removeAllChildrenFromMember(spouse)
		memberService.removeAllChildrenFromMember(parent)
		
		member.delete()
		spouse.delete()
		parent.delete()
		child.delete()
		sibling.delete()
	}
	
	void testGetValidParents(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def sibling = memberService.createMember("Hank","Tester",new Date().parse('yyyy-MM-dd','1987-12-24'),"hank@tester.com",Gender.MALE)
		assert sibling
		
		def spouse = memberService.createMember("Hester","Tester",new Date().parse('yyyy-MM-dd','1981-01-01'),"hester@tester.com",Gender.FEMALE)
		assert spouse
		
		def parent = memberService.createMember("Nester","Tester",new Date().parse('yyyy-MM-dd','1934-01-01'),"nester@tester.com",Gender.MALE)
		assert parent
		
		def child = memberService.createMember("Lester","Tester",new Date(),"lester@tester.com",Gender.MALE)
		assert child
		
		memberService.addSpouseToMember(member,spouse)
		memberService.addChildToParent(member,child)
		memberService.addChildToParent(spouse,child)
		memberService.addChildToParent(parent,sibling)
		
		def validParents = memberService.getValidParents(member)
		
		assert validParents.contains(parent)
		assertFalse validParents.contains(spouse)
		assertFalse validParents.contains(member)
		assertFalse validParents.contains(child)
		assertFalse validParents.contains(sibling)
		
		memberService.removeSpouseFromMember(member)
		memberService.removeAllChildrenFromMember(member)
		memberService.removeAllChildrenFromMember(spouse)
		memberService.removeAllChildrenFromMember(parent)
		
		memberService.deleteMember(member)
		memberService.deleteMember(spouse)
		memberService.deleteMember(parent)
		memberService.deleteMember(child)
		memberService.deleteMember(sibling)
	}
	
	void testRemoveAddressFromMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def address = memberService.createAddress("Test Address","123 Maple","","Heresville","TX","76000")
		assert address
		
		member.addToAddresses(address)
		memberService.saveMember(member)
		assert member.addresses.contains(address)
		
		memberService.removeAddressFromMember(member,address)
		assertFalse member.addresses.contains(address)
		
		memberService.deleteMember(member)
	}
	
	void testRemovePhoneFromMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def phone = memberService.createPhone("Test Phone",987,654,3210,"")
		assert phone
		
		member.addToPhones(phone)
		memberService.saveMember(member)
		assert member.phones.contains(phone)
		
		memberService.removePhoneFromMember(member,phone)
		assertFalse member.phones.contains(phone)
		
		memberService.deleteMember(member)
	}
	
	void testRemoveAllChildrenFromMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def sibling = memberService.createMember("Hank","Tester",new Date().parse('yyyy-MM-dd','1987-12-24'),"hank@tester.com",Gender.MALE)
		assert sibling
		
		def parent = memberService.createMember("Nester","Tester",new Date().parse('yyyy-MM-dd','1934-01-01'),"nester@tester.com",Gender.MALE)
		assert parent
		
		memberService.addChildToParent(parent,member)
		memberService.addChildToParent(parent,sibling)
		
		assertEquals 2,parent.children().size()
		
		memberService.removeAllChildrenFromMember(parent)
		
		assertEquals 0,parent.children().size()
		
		memberService.deleteMember(member)
		memberService.deleteMember(parent)
		memberService.deleteMember(sibling)
	}
	
	void testRemoveAllParentsFromMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def mother = memberService.createMember("Delores","Tester",new Date().parse('yyyy-MM-dd','1987-12-24'),"delores@tester.com",Gender.FEMALE)
		assert mother
		
		def father = memberService.createMember("Nester","Tester",new Date().parse('yyyy-MM-dd','1934-01-01'),"nester@tester.com",Gender.MALE)
		assert father
		
		memberService.addChildToParent(mother,member)
		memberService.addChildToParent(father,member)
		
		assertEquals 2,member.parents().size()
		
		memberService.removeAllParentsFromMember(member)
		
		assertEquals 0,member.parents().size()
		
		memberService.deleteMember(member)
		memberService.deleteMember(father)
		memberService.deleteMember(mother)
	}
	
	void testRemoveChildFromParent(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def child = memberService.createMember("Lester","Tester",new Date(),"lester@tester.com",Gender.MALE)
		assert child
		
		memberService.addChildToParent(member,child)
		assert member.children().contains(child)
		
		memberService.removeChildFromParent(member,child)
		assertFalse member.children().contains(child)
		
		memberService.deleteMember(member)
		memberService.deleteMember(child)
	}
	
	void testRemoveSpouseFromMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		def spouse = memberService.createMember("Hester","Tester",new Date().parse('yyyy-MM-dd','1981-01-01'),"hester@tester.com",Gender.FEMALE)
		assert spouse
		
		memberService.addSpouseToMember(member,spouse)
		assertEquals spouse, member.spouse()
		assertEquals member, spouse.spouse()
		
		memberService.removeSpouseFromMember(member)
		assertNull member.spouse()
		assertNull spouse.spouse()
		
		memberService.deleteMember(member)
		memberService.deleteMember(spouse)
	}
	
	void testSaveAddress(){
		def address = memberService.createAddress("Test Address","123 Maple","","Heresville","TX","76000")
		assert address
		
		address.street2 = "Apt 123"
		
		assert memberService.saveAddress(address)
		
		memberService.deleteAddress(address)
	}
	
	void testSaveMember(){
		def member = memberService.createMember("Chester","Tester",new Date().parse('yyyy-MM-dd','1979-12-25'),"chester@tester.com",Gender.MALE)
		assert member
		
		member.email = "chester2@tester.com"
		
		assert memberService.saveMember(member)
		
		memberService.deleteMember(member)
	}
	
	void testSavePhone(){
		def phone = memberService.createPhone("Test Phone",987,654,3210,"")
		assert phone
		
		phone.extension = "x123"
		
		assert memberService.savePhone(phone)
		
		memberService.deletePhone(phone)
	}
}
