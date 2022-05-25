Vue.component("reservations-history", {
    data() {
        return {
            input_started: false,
            start_date: null,
            end_date: null,
            reservations: [],
            sort_list: ['Date', 'Cottage']
        }
    },

    template: `
    <div>
        <div class="d-flex justify-content-center">
            <div class="w-50 d-flex justify-content-evenly mt-3">
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="start-date">Start date</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>     
                </div>
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="end-date">End date</label>
                    <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date"></vuejs-datepicker>
                </div>
            </div>
        </div>
    </div>
   `
});