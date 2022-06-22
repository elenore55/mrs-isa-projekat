Vue.component('admin-nav', {
    props: ['offer'],

    template: `
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" :href="updateAdminInfo"><i class="fa fa-user me-2"></i>My Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="addAdmin">Add admin</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminAllEntities">Entities</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Reports
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" :href="adminVisitsREport">Income</a></li>
                            <li><a class="dropdown-item" :href="adminsIncomeReport">Visits</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminComplaints">Complaints</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminDeletionReq">Del reqs</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminFeedbacks">Feedbacks</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminLoyalProgram">Loyalty</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminPenalties">Penalties</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adminRegistrationReq">Reg req</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
    `,

    computed: {
        updateAdminInfo() {
            return "/#/updateAdminInfo/" + this.$route.params.id;
        },

        addAdmin() {
            return "/#/addAdmin/"+ this.$route.params.id;
        },

        adminAllEntities() {
            return "/#/adminEntities/" + this.$route.params.id;
        },

        adminComplaints() {
            return "/#/adminComplaint/" + this.$route.params.id;
        },

        adminVisitsREport() {
            return "/#/adminVisit/" + this.$route.params.id;
        },

        adminsIncomeReport() {
            return "/#/adminInmcome/" + this.$route.params.id;
        },
        adminDeletionReq() {
            return "/#/adminDeletionReqs/" + this.$route.params.id;
        },
        adminFeedbacks() {
            return "/#/adminFeedbacks/" + this.$route.params.id;
        },
        adminLoyalProgram() {
            return "/#/adminLoyal/" + this.$route.params.id;
        },
        adminPenalties() {
            return "/#/adminPenalties/" + this.$route.params.id;
        },
        adminRegistrationReq() {
            return "/#/adminRegReq/" + this.$route.params.id;
        },
    }
});