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
                   street: false,
                   city: false,
                   country: false
               }
           }
       }
   },
   template: `
    <form>
      <h2>Add Cottage</h2>
      <div id="wrapper1">
          <div id="name-div">
            <input v-on:focus="cottage.errors.name = false" v-model="cottage.name" type="text" placeholder="Name" />
            <p v-if="!isValidName && cottage.errors.name" class="error-msg">Name is required.</p>
          </div>
          <div id="desc-div">
            <input v-on:focus="cottage.errors.description = false" v-model="cottage.description" type="text" placeholder="Description" />
            <p v-if="!isValidDescription && cottage.errors.description" class="error-msg">Description is required.</p>
          </div>
          <div id="price-div">
            <input v-on:focus="cottage.errors.price = false" v-model="cottage.price" type="number" step="0.01" min="0" placeholder="Price (EUR)" />
            <p v-if="!isValidPrice && cottage.errors.price" class="error-msg">Price is required.</p>
          </div>
      </div>
      <div id="wrapper2">
          <div id="address-div">
            <label>Address</label>
            <div>
              <div>
                <input v-on:focus="cottage.errors.street = false" v-model="cottage.address.street" type="text" placeholder="Street" />
                <p v-if="!isValidAddress && cottage.errors.street" class="error-msg">Invalid street name.</p>
              </div>
              <div>
                <input v-on:focus="cottage.errors.city = false" v-model="cottage.address.city" type="text" placeholder="City" />
                <p v-if="!isValidAddress && cottage.errors.city" class="error-msg">Invalid city name.</p>
              </div>
              <div>
                <input v-on:focus="cottage.errors.country = false" v-model="cottage.address.country" type="text" placeholder="Country"/>
                <p v-if="!isValidAddress && cottage.errors.country" class="error-msg">Invalid country name.</p>
              </div>
            </div>
          </div>
          <div id="info-div">
            <label>Additional info</label>
            <br />
            <textarea v-model="cottage.additionalInfo" cols="30" rows="5"></textarea>
          </div>
      </div>
      <div id="wrapper3">
          <div id="rules-div">
            <label>Rules</label>
            <div v-for="(r, i) in cottage.rules" id="rules-div-inner">
            <p>{{ i + 1 }}. {{ r }}</p>
            </div>
            <input v-model="cottage.rule" id="rule-input" type="text">
            <button v-on:click="addRule">Add rule</button>
          </div>
          <div id="rooms-div">
            <label>Rooms</label>
            <div v-for="(r, i) in cottage.rooms" id="rooms-div-inner">
               <p>Room number {{ i + 1 }}: {{ r }} beds</p>
            </div>
            <input v-model="cottage.numBeds" id="room-input" type="number" min="1" placeholder="Number of beds">
            <button v-on:click="addRoom">Add room</button>
          </div>
          <div id="images-div">
            <label>Images</label>
            <div v-for="img in cottage.images" id="images-div-inner">
               <p>{{ img }}</p>
            </div>
            <input type="file" id="img" name="img" accept="image/*" @change="addImage($event)" multiple>
          </div>
      </div>
      <button id="add-submit-btn" v-on:click="sendRequest">Submit</button>
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
       }
    }

});