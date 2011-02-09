package org.stevegood.member

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import org.compass.core.engine.SearchEngineQueryParseException
import org.stevegood.user.UserMember
import org.stevegood.user.UserService

@Secured(['IS_AUTHENTICATED_FULLY'])
class MemberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def memberService
	def searchableService
    def springSecurityService
	def userService
    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
    	params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
        	memberInstanceList: Member.list(params),
        	memberInstanceTotal: Member.count()
        ]
    }

    def create = {
        def memberInstance = new Member()
        memberInstance.properties = params
        return [memberInstance: memberInstance]
    }

    def save = {
        def memberInstance = new Member(params)
        if (memberService.saveMember(memberInstance)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'member.label', default: memberInstance.toString())])}"
            redirect(action: "show", id: memberInstance.id)
        }
        else {
            render(view: "create", model: [memberInstance: memberInstance])
        }
    }

    def show = {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
            [member: memberInstance]
        }
    }

    def edit = {
        def memberInstance = memberService.getMember(params.id as int)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [memberInstance: memberInstance]
        }
    }

    def update = {
        def memberInstance = memberService.getMember(params.id as int)
        if (memberInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (memberInstance.version > version) {
                    
                    memberInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'member.label', default: 'Member')] as Object[], "Another user has updated this Member while you were editing")
                    render(view: "edit", model: [memberInstance: memberInstance])
                    return
                }
            }
            memberInstance.properties = params
            if (!memberInstance.hasErrors() && memberService.saveMember(memberInstance)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'member.label', default: memberInstance.toString())])}"
                redirect(action: "show", id: memberInstance.id)
            }
            else {
                render(view: "edit", model: [memberInstance: memberInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
    }
	
	@Secured(['ROLE_ADMIN','ROLE_SUPER_USER'])
    def delete = {
        def memberInstance = memberService.getMember(params.id as int)
        if (memberInstance) {
            try {
                memberService.saveMember(memberInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def editLinked = {
    	def member = memberService.getMember(springSecurityService.getCurrentUser())
    	if (!member){
    		flash.message = "No linked member for this account"
    		redirect(controller:"user",action:"show")
    		return
    	}
    	
    	[member:member,memberList:memberService.getEligableSpouses(member),childrenList:memberService.getValidChildren(member),parentsList:memberService.getValidParents(member)]
    }
	
	def saveLinked = {
		def member
		println params
		if (params?.id){
			member = Member.get(UserMember.findByUser(springSecurityService.getCurrentUser()).member.id)
			member.properties = params
			memberService.saveMember(member)
		} else {
			member = memberService.saveMember(new Member(params))
			userService.removeMemberFromUser(springSecurityService.getCurrentUser())
			userService.addMemberToUser(springSecurityService.getCurrentUser(),member)
		}
		
		memberService.removeSpouseFromMember(member)
		def spouse = Member.get(params?.spouse)
		if (spouse){
			memberService.addSpouseToMember(member,spouse)
		}
		
		memberService.removeAllChildrenFromMember(member)
		params?.children?.each { childid ->
			def child = Member.get(childid)
			if (child){
				memberService.addChildToParent(member,child)
			}
		}
		
		memberService.removeAllParentsFromMember(member)
		params?.parents?.each { parentid ->
			def parent = Member.get(parentid)
			if (parent){
				memberService.addChildToParent(parent,member)
			}
		}
		
		flash.message = "$member saved!"
		redirect(action:'editLinked')
	}
	
	def createLinked = {
		
	}
	
	def search = {
		if (!params.q?.trim()) {
            return [:]
        }
        try {
			return [searchResult: searchableService.search(params.q, params)]
        } catch (SearchEngineQueryParseException ex) {
            return [parseException: true]
        }
	}
}
