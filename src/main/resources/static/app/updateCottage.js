Vue.component("update-cottage", {
    data: function() {
        return {
            cottage: {
                name: "Ime vikendice",
                description: "Ovo je neki opis",
                address: {
                    street: "Marka Pola 1",
                    city: "Novi Sad",
                    country: "Srbija"
                },
                price: 300,
                rules: ["No smoking", "Pets allowed"],
                additionalInfo: "This is my info.",
                rooms: [{numberOfBeds: 3}, {numberOfBeds: 4}],
                rule: "",
                numBeds: null,
                imgPath: "",
                images: ["img1.png", "img2.png"],
                errors: {
                    name: false,
                    description: false,
                    price: false,
                    street: false,
                    city: false,
                    country: false
                }
            }
        }
    },
    template: `
    <form novalidate>  
        <h2 class="text-center my-4">Cottage Profile</h2>
        <div class="container">
            <div class="row my-4 mx-1">
                <div class="col form-floating has-validation">
                    <input v-on:focus="cottage.errors.name = false" v-model="cottage.name" type="text" class="form-control" id="name-input" required/>
                    <label for="name-input">Name</label>
                    <p v-if="!isValidName && cottage.errors.name" class="text-danger">Name is required.</p>
                </div>
                <div class="col form-floating has-validation">
                    <input v-on:focus="cottage.errors.description = false" v-model="cottage.description" type="text" class="form-control" id="desc-input" required/>
                    <label for="desc-input">Description</label>
                    <p v-if="!isValidDescription && cottage.errors.description" class="text-danger">Description is required.</p>
                </div>
                <div class="col form-floating has-validation">
                    <input v-on:focus="cottage.errors.price = false" v-model="cottage.price" type="number" step="0.01" min="0" class="form-control" id="price-input" required/>
                    <label for="price-input">Price (EUR)</label>
                    <p v-if="!isValidPrice && cottage.errors.price" class="text-danger">Price is required.</p>
                </div>
            </div>
            <div class="row justify-content-center my-4 mx-1">
                <div class="col container">
                    <label class="form-label h5">Address</label>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="cottage.errors.street = false" v-model="cottage.address.street" type="text" class="form-control" id="street-input" />
                            <label for="street-input">Street</label>
                            <p v-if="!isValidStreet && cottage.errors.street" class="text-danger">Invalid street name.</p> 
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="cottage.errors.city = false" v-model="cottage.address.city" type="text" class="form-control" id="city-input" />
                            <label for="city-input">City</label>
                            <p v-if="!isValidCity && cottage.errors.city" class="text-danger">Invalid city name.</p>
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="cottage.errors.country = false" v-model="cottage.address.country" type="text" class="form-control" id="country-input"/>
                            <label for="country-input">Country</label>
                            <p v-if="!isValidCountry && cottage.errors.country" class="text-danger">Invalid country name.</p>
                        </div>
                    </div>
                </div>
                <div class="col form-floating">
                    <textarea v-model="cottage.additionalInfo" class="form-control mt-5" id="info-textarea" style="height: 150px"></textarea>
                    <label for="info-textarea" class="mt-5">Additional info</label>
                </div>
            </div>
            <div class="row my-4 mx-1">
                <div class="col">
                    <label class="form-label h5">Rules</label>
                    <div v-for="(r, i) in cottage.rules" class="mb-2">
                        <span>{{ i + 1 }}. {{ r }}</span>
                        <button v-on:click="cottage.rules.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                    </div>
                    <input v-model="cottage.rule" id="rule-input" type="text" class="form-control">
                    <button v-on:click="addRule" class="btn btn-secondary my-1">Add rule</button>
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
                                    <button v-on:click="cottage.rooms.splice(i, 1)" class="btn btn-sm btn-outline-danger float-end">Delete</button>
                                </div>
                            </div>
                        </div>
                    </div>
                        <input v-model="cottage.numBeds" id="room-input" type="number" min="1" placeholder="Number of beds" class="form-control">            
                        <button v-on:click="addRoom" class="btn btn-secondary my-1">Add room</button>
                </div>
            </div>
            <div class="row mt-1">
                <div class="col text-end">
                    <button class="btn btn-primary btn-lg" v-on:click="sendRequest">Submit</button>
                </div>
            </div>
        </div>
    </form>
   `,
    methods: {
        addRule() {
            if (this.cottage.rule) {
                this.cottage.rules.push(this.cottage.rule);
            }
            this.cottage.rule = "";
        },

        addRoom() {
            if (this.cottage.numBeds) {
                this.cottage.rooms.push({numberOfBeds: this.cottage.numBeds});
            }
            this.cottage.numBeds = null;
        },

        sendRequest() {
            if (this.isValidName && this.isValidDescription && this.isValidPrice && this.isValidAddress) {
                axios.post("api/cottages/updateCottage", {
                    name: this.cottage.name,
                    description: this.cottage.description,
                    address: this.cottage.address,
                    rooms: this.cottage.rooms,
                    price: this.cottage.price,
                    rules: this.cottage.rules,
                    additionalInfo: this.cottage.additionalInfo,
                    imagePaths: this.cottage.images,
                    ownerId: 1
                }).then(function(response) {
                    alert('Cottage successfully updated!');
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            } else {
                this.cottage.errors.name = true;
                this.cottage.errors.description = true;
                this.cottage.errors.price = true;
                this.cottage.errors.street = true;
                this.cottage.errors.city = true;
                this.cottage.errors.country = true;
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