Vue.component('update-ship-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" :href="allShips">All ships</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="shipProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="shipImages">Images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="shipReservations">Reservations</a>
                    </li>
                </ul>
            </div>
        </nav>
    `,

    computed: {
        allShips() {
            return "/index.html#/shipsViewOwner";
        },

        shipProfile() {
            return "/index.html#/shipProfile/" + this.$route.params.id;
        },

        shipImages() {
            return "/index.html#/shipImages/" + this.$route.params.id;
        },

        shipReservations() {
            return "/index.html#/shipReservations/" + this.$route.params.id;
        }
    }
});