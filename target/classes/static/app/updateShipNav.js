Vue.component('update-ship-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="navbar_ship">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/updateShip/'})">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)" @click="$router.push({path: '/shipImages/'})">Images</a>
                    </li>
                </ul>
            </div>
        </nav>
    `
});