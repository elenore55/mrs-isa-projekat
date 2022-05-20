Vue.component("reservations-calendar", {
    data() {
        return {
            reservations: []
        }
    },

    props: ['id'],

    mounted() {
        axios.get("api/users/getOwnersReservations/" + this.id).then(response => {
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
    },

    template: `
    <div class="m-4">
        <div id="calendar" class="p-4 shadow-lg" style="background-color: white; border-radius: 10px"></div>
    </div>
    `
});