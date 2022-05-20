Vue.component("ship-reservations", {
    data: function () {
        return {
            id: null,
            ship: {},
            input_started: false
        }
    },

    mounted() {
        this.id = this.$route.params.id;

        axios.get("api/ships/getShip/" + this.$route.params.id).then(response => {
            this.ship = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
    <div style="background-color: #f2e488">
        <update-ship-nav></update-ship-nav>
        <div>
            <div class="d-flex justify-content-between">
                <div class="d-flex justify-content-start" style="width: 70%">
                    <div class="card mt-4 ms-4 w-50 shadow-lg">
                        <div class="m-3 card-title d-flex justify-content-center">
                            <h5>Regular reservation period</h5>
                        </div>
                        <div class="d-flex justify-content-center card-body">
                            <div class="me-3 mb-1">
                                <label for="start-date">Start</label>
                                <vuejs-datepicker v-model="ship.availableStart" format="dd.MM." id="start-date"></vuejs-datepicker>
                            </div>
                            <div class="mb-1">
                                <label for="end-date">End</label>                
                                <vuejs-datepicker v-model="ship.availableEnd" format="dd.MM." id="end-date"></vuejs-datepicker>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <p v-if="!areValidDates" class="text-danger">Dates are required and must be in ascending order!</p>
                        </div>
                        <div class="row mb-3 me-3 mt-3">
                            <div class="col text-end">
                                <button type="button" class="btn btn-primary" v-on:click="updateReservationPeriod">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-end" style="height: 30%">
                    <a class="btn btn-secondary btn-lg me-4 mt-3" href="javascript:void(0)" @click="$router.push({path: '/addReservation/' + $route.params.id})">Add reservation</a>
                    <a class="btn btn-secondary btn-lg me-4 mt-3" href="javascript:void(0)" @click="$router.push({path: '/fastReservations/' + $route.params.id})">Fast reservations</a>
                </div>
            </div>
        </div>
        <reservations-calendar id="1"></reservations-calendar>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            this.input_started = true;
            if (this.areValidDates) {
                axios.post("api/ships/updateReservationPeriod", this.ship).then(function(response) {
                    alert('Ship successfully updated!');
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            }
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.ship.availableStart && this.ship.availableEnd && (new Date(this.ship.availableStart) < new Date(this.ship.availableEnd)));
        }
    }

});