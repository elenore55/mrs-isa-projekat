Vue.component("add-reservation", {
    data: function () {
        return {
            id: null,
            start: null, end: null,
            name: "",
            email: '',
            input_started: false,
            value: '',
            tdStart: null
        }
    },

    mounted() {
        $("body").css("background-image", "none");
        $("body").css("background-color", "#f2e488");
        $("body").css("background-size", "100% 200%");
        this.id = this.$route.params.id;

        axios({
            method: "get",
            url: "api/offers/getName/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.name = response.data;
            this.tdStart = new tempusDominus.TempusDominus(document.getElementById('startPicker'));
            this.tdEnd = new tempusDominus.TempusDominus(document.getElementById('endPicker'));
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div>
        <div class="d-flex justify-content-center">
            <div class="container w-50 m-5 card shadow-lg" style="border-radius: 10px">
                <div class="mt-4 ms-3 mb-2 card-title d-flex justify-content-center">
                    <h4>Add a reservation for {{ name }}</h4>
                </div>
                <div class="card-body">
                    <div class="row mt-3 ms-3 me-3 mb-1">
                        <div class="col form-floating">
                            <input type="email" id="client-mail-input" v-model="email" class="form-control">
                            <label for="client-mail-input">Client email</label>
                        </div>
                    </div>
                    <p v-if="!isValidEmail" class="ms-4 text-danger">Client's email is required!</p>
                    <div class="d-flex justify-content-around ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-date">Start date</label>
                            <div class='input-group' id='startPicker' data-td-target-input='nearest' data-td-target-toggle='nearest'>
                                <input id='startPickerInput' type='text' class='form-control' data-td-target='#startPicker' />
                                <span class='input-group-text' data-td-target='#startPicker' data-td-toggle='datetimepicker'>
                                    <span class='fa-solid fa-calendar'></span>
                               </span>
                            </div>
                        </div>
                        <div class="mt-1 mb-1 me-3">
                            <label for="end-date">End date</label>                
                            <div class='input-group' id='endPicker' data-td-target-input='nearest' data-td-target-toggle='nearest'>
                                <input id='endPickerInput' type='text' class='form-control' data-td-target='#endPicker' />
                                <span class='input-group-text' data-td-target='#endPicker' data-td-toggle='datetimepicker'>
                                    <span class='fa-solid fa-calendar'></span>
                               </span>
                            </div>
                        </div>
                    </div>
                    <p v-if="!areValidDates" class="ms-4 text-danger">Dates are required and must be in ascending order!</p>
                    <div class="row mt-4 mb-3 me-3">
                        <div class="col text-end">
                            <button type="button" class="btn btn-primary" v-on:click="addReservation">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        addReservation() {
            this.input_started = true;
            if (this.isValidEmail && this.areValidDates) {
                this.start.setTime(this.start.getTime() + 2 * 60 * 60 * 1000);
                this.end.setTime(this.end.getTime() + 2 * 60 * 60 * 1000);
                axios.post("api/reservations/addReservation", {
                    offerId: this.id,
                    startDate: this.start,
                    endDate: this.end,
                    clientEmail: this.email,
                    ownerId: JSON.parse(localStorage.getItem("jwt")).userId
                }, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(response => {
                    Swal.fire('Success', 'Reservation added!', 'success');
                }).catch(error => {
                    if(error.response.status === 404) Swal.fire('Error', 'Client not found!', 'error');
                    else if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Cottage already reserved!', 'error');
                });
            }
        },
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            this.start = this.tdStart.viewDate;
            this.end = this.tdEnd.viewDate;
            return !!(this.start && this.end && new Date(this.start) < new Date(this.end));
        },

        isValidEmail() {
            if (!this.input_started) return true;
            return !!(this.email);
        }
    }
});