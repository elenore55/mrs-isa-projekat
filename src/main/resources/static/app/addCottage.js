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
    <label>Name</label>
    <input v-model="cottage.name" type="text">
    <label>Description</label>
    <input v-model="cottage.description" type="text">
    <label>Address</label>
    <div>
        <label>Street</label>
        <input v-model="cottage.address.street" type="text">
        <label>City</label>
        <input v-model="cottage.address.city" type="text">
        <label>Country</label>
        <input v-model="cottage.address.country" type="text">
    </div>
    <label>Price</label>
    <input v-model="cottage.price" type="number" step="0.01" min="0">
    <label>Additional info</label>
    <textarea v-model="cottage.additionalInfo" cols="20" rows="5"></textarea>
   </form>
   `,
    methods: {

    }
});