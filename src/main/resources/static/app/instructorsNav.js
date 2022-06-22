Vue.component('instructor-nav', {
    props: ['offer'],

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
            return "/#/profilePageInstructorPI/" + this.$route.params.id;
        },

        instructorsAdventures() {
            return "/#/instructorsAdventures/"+ this.$route.params.id;
        },

        scheduleCalendarInstructor() {
            return "/#/instructorsCalendar/" + this.$route.params.id;
        },

        incomeReport() {
            return "/#/incomeReport/" + this.$route.params.id;
        },

        visitReport() {
            return "/#/visitReport/" + this.$route.params.id;
        },

        priceHistoryReport() {
            return "/#/priceHistoryReport/" + this.$route.params.id;
        },

        defInstFreeTime() {
            return "/#/availabilityInstructor/" + this.$route.params.id;
        },
        instAdvReservStatus() {
            return "/#/instAdvReservSatatus/" + this.$route.params.id;
        },
        InstructorComplaint() {
            return "/#/instructorComplaint/" + this.$route.params.id;
        },
        addAdventure() {
            return "/#/addAdventure/" + this.$route.params.id;
        },
        adveHistReserv() {
            return "/#/adventureHistory/" + this.$route.params.id;
        },
        adventureImages() {
            return "/#/adventureImg/" + this.$route.params.id;
        },
        adventureQuickReserv() {
            return "/#/adventureQuickReserv/" + this.$route.params.id;
        },
        advReservWithClient() {
            return "/#/advReservWithClient/" + this.$route.params.id;
        },



    }
});