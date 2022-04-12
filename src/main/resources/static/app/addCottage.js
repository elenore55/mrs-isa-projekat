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
               errors: {
                   name: false,
                   description: false,
                   price: false,
                   address: false
               }
           }
       }
   },
   template: `
    <form>
      <h2>Add Cottage</h2>
      <div>
        <input v-on:focus="cottage.errors.name = false" v-model="cottage.name" type="text" placeholder="Name" />
        <p v-if="!isValidName && cottage.errors.name" class="error-msg">Name is required.</p>
      </div>
      <div>
        <input v-on:focus="cottage.errors.description = false" v-model="cottage.description" type="text" placeholder="Description" />
        <p v-if="!isValidDescription && cottage.errors.description" class="error-msg">Description is required.</p>
      </div>
      <div>
        <input v-on:focus="cottage.errors.price = false" v-model="cottage.price" type="number" step="0.01" min="0" placeholder="Price (EUR)" />
        <p v-if="!isValidPrice && cottage.errors.price" class="error-msg">Price is required.</p>
      </div>
      <div id="address-div">
        <label>Address</label>
        <div>
          <input v-on:focus="cottage.errors.address = false" v-model="cottage.address.street" type="text" placeholder="Street" />
          <br />
          <input v-on:focus="cottage.errors.address = false" v-model="cottage.address.city" type="text" placeholder="City" />
          <br />
          <input v-on:focus="cottage.errors.address = false" v-model="cottage.address.country" type="text" placeholder="Country"/>
        </div>
        <p v-if="!isValidAddress && cottage.errors.address" class="error-msg">Invalid address.</p>
      </div>
      <div>
        <label>Additional info</label>
        <br />
        <textarea v-model="cottage.additionalInfo" cols="25" rows="5"></textarea>
      </div>
      <div>
        <label>Rules</label>
        <div v-for="(r, i) in cottage.rules" id="rules-div">
        <p>{{ i + 1 }}. {{ r }}</p>
        </div>
        <input v-model="cottage.rule" id="rule-input" type="text">
        <button v-on:click="addRule">Add rule</button>
      </div>
      <div>
        <label>Rooms</label>
        <div v-for="(r, i) in cottage.rooms" id="rooms-div">
           <p>Room number {{ i + 1 }}: {{ r }} beds</p>
        </div>
        <input v-model="cottage.numBeds" id="room-input" type="number" min="1" placeholder="Number of beds">
        <button v-on:click="addRoom">Add room</button>
      </div>
      <div>
        <label>Images</label>
        <div v-for="img in cottage.images" id="images-div">
           <p>{{ img }}</p>
        </div>
        <input type="file" id="img" name="img" accept="image/*" @change="addImage($event)" multiple>
      </div>
      <button v-on:click="sendRequest">Submit</button>
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
                this.cottage.rooms.push(this.cottage.numBeds);
            }
            this.cottage.numBeds = null;
        },

        addImage(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) {
                this.cottage.images.push(file.name);
            }
        },

        sendRequest() {
            if (this.isValidName && this.isValidDescription && this.isValidPrice && this.isValidAddress) {
            } else {
                this.cottage.errors.name = true;
                this.cottage.errors.description = true;
                this.cottage.errors.price = true;
                this.cottage.errors.address = true;
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
           return !!this.cottage.address.city;
       },

       isValidCountry() {
           return !!this.cottage.address.country;
       },

       isValidAddress() {
           return this.isValidStreet && this.isValidCity && this.isValidCountry;
       }
    }

});