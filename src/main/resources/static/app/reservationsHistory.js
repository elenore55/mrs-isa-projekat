Vue.component("reservations-history", {
    data() {
        return {
            input_started: false,
            review_input_started: false,
            start_date: null,
            end_date: null,
            reservations: [],
            sort_list: ['Date', 'Name'],
            sort_by: 'Date',
            direction: 'Ascending',
            calendarDisplay: false,
            focused_reservation: {
                startDate: new Date(),
                endDate: new Date(),
                clientEmail: '',
                offerName: '',
                client: {
                    name: '', surname: ''
                }
            },
            review: '',
            receives_penalty: false,
            my_modal: null
        }
    },

    mounted() {
        this.my_modal =  new bootstrap.Modal(document.getElementById("reportModal"), {});
    },

    template: `
    <div style="background-color: #fff9e8">
        <owners-nav></owners-nav>
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-25 d-flex justify-content-evenly my-3">
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="start-date">Start date</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date" :monday-first="true"></vuejs-datepicker>     
                </div>
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="end-date">End date</label>
                    <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date" :monday-first="true"></vuejs-datepicker>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-50 d-flex justify-content-evenly mb-4">
                <div class="form-floating input-group mx-1">
                    <select class="form-select" v-model="sort_by" id="sort-by-select">
                        <option v-for="s in sort_list">{{ s }}</option>
                    </select>
                    <label for="sort-by-select">Sort by</label>
                </div>
                <div class="form-floating input-group mx-1">
                    <select class="form-select" v-model="direction" id="direction-select">
                        <option selected="selected">Ascending</option>
                        <option>Descending</option>
                    </select>
                    <label for="direction-select">Direction</label>
                </div>
                <button class="btn btn-success px-3 mx-2" v-on:click="getReservations">Display</button>
            </div>
        </div>
        <div class="w-100 d-flex justify-content-end" v-if="input_started">
            <button class="btn btn-primary mx-4 my-2" v-on:click="calendarDisplay = !calendarDisplay">{{ btnCalendarText }}</button>
        </div>
        <div v-if="!calendarDisplay">
            <div class="d-flex justify-content-center" v-for="(r, i) in reservations">
                <div class="card w-50 my-4 shadow px-1" style="border-radius: 10px">
                    <div class="card-body mx-2">
                        <h3 class="card-title d-flex justify-content-center my-3">{{ r.offerName }}</h3>
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="my-3">Client: {{ r.clientEmail }}</h5>
                            <a class="btn btn-primary btn-sm h-50 mt-3" href="javascript:void(0)" @click="$router.push({path: '/clientReadonlyProfile/' + r.clientEmail})">View profile</a>
                        </div>
                        <table>
                            <tr class="mt-3">
                                <td><h5>From: &nbsp</h5></td>
                                <td><h5>{{ getFormattedDate(r.startDate) }} at {{ getFormattedTime(r.startDate) }}h</h5></td>
                            </tr>
                            <tr class="mb-3">
                                <td><h5>To: &nbsp</h5></td>
                                <td><h5>{{ getFormattedDate(r.endDate) }} at {{ getFormattedTime(r.endDate) }}h</h5></td>
                            </tr>
                        </table>
                        <div class="d-flex justify-content-between">
                            <h5 class="my-3"><span class="badge" style="background-color: purple">{{ reservationStatus(r.status) }}</span></h5>
                            <button class="btn btn-success btn-sm h-50 my-3" v-on:click="setFocusedReservation(r)" type="button">Leave a review</button>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        <div v-if="calendarDisplay">
            <owners-reservations-calendar :reservations="reservations"></owners-reservations-calendar>
        </div>
        <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body" style="background-color: #fff9e8">
                        <h5 class="d-flex justify-content-center my-2">Reservation for {{ focused_reservation.offerName }}</h5>
                        <h6 class="d-flex justify-content-center mb-4 mt-3">
                            {{ getFormattedDate(focused_reservation.startDate) }} at {{ getFormattedTime(focused_reservation.startDate) }}h - 
                            {{ getFormattedDate(focused_reservation.endDate) }} at {{ getFormattedTime(focused_reservation.endDate) }}h
                        </h6>
                        <h5 class="m-2">Client: </h5>
                        <h4 class="ms-4">{{ focused_reservation.client.name }} {{ focused_reservation.client.surname }}</h4>
                        <h5 class="ms-4">({{ focused_reservation.clientEmail }})</h5>
                        
                        <hr>
                        <div class="form-floating">
                            <textarea v-model="review" class="form-control" id="review-input" style="height: 150px"/>
                            <label for="review-input">Leave a review</label>
                            <p v-if="!isValidReview" class="text-danger">Review cannot be empty.</p>
                        </div>
                        <div class="form-check mt-3">
                            <input class="form-check-input" type="checkbox" value="" id="penaltyCheck" v-model="receives_penalty">
                            <label class="form-check-label" for="penaltyCheck">Client did not show up</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" v-on:click="submitReview">Submit</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" v-on:click="resetModalData">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
   `,

    methods: {
        getReservations() {
            this.input_started = true;
            let desc = this.direction === 'Descending';
            axios.post('api/users/getFilteredOwnersReservations/' + JSON.parse(localStorage.getItem("jwt")).userId, {
                startDate: this.start_date,
                endDate: this.end_date,
                sortBy: this.sort_by,
                desc: desc
            }, {
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.reservations = response.data;
            }).catch(error => {
                if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        getFormattedDate(date) {
            let dateStr = new Date(date).toISOString();
            let year = dateStr.substring(0, 4);
            let month = dateStr.substring(5, 7);
            let day = dateStr.substring(8, 10);
            return day + '.' + month + '.' + year + '.';
        },

        getFormattedTime(date) {
            let dateStr = new Date(date).toISOString();
            let hours = dateStr.substring(11, 13);
            let minutes = dateStr.substring(14, 16);
            return hours + ':' + minutes;
        },

        reservationStatus(status) {
            if (status === "CLIENT_NOT_ARRIVED") {
                return "MISSED";
            }
            return status;
        },

        setFocusedReservation(r) {
            this.focused_reservation = r;
            this.my_modal.show();
        },

        submitReview() {
            this.review_input_started = true;
            if (this.isValidReview) {
                axios.put("api/clientReviews/addReview", {
                    content: this.review,
                    penaltyRequested: this.receives_penalty,
                    clientEmail: this.focused_reservation.clientEmail,
                    ownerId: JSON.parse(localStorage.getItem("jwt")).userId,
                    reservationId: this.focused_reservation.id
                }, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(response => {
                    this.my_modal.hide();
                    this.resetModalData();
                    Swal.fire("Success", "Review submitted", "success");
                }).catch(error => {
                    if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                    else Swal.fire("Error", "Something went wrong", "error");
                });
            }
        },

        resetModalData() {
            this.review = '';
            this.receives_penalty = false;
            this.review_input_started = false;
        }
    },

    computed: {
        btnCalendarText() {
            if (this.calendarDisplay) return "Hide calendar";
            return "Show calendar";
        },

        isValidReview() {
            if (!this.review_input_started) return true;
            return !!(this.review);
        }
    }
});