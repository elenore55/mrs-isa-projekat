Vue.component("reservations-calendar", {
    data() {
        return {
            reservations: []
        }
    },

    mounted() {
        var el = document.getElementById("calendar");
        var calendar = new FullCalendar.Calendar(el, {
            plugins: ['dayGrid', 'bootstrap'],
            themeSystem: 'bootstrap',
            weekNumbers: true,
            eventLimit: true,
            events: 'https://fullcalendar.io/demo-events.json'
        });
        calendar.render();
    },

    template: `
    <div class="m-3">
        <div id="calendar">
       
        </div>
    </div>
    `
});