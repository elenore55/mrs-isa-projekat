Vue.component("cottage-reservations", {
    data: function () {
        return {
            start_date: null,
            end_date: null,
            input_started: false
        }
    },

    template: `
    <div>
        <update-cottage-nav></update-cottage-nav>
        <div class="container card mt-4">
            <div class="m-3 card-title">
                <h5>Regular reservation period</h5>
            </div>
            <div class="d-flex justify-content-start card-body">
                <div class="me-3 mb-4">
                    <label for="start-date">Start</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM." id="start-date"></vuejs-datepicker>
                </div>
                <div class="mb-4">
                    <label for="end-date">End</label>                
                    <vuejs-datepicker v-model="end_date" format="dd.MM." id="end-date"></vuejs-datepicker>
                </div>
            </div>
            <p v-if="!areValidDates" class="ms-3 text-danger">Dates are required and must be in ascending order!</p>
        </div>
        <div class="row mt-1 mb-2 me-5">
            <div class="col text-end">
                <button type="button" class="btn btn-primary btn-lg" v-on:click="updateReservationPeriod">Save changes</button>
            </div>
        </div>
    </div>
    `,

    methods: {
        updateReservationPeriod() {
            this.input_started = true;
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.start_date && this.end_date && this.start_date < this.end_date);
        }
    }
});