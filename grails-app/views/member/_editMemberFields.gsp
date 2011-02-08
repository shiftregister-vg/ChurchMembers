<ul>
	<li>
		<label for="firstName">First Name</label>
		<g:textField name="firstName" value="${ member?.firstName }" />
	</li>
	<li>
		<label for="lastName">Last Name</label>
		<g:textField name="lastName" value="${ member?.lastName }" />
	</li>
	<li>
		<label for="email">Email</label>
		<g:textField name="email" value="${ member?.email }" />
	</li>
	<li>
		<label for="gender"><g:message code="member.gender.label" default="Gender" /></label>
		<g:select name="gender" from="${org.stevegood.member.Gender?.values()}" keys="${org.stevegood.member.Gender?.values()*.name()}" value="${member?.gender?.name()}"  />
	</li>
	<li>
		<label for="dob">Date of Birth</label>
		<g:datePicker name="dob" value="${ member?.dob }" precision="day" />
	</li>
</ul>
<g:if test="${ member }">
<ul>
	<li>
		<label for="spouse">Spouse</label>
		<select id="spouse" name="spouse">
			<option value="0">No Spouse</option>
			<g:set var="spouse" value="${ member?.spouse() }" />
			<g:each in="${ memberList }" var="mSpouse">
				<option value="${ mSpouse.id }" ${ (mSpouse == spouse) ? 'selected=selected' : '' } >${ mSpouse }</option>
			</g:each>
		</select>
	</li>
	<li>
		<label for="children">Children</label>
		<select name="children" id="children" multiple="true">
			<g:each in="${ childrenList }" var="child">
				<option value="${ child.id }" ${ child.id in member?.children()*.id ? 'selected=selected' : '' } >${ child }</option>
			</g:each>
		</select>
	</li>
	<li>
		<label for="parents">Parents</label>
		<select name="parents" id="parents" multiple="true">
			<g:each in="${ parentsList }" var="parent">
				<option value="${ parent.id }" ${ parent.id in member?.parents()*.id ? 'selected=selected' : '' } >${ parent }</option>
			</g:each>
		</select>
	</li>
</ul>
</g:if>
