class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/index")
		"/"(controller:'member',action:'list')
		"/appinfo"(view:"/index")
		"/admin/editUser/$username"(controller:'admin',action:'editUser')
		"/register"(controller:'registration',action:'register')
		"/profile"(controller:'user',action:'show')
		"/changePassword"(controller:'user',action:'changePassword')
		"500"(view:'/error')
	}
}
