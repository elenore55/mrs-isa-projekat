Vue.component("client-navbar", {

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

                <div class="collapse navbar-collapse" id="navmenu">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/clientHome/'})">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/pastReservations'})">Past Reservations</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/upcomingReservations/0'})">Upcoming Reservations</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/clientSubs/0'})">My Actions </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/clientProfile/'})"><i class="fa fa-fw fa-user"></i> My Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/login/'})">Log out</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
   `,

});