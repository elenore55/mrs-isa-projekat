Vue.component("reservations-calendar", {
    data() {
        return {
            reservations: [],
            fast: []
        }
    },

    props: ['rangeStart', 'rangeEnd', 'offerId'],

    mounted() {
        let events = [];
        axios({
            method: "get",
            url: "api/users/getOwnersReservations/" + JSON.parse(localStorage.getItem("jwt")).userId + "/" + this.offerId,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.reservations = response.data;
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
                    start: this.getValidDate(r.startDate),
                    end: this.getValidDate(r.endDate),
                    backgroundColor: color,
                    id: r.id,
                    groupId: 1,
                    description: reservationText
                });
            }
            /*events.push({
                start: new Date(-8640000000),
                end: new Date(this.rangeStart),
                rendering: 'background',
                allDay: true,
                color: "#6Bf251"
            });*/
            axios({
                method: "get",
                url: "api/offers/getFastReservations/" + this.offerId,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.fast = response.data;
                for (let f of this.fast) {
                    let startDate = this.getValidDate(f.start);
                    let endDate = this.getValidDate(f.start);
                    endDate.setDate(endDate.getDate() + f.duration);
                    let actionStartDate = this.getValidDate(f.actionStart);
                    let actionEndDate = this.getValidDate(f.actionStart);
                    actionEndDate.setDate(actionEndDate.getDate() + f.actionDuration);
                    events.push({
                        title: "Fast reservation",
                        start: startDate,
                        end: endDate,
                        backgroundColor: "#e84fe0",
                        id: f.id,
                        groupId: 2,
                        actionStart: actionStartDate,
                        actionEnd: actionEndDate
                    });
                }
                var el = document.getElementById("calendar");
                var calendar = new FullCalendar.Calendar(el, {
                    plugins: ['dayGrid', 'timeGrid', 'list', 'bootstrap'],
                    themeSystem: 'bootstrap',
                    weekNumbers: false,
                    eventLimit: true,
                    events: events,
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
                    },
                    footer: {
                        left: 'prev,next today',
                        right: 'dayGridMonth,timeGridWeek,timeGridDay,listYear'
                    },
                    firstDay: 1,
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
                        if (info.event.groupId == 1) {
                            Swal.fire({
                                title: info.event.extendedProps.description + ' reservation',
                                html: '<p>Client: ' + info.event.title + '</p><p>Start: ' + startDate + ' ' + startTime + '</p><p>End: ' + endDate + ' ' + endTime + '</p>',
                                icon: 'info',
                                backdrop: `rgba(106, 124, 137, 0.4)`
                            });
                        } else {
                            let startDateTimeA = new Date(info.event.extendedProps.actionStart.getTime() - (offset * 60 * 1000));
                            let startDateA = startDateTimeA.toISOString().split('T')[0];
                            startDateA = startDateA.substring(8, 10) + '/' + startDateA.substring(5, 7) + '/' + startDateA.substring(0, 4);

                            let endDateTimeA = new Date(info.event.extendedProps.actionEnd.getTime() - (offset * 60 * 1000));
                            let endDateA = endDateTimeA.toISOString().split('T')[0];
                            endDateA = endDateA.substring(8, 10) + '/' + endDateA.substring(5, 7) + '/' + endDateA.substring(0, 4);
                            Swal.fire({
                                title: info.event.title,
                                html: '<p>Start: ' + startDate + '</p><p>End: ' + endDate + '</p><p>Action start: '
                                    + startDateA + '</p><p>Action end: ' + endDateA + '</p>',
                                icon: 'info',
                                backdrop: `rgba(106, 124, 137, 0.4)`
                            });
                        }
                    }
                });
                calendar.render();
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div class="mx-4 mb-5 d-flex justify-content-center">
        <div id="calendar" class="p-4 shadow-lg w-75 h-75" style="background-color: white; border-radius: 10px"></div>
    </div>
    `,

    methods: {
        getValidDate(date) {
            let arr = date.toString().split(',');
            return new Date(parseInt(arr[0]), parseInt(arr[1]) - 1, parseInt(arr[2]), parseInt(arr[3]), parseInt(arr[4]));
        }
    }
});