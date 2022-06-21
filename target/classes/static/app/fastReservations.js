Vue.component('fast-reservations', {
    data() {
        return {
            id: null,
            actions: [],
            start: null, duration: null, actionStart: null, actionDuration: null,
            price: null, maxPeople: null, input_started: false,
            role: JSON.parse(localStorage.getItem("jwt")).userRole,
            disabled: {
                to: new Date()
            },
            additional_services: [],
            service: ''
        }
    },

    mounted(){
        this.id = this.$route.params.id;
        this.reload();
    },

    template: `
    <div style="background-color: #f2e488;height: 1000px">
        <update-ship-nav v-if="role=='ROLE_SHIP'"></update-ship-nav>
        <update-cottage-nav v-if="role=='ROLE_COTTAGE'"></update-cottage-nav>
        <div class="d-flex justify-content-start ms-3 mt-3">
            <h2 class="mt-2 ms-3">Fast reservations</h2>
        </div>
        <div class="d-flex justify-content-between">
            <div>
                <div v-for="(fr, i) in actions" class="card mt-3 ms-3 shadow">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div>
                                <h5>Stay period</h5>
                                <label class="form-label">Start date: {{ fr.startStr }} Duration: {{ fr.duration }} days</label>
                            </div>
                            <div class="text-end me-1">
                                <h3 class="text-success">{{ fr.price }} EUR</h3>
                                <h6 class="text-success">{{ fr.maxPeople }} people</h6>
                            </div>
                        </div>
                        <h5 class="mt-3">Action period</h5>
                        <label>Start date: {{ fr.actionStartStr }} Duration: {{ fr.actionDuration }} days</label>
                        <div class="d-flex flex-wrap my-3">
                            <span class="badge bg-success mx-2" v-for="(s, i) in fr.additionalServices" style="font-size: 1.1em">
                                {{ s }}
                            </span>
                        </div>
                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" v-on:click="deleteAction(i)" class="btn btn-outline-danger btn-sm">Delete</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card sticky-top shadow">
                <div class="card-body">
                    <h5 class="ms-3 mt-2">Stay period</h5>
                    <div class="d-flex justify-content-left ms-3 mt-4 me-3 mb-1">
                        <div class="mt-1 ms-3 me-4 mb-1">
                            <label for="start-date">Start date</label>
                            <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date" :disabled-dates="disabled" :monday-first="true"></vuejs-datepicker>
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
                            <vuejs-datepicker v-model="actionStart" format="dd.MM.yyyy." id="start-action-date" :disabled-dates="disabled" :monday-first="true"></vuejs-datepicker>
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
                            <label for="price-input">Price (EUR)</label>
                            <p v-if="!isValidPrice" class="text-danger">Price must be positive</p>
                        </div>
                        <div class="form-floating">
                            <input type="number" v-model="maxPeople" id="max-people-input" class="form-control">
                            <label for="max-people-input">Max people</label>
                            <p v-if="!isValidMaxPeople" class="text-danger">Number of people must be positive</p>
                        </div>
                    </div>
                    <div class="input-group my-4 mx-4">
                        <div class="form-floating">
                            <input type="text" v-model="service" id="additional-service-input" class="form-control">
                            <label for="additional-service-input">Additional service</label>
                        </div>
                        <button type="button" class="btn btn-secondary" v-on:click="addService">
                                <i class="fa fa-plus"></i>
                         </button>
                    </div>
                    <div class="d-flex flex-wrap ms-4 mb-4">
                        <span class="badge bg-success mx-2" v-for="(s, i) in additional_services">
                            {{ s }} <button class="btn btn-success btn-sm" type="button"><i class="fa fa-x" v-on:click="additional_services.splice(i, 1)"></i></button>
                        </span>
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
                maxPeople: this.maxPeople,
                additionalServices: this.additional_services
            };
            if (this.isValidActionDate && this.isValidActionDuration && this.isValidPrice && this.isValidMaxPeople &&
                this.isValidStayDate && this.isValidStayDuration) {
                axios.post("api/offers/addFastReservation/" + this.$route.params.id, fr, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(response => {
                    this.reload();
                }).catch(error => {
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            }
        },

        formatDate(dt) {
            let startStr = dt.toISOString();
            let y = startStr.slice(0, 4);
            let m = startStr.slice(5, 7);
            let d = startStr.slice(8, 10);
            return d + '.' + m + '.' + y + '.';
        },

        deleteAction(index) {
            let actionId = this.actions[index].id;
            axios({
                method: 'delete',
                url: "api/offers/deleteFastReservation/" + this.$route.params.id + "/" + actionId,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.actions.splice(index, 1)
            }).catch(error => {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        reload() {
            axios({
                method: "get",
                url: "api/offers/getFastReservations/" + this.$route.params.id,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.actions = response.data;
                this.additional_services = [];
            }).catch(error => {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        addService() {
            if (this.service) this.additional_services.push(this.service);
            this.service = '';
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