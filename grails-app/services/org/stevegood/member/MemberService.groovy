package org.stevegood.member

import org.stevegood.Address
import org.stevegood.Phone
import org.stevegood.user.User
import org.stevegood.user.UserMember

class MemberService {

    static transactional = true
    
    def addChildToParent(Member parent, Member child){
        ParentChild.create(parent,child)
    }
    
    def addSpouseToMember(Member member, Member spouse){
        MemberSpouse.create(member,spouse)
    }
    
    def createAddress(String label, String street1, String street2 = "", String city, String state, String zip){
        Address.findWhere(label:label,street1:street1,street2:street2,city:city,state:state,zip:zip) ?: saveAddress(new Address(label:label,street1:street1,street2:street2,city:city,state:state,zip:zip))
    }
    
    def createMember(String firstName, String lastName, Date dob, String email,Gender gender) {
		Member.findWhere(firstName:firstName,lastName:lastName,dob:dob,gender:gender) ?: saveMember(new Member(firstName:firstName,lastName:lastName,dob:dob,email:email,gender:gender))
    }
    
    def createPhone(String label, int npa, int nxx, int nxxx, String extension){
        Phone.findWhere(npa:npa,nxx:nxx,nxxx:nxxx,extenstion:extension) ?: savePhone(new Phone(label:label,npa:npa,nxx:nxx,nxxx:nxxx,extension:extension))
    }
    
    def deleteAddress(Address address){
        address.delete(flush:true)
    }
    
    def deleteMember(Member member){
        UserMember.findByMember(member)?.delete(flush:true)
        member.delete(flush:true)
    }
    
    def deletePhone(Phone phone){
        phone.delete(flush:true)
    }
    
	def getAvailableMembers(){
		Member.list() - UserMember.list()*.member
	}

    def getEligableSpouses(Member member){
		Member.findAll("from Member as m where m.id not in (:ids)",[ids:[member.id] + member.children()*.id + member.parents()*.id])
    }
    
    Member getMember(int id){
        Member.get(id)
    }
    
    Member getMember(User user){
    	UserMember.findByUser(user)?.member
    }
    
    Member getNewestMember(){
    	def criteria = Member.createCriteria()
    	int maxId = criteria.get{
    		projections{
    			max("id")
    		}
    	}
    	Member.get(maxId)
    }
    
	def getValidChildren(Member member){
		// valid children include anyone younger than the supplied member who is not the member's parent or spouse or a sibling
		def siblingids = []
		member.parents()?.each{ parent ->
			parent?.children()?.each{ child ->
				siblingids << child?.id
			}
		}
		Member.findAll("from Member as m where m.dob > :dob and m.id not in (:ids)",[dob:member.dob,ids:[member.id] + member.parents()*.id + siblingids + [member.spouse()?.id]])
	}
	
	def getValidParents(Member member){
		def siblingids = []
		member.parents()?.each{ parent ->
			parent?.children()?.each{ child ->
				siblingids << child?.id
			}
		}
		Member.findAll("from Member as m where m.dob < :dob and m.id not in (:ids)",[dob:member.dob,ids:[member.id] + member.children()*.id + siblingids + [member.spouse()?.id]])
	}

    def removeAddressFromMember(Member member, Address address){
        address.removeFromMembers(member)
        if (!address.members.length) deleteAddress(address)
    }
    
    def removePhoneFromMember(Member member, Phone phone){
        phone.removeFromMembers(member)
        if (!phone.members.length) deletePhone(phone)
    }
    
	def removeAllChildrenFromMember(Member member){
		ParentChild.findAllByParent(member)?.each{ it?.delete(flush:true) }
	}
	
	def removeAllParentsFromMember(Member member){
		ParentChild.findAllByChild(member)?.each{ it?.delete(flush:true) }
	}
	
    def removeChildFromParent(Member parent,Member child){
        ParentChild.findByParentAndChild(parent,child)?.delete(flush:true)
    }
    
    def removeSpouseFromMember(Member member){
        def memberSpouse = MemberSpouse.findByMember(member) ?: MemberSpouse.findBySpouse(member)
        memberSpouse?.delete(flush:true)
    }
    
    def saveAddress(Address address){
        address.save(failOnError:true)
    }
    
    def saveMember(Member member){
		member.save(failOnError:true)
    }
    
    def savePhone(Phone phone){
        phone.save(failOnError:true)
    }
    
}
