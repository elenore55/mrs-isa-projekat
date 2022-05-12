Vue.component('update-ship-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="navbar_ship">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/shipsViewOwner/'})">All</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/updateShip/ + $route.params.id'})">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/shipImages/' + $route.params.id})">Images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/shipReservations/' + $route.params.id})">Reservations</a>
                    </li>
                </ul>
            </div>
        </nav>
    `
});