package org.stevegood

import org.stevegood.member.Member

class Phone {
    
    int npa
    int nxx
    int nxxx
    String extension
    String label
    
    static belongsTo = [Member]
    static hasMany = [members:Member]
    
    static constraints = {
        label blank:false
        npa
        nxx
        nxxx
        extension blank:true, nullable:true
    }
    
    String toString(){
        label
    }
    
    String format(){
        "($npa) $nxx-$nxxx ${ extension ?: '' }"
    }
}
