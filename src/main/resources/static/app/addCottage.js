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
               numBeds: null
           }
       }
   },
   template: `
    <form>
      <h2>Add Cottage</h2>
      <div>
        <input v-model="cottage.name" type="text" placeholder="Name" />
        <br />
      </div>
      <div>
        <input v-model="cottage.description" type="text" placeholder="Description" />
      </div>
      <div>
        <input v-model="cottage.price" type="number" step="0.01" min="0" placeholder="Price (EUR)" />
      </div>
      <div id="address-div">
        <label>Address</label>
        <div>
          <input v-model="cottage.address.street" type="text" placeholder="Street" />
          <br />
          <input v-model="cottage.address.city" type="text" placeholder="City" />
          <br />
          <input v-model="cottage.address.country" type="text" placeholder="Country"/>
        </div>
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
      <input type="submit" value="Submit" />
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
        }
    }
});