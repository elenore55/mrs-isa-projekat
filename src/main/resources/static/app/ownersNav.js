Vue.component('owners-nav', {
    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" :href="ownersProfile"><i class="fa fa-user me-2"></i>My Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="offers">Offers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="reservationsHistory">Reservations</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Reports
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" :href="incomeReport">Income</a></li>
                            <li><a class="dropdown-item" :href="visitReport">Visits</a></li>
                            <li><a class="dropdown-item" :href="priceHistoryReport">Average prices</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    `,

    computed: {
        ownersProfile() {
            return "/index.html#/ownersProfile/" + this.$route.params.id;
        },

        offers() {
            let role = JSON.parse(localStorage.getItem("jwt")).userRole;
            let offer = "";
            if (role === "ROLE_COTTAGE") offer = "cottages";
            else if (role === "ROLE_SHIP") offer = "ships";
            return "/index.html#/" + offer + "ViewOwner/" + this.$route.params.id;
        },

        reservationsHistory() {
            return "/index.html#/reservationsHistory/" + this.$route.params.id;
        },

        incomeReport() {
            return "/index.html#/incomeReport/" + this.$route.params.id;
        },

        visitReport() {
            return "/index.html#/visitReport/" + this.$route.params.id;
        },

        priceHistoryReport() {
            return "/index.html#/priceHistoryReport/" + this.$route.params.id;
        }
    }
});