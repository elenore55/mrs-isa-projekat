Vue.component("update-ship", {
    data: function () {
        return {
            name: "My ship",
            description: "This is my ship",
            address: { street: "Marka Pola 12", city: "Beograd", country: "Srbija" },
            price: 1000,
            rules: ["No smoking", "No drinking"],
            additional_info: "This is some additional info",
            rule: "",
            imgPath: "",
            images: ["img1.png", "img2.png"],
            conditions: "14 days before",
            nav_equipment_list: [{name: "GPD", amount: 3}],
            nav_equipment: { name: "", amount: null },
            fishing_equipment_list: [{name: "Rod", amount: 5}, {name: "Hook", amount: "30"}],
            fishing_equipment: { name: "", amount: null },
            ship_type: 1,
            length: 40,
            capacity: 100,
            num_engines: 10,
            engine_power: 100,
            max_speed: 150,
            errors: {
                name: false, description: false, price: false, street: false, city: false, country: false,
                length: false, capacity: false, num_engines: false, power: false, max_speed: false
            }
        };
    },

    template: `
    <form novalidate>
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
            <div class="row my-4 mx-1">
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
                    <textarea v-model="additional_info" class="form-control mt-5" id="info-textarea" style="height: 150px"></textarea>
                    <label for="info-textarea" class="mt-5">Additional info</label>
                </div>
            </div>
        </div>
    </form>
    `
});