<html>
<head>
  <title>Welcome!</title>
  <meta name="layout" content="main" />
</head>
<body>
  <!-- start content -->
		<div id="content">
			<div class="post">
				<h2 class="title">Welcome to Grails!</h2>
				<p class="byline"><small>The Search is Over</small></p>
				<div class="entry">
				  <p>Congratulations, you have successfully started your first Grails application! At the moment
            this is the default page, feel free to modify it to either redirect to a controller or display whatever
            content you may choose. Below is a list of controllers that are currently deployed in this application,
            click on each to execute its default action:</p>
				</div>
        <p class="links"><!--a href="#" class="more">Read More</a> &nbsp;&nbsp;&nbsp; <a href="#" class="comments">No Comments</a--></p>
			</div>
			<div class="post">
				<h2 class="title">Available Controllers</h2>
				<p class="byline"><small>Who doesn't love control?</small></p>
				<div class="entry">
          <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
              <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
            </g:each>
          </ul>
				</div>
        <p class="links"><!--a href="#" class="more">Read More</a> &nbsp;&nbsp;&nbsp; <a href="#" class="comments">No Comments</a--></p>
			</div>
		</div>
		<!-- end content -->
		<!-- start sidebar -->
		<div id="sidebar">
			<ul>
				<li>
					<h2>Application Status</h2>
					<ul>
						<li>App version: <g:meta name="app.version"></g:meta></li>
            <li>Grails version: <g:meta name="app.grails.version"></g:meta></li>
            <li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
            <li>JVM version: ${System.getProperty('java.version')}</li>
            <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
            <li>Domains: ${grailsApplication.domainClasses.size()}</li>
            <li>Services: ${grailsApplication.serviceClasses.size()}</li>
            <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
					</ul>
				</li>
				<li>
					<h2>Installed Plugins</h2>
					<ul>
						<g:set var="pluginManager"
                   value="${applicationContext.getBean('pluginManager')}"></g:set>

              <g:each var="plugin" in="${pluginManager.allPlugins}">
                <li>${plugin.name} - ${plugin.version}</li>
              </g:each>
					</ul>
				</li>
			</ul>
		</div>
		<!-- end sidebar -->
</body>