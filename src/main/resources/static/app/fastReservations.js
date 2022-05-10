Vue.component('fast-reservations', {
    data() {
        return {
            id: null,
            actions: [],
            start: null, duration: null, actionStart: null, actionDuration: null,
            price: null, maxPeople: null
        }
    },

    mounted(){
        this.id = this.$route.params.id;

        axios.get("api/cottages/getFastReservations/" + this.$route.params.id).then(response => {
            this.actions = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
    <div>
        <update-cottage-nav></update-cottage-nav>
        <h3 class="mt-2 ms-3">Fast reservations</h3>
        <div class="d-flex justify-content-left">
            <div v-for="(fr, i) in actions" class="card mt-3 ms-3">
                <div class="card-body">
                    <h5>Stay period</h5>
                    <label>Start date: {{ fr.start }} Duration: {{ fr.duration }} days</label>
                    <h5>Action period</h5>
                    <label>Start date: {{ fr.actionStart }} Duration: {{ fr.actionDuration }} days</label>
                    <button type="button" v-on:click="actions.splice(i, 1)" class="btn btn-outline-danger">Delete</button>
                </div>
            </div>
            <div class="card position-fixed bottom-0 end-0 mb-5">
                <div class="card-body">
                    <h5 class="ms-3 mt-2">Stay period</h5>
                    <div class="d-flex justify-content-left ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-date">Start date</label>
                            <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>
                        </div>
                        <div class="mt-1 mb-1 me-3 form-floating">
                            <input id="duration-input" v-model="duration" type="number" class="form-control" />
                            <label for="duration-input">Duration (days)</label>  
                        </div>
                    </div>
                    <hr>
                    <h5 class="ms-3 mt-3">Action period</h5>
                    <div class="d-flex justify-content-left ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-action-date">Start date</label>
                            <vuejs-datepicker v-model="actionStart" format="dd.MM.yyyy." id="start-action-date"></vuejs-datepicker>
                        </div>
                        <div class="mt-1 mb-1 me-3 form-floating">               
                            <input id="action-duration-input" v-model="actionDuration" type="number" class="form-control" />
                            <label for="action-duration-input">Duration (days)</label> 
                        </div>
                    </div>
                    <hr>
                    <div class="d-flex justify-content-evenly mt-3 mb-4">
                        <div class="form-floating">
                            <input type="number" v-model="price" step="0.01" id="price-input" class="form-control">
                            <label for="price-input">Price</label>
                        </div>
                        <div class="form-floating">
                            <input type="number" v-model="maxPeople" id="max-people-input" class="form-control">
                            <label for="max-people-input">Max people</label>
                        </div>
                    </div>
                    <div class="text-end">
                        <button type="button" class="btn btn-primary">Add</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        addAction() {
            alert('Adding');
        }
    }
});