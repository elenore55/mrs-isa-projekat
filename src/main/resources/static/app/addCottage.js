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
               price: 0,
               rules: [],
               additionalInfo: "",
               rooms: [],
               room: {
                   numberOfBeds: 0,
                   images: []
               }
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
      <input type="submit" value="Submit" />
    </form>
   `,
    methods: {

    }
});