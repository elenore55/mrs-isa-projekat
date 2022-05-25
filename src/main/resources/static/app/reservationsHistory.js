Vue.component("reservations-history", {
    data() {
        return {
            input_started: false,
            start_date: null,
            end_date: null,
            reservations: [],
            sort_list: ['Date', 'Cottage'],
            sort_by: '',
            direction: ''
        }
    },

    mounted() {

    },

    template: `
    <div>
        <div class="d-flex justify-content-center">
            <div class="w-50 d-flex justify-content-evenly my-3">
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
        <div class="d-flex justify-content-center">
            <div class="w-50 d-flex justify-content-evenly">
                <div class="form-floating input-group mx-4">
                    <select class="form-select" v-model="sort_by" id="sort-by-select">
                        <option v-for="s in sort_list">{{ s }}</option>
                    </select>
                    <label for="sort-by-select">Sort by</label>
                </div>
                <div class="form-floating input-group mx-4">
                    <select class="form-select" v-model="direction" id="direction-select">
                        <option selected="selected">Ascending</option>
                        <option>Descending</option>
                    </select>
                    <label for="direction-select">Direction</label>
                </div>
            </div>
        </div>
        <div>
            <button class="btn btn-primary" v-on:click="getReservations">Show</button>
        </div>
        <div class="d-flex justify-content-center w-50" v-for="(r, i) in reservations">
        
        </div>
    </div>
   `,

    methods: {
        getReservations() {
            let desc = this.direction === 'Descending';
            axios.get('api/users/getOwnersReservations/' + this.$route.params.id + '/' + this.sort_by + '/' + desc).then(response => {
                this.reservations = response.data;
            }).catch(error => {
                alert('Something went wrong');
            });
        }

    }
});