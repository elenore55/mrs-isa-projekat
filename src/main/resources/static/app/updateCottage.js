Vue.component("update-cottage", {
    data: function() {
        return {
            cottage: {
                name: "Ime vikendice",
                description: "Ovo je neki opis",
                address: {
                    street: "Marka Pola",
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
    <form>  
        <h2 class="text-center my-4">Cottage Profile</h2>
        <div class="container">
            <div class="row my-4 mx-1">
                <div class="col form-floating has-validation">
                    <input v-on:focus="cottage.errors.name = false" v-model="cottage.name" type="text" class="form-control" id="name-input" required/>
                    <label for="name-input">Name</label>
                    <p v-if="!isValidName && cottage.errors.name" class="text-danger">Price is required.</p>
                </div>
                <div class="col form-floating has-validation">
                    <input v-on:focus="cottage.errors.description = false" v-model="cottage.description" type="text" class="form-control" id="desc-input" required/>
                    <label for="desc-input">Description</label>
                    <p v-if="!isValidDescription && cottage.errors.description" class="text-danger">Price is required.</p>
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
                            <p v-if="!isValidAddress && cottage.errors.street" class="text-danger">Invalid street name.</p> 
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="cottage.errors.city = false" v-model="cottage.address.city" type="text" class="form-control" id="city-input" />
                            <label for="city-input">City</label>
                            <p v-if="!isValidAddress && cottage.errors.city" class="text-danger">Invalid city name.</p>
                        </div>
                    </div>
                    <div class="row m-1">
                        <div class="col form-floating">
                            <input v-on:focus="cottage.errors.country = false" v-model="cottage.address.country" type="text" class="form-control" id="country-input"/>
                            <label for="country-input">Country</label>
                            <p v-if="!isValidAddress && cottage.errors.country" class="text-danger">Invalid country name.</p>
                        </div>
                    </div>
                </div>
                <div class="col form-floating">
                    <textarea v-model="cottage.additionalInfo" class="form-control mt-5" id="info-textarea" style="height: 150px"></textarea>
                    <label for="info-textarea" class="mt-5">Additional info</label>
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