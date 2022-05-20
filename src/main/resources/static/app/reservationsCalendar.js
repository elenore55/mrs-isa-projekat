Vue.component("reservations-calendar", {
    data() {
        return {
            reservations: []
        }
    },

    props: ['id'],

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
    <div class="m-4">
        <div id="calendar" class="p-4 shadow-lg" style="background-color: white; border-radius: 10px"></div>
    </div>
    `
});