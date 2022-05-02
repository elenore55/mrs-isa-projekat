Vue.component('update-cottage-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/updateCottage/'})">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/cottageImages/'})">Images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/cottageReservations/'})">Reservations</a>
                    </li>
                </ul>
            </div>
        </nav>
    `
});