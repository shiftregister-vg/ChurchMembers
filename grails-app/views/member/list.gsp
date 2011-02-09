
<%@ page import="org.stevegood.member.Member" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="content">
	       <div class="post">
        	 	<h2><g:message code="default.list.label" args="[entityName]" /></h2>
	            <div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                        
	                            <g:sortableColumn property="firstName" title="${message(code: 'member.firstName.label', default: 'First Name')}" />
	                        
	                            <g:sortableColumn property="lastName" title="${message(code: 'member.lastName.label', default: 'Last Name')}" />
	                        
	                            <g:sortableColumn property="email" title="${message(code: 'member.email.label', default: 'Email')}" />
	                        
	                            <g:sortableColumn property="dob" title="${message(code: 'member.dob.label', default: 'Date of Birth')}" />
	                        
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${memberInstanceList}" status="i" var="memberInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "firstName")}</g:link></td>
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "lastName")}</g:link></td>
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "email")}</g:link></td>
	                        
	                            <td><g:formatDate date="${memberInstance.dob}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	            </div>
	            <div class="paginateButtons">
	                <g:paginate total="${memberInstanceTotal}" />
	            </div>
	        </div>
        </div>
        
        <div id="sidebar">
        	<ul>
        		<li>
        			<h2>Search</h2>
					<ul><li>
						<g:form url='[controller: "member", action: "search"]' id="searchableForm" name="searchableForm" method="get">
							<g:textField name="q" style="height:20px;width:140px;" /> <input type="image" src="${ resource(dir:'images',file:'search.gif') }" width="25" height="25" alt="Member Search" style="vertical-align:middle;" />
						</g:form>
					</li></ul>
        		</li>
        	</ul>
        </div>
    </body>
</html>
