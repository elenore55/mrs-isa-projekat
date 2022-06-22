Vue.component('admin-nav', {
    props: ['offer'],
    id:[],
    token: {},
    mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;
        alert("Trenutni id je " + this.id);
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 210%");

    },
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
            return "/index.html#/updateAdminInfo/" + this.id;   //odradjen
        },

        addAdmin() {
            return "/index.html#/addAdmin/"+ this.id;       //odradjen
        },

        adminAllEntities() {
            return "/index.html#/adminEntities/" + this.id;  //odradjeno
        },

        adminComplaints() {
            return "/index.html#/adminComplaint/" + this.id;    //odradjeno
        },

        adminVisitsREport() {
            return "/index.html#/adminVisit/" + this.id;    //odradjen
        },

        adminsIncomeReport() {
            return "/index.html#/adminInmcome/" + this.id;      //odradjen
        },
        adminDeletionReq() {
            return "/index.html#/adminDeletionReqs/" + this.id; //odradjen
        },
        adminFeedbacks() {
            return "/index.html#/adminFeedbacks/" + this.id;        //odradjen
        },
        adminLoyalProgram() {
            return "/index.html#/adminLoyal/" + this.id;        //odradjen
        },
        adminPenalties() {
            return "/index.html#/adminPenalties/" + this.id;        //odradjen
        },
        adminRegistrationReq() {
            return "/index.html#/adminRegReq/" + this.id;       //odradjen
        },
    }
});