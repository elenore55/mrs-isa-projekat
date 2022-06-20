Vue.component("add-cottage", {
   data: function() {
       return {
           cottage: {
               name: "",
               description: "",
               address: {
                   street: "",
                   city: "",
                   country: ""
               },
               price: null,
               rules: [],
               additionalInfo: "",
               rooms: [],
               rule: "",
               numBeds: null,
               imgPath: "",
               images: [],
               start_av: null, end_av: null,
               errors: {
                   name: false,
                   description: false,
                   price: false,
                   street: false,
                   city: false,
                   country: false
               },
               owner_id: 2
           },
           input_started: false
       }
   },
   template: `
    <div style="background-color: #f2e488; height: 100%" class="p-4">
        <div class="container card mt-3 shadow-lg w-75 px-2">
            <div class="card-body">
                <h2 class="text-center mb-4 card-title">Add Cottage</h2>
                <div class="container">
                    <div class="row my-4 mx-1">
                        <div class="col">
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa fa-user icon"></i></span>
                                <div class="form-floating has-validation flex-grow-1">
                                    <input v-on:focus="cottage.errors.name = false" v-model="cottage.name" type="text" class="form-control" id="name-input"/>
                                    <label for="name-input">Name</label>
                                </div>
                            </div>
                            <p v-if="!isValidName && cottage.errors.name" class="text-danger">Name is required.</p>
                        </div>
                        <div class="col">
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa fa-eur icon"></i></span>
                                <div class="form-floating flex-grow-1">
                                    <input v-on:focus="cottage.errors.price = false" v-model="cottage.price" type="number" step="0.01" min="0" class="form-control" id="price-input"/>
                                    <label for="price-input">Price (EUR)</label>
                                </div>
                            </div>
                            <p v-if="!isValidPrice && cottage.errors.price" class="text-danger">Price is required.</p>
                        </div>
                    </div>
                    <div class="row my-4 mx-1">
                        <div class="col">
                            <div class="form-floating">
                                <textarea v-on:focus="cottage.errors.description = false" v-model="cottage.description" class="form-control" id="desc-input" style="height: 150px"/>
                                <label for="desc-input">Description</label>
                                <p v-if="!isValidDescription && cottage.errors.description" class="text-danger">Description is required.</p>
                            </div>
                        </div>
                        <div class="col form-floating">
                            <textarea v-model="cottage.additionalInfo" class="form-control" id="info-textarea" style="height: 150px"></textarea>
                            <label for="info-textarea">Additional info</label>
                        </div>
                    </div>
                    <div class="row justify-content-center my-4 mx-1">
                        <div class="col container">
                            <i class="fa fa-home"></i>
                            <label class="form-label h5 ms-1">Address</label>
                            <div class="row my-2">
                                <div class="col form-floating">
                                    <input v-on:focus="cottage.errors.street = false" v-model="cottage.address.street" type="text" class="form-control" id="street-input" />
                                    <label for="street-input">Street</label>
                                    <p v-if="!isValidStreet && cottage.errors.street" class="text-danger">Invalid street name.</p> 
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="col form-floating">
                                    <input v-on:focus="cottage.errors.city = false" v-model="cottage.address.city" type="text" class="form-control" id="city-input" />
                                    <label for="city-input">City</label>
                                    <p v-if="!isValidCity && cottage.errors.city" class="text-danger">Invalid city name.</p>
                                </div>
                                <div class="col form-floating">
                                    <input v-on:focus="cottage.errors.country = false" v-model="cottage.address.country" type="text" class="form-control" id="country-input"/>
                                    <label for="country-input">Country</label>
                                    <p v-if="!isValidCountry && cottage.errors.country" class="text-danger">Invalid country name.</p>
                                </div>
                            </div>
                        </div>
                    </div>      
                    <div class="row mt-5 mx-1">
                        <div class="col">
                            <label class="form-label h5">Rules</label>
                            <div v-for="(r, i) in cottage.rules" class="mb-2">
                                <span>{{ i + 1 }}. {{ r }}</span>
                                <button type="button" v-on:click="cottage.rules.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                            </div>
                            <input v-model="cottage.rule" id="rule-input" type="text" class="form-control">
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
                            <input v-model="cottage.numBeds" id="room-input" type="number" min="1" placeholder="Number of beds" class="form-control">            
                            <button type="button" v-on:click="addRoom" class="btn btn-secondary my-1">Add room</button>
                        </div>
                    </div>
                    <div class="row mt-5 mx-1">
                        <div class="col">
                            <div class="m-3">
                                <h5>Regular reservation period</h5>
                            </div>
                            <div class="d-flex justify-content-start">
                                <div class="me-3 mb-4">
                                    <label for="start-date">Start</label>
                                    <vuejs-datepicker v-model="cottage.start_av" format="dd.MM." id="start-date" :monday-first="true"></vuejs-datepicker>
                                </div>
                                <div class="mb-4">
                                    <label for="end-date">End</label>                
                                    <vuejs-datepicker v-model="cottage.end_av" format="dd.MM." id="end-date" :monday-first="true"></vuejs-datepicker>
                                </div>
                            </div>
                            <p v-if="!areValidDates" class="ms-3 text-danger">Dates are required and must be in ascending order!</p>
                        </div>
                         <div class="col form-group">
                            <label class="form-label h5">Images</label> <br />
                            <div v-for="(img, i) in cottage.images" class="mb-2">
                               <span>{{ img }}</span>
                               <button type="button" v-on:click="cottage.images.splice(i, 1)" class="btn btn-outline-danger btn-sm float-end">Delete</button>
                            </div>
                            <input type="file" class="form-control-file" id="img" name="img" accept="image/*" @change="addImage($event)" multiple>
                          </div>
                    </div>
                    <div class="row my-3">
                        <div class="col text-end">
                            <button type="button" class="btn btn-primary btn-lg" v-on:click="sendRequest">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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

        addImage(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) {
                this.cottage.images.push("images/" + file.name);
            }
        },

        sendRequest() {
            this.input_started = true;
            if (this.isValidName && this.isValidDescription && this.isValidPrice && this.isValidAddress) {
                axios.post("api/cottages/addCottage", {
                    name: this.cottage.name,
                    description: this.cottage.description,
                    address: this.cottage.address,
                    rooms: this.cottage.rooms,
                    price: this.cottage.price,
                    rules: this.cottage.rules,
                    additionalInfo: this.cottage.additionalInfo,
                    imagePaths: this.cottage.images,
                    availableStart: this.start_av,
                    availableEnd: this.end_av,
                    ownerId: this.cottage.owner_id
                }, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(function(response) {
                    Swal.fire('Success', 'Cottage added!', 'success');
                }).catch(function (error) {
                    if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            } else {
                this.cottage.errors.name = true;
                this.cottage.errors.description = true;
                this.cottage.errors.price = true;
                this.cottage.errors.street = true;
                this.cottage.errors.city = true;
                this.cottage.errors.country = true;
            }
        },
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
       },

        areValidDates() {
            if (!this.input_started) return true;
            return !!(this.cottage.start_av && this.cottage.end_av && this.cottage.start_av < this.cottage.end_av);
        }
    }

});