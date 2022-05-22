Vue.component("add-reservation", {
    data: function () {
        return {
            id: null,
            start: null, end: null,
            name: "",
            email: '',
            input_started: false,
            value: ''
        }
    },

    mounted() {
        this.id = this.$route.params.id;

        axios.get("api/offers/getName/" + this.$route.params.id).then(response => {
            this.name = response.data;
        }).catch(function (error) {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div style="background-color: #f2e488; height: 100vh">
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
                            <div class="input-group" v-on:click="setStartPicker">
                                <span class="input-group-text" v-on:click="setStartPicker">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control" id="start-date-pick" v-on:click="setStartPicker">
                            </div>
                        </div>
                        <div class="mt-1 mb-1 me-3">
                            <label for="end-date">End date</label>                
                            <div class="input-group" v-on:click="setEndPicker">
                                <span class="input-group-text" v-on:click="setEndPicker">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control" id="end-date-pick" v-on:click="setEndPicker">
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
                axios.post("api/reservations/addReservation", {
                    offerId: this.id,
                    startDate: this.start,
                    endDate: this.end,
                    clientEmail: this.email,
                    ownerId: 1
                }).then(function (response) {
                    Swal.fire('Success', 'Reservation added!', 'success');
                }).catch(function (error) {
                    if (error.response.status === 404) {
                        Swal.fire('Error', 'Client not found!', 'error');
                    } else {
                        Swal.fire('Error', 'Already reserved!', 'error');
                    }
                });
            }
        },

        setStartPicker() {
            $(function () {
                $('#start-date-pick').datetimepicker({
                    pickerPosition: 'bottom-right',
                    initialDate: new Date(),
                    format: 'dd.mm.yyyy. HH:mm',
                    todayBtn: true,
                    todayHighlight: true
                })
            })
        },

        setEndPicker() {
            $(function () {
                $('#end-date-pick').datetimepicker({
                    pickerPosition: 'bottom-right',
                    initialDate: new Date(),
                    format: 'dd.mm.yyyy. HH:mm',
                    todayBtn: true,
                    todayHighlight: true
                });
            })
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            this.start = $('#start-date-pick').datetimepicker('getDate');
            this.end = $('#end-date-pick').datetimepicker('getDate');
            return !!(this.start && this.end && new Date(this.start) < new Date(this.end));
        },

        isValidEmail() {
            if (!this.input_started) return true;
            return !!(this.email);
        }
    }
});