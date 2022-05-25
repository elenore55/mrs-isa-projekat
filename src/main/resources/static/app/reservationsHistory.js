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
    <div style="background-color: #fff9e8">
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
                <div class="card w-50 my-4 shadow px-1" style="border-radius: 10px">
                    <div class="card-body mx-2">
                        <h3 class="card-title d-flex justify-content-center my-3">{{ r.offerName }}</h3>
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="my-3">Client: {{ r.clientEmail }}</h5>
                            <a class="btn btn-primary btn-sm h-50 mt-3" href="#">View profile</a>
                        </div>
                        <table>
                            <tr class="mt-3">
                                <td><h5>From: &nbsp</h5></td>
                                <td><h5>{{ getFormattedDate(r.startDate) }} at {{ getFormattedTime(r.startDate) }}h</h5></td>
                            </tr>
                            <tr class="mb-3">
                                <td><h5>To: &nbsp</h5></td>
                                <td><h5>{{ getFormattedDate(r.endDate) }} at {{ getFormattedTime(r.endDate) }}h</h5></td>
                            </tr>
                        </table>
                        <h5 class="my-3"><span class="badge" style="background-color: purple">{{ r.status }}</span></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
   `,

    methods: {
        getReservations() {
            let desc = this.direction === 'Descending';
            axios.post('api/users/getFilteredOwnersReservations/' + this.$route.params.id, {
                startDate: this.start_date,
                endDate: this.end_date,
                sortBy: this.sort_by,
                desc: desc
            }).then(response => {
                this.reservations = response.data;
            }).catch(error => {
                Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        getFormattedDate(date) {
            let dateStr = new Date(date).toISOString();
            let year = dateStr.substring(0, 4);
            let month = dateStr.substring(5, 7);
            let day = dateStr.substring(8, 10);
            return day + '.' + month + '.' + year + '.';
        },

        getFormattedTime(date) {
            let dateStr = new Date(date).toISOString();
            let hours = dateStr.substring(11, 13);
            let minutes = dateStr.substring(14, 16);
            return hours + ':' + minutes;
        }

    }
});