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
    <div>
        <update-ship-nav></update-ship-nav>
        <div class="d-flex justify-content-center">
            <div class="container card mt-4 w-50">
                <div class="m-3 card-title d-flex justify-content-center">
                    <h5>Regular reservation period</h5>
                </div>
                <div class="d-flex justify-content-center card-body">
                    <div class="me-3 mb-4">
                        <label for="start-date">Start</label>
                        <vuejs-datepicker v-model="ship.availableStart" format="dd.MM." id="start-date"></vuejs-datepicker>
                    </div>
                    <div class="mb-4">
                        <label for="end-date">End</label>                
                        <vuejs-datepicker v-model="ship.availableEnd" format="dd.MM." id="end-date"></vuejs-datepicker>
                    </div>
                </div>
                <div class="row mb-3 me-3">
                    <div class="col text-end">
                        <button type="button" class="btn btn-primary" v-on:click="updateReservationPeriod">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            alert('Updating');
        }
    }

});