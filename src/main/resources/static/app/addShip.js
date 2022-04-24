Vue.component("add-ship", {
    data: function () {
        return {
            name: "",
            description: "",
            address: { street: "", city: "", country: "" },
            price: null,
            rules: [],
            additionalInfo: "",
            rule: "",
            imgPath: "",
            images: [],
            conditions: "",
            nav_equipment_list: [],
            nav_equipment: { name: "", amount: null },
            fishing_equipment_list: [],
            fishing_equipment: { name: "", amount: null },
            ship_type: "",
            length: null,
            capacity: null,
            num_engines: null,
            engine_power: null,
            max_speed: null,
            errors: {
                name: false, description: false, price: false, street: false, city: false, country: false,
                length: false, capacity: false, num_engines: false, power: false, max_speed: false
            }
        };
    },

    template: `
    <form novalidate>
        <h2 class="text-center my-4">Add Ship</h2>
        <div class="container">
            <div class="row my-4 mx-1">
                <div class="col form-floating has-validation">
                    <input v-on:focus="errors.name = false" v-model="name" type="text" class="form-control" id="name-input" required/>
                    <p v-if="!isValidName && errors.name" class="text-danger">Name is required.</p>
                    <label for="name-input">Name</label>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.description = false" v-model="description" type="text" class="form-control" id="desc-input" required/>
                    <label for="desc-input">Description</label>
                    <p v-if="!isValidDescription && errors.description" class="text-danger">Description is required.</p>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.price = false" v-model="price" type="number" step="0.01" min="0" class="form-control" id="price-input" required/>
                    <label for="price-input">Price (EUR)</label>
                    <p v-if="!isValidPrice && errors.price" class="text-danger">Price is required.</p>
                </div>
            </div>
            <div class="row justify-content-center my-4 mx-1">
                <div class="col container">
                    <label class="form-label h5">Address</label>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="errors.street = false" v-model="address.street" type="text" class="form-control" id="street-input" />
                            <label for="street-input">Street</label>
                            <p v-if="!isValidStreet && errors.street" class="text-danger">Invalid street name.</p>
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="errors.city = false" v-model="address.city" type="text" class="form-control" id="city-input" />
                            <label for="city-input">City</label>
                            <p v-if="!isValidCity && errors.city" class="text-danger">Invalid city name.</p>
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="errors.country = false" v-model="address.country" type="text" class="form-control" id="country-input"/>
                            <label for="country-input">Country</label>
                            <p v-if="!isValidCountry && errors.country" class="text-danger">Invalid country name.</p>
                        </div>
                    </div>
                </div>
                <div class="col form-floating">
                    <textarea v-model="additionalInfo" class="form-control mt-5" id="info-textarea" style="height: 150px"></textarea>
                    <label for="info-textarea" class="mt-5">Additional info</label>
                </div>
            </div>
            <div class="row my-4 mx-1">
                <div class="col form-floating">
                    <select class="form-select" aria-label="Default select example" id="type-select">
                        <option value="1" selected>Ship</option>
                        <option value="2">Boat</option>
                    </select>
                    <label for="type-select">Type</label>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.length = false" v-model="length" type="number" step="0.1" min="0" class="form-control" id="length-input" required/>
                    <label for="length-input">Length (m)</label>
                    <p v-if="!isValidLength && errors.length" class="text-danger">Length is required.</p>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.capacity = false" v-model="capacity" type="number" min="0" class="form-control" id="capacity-input" required/>
                    <label for="capacity-input">Capacity</label>
                    <p v-if="!isValidCapacity && errors.capacity" class="text-danger">Capacity is required.</p>
                </div>
            </div>
            <div class="row my-4 mx-1">
                <div class="col form-floating">
                    <input v-on:focus="errors.num_engines = false" v-model="num_engines" type="number" min="0" class="form-control" id="num-engines-input" required/>
                    <label for="num-engines-input">Number of engines</label>
                    <p v-if="!isValidNumEngines && errors.num_engines" class="text-danger">Number of engines is required.</p>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.power = false" v-model="engine_power" type="number" min="0" class="form-control" id="power-input" required/>
                    <label for="power-input">Engine power (kW)</label>
                    <p v-if="!isValidPower && errors.power" class="text-danger">Engine power is required.</p>
                </div>
                <div class="col form-floating">
                    <input v-on:focus="errors.max_speed = false" v-model="max_speed" type="number" min="0" class="form-control" id="speed-input" required/>
                    <label for="speed-input">Maximum speed (km/h)</label>
                    <p v-if="!isValidSpeed && errors.max_speed" class="text-danger">Length is required.</p>
                </div>
            </div>
            <div class="row my-4 mx-1">
                <div class="col form-floating container">
                    <div class="row">
                        <label class="form-label h5">Navigation equipment</label>                    
                    </div>
                    <div class="row">
                        <div v-for="(n, i) in nav_equipment_list" class="container card mb-1">
                            <div class="card-body">
                                <p class="card-title h6 mt-1">{{ n.name }}</p>
                                <label>Amount</label>
                                <div class="row">
                                    <div class="col">
                                        <input v-model="n.amount" type="number" min="1" class="form-control w-75">
                                    </div>
                                    <div class="col">
                                        <button type="button" v-on:click="nav_equipment_list.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                                    </div>
                                </div>
                            </div> 
                        </div>                    
                    </div>
                    <div class="row">
                        <div class="col form-floating">
                            <input type="text" v-model="nav_equipment.name" id="nav-equipment-input" class="form-control">
                            <label for="nav-equipment-input">Name</label>
                        </div>
                        <div class="col form-floating">
                            <input type="number" v-model="nav_equipment.amount" id="nav-equipment-amount-input" class="form-control">
                            <label for="nav-equipment-amount-input">Amount</label>
                        </div>
                    </div>
                    <button type="button" v-on:click="addNavEquipment" class="btn btn-secondary my-1">Add</button>
                </div>
                <div class="col form-floating container">
                    <div class="row">
                        <label class="form-label h5">Fishing equipment</label>                    
                    </div>
                    <div class="row">
                        <div v-for="(n, i) in fishing_equipment_list" class="container card mb-1">
                            <div class="card-body">
                                <p class="card-title h6 mt-1">{{ n.name }}</p>
                                <label>Amount</label>
                                <div class="row">
                                    <div class="col">
                                        <input v-model="n.amount" type="number" min="1" class="form-control w-75">
                                    </div>
                                    <div class="col">
                                        <button type="button" v-on:click="fishing_equipment_list.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                                    </div>
                                </div>
                            </div> 
                        </div>                    
                    </div>
                    <div class="row">
                        <div class="col form-floating">
                            <input type="text" v-model="fishing_equipment.name" id="fishing-equipment-input" class="form-control">
                            <label for="fishing-equipment-input">Name</label>
                        </div>
                        <div class="col form-floating">
                            <input type="number" v-model="fishing_equipment.amount" id="fishing-equipment-amount-input" class="form-control">
                            <label for="fishing-equipment-amount-input">Amount</label>
                        </div>
                    </div>
                    <button type="button" v-on:click="addFishingEquipment" class="btn btn-secondary my-1">Add</button>
                </div>
            </div>
            <div class="row mt-5 mx-1">
                <div class="col form-floating">
                    <textarea v-model="conditions" class="form-control" id="conditions-textarea" style="height: 150px"></textarea>
                    <label for="conditions-textarea">Cancellation conditions</label>
                </div>
                <div class="col">
                    <label class="form-label h5">Rules</label>
                    <div v-for="(r, i) in rules" class="mb-2">
                        <span>{{ i + 1 }}. {{ r }}</span>
                        <button type="button" v-on:click="rules.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                    </div>
                    <input v-model="rule" id="rule-input" type="text" class="form-control">
                    <button type="button" v-on:click="addRule" class="btn btn-secondary my-1">Add rule</button>
                </div>
                <div class="col form-group">
                    <label class="form-label h5">Images</label> <br />
                    <div v-for="(img, i) in images" class="mb-2">
                        <span>{{ img }}</span>
                        <button type="button" v-on:click="images.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                    </div>
                    <input type="file" class="form-control-file" id="img" name="img" accept="image/*" @change="addImage($event)" multiple>
                </div>
            </div>
        </div>
    </form>
    `,

    methods: {
        addRule() {
            if (this.rule) {
                this.rules.push(this.rule);
            }
            this.rule = "";
        },

        addRoom() {
            if (this.numBeds) {
                this.rooms.push({numberOfBeds: this.numBeds});
            }
            this.numBeds = null;
        },

        addImage(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) {
                this.images.push(file.name);
            }
        },

        addNavEquipment(e) {
            if (this.nav_equipment.name && this.nav_equipment.amount) {
                this.nav_equipment_list.push({ name: this.nav_equipment.name, amount: this.nav_equipment.amount });
            }
            this.nav_equipment.name = "";
            this.nav_equipment.amount = null;
        },

        addFishingEquipment(e) {
            if (this.fishing_equipment.name && this.fishing_equipment.amount) {
                this.fishing_equipment_list.push({ name: this.fishing_equipment.name, amount: this.fishing_equipment.amount });
            }
            this.fishing_equipment.name = "";
            this.fishing_equipment.amount = null;
        },

        sendRequest() {

        },
    },

    computed: {
        isValidName() {
            return !!this.name;
        },

        isValidDescription() {
            return !!this.description;
        },

        isValidPrice() {
            return this.price > 0;
        },

        isValidStreet() {
            return !!this.address.street;
        },

        isValidCity() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.address.city && re.test(this.address.city);
        },

        isValidCountry() {
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.address.country && re.test(this.address.country);
        },

        isValidAddress() {
            return this.isValidStreet && this.isValidCity && this.isValidCountry;
        },

        isValidLength() {
            return true;
        },

        isValidCapacity() {
            return true;
        },

        isValidNumEngines() {
            return true;
        },

        isValidPower() {
            return true;
        },

        isValidSpeed() {
            return true;
        }
    }
});