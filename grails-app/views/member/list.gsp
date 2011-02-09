
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
	                        
	                            <g:sortableColumn property="gender" title="${message(code: 'member.gender.label', default: 'Gender')}" />
	                        
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${memberInstanceList}" status="i" var="memberInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "firstName")}</g:link></td>
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "lastName")}</g:link></td>
	                        
	                            <td><g:link action="show" id="${ memberInstance.id }">${fieldValue(bean: memberInstance, field: "email")}</g:link></td>
	                        
	                            <td>${fieldValue(bean: memberInstance, field: "gender")}</td>
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
        			<h2>Member Stats</h2>
        			<ul>
        				<li>Total Members: ${ memberInstanceTotal }</li>
        			</ul>
        		</li>
        		<li>
        			<g:set var="colors" value="${['000000','333333','999999','CCCCCC','00FF00','0000FF','FF0000']}" />
        			<g:set var="chartSize" value="${[225,130]}" />
        			
        			<g:pieChart
        				title="Gender"
        				size="${chartSize}"
        				labels="${['Male (' + maleCount + ')','Female (' + femaleCount + ')']}"
        				colors="${colors}"
        				data="${[maleCount,femaleCount]}"
        				dataType="simple" />
        		</li>
        		<li>
        			<g:pieChart
        				title="Ages"
        				size="${chartSize}"
        				labels="${['Minors (' + minorCount + ')','Adults (' + adultCount + ')' ,'Seniors (' + overFiftyCount + ')']}"
        				colors="${colors}"
        				data="${[minorCount,adultCount,overFiftyCount]}"
        				dataType="simple" />
        		</li>
        	</ul>
        </div>
    </body>
</html>
