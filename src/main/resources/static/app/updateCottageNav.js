Vue.component('update-cottage-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/cottagesViewOwner/'})">All</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/updateCottage/' + $route.params.id})">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/cottageImages/' + $route.params.id})">Images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/cottageReservations/' + $route.params.id})">Reservations</a>
                    </li>
                </ul>
            </div>
        </nav>
    `
});