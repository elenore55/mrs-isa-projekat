Vue.component("update-ship", {
    data: function () {
        return {
            id: null,
            ship: {},
            rule: "",
            nav_equipment: { name: "", amount: null },
            fishing_equipment: { name: "", amount: null },
            errors: {
                name: false, description: false, price: false, street: false, city: false, country: false,
                length: false, capacity: false, num_engines: false, power: false, max_speed: false
            }
        };
    },

    mounted() {
        this.id = this.$route.params.id;

        axios({
            method: "get",
            url: "api/ships/getShip/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.ship = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <form novalidate style="background-color: #f2e488">
        <update-ship-nav></update-ship-nav>
        <div class="container card mt-3 shadow-lg w-75 px-1">
            <div class="card-body">
                <div class="row my-4 mx-1">
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa fa-user icon"></i></span>
                            <div class="form-floating has-validation flex-grow-1">
                                <input v-on:focus="errors.name = false" v-model="ship.name" type="text" class="form-control" id="name-input" required/>
                                <label for="name-input">Name</label>
                            </div>
                        </div>
                        <p v-if="!isValidName && errors.name" class="text-danger">Name is required.</p>
                    </div>
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa fa-eur icon"></i></span>
                            <div class="form-floating flex-grow-1">
                                <input v-on:focus="errors.price = false" v-model="ship.price" type="number" step="0.01" min="0" class="form-control" id="price-input" required/>
                                <label for="price-input">Price (EUR)</label>
                            </div>
                        </div>
                        <p v-if="!isValidPrice && errors.price" class="text-danger">Price is required.</p>
                    </div>
                </div>
                <div class="row my-4 mx-1">
                    <div class="col form-floating">
                        <textarea v-on:focus="errors.description = false" v-model="ship.description" class="form-control" id="desc-input" style="height: 150px" required/>
                        <label for="desc-input" class="ms-2">Description</label>
                        <p v-if="!isValidDescription && errors.description" class="text-danger">Description is required.</p>
                    </div>
                    <div class="col form-floating">
                        <textarea v-model="ship.additionalInfo" class="form-control" id="info-textarea" style="height: 150px"></textarea>
                        <label for="info-textarea" class="ms-2">Additional info</label>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col container">
                        <i class="fa fa-home ms-4"></i>
                        <label class="form-label h5 ms-1">Address</label>
                        <div class="row m-1">
                            <div class="col form-floating">
                                <input v-on:focus="errors.street = false" v-model="ship.address.street" type="text" class="form-control" id="street-input" />
                                <label for="street-input" class="ms-2">Street</label>
                                <p v-if="!isValidStreet && errors.street" class="text-danger">Invalid street name.</p>
                            </div>
                        </div>
                        <div class="row m-1">
                            <div class="col form-floating">
                                <input v-on:focus="errors.city = false" v-model="ship.address.city" type="text" class="form-control" id="city-input" />
                                <label for="city-input" class="ms-2">City</label>
                                <p v-if="!isValidCity && errors.city" class="text-danger">Invalid city name.</p>
                            </div>
                            <div class="col form-floating">
                                <input v-on:focus="errors.country = false" v-model="ship.address.country" type="text" class="form-control" id="country-input"/>
                                <label for="country-input" class="ms-2">Country</label>
                                <p v-if="!isValidCountry && errors.country" class="text-danger">Invalid country name.</p>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row mt-4 mb-2 mx-1">
                    <div class="col form-floating">
                        <select class="form-select" v-model="ship.shipType" aria-label="Default select example" id="type-select">
                            <option value="1" selected>Ship</option>
                            <option value="2">Boat</option>
                        </select>
                        <label for="type-select" class="ms-2">Type</label>
                    </div>
                    <div class="col form-floating">
                        <input v-on:focus="errors.length = false" v-model="ship.length" type="number" step="0.1" min="0" class="form-control" id="length-input" required/>
                        <label for="length-input" class="ms-2">Length (m)</label>
                        <p v-if="!isValidLength && errors.length" class="text-danger">Length is required.</p>
                    </div>
                    <div class="col form-floating">
                        <input v-on:focus="errors.capacity = false" v-model="ship.capacity" type="number" min="0" class="form-control" id="capacity-input" required/>
                        <label for="capacity-input" class="ms-2">Capacity</label>
                        <p v-if="!isValidCapacity && errors.capacity" class="text-danger">Capacity is required.</p>
                    </div>
                </div>
                <div class="row mb-4 mt-2 mx-1">
                    <div class="col form-floating">
                        <input v-on:focus="errors.num_engines = false" v-model="ship.numberOfEngines" type="number" min="0" class="form-control" id="num-engines-input" required/>
                        <label for="num-engines-input" class="ms-2">Number of engines</label>
                        <p v-if="!isValidNumEngines && errors.num_engines" class="text-danger">Number of engines is required.</p>
                    </div>
                    <div class="col form-floating">
                        <input v-on:focus="errors.power = false" v-model="ship.powerOfEngine" type="number" min="0" class="form-control" id="power-input" required/>
                        <label for="power-input" class="ms-2">Engine power (kW)</label>
                        <p v-if="!isValidPower && errors.power" class="text-danger">Engine power is required.</p>
                    </div>
                    <div class="col form-floating">
                        <input v-on:focus="errors.max_speed = false" v-model="ship.maxSpeed" type="number" min="0" class="form-control" id="speed-input" required/>
                        <label for="speed-input" class="ms-2">Maximum speed (km/h)</label>
                        <p v-if="!isValidSpeed && errors.max_speed" class="text-danger">Length is required.</p>
                    </div>
                </div>
                <div class="row my-4 mx-1">
                    <div class="col form-floating container me-1">
                        <div class="row">
                            <div class="col">
                                <i class="fa fa-compass"></i>
                                <label class="form-label h5">Navigation equipment</label>       
                            </div>             
                        </div>
                        <div class="row">
                            <div v-for="(n, i) in ship.navigationEquipmentList" class="container card mb-1 ms-1 me-1">
                                <div class="card-body">
                                    <p class="card-title h6 mt-1">{{ n.name }}</p>
                                    <label>Amount</label>
                                    <div class="row">
                                        <div class="col">
                                            <input v-model="n.amount" type="number" min="1" class="form-control w-75">
                                        </div>
                                        <div class="col">
                                            <button type="button" v-on:click="ship.navigationEquipmentList.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                                        </div>
                                    </div>
                                </div> 
                            </div>                    
                        </div>
                        <div class="row">
                            <div class="col form-floating">
                                <input type="text" v-model="nav_equipment.name" id="nav-equipment-input" class="form-control">
                                <label for="nav-equipment-input" class="ms-2">Name</label>
                            </div>
                            <div class="col form-floating">
                                <input type="number" v-model="nav_equipment.amount" id="nav-equipment-amount-input" class="form-control">
                                <label for="nav-equipment-amount-input" class="ms-2">Amount</label>
                            </div>
                        </div>
                        <button type="button" v-on:click="addNavEquipment" class="btn btn-secondary my-1">Add equipment</button>
                    </div>
                    <div class="col form-floating container ms-1">
                        <div class="row">
                            <div class="col">
                                <i class="fa fa-fish"></i>
                                <label class="form-label h5">Fishing equipment</label>      
                            </div>              
                        </div>
                        <div class="row">
                            <div v-for="(n, i) in ship.fishingEquipmentList" class="container card mb-1 ms-1 me-1">
                                <div class="card-body">
                                    <p class="card-title h6 mt-1">{{ n.name }}</p>
                                    <label>Amount</label>
                                    <div class="row">
                                        <div class="col">
                                            <input v-model="n.amount" type="number" min="1" class="form-control w-75">
                                        </div>
                                        <div class="col">
                                            <button type="button" v-on:click="ship.fishingEquipmentList.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                                        </div>
                                    </div>
                                </div> 
                            </div>                    
                        </div>
                        <div class="row">
                            <div class="col form-floating">
                                <input type="text" v-model="fishing_equipment.name" id="fishing-equipment-input" class="form-control">
                                <label for="fishing-equipment-input" class="ms-2">Name</label>
                            </div>
                            <div class="col form-floating">
                                <input type="number" v-model="fishing_equipment.amount" id="fishing-equipment-amount-input" class="form-control">
                                <label for="fishing-equipment-amount-input" class="ms-2">Amount</label>
                            </div>
                        </div>
                        <button type="button" v-on:click="addFishingEquipment" class="btn btn-secondary my-1">Add equipment</button>
                    </div>
                </div>
                <div class="row mt-5 mx-1">
                    <div class="col form-floating">
                        <textarea v-model="ship.cancellationConditions" class="form-control" id="conditions-textarea" style="height: 150px"></textarea>
                        <label for="conditions-textarea" class="ms-2">Cancellation conditions</label>
                    </div>
                    <div class="col">
                        <label class="form-label h5">Rules</label>
                        <div v-for="(r, i) in ship.rules" class="mb-2">
                            <span>{{ i + 1 }}. {{ r }}</span>
                            <button type="button" v-on:click="ship.rules.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                        </div>
                        <input v-model="rule" id="rule-input" type="text" class="form-control">
                        <button type="button" v-on:click="addRule" class="btn btn-secondary my-1">Add rule</button>
                    </div>
                </div>
                <div class="row mt-1 mb-2">
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
                this.ship.rules.push(this.rule);
            }
            this.rule = "";
        },

        addNavEquipment(e) {
            if (this.nav_equipment.name && this.nav_equipment.amount) {
                this.ship.navigationEquipmentList.push({ name: this.nav_equipment.name, amount: this.nav_equipment.amount });
            }
            this.nav_equipment.name = "";
            this.nav_equipment.amount = null;
        },

        addFishingEquipment(e) {
            if (this.fishing_equipment.name && this.fishing_equipment.amount) {
                this.ship.fishingEquipmentList.push({ name: this.fishing_equipment.name, amount: this.fishing_equipment.amount });
            }
            this.fishing_equipment.name = "";
            this.fishing_equipment.amount = null;
        },

        sendRequest() {
            if (this.isValidName && this.isValidDescription && this.isValidPrice && this.isValidAddress &&
                this.isValidLength && this.isValidCapacity && this.isValidNumEngines && this.isValidPower && this.isValidSpeed) {
                axios.post("api/ships/updateShip", this.ship, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(function(response) {
                    Swal.fire('Success', 'Ship updated!', 'success');
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
                this.errors.length = true;
                this.errors.capacity = true;
                this.errors.num_engines = true;
                this.errors.power = true;
                this.errors.max_speed = true;
            }
        },
    },

    computed: {
        isValidName() {
            return !!this.ship.name;
        },

        isValidDescription() {
            return !!this.ship.description;
        },

        isValidPrice() {
            return this.ship.price > 0;
        },

        isValidStreet() {
            return !!this.ship.address.street;
        },

        isValidCity() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.ship.address.city && re.test(this.ship.address.city);
        },

        isValidCountry() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.ship.address.country && re.test(this.ship.address.country);
        },

        isValidAddress() {
            return this.isValidStreet && this.isValidCity && this.isValidCountry;
        },

        isValidLength() {
            return this.ship.length > 0;
        },

        isValidCapacity() {
            return this.ship.capacity > 0;
        },

        isValidNumEngines() {
            return this.ship.numberOfEngines > 0;
        },

        isValidPower() {
            return this.ship.powerOfEngine > 0;
        },

        isValidSpeed() {
            return this.ship.maxSpeed > 0;
        }
    }
});