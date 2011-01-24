
<%@ page import="org.stevegood.Phone" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'phone.label', default: 'Phone')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'phone.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="label" title="${message(code: 'phone.label.label', default: 'Label')}" />
                        
                            <g:sortableColumn property="extension" title="${message(code: 'phone.extension.label', default: 'Extension')}" />
                        
                            <g:sortableColumn property="npa" title="${message(code: 'phone.npa.label', default: 'Npa')}" />
                        
                            <g:sortableColumn property="nxx" title="${message(code: 'phone.nxx.label', default: 'Nxx')}" />
                        
                            <g:sortableColumn property="nxxx" title="${message(code: 'phone.nxxx.label', default: 'Nxxx')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${phoneInstanceList}" status="i" var="phoneInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${phoneInstance.id}">${fieldValue(bean: phoneInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: phoneInstance, field: "label")}</td>
                        
                            <td>${fieldValue(bean: phoneInstance, field: "extension")}</td>
                        
                            <td>${fieldValue(bean: phoneInstance, field: "npa")}</td>
                        
                            <td>${fieldValue(bean: phoneInstance, field: "nxx")}</td>
                        
                            <td>${fieldValue(bean: phoneInstance, field: "nxxx")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${phoneInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
