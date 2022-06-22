Vue.component('update-cottage-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" :href="allCottages">All cottages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="cottageProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="cottageImages">Images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="cottageReservations">Reservations</a>
                    </li>
                </ul>
            </div>
        </nav>
    `,

    computed: {
        allCottages() {
            return "/index.html#/cottagesViewOwner";
        },

        cottageProfile() {
            return "/index.html#/cottageProfile/" + this.$route.params.id;
        },

        cottageImages() {
            return "/index.html#/cottageImages/" + this.$route.params.id;
        },

        cottageReservations() {
            return "/index.html#/cottageReservations/" + this.$route.params.id;
        }
    }
});