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
                let color = "#36b5e3";
                let reservationText = "Pending";
                switch (r.status) {
                    case "ACTIVE":
                        color = "#1d9e11";
                        reservationText = "Active";
                        break;
                    case "FINISHED":
                        color = "#441c80";
                        reservationText = "Finished";
                        break;
                    case "CLIENT_NOT_ARRIVED":
                        color = "#bf2b17";
                        reservationText = "Missed";
                        break;
                    case "CANCELLED":
                        color = "#5a5a5e";
                        reservationText = "Cancelled";
                        break;
                }
                events.push({
                    title: r.clientEmail,
                    start: new Date(r.startDate),
                    end: new Date(r.endDate),
                    backgroundColor: color,
                    id: r.id,
                    description: reservationText
                })
            }
            var el = document.getElementById("calendar");
            var calendar = new FullCalendar.Calendar(el, {
                plugins: ['dayGrid', 'timeGrid', 'list', 'bootstrap'],
                themeSystem: 'bootstrap',
                weekNumbers: false,
                eventLimit: true,
                events: events,
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
                },
                footer: {
                    left: 'prev,next today',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay,listYear'
                },
                firstDay: 1,
                weekNumberCalculation: 'ISO',
                editable: true,
                selectable: true,
                eventClick: function (info) {
                    const offset = info.event.start.getTimezoneOffset();
                    let startDateTime = new Date(info.event.start.getTime() - (offset * 60 * 1000));
                    let startDate = startDateTime.toISOString().split('T')[0];
                    startDate = startDate.substring(8, 10) + '/' + startDate.substring(5, 7) + '/' + startDate.substring(0, 4);
                    let startTime = startDateTime.toISOString().split('T')[1];
                    startTime = startTime.substring(0, 5);

                    let endDateTime = new Date(info.event.end.getTime() - (offset * 60 * 1000));
                    let endDate = endDateTime.toISOString().split('T')[0];
                    endDate = endDate.substring(8, 10) + '/' + endDate.substring(5, 7) + '/' + endDate.substring(0, 4);
                    let endTime = endDateTime.toISOString().split('T')[1];
                    endTime = endTime.substring(0, 5);
                    Swal.fire({
                        title: info.event.extendedProps.description + ' reservation',
                        html: '<p>Client: ' + info.event.title + '</p><p>Start: ' + startDate + ' ' + startTime + '</p><p>End: ' + endDate + ' ' + endTime + '</p>',
                        icon: 'info',
                        showCloseButton: true,
                        backdrop: `rgba(106, 124, 137, 0.4)`
                    });
                }
            });
            calendar.render();
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
    <div class="mx-4 mb-5 d-flex justify-content-center">
        <div id="calendar" class="p-4 shadow-lg w-75 h-75" style="background-color: white; border-radius: 10px"></div>
    </div>
    `
});