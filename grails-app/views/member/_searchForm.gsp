<g:form url='[controller: "member", action: "search"]' id="searchableForm" name="searchableForm" method="get">
	<ul>
		<li>
			<g:textField name="q" value="${ params?.q }" style="height:20px;width:325px;" /> <input type="image" src="${ resource(dir:'images',file:'search.gif') }" width="25" height="25" alt="Member Search" style="vertical-align:middle;" /><br/>
			<small>Use <strong style="color:#FF0000;">*</strong> as a wildcard</small>
		</li>
	</ul>
</g:form>