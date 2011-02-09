
<%@ page import="org.stevegood.member.Member" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        
		<div id="content">
			
			<div class="post">
				<h2>${ member }</h2>
				<g:render template="/member/details" />
			</div>
			
		</div>
		
		<div id="sidebar">
			<ul>
				<li>
					<h2>Member Actions</h2>
					<div class="buttons">
		                <g:form>
		                    <g:hiddenField name="id" value="${member?.id}" />
		                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
		                    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPER_USER">
								<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
							</sec:ifAnyGranted>
		                </g:form>
		            </div>
				</li>
			</ul>
		</div>

        
    </body>
</html>
