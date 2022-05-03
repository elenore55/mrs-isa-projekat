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

        axios.get("api/cottages/getCottage/" + this.$route.params.id).then(response => {
            this.cottage = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
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
                    <vuejs-datepicker v-model="cottage.availableStart" format="dd.MM." id="start-date"></vuejs-datepicker>
                </div>
                <div class="mb-4">
                    <label for="end-date">End</label>                
                    <vuejs-datepicker v-model="cottage.availableEnd" format="dd.MM." id="end-date"></vuejs-datepicker>
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
            axios.post("api/cottages/updateReservationPeriod", this.cottage).then(function(response) {
                alert('Cottage successfully updated!');
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    },

    computed: {
        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.cottage.availableStart && this.cottage.availableEnd && this.cottage.availableStart < this.cottage.availableEnd);
        },
    }
});