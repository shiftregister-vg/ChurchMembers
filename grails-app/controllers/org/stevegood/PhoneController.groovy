package org.stevegood

class PhoneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [phoneInstanceList: Phone.list(params), phoneInstanceTotal: Phone.count()]
    }

    def create = {
        def phoneInstance = new Phone()
        phoneInstance.properties = params
        return [phoneInstance: phoneInstance]
    }

    def save = {
        def phoneInstance = new Phone(params)
        if (phoneInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'phone.label', default: 'Phone'), phoneInstance.id])}"
            redirect(action: "show", id: phoneInstance.id)
        }
        else {
            render(view: "create", model: [phoneInstance: phoneInstance])
        }
    }

    def show = {
        def phoneInstance = Phone.get(params.id)
        if (!phoneInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
            redirect(action: "list")
        }
        else {
            [phoneInstance: phoneInstance]
        }
    }

    def edit = {
        def phoneInstance = Phone.get(params.id)
        if (!phoneInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [phoneInstance: phoneInstance]
        }
    }

    def update = {
        def phoneInstance = Phone.get(params.id)
        if (phoneInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (phoneInstance.version > version) {
                    
                    phoneInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'phone.label', default: 'Phone')] as Object[], "Another user has updated this Phone while you were editing")
                    render(view: "edit", model: [phoneInstance: phoneInstance])
                    return
                }
            }
            phoneInstance.properties = params
            if (!phoneInstance.hasErrors() && phoneInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'phone.label', default: 'Phone'), phoneInstance.id])}"
                redirect(action: "show", id: phoneInstance.id)
            }
            else {
                render(view: "edit", model: [phoneInstance: phoneInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def phoneInstance = Phone.get(params.id)
        if (phoneInstance) {
            try {
                phoneInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phone.label', default: 'Phone'), params.id])}"
            redirect(action: "list")
        }
    }
}
