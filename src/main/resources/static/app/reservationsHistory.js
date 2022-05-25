Vue.component("reservations-history", {
    data() {
        return {
            input_started: false,
            start_date: null,
            end_date: null,
            reservations: [],
            sort_list: ['Date', 'Cottage'],
            sort_by: 'Date',
            direction: 'Ascending'
        }
    },

    template: `
    <div style="background-color: #fff9e8; height: 100vh">
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-25 d-flex justify-content-evenly my-3">
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
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-50 d-flex justify-content-evenly mb-4">
                <div class="form-floating input-group mx-1">
                    <select class="form-select" v-model="sort_by" id="sort-by-select">
                        <option v-for="s in sort_list">{{ s }}</option>
                    </select>
                    <label for="sort-by-select">Sort by</label>
                </div>
                <div class="form-floating input-group mx-1">
                    <select class="form-select" v-model="direction" id="direction-select">
                        <option selected="selected">Ascending</option>
                        <option>Descending</option>
                    </select>
                    <label for="direction-select">Direction</label>
                </div>
                <button class="btn btn-success px-3 mx-2" v-on:click="getReservations">Display</button>
            </div>
        </div>
        <div>
            <div class="d-flex justify-content-center" v-for="(r, i) in reservations">
                <div class="card w-50 my-3">
                    <div class="card-body">
                        <h3 class="card-title">{{ r.offerName }}</h3>
                        <h4>Client: {{ r.clientEmail }}</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
   `,

    methods: {
        getReservations() {
            let desc = this.direction === 'Descending';
            axios.get('api/users/getOwnersReservations/' + this.$route.params.id, {
                startDate: this.start_date,
                endDate: this.end_date,
                sortBy: this.sort_by,
                desc: desc
            }).then(response => {
                this.reservations = response.data;
            }).catch(error => {
                alert('Something went wrong');
            });
        }

    }
});