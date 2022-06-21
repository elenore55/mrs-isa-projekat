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

        axios({
            method: "get",
            url: "api/cottages/getCottage/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.cottage = response.data;
            this.cottage.availableStart = this.getValidDate(this.cottage.availableStart);
            this.cottage.availableEnd = this.getValidDate(this.cottage.availableEnd);
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
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
                            <vuejs-datepicker :disabled="!cottage.enabled" v-model="cottage.availableStart" format="dd.MM." id="start-date" :monday-first="true"></vuejs-datepicker>
                        </div>
                        <div class="mb-4">
                            <label for="end-date">End</label>                
                            <vuejs-datepicker :disabled="!cottage.enabled" v-model="cottage.availableEnd" format="dd.MM." id="end-date" :monday-first="true"></vuejs-datepicker>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <p v-if="!areValidDates" class="text-danger">Dates are required and must be in ascending order!</p>
                    </div>
                    <div class="row mb-3 me-3">
                        <div class="col text-end">
                            <button v-if="cottage.enabled" type="button" class="btn btn-primary" v-on:click="updateReservationPeriod">Save changes</button>
                            <button v-if="!cottage.enabled" type="button" class="btn btn-primary" style="cursor: not-allowed; opacity: 50%">Save changes</button>
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
        <reservations-calendar :offerId="$route.params.id" rangeStart="cottage.availableStart" :rangeEnd="cottage.availableEnd"></reservations-calendar>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            this.input_started = true;
            if (this.areValidDates) {
                axios.post("api/cottages/updateReservationPeriod", this.cottage, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(function(response) {
                    Swal.fire('Success', 'Cottage updated!', 'success');
                }).catch(function (error) {
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            }
        },

        getValidDate(date) {
            let arr = date.toString().split(',');
            return new Date(parseInt(arr[0]), parseInt(arr[1]), parseInt(arr[2]) - 1, parseInt(arr[3]), parseInt(arr[4]));
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.cottage.availableStart && this.cottage.availableEnd && (new Date(this.cottage.availableStart) < new Date(this.cottage.availableEnd)));
        },
    }
});