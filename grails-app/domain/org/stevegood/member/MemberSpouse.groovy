package org.stevegood.member

class MemberSpouse {
    
    Member member
    Member spouse
    
    static MemberSpouse create(Member member, Member spouse) {
        def memberSpouse = findByMemberAndSpouse(member,spouse)
        if (!memberSpouse){
            memberSpouse = findByMemberAndSpouse(spouse,member)
        }
        
        if (!memberSpouse){
            memberSpouse = new MemberSpouse(member:member,spouse:spouse).save(flush:true,insert:true)
        }
        memberSpouse
    }
}
