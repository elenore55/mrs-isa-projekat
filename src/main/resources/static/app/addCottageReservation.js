Vue.component('add-cottage-reservation', {
    data: function () {
        return {
            id: null,
            start: null, end: null,
            name: "",
            email: '',
            input_started: false
        }
    },

    mounted() {
        this.id = this.$route.params.id;

        axios({
            method: "get",
            url: "api/cottages/getCottage/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.name = response.data.name;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div>
        <update-cottage-nav></update-cottage-nav>
        <div class="d-flex justify-content-center">
            <div class="container w-50 m-5 card">
                <div class="mt-4 ms-3 mb-2 card-title">
                    <h5>Reservation for {{ name }}</h5>
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
                            <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date" :monday-first="true"></vuejs-datepicker>
                        </div>
                        <div class="mt-1 mb-1 me-3">
                            <label for="end-date">End date</label>                
                            <vuejs-datepicker v-model="end" format="dd.MM.yyyy." id="end-date" :monday-first="true"></vuejs-datepicker>
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
                }).then(function(response) {
                    Swal.fire('Success', 'Reservation added!', 'success');
                }).catch(function (error) {
                    if(error.response.status === 404) Swal.fire('Error', 'Client not found!', 'error');
                    else if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Cottage already reserved!', 'error');
                });
            }
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.start && this.end && this.start < this.end);
        },

        isValidEmail() {
            if (!this.input_started) return true;
            return !!(this.email);
        }
    }
});