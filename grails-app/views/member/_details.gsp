<ul>
	<li>First Name: <strong>${ member.firstName }</strong></li>
	<li>Last Name: <strong>${ member.lastName }</strong></li>
	<li>Gender: <strong>${ member.gender }</strong></li>
	<li>Date of Birth: <strong><g:formatDate date="${ member.dob }" format="MMMM dd, yyyy" /></strong></li>
	<li>Email: <strong>${ member.email }</strong></li>
</ul>
<ul>
	<g:each in="${ member.phones }" var="phone">
		<li>${ phone }: <strong>${ phone.format() }</strong></li>
	</g:each>
	<g:each in="${ member.addresses }" var="address">
		<li>${ address }: <strong>${ address.format() }</strong></li>
	</g:each>
</ul>
<g:if test="${ member?.spouse() }">
	<h2>Spouse</h2>
	<ul><li><g:link controller="member" action="show" id="${ member?.spouse()?.id }">${ member?.spouse() }</g:link></li></ul>
</g:if>

<g:if test="${ member?.children() }">
	<h2>Children</h2>
	<ul>
		<g:each in="${ member?.children() }" var="child">
			<li><g:link controller="member" action="show" id="${ child.id }">${ child }</g:link></li>
		</g:each>
	</ul>
</g:if>

<g:if test="${ member?.parents() }">
	<h2>Parents</h2>
	<ul>
		<g:each in="${ member?.parents() }" var="parent">
			<li><g:link controller="member" action="show" id="${ parent.id }">${ parent }</g:link></li>
		</g:each>
	</ul>
</g:if>