class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"/admin/editUser/$username"(controller:'admin',action:'editUser')
		"500"(view:'/error')
	}
}
