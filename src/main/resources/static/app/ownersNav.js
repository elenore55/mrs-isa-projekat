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
                    <li class="nav-item dropdown">
                        <a class="nav-link"  href="javascript:void(0)" @click="logOut">Log out</a>
                    </li>
                </ul>
            </div>
        </nav>
    `,

    methods: {
        logOut() {
            localStorage.removeItem("jwt");
            this.$router.push({path: '/login'});
        }
    },

    computed: {
        ownersProfile() {
            return "/index.html#/ownersProfile";
        },

        offers() {
            let role = JSON.parse(localStorage.getItem("jwt")).userRole;
            let offer = "";
            if (role === "ROLE_COTTAGE") offer = "cottages";
            else if (role === "ROLE_SHIP") offer = "ships";
            return "/index.html#/" + offer + "ViewOwner";
        },

        reservationsHistory() {
            return "/index.html#/reservationsHistory";
        },

        incomeReport() {
            return "/index.html#/incomeReport";
        },

        visitReport() {
            return "/index.html#/visitReport";
        },

        priceHistoryReport() {
            return "/index.html#/priceHistoryReport";
        }
    }
});