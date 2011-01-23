package org.stevegood.member

import org.stevegood.Address
import org.stevegood.Phone
import org.stevegood.user.UserMember

class MemberService {

    static transactional = true
    
    def addChildToParent(Member parent, Member child){
        ParentChild.create(parent,member)
    }
    
    def addSpouseToMember(Member member, Member spouse){
        MemberSpouse(member,spouse)
    }
    
    def createAddress(String label, String street1, String street2 = "", String city, String state, String zip){
        Address.findWhere(street1:street1,street2:street2,city:city,state:state,zip:zip) ?: saveAddress(new Address(street1:street1,street2:street2,city:city,state:state,zip:zip))
    }
    
    def createMember(String firstName, String lastName, Date dob, String email) {
        Member.findByFirstNameAndLastNameAndDob(firstName,lastName,dob) ?: saveMember(new Member(firstName:firstName,lastName:lastName,dob:dob,email:email))
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
    
    def removeAddressFromMember(Member member, Address address){
        address.removeFromMembers(member)
        if (!address.members.length) deleteAddress(address)
    }
    
    def removePhoneFromMember(Member member, Phone phone){
        phone.removeFromMembers(member)
        if (!phone.members.length) deletePhone(phone)
    }
    
    def removeChildFromParent(Member parent,Member child){
        ParentChild.findByParentAndChild(parent,child)?.delete(flush:true)
    }
    
    def removeSpouseFromMember(Member member, Member spouse){
        def memberSpouse = MemberSpouse.findByMemberAndSpouse(member,spouse) ?: MemberSpouse.findByMemberAndSpouse(spouse,member)
        memberSpouse.delete(flush:true)
    }
    
    def saveAddress(Address address){
        address.save()
    }
    
    def saveMember(Member member){
        member.save()
    }
    
    def savePhone(Phone phone){
        phone.save()
    }
    
}
