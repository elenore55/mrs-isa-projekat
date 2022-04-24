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
            conditions: [],
            condition: "",
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
                name: false,
                description: false,
                price: false,
                street: false,
                city: false,
                country: false
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
        }
    }
});