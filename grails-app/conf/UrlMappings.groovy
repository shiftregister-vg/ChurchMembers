class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"/admin/editUser/$username"(controller:'admin',action:'editUser')
		"/register"(controller:'registration',action:'register')
		"500"(view:'/error')
	}
}
