Vue.component('instructor-nav', {
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
                        <a class="nav-link" :href="profilePageInstructorPI"><i class="fa fa-user me-2"></i>My Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="instructorsAdventures">Adventures</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="scheduleCalendarInstructor">Calendar</a>
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
                    <li class="nav-item">
                        <a class="nav-link" :href="defInstFreeTime">Free time</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="instAdvReservStatus">Reserv Status</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="InstructorComplaint">Complaints</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="addAdventure">Add adventure</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adveHistReserv">Adv hist res</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adventureImages">Adv images</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="adventureQuickReserv">Adventure quick res</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" :href="advReservWithClient">Res client</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
    `,

    computed: {
        profilePageInstructorPI() {
            return "/index.html#/profilePageInstructorPI/" + this.id;   //odradjen
        },

        instructorsAdventures() {
            return "/index.html#/instructorsAdventures/"+ this.id;      //odradjeno
        },

        scheduleCalendarInstructor() {
            return "/index.html#/instructorsCalendar/" + this.id;       //odradjeno
        },

        incomeReport() {
            return "/index.html#/incomeReport/";
        },

        visitReport() {
            return "/index.html#/visitReport/";
        },

        priceHistoryReport() {
            return "/index.html#/priceHistoryReport/";
        },

        defInstFreeTime() {
            return "/index.html#/availabilityInstructor/" + this.id;    //odradjeno
        },
        instAdvReservStatus() {
            return "/index.html#/instAdvReservSatatus/" + this.id;      //odradjeno
        },
        InstructorComplaint() {
            return "/index.html#/instructorComplaint/" + this.id;       //odradjeno
        },
        addAdventure() {
            return "/index.html#/addAdventure/" + this.id;      //odradjeno
        },
        adveHistReserv() {
            return "/index.html#/adventureHistory/" + this.id;  //odradjeno
        },
        adventureImages() {
            return "/index.html#/adventureImg/" + this.id;          //odradjeno
        },
        adventureQuickReserv() {
            return "/index.html#/adventureQuickReserv/" + this.id;      //odradjeno
        },
        advReservWithClient() {
            return "/index.html#/advReservWithClient/" + this.id;       //odradjeno
        },



    }
});