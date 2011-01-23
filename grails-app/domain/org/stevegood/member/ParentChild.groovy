package org.stevegood.member

class ParentChild {
    
    Member parent
    Member child
    
    static ParentChild create(Member parent, Member child) {
        ParentChild.findByParentAndChild(parent,child) ?: new ParentChild(parent:parent,child:child).save(flush:true,insert:true)
    }
}
