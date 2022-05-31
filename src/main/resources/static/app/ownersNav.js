Vue.component('owners-nav', {
    props: ['offer'],

    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" :href="ownersProfile">My Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="offers">Offers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="reservationsHistory">Reservations</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="reports">Reports</a>
                    </li>
                </ul>
            </div>
        </nav>
    `,

    computed: {
        ownersProfile() {
            return "/#/ownersProfile/" + this.$route.params.id;
        },

        offers() {
            return "/#/" + this.offer + "ViewOwner/" + this.$route.params.id;
        },

        reservationsHistory() {
            return "/#/reservationsHistory/" + this.$route.params.id;
        },

        reports() {
            return "/#/incomeReport/" + this.$route.params.id;
        }
    }

});