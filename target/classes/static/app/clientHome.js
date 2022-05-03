Vue.component("client-home", {
   data: function() {
   return {

             entityType: "",
             cottages: [],
             owner_id: 2,
             sort_by_list: ["Name", "Price", "Number of rooms", "Rating"],
             search_criterion: "",
             all_cities: [],
             all_countries: [],
             cities: [],
             countries: [],
             country: "",
             low_price: null,
             high_price: null,
             sort_by: "",
             direction: "",
             price_error: false,
             current_id: null,

           }
        },

mounted() {
        this.reload();
    },

   template: `
   <div class="w-100">
       <client-navbar></client-navbar>
       <div class="d-sm-flex align-items-center" style=" margin-top: 80px">
           <div class ="vertical-left col-md-2 m-2" style="border:1px solid rgb(156, 151, 151); border-radius: 10px">
                   <form class="p-3">
                       <h3> Search entities </h3>
                       <div class="form-check">
                           <input v-model="this.entityType" name = "et"
                           type="radio" id="cottageRadioButton" value="cottage">
                           <label for="cottageRadioButton">Cottage</label>
                       </div>
                       <div class="form-check ">
                           <input v-model="this.entityType" name = "et"
                           type="radio" id="shipRadioButton" value="ship">
                           <label for="shipRadioButton">Ship</label>
                       </div>
                       <div class="form-check">
                           <input v-model="this.entityType" name = "et"
                           type="radio" id="adventureRadioButton" value="adventure">
                           <label for="adventureRadioButton">Adventure</label>
                       </div>

                       <div class="p-2">
                           <div class="form-group col-md-6 ">
                               <label for="startDate" class="control-label">Start Date</label>
                               <input type="date" value='' class="" id="startDate" required>
                           </div>

                           <div class="form-group col-md-6">
                               <label for="endDate" class="control-label">End Date</label>
                               <input type="date" value='' class="" id="endDate" required>
                           </div>
                       </div>

                       <div class="p-2">
                           <div class="form-group ">
                               <label for="country" class="control-label">Country</label>
                               <input type="text" value='' class="" id="country" required>
                           </div>

                           <div class="form-group">
                               <label for="city" class="control-label">City</label>
                               <input type="text" value='' class=" " id="city" required>
                           </div>
                       </div>

                       <div class="row py-3">
                           <div class="">
                               <label for="rate"> Rate (from)</label>
                               <input type="number" value='' min="0" max="10" class="col-md-4" id="rate">
                           </div>
                       </div>

                       <hr/>

                       <div class="row py-3">
                           <div class="">
                               <label for="sort"> Sort by</label>
                               <select class="mdb-select md-form">
                                   <option value="" disabled selected>...</option>
                                   <option value="1">Name</option>
                                   <option value="2">Rate</option>
                                   <option value="3">Address</option>
                                   <option value="4">Price</option>
                                 </select>
                           </div>
                       </div>
                       <hr/>

                       <div class="row mx-5">
                           <button type="submit" class="float-right btn btn-primary">Search</button>
                       </div>
                   </form>
           </div>

           <h3 v-if="cottages.length == 0" class="text-info ms-5 mt-3">No cottages to show</h3>
           <div class="container">
                <div v-for="(c, i) in cottages" class="container card m-3">
                     <div class="row">
                          <div class="col-3 mt-2">
                                <img src="https://picsum.photos/id/81/200/300" class="card-img rounded-3 mt-3" width="200" height="200"  alt="cottage image">
                                <p class="ms-2 mt-3">{{ c.description }}</p>
                          </div>
                          <div class="col-3 card-body container">
                               <h3 class="card-title mb-2">{{ c.name }}</h3>
                               <p class="card-text mt-2 mb-4 h5">{{ c.address.street }}, {{ c.address.city }}, {{ c.address.country }}</p>
                               <p class="card-text mb-2">Price: {{ c.price }} EUR</p>
                               <p class="card-text mb-2">Number of rooms: {{ c.rooms.length }}</p>
                               <p class="card-text">Number of beds: {{ c.numberOfBeds }}</p>
                               <div class="d-flex flex-row mt-3">
                                    <a :href="'/#/updateCottage/' + c.id" class="btn btn-primary me-3 mt-3">View</a>

                               </div>
                          </div>
                     </div>
                </div>
           </div>

       </div>
   </div>
   `,

methods: {

        reload() {
            axios.get("api/cottageOwner/getCottages/" + this.owner_id).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    }

});