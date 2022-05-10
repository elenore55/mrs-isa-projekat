Vue.component('fast-reservations', {
    data() {
        return {
            id: null,
            actions: [],
            start: null, duration: null, actionStart: null, actionDuration: null,
            price: null, maxPeople: null, input_started: false
        }
    },

    mounted(){
        this.id = this.$route.params.id;
        this.reload();
    },

    template: `
    <div>
        <update-cottage-nav></update-cottage-nav>
        <h3 class="mt-2 ms-3">Fast reservations</h3>
        <div class="w-50">
            <div v-for="(fr, i) in actions" class="card mt-3 ms-3">
                <div class="card-body ms-3">
                    <h5>Stay period</h5>
                    <label>Start date: {{ formatDate(fr.start) }} Duration: {{ fr.duration }} days</label>
                    <h5 class="mt-3">Action period</h5>
                    <label>Start date: {{ formatDate(fr.actionStart) }} Duration: {{ fr.actionDuration }} days</label>
                    <div class="text-end mb-1 me-1 mt-1">
                        <button type="button" v-on:click="deleteAction(i)" class="btn btn-outline-danger btn-sm">Delete</button>
                    </div>
                </div>
            </div>
            <div class="card position-fixed bottom-0 end-0 mb-5">
                <div class="card-body">
                    <h5 class="ms-3 mt-2">Stay period</h5>
                    <div class="d-flex justify-content-left ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-date">Start date</label>
                            <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>
                            <p v-if="!isValidStayDate" class="text-danger">Date must not be empty</p>
                        </div>
                        <div class="mt-1 mb-1 me-3 form-floating">
                            <input id="duration-input" v-model="duration" type="number" class="form-control" />
                            <label for="duration-input">Duration (days)</label>  
                            <p v-if="!isValidStayDuration" class="text-danger">Duration must be positive</p>
                        </div>
                    </div>
                    <hr>
                    <h5 class="ms-3 mt-3">Action period</h5>
                    <div class="d-flex justify-content-left ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-action-date">Start date</label>
                            <vuejs-datepicker v-model="actionStart" format="dd.MM.yyyy." id="start-action-date"></vuejs-datepicker>
                            <p v-if="!isValidActionDate" class="text-danger">Date must not be empty</p>
                        </div>
                        <div class="mt-1 mb-1 me-3 form-floating">               
                            <input id="action-duration-input" v-model="actionDuration" type="number" class="form-control" />
                            <label for="action-duration-input">Duration (days)</label> 
                            <p v-if="!isValidActionDuration" class="text-danger">Duration must be positive</p>
                        </div>
                    </div>
                    <hr>
                    <div class="d-flex justify-content-evenly mt-3 mb-4">
                        <div class="form-floating">
                            <input type="number" v-model="price" step="0.01" id="price-input" class="form-control">
                            <label for="price-input">Price</label>
                            <p v-if="!isValidPrice" class="text-danger">Price must be positive</p>
                        </div>
                        <div class="form-floating">
                            <input type="number" v-model="maxPeople" id="max-people-input" class="form-control">
                            <label for="max-people-input">Max people</label>
                            <p v-if="!isValidMaxPeople" class="text-danger">Number of people must be positive</p>
                        </div>
                    </div>
                    <div class="text-end">
                        <button type="button" class="btn btn-primary" v-on:click="addAction">Add</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        addAction() {
            this.input_started = true;
            let fr = {
                start: this.start,
                duration: this.duration,
                actionStart: this.actionStart,
                actionDuration: this.actionDuration,
                price: this.price,
                maxPeople: this.maxPeople
            };
            if (this.isValidActionDate && this.isValidActionDuration && this.isValidPrice && this.isValidMaxPeople &&
                this.isValidStayDate && this.isValidStayDuration) {
                axios.post("api/cottages/addFastReservation/" + this.$route.params.id, fr).then(response => {
                    this.reload();
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            }
        },

        formatDate(dt) {
            let startStr = new Date(dt).toISOString();
            let y = startStr.slice(0, 4);
            let m = startStr.slice(5, 7);
            let d = startStr.slice(8, 10);
            return d + '.' + m + '.' + y + '.';
        },

        deleteAction(index) {
            let actionId = this.actions[index].id;
            axios.delete("api/cottages/deleteFastReservation/" + this.$route.params.id + "/" + actionId).then(response => {
                this.actions.splice(index, 1)
            }).catch(function (error) {
                alert('An error occurred!');
            });
        },

        reload() {
            axios.get("api/cottages/getFastReservations/" + this.$route.params.id).then(response => {
                this.actions = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    },

    computed: {
        isValidPrice() {
            if (!this.input_started) return true;
            return this.price > 0;
        },

        isValidMaxPeople() {
            if (!this.input_started) return true;
            return this.maxPeople > 0;
        },

        isValidStayDate() {
            if (!this.input_started) return true;
            return !!(this.start)
        },

        isValidActionDate() {
            if (!this.input_started) return true;
            return !!(this.actionStart)
        },

        isValidStayDuration() {
            if (!this.input_started) return true;
            return this.duration > 0;
        },

        isValidActionDuration() {
            if (!this.input_started) return true;
            return this.actionDuration > 0;
        }
    }

});