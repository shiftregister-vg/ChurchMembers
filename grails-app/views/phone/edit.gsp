

<%@ page import="org.stevegood.Phone" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'phone.label', default: 'Phone')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${phoneInstance}">
            <div class="errors">
                <g:renderErrors bean="${phoneInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${phoneInstance?.id}" />
                <g:hiddenField name="version" value="${phoneInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="label"><g:message code="phone.label.label" default="Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'label', 'errors')}">
                                    <g:textField name="label" value="${phoneInstance?.label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="extension"><g:message code="phone.extension.label" default="Extension" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'extension', 'errors')}">
                                    <g:textField name="extension" value="${phoneInstance?.extension}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="members"><g:message code="phone.members.label" default="Members" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'members', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="npa"><g:message code="phone.npa.label" default="Npa" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'npa', 'errors')}">
                                    <g:textField name="npa" value="${fieldValue(bean: phoneInstance, field: 'npa')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nxx"><g:message code="phone.nxx.label" default="Nxx" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'nxx', 'errors')}">
                                    <g:textField name="nxx" value="${fieldValue(bean: phoneInstance, field: 'nxx')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nxxx"><g:message code="phone.nxxx.label" default="Nxxx" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: phoneInstance, field: 'nxxx', 'errors')}">
                                    <g:textField name="nxxx" value="${fieldValue(bean: phoneInstance, field: 'nxxx')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
