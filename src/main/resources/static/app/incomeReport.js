Vue.component('income-report', {
    data() {
        return {
            start_date: null,
            end_date: null,
            reports: [],
            disabled: {
                from: new Date()
            }

        }
    },

    template: `
    <div>
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-25 d-flex justify-content-evenly my-3">
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="start-date">Start date</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date" :disabled-dates="disabled"></vuejs-datepicker>     
                </div>
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="end-date">End date</label>
                    <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date" :disabled-dates="disabled"></vuejs-datepicker>
                </div>
                <div class="d-flex align-items-end">
                    <button class="btn btn-success px-3 mx-2 h-75 my-1" v-on:click="getReports">Display</button>
                </div>
            </div>
        </div>    
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-50 d-flex justify-content-end">
                 
            </div>
        </div>
    </div>
    `,

    methods: {
        getReports() {
            axios.post("api/users/getIncomeReport/" + this.$route.params.id, {
                start: this.start_date,
                end: this.end_date
            }).then(response => {
                this.reports = response.data;
                alert(this.reports.length);
            }).catch(error => {
                Swal.fire('Error', 'Something went wrong!', 'error');
            })
        }
    },

    computed: {
        today() {
            return new Date();
        }
    }
});