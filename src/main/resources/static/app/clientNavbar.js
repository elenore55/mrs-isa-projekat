Vue.component("client-navbar", {
   data: function() {

   },
   template: `
    <nav class = "navbar navbar-expand-lg bg-dark navbar-dark py-3">
            <div class="container">

                <div>
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a href="#reserve" class="nav-link mx-3">Reserve</a>
                        </li>
                        <li class="nav-item">
                            <a href="#past_reservations" class="nav-link mx-3">Past Reservations</a>
                        </li>
                        <li class="nav-item">
                            <a href="#upcoming_reservatons" class="nav-link mx-3">Upcoming Reservatons</a>
                        </li>
                        <li class="nav-item">
                            <a href="#actions" class="nav-link mx-3">Actions</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/clientProfile/'})"><i class="fa fa-fw fa-user"></i> My Account</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
   `,

});