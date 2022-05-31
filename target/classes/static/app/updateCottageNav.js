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
            return "/#/cottagesViewOwner/2";
        },

        cottageProfile() {
            return "/#/updateCottage/" + this.$route.params.id;
        },

        cottageImages() {
            return "/#/cottageImages/" + this.$route.params.id;
        },

        cottageReservations() {
            return "/#/cottageReservations/" + this.$route.params.id;
        }
    }
});