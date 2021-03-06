Vue.component("unregistered-navbar", {

   template: `
    <nav class = "navbar navbar-expand-lg bg-dark navbar-dark py-3 fixed-top">
        <div class="container">
        <button
             class="navbar-toggler"
             type="button"
             data-bs-toggle="collapse"
             data-bs-target="#navmenu"
             >
             <span class="navbar-toggler-icon"></span>
             </button>

            <div class="collapse navbar-collapse bg-dark" id="navmenu">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/unregisteredHome/'})">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/login/'})">Login</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Registration
                        </a>
                        <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/registration/'})">Client registration</a></li>
                            <li><a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/ownersRegistration/'})">Owner registration</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
   `,

});