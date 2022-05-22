Vue.component("cottage-reservations", {
    data: function () {
        return {
            id: null,
            cottage: {},
            input_started: false
        }
    },

    mounted() {
        this.id = this.$route.params.id;

        axios.get("api/cottages/getCottage/" + this.$route.params.id).then(response => {
            this.cottage = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
    <div style="background-color: #f2e488">
        <update-cottage-nav></update-cottage-nav>
        <div>
            <div class="container-fluid d-flex justify-content-center">
                <div class="card mt-4 ms-4 w-50 shadow-lg" style="border-radius: 10px">
                    <div class="m-3 card-title d-flex justify-content-center">
                        <h5>Regular reservation period</h5>
                    </div>
                    <div class="d-flex justify-content-center card-body">
                        <div class="me-3 mb-4">
                            <label for="start-date">Start</label>
                            <vuejs-datepicker v-model="cottage.availableStart" format="dd.MM." id="start-date"></vuejs-datepicker>
                        </div>
                        <div class="mb-4">
                            <label for="end-date">End</label>                
                            <vuejs-datepicker v-model="cottage.availableEnd" format="dd.MM." id="end-date"></vuejs-datepicker>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <p v-if="!areValidDates" class="text-danger">Dates are required and must be in ascending order!</p>
                    </div>
                    <div class="row mb-3 me-3">
                        <div class="col text-end">
                            <button type="button" class="btn btn-primary" v-on:click="updateReservationPeriod">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <div class="d-flex justify-content-start w-75 ms-5 mt-3">
                    <a class="btn btn-secondary me-1 mt-4" href="javascript:void(0)" @click="$router.push({path: '/addReservation/' + $route.params.id})">Add reservation</a>
                    <a class="btn btn-secondary me-1 mt-4" href="javascript:void(0)" @click="$router.push({path: '/fastReservations/' + $route.params.id})">Fast reservations</a>
                </div>
            </div>
        </div>
        <reservations-calendar id="2"></reservations-calendar>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            this.input_started = true;
            if (this.areValidDates) {
                axios.post("api/cottages/updateReservationPeriod", this.cottage).then(function(response) {
                    alert('Cottage successfully updated!');
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            }
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.cottage.availableStart && this.cottage.availableEnd && (new Date(this.cottage.availableStart) < new Date(this.cottage.availableEnd)));
        },
    }
});