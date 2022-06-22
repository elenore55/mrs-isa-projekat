Vue.component("instructor-calendar",{
    data() {
        return {
            reservations: [],
            id: [],
            token: {}
        }
    },
    mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;
        alert("Trenutni id je " + this.id);
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 210%");
        this.loadInstructorsReservations()
    },
    template: `
    <div class="m-4">
        <div id="calendar" class="p-4 shadow-lg" style="background-color: white; border-radius: 10px"></div>
    </div>
    `,
    methods: {
        loadInstructorsReservations() { //zasad nek bude postavljen na 1
            // axios.get("api/users/getOwnersReservations/" + this.id).then(response => {
            //     this.reservations = response.data;
            //     let events = [];
            //     for (const r of this.reservations) {
            //         events.push({
            //             title: r.clientEmail,
            //             start: new Date(r.startDate),
            //             end: new Date(r.endDate)
            //         })
            //     }
            //     alert(events.length);
            //     var el = document.getElementById("calendar");
            //     var calendar = new FullCalendar.Calendar(el, {
            //         plugins: ['dayGrid', 'bootstrap'],
            //         themeSystem: 'bootstrap',
            //         weekNumbers: false,
            //         eventLimit: true,
            //         events: events
            //     });
            //     calendar.render();
            // }).catch(function (error) {
            //     alert('An error occurred!');
            // });

            axios({
                method: 'get',
                url: "api/users/getOwnersReservations/"+this.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.reservations = response.data;
                let events = [];
                for (const r of this.reservations) {
                    events.push({
                        title: r.clientEmail,
                        start: new Date(r.startDate),
                        end: new Date(r.endDate)
                    })
                }
                alert(events.length);
                var el = document.getElementById("calendar");
                var calendar = new FullCalendar.Calendar(el, {
                    plugins: ['dayGrid', 'bootstrap'],
                    themeSystem: 'bootstrap',
                    weekNumbers: false,
                    eventLimit: true,
                    events: events
                });
                calendar.render();
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    }
});