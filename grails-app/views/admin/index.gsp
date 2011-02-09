<html>
	<head>
		<title>Admin</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Statistics</h2>
				<ul>
					<li><strong><g:link controller="member" action="show" id="${ newestMember.id }">${ newestMember }</g:link></strong> is the newest member</li>
					<li>Managing <strong>${ memberCount }</strong> member's data</li>
					<li><strong><g:link action="editUser" params="[username:newestUser]">${ newestUser }</g:link></strong> is the newest user</li>
					<li><strong>${ userCount }</strong> users registered</li>
				</ul>
				<g:set var="colors" value="009900|333333|000099|999999|990000|CCCCCC" />
    			<g:set var="chartSize" value="${[275,150]}" />
    			<div>
    				<g:pieChart
	    				title="Gender"
	    				size="${chartSize}"
	    				labels="${['Male (' + maleCount + ')','Female (' + femaleCount + ')']}"
	    				colors="${colors}"
	    				data="${[maleCount,femaleCount]}"
	    				dataType="simple" />
				</div>
				<div>
					<g:pieChart
	       				title="Generations"
	       				size="${chartSize}"
	       				labels="${['Minors (' + minorCount + ')','Adults (' + adultCount + ')' ,'Seniors (' + overFiftyCount + ')']}"
	       				colors="${colors}"
	       				data="${[minorCount,adultCount,overFiftyCount]}"
	       				dataType="simple" />
				</div>
			</div>
			
			<g:if test="${ pendingRegistrationRequests }">
				<div class="post">
					<h2>${ pendingRegistrationRequestsCount } Pending Registration Requests</h2>
					<ul>
						<g:each in="${ pendingRegistrationRequests }" var="registrationRequest">
							<li><g:link controller="registration" action="processRequest" id="${ registrationRequest.id }">${ registrationRequest.user }</g:link></li>
						</g:each>
					</ul>
				</div>
			</g:if>
			
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>		
	</body>
</html>