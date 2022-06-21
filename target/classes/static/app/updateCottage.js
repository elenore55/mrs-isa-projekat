Vue.component("update-cottage", {
    data: function() {
        return {
            cottage: {},
            rule: "",
            numBeds: null,
            errors: {
                name: false,
                description: false,
                price: false,
                street: false,
                city: false,
                country: false
            }
        }
    },

    mounted() {
        this.cottage.id = this.$route.params.id;
        axios({
            method: "get",
            url: "api/cottages/getCottage/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.cottage = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <form novalidate style="background-color: #f2e488">  
        <update-cottage-nav></update-cottage-nav>
        <div class="container card mt-3 shadow-lg w-75 px-1">
            <div class="card-body">
                <div class="row my-4 mx-1">
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa fa-user icon"></i></span>
                            <div class="form-floating has-validation flex-grow-1">
                                <input v-on:focus="errors.name = false" v-model="cottage.name" type="text" class="form-control rounded-0 rounded-end" id="name-input" required/>
                                <label for="name-input">Name</label>
                            </div>
                        </div>
                        <p v-if="!isValidName && errors.name" class="text-danger">Name is required.</p>
                    </div>
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa fa-eur icon"></i></span>
                            <div class="form-floating has-validation flex-grow-1">
                                <input v-on:focus="errors.price = false" v-model="cottage.price" type="number" step="0.01" min="0" class="form-control" id="price-input" required/>
                                <label for="price-input">Price (EUR)</label>
                            </div>
                        </div>
                        <p v-if="!isValidPrice && errors.price" class="text-danger">Price is required.</p>
                    </div>
                </div>
                <div class="row justify-content-center my-4 mx-1">
                    <div class="col form-floating has-validation">
                        <textarea v-on:focus="errors.description = false" v-model="cottage.description" class="form-control" id="desc-input" style="height: 150px" required/>
                        <label for="desc-input" class="ms-2">Description</label>
                        <p v-if="!isValidDescription && errors.description" class="text-danger">Description is required.</p>
                    </div>
                    <div class="col form-floating">
                        <textarea v-model="cottage.additionalInfo" class="form-control" id="info-textarea" style="height: 150px"></textarea>
                        <label for="info-textarea" class="ms-2">Additional info</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col container">
                        <i class="fa fa-home ms-4"></i>
                        <label class="form-label h5 ms-1">Address</label>
                        <div class="row m-1">
                            <div class="col form-floating">
                                <input v-on:focus="errors.street = false" v-model="cottage.address.street" type="text" class="form-control" id="street-input" />
                                <label for="street-input" class="ms-2">Street</label>
                                <p v-if="!isValidStreet && errors.street" class="text-danger">Invalid street name.</p> 
                            </div>
                        </div>
                        <div class="row m-1">
                            <div class="col form-floating">
                                <input v-on:focus="errors.city = false" v-model="cottage.address.city" type="text" class="form-control" id="city-input" />
                                <label for="city-input" class="ms-2">City</label>
                                <p v-if="!isValidCity && errors.city" class="text-danger">Invalid city name.</p>
                            </div>
                            <div class="col form-floating">
                                <input v-on:focus="errors.country = false" v-model="cottage.address.country" type="text" class="form-control" id="country-input"/>
                                <label for="country-input" class="ms-2">Country</label>
                                <p v-if="!isValidCountry && errors.country" class="text-danger">Invalid country name.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row my-4 mx-1">
                    <div class="col">
                        <label class="form-label h5">Rules</label>
                        <div v-for="(r, i) in cottage.rules" class="mb-2">
                            <span>{{ i + 1 }}. {{ r }}</span>
                            <button type="button" v-on:click="cottage.rules.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                        </div>
                        <input v-model="rule" id="rule-input" type="text" class="form-control">
                        <button type="button" v-on:click="addRule" class="btn btn-secondary my-1">Add rule</button>
                    </div>
                    <div class="col">
                        <label class="form-label h5">Rooms</label>
                        <div v-for="(r, i) in cottage.rooms" class="container mb-1 card">
                            <div class="card-body">
                                <p class="card-title h6 mt-1">Room number {{ i + 1 }}</p>
                                <label>Number of beds</label>
                                <div class="row">
                                    <div class="col">
                                        <input v-model="r.numberOfBeds" type="number" min="1" class="form-control w-75">
                                    </div>
                                    <div class="col">
                                        <button type="button" v-on:click="cottage.rooms.splice(i, 1)" class="btn btn-sm btn-outline-danger float-end">Delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <input v-model="numBeds" id="room-input" type="number" min="1" placeholder="Number of beds" class="form-control">            
                            <button type="button" v-on:click="addRoom" class="btn btn-secondary my-1">Add room</button>
                    </div>
                </div>
                <div class="row mt-1">
                    <div class="col text-end">
                        <button type="button" class="btn btn-primary btn-lg" v-on:click="sendRequest">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
   `,
    methods: {
        addRule() {
            if (this.rule) {
                this.cottage.rules.push(this.rule);
            }
            this.rule= "";
        },

        addRoom() {
            if (this.numBeds) {
                this.cottage.rooms.push({numberOfBeds: this.numBeds});
            }
            this.numBeds = null;
        },

        sendRequest() {
            if (this.isValidName && this.isValidDescription && this.isValidPrice && this.isValidAddress) {
                axios.post("api/cottages/updateCottage", this.cottage, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(function(response) {
                    Swal.fire('Success', 'Cottage updated!', 'success');
                }).catch(function (error) {
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            } else {
                this.errors.name = true;
                this.errors.description = true;
                this.errors.price = true;
                this.errors.street = true;
                this.errors.city = true;
                this.errors.country = true;
            }
        }
    },

    computed: {
        isValidName() {
            return !!this.cottage.name;
        },

        isValidDescription() {
            return !!this.cottage.description;
        },

        isValidPrice() {
            return this.cottage.price > 0;
        },

        isValidStreet() {
            return !!this.cottage.address.street;
        },

        isValidCity() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.cottage.address.city && re.test(this.cottage.address.city);
        },

        isValidCountry() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.cottage.address.country && re.test(this.cottage.address.country);
        },

        isValidAddress() {
            return this.isValidStreet && this.isValidCity && this.isValidCountry;
        }
    }

});