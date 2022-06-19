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

        axios({
            method: "get",
            url: "api/ships/getShip/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.ship = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div style="background-color: #f2e488">
        <update-ship-nav></update-ship-nav>
        <div>
            <div class="container-fluid d-flex justify-content-center">
                <div class="card mt-4 ms-4 w-50 shadow-lg" style="border-radius: 10px">
                    <div class="m-3 card-title d-flex justify-content-center">
                        <h4>Regular reservation period</h4>
                    </div>
                    <div class="d-flex justify-content-center card-body">
                        <div class="me-3 mb-1">
                            <label for="start-date">Start</label>
                            <vuejs-datepicker :disabled="!ship.enabled" v-model="ship.availableStart" format="dd.MM." id="start-date" :monday-first="true"></vuejs-datepicker>
                        </div>
                        <div class="mb-1">
                            <label for="end-date">End</label>                
                            <vuejs-datepicker :disabled="!ship.enabled" v-model="ship.availableEnd" format="dd.MM." id="end-date" :monday-first="true"></vuejs-datepicker>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <p v-if="!areValidDates" class="text-danger">Dates are required and must be in ascending order!</p>
                    </div>
                    <div class="row mb-3 me-3 mt-3">
                        <div class="col text-end">
                            <button v-if="ship.enabled" type="button" class="btn btn-primary" v-on:click="updateReservationPeriod">Save changes</button>
                            <button v-if="!ship.enabled" type="button" class="btn btn-primary" style="cursor: not-allowed; opacity: 50%">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <div class="d-flex justify-content-start w-75 ms-5 mt-3">
                    <a class="btn btn-secondary me-1 mt-4" href="javascript:void(0)" @click="$router.push({path: '/addReservation/' + $route.params.id})">Add reservation</a>
                    <a class="btn btn-secondary me-4 mt-4" href="javascript:void(0)" @click="$router.push({path: '/fastReservations/' + $route.params.id})">Fast reservations</a>
                </div>
            </div>
        </div>
        <reservations-calendar :offerId="$route.params.id" rangeStart="ship.availableStart" rangeEnd="ship.availableEnd"></reservations-calendar>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            this.input_started = true;
            if (this.areValidDates) {
                axios.post("api/ships/updateReservationPeriod", this.ship, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(function(response) {
                    Swal.fire('Success', 'Ship updated!', 'success');
                }).catch(function (error) {
                    if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                    else Swal.fire('Error', 'Something went wrong!', 'error');
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