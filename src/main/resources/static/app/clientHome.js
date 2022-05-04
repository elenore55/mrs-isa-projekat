Vue.component("client-home", {
   data: function() {
   return {
             entityType: "",
             cottages: [],
             ships: [],
             adventures: [],
             ship_owner_id: 1,
             cottage_owner_id: 2,
             all_cities: [],
             all_countries: [],
             city: "",
             country: "",
             sort_by: "",
             direction: "",
             cottageRadio: false,
             shipRadio: false,
             adventureRadio: false,
             fromDate: "",
             toDate: "",
             error: false,
           }
        },

mounted() {
        this.reload();
    },

   template: `
   <div class="w-100">
       <client-navbar></client-navbar>
       <div class="d-sm-flex align-items-center" style=" margin-top: 80px">
           <div class ="vertical-left col-md-2 m-2 fixed-top" >
                   <h3> - </h3>
                   <h3> - </h3>
                   <form @submit.prevent class="p-3" style="border:1px solid rgb(156, 151, 151); border-radius: 10px;">
                       <h3> Search entities </h3>
                       <div class="form-check">
                           <input v-model="cottageRadio" name = "et"
                           type="radio" id="cottageRadioButton" value="cottage">
                           <label for="cottageRadioButton">Cottage</label>
                       </div>
                       <div class="form-check ">
                           <input v-model="shipRadio" name = "et"
                           type="radio" id="shipRadioButton" value="ship">
                           <label for="shipRadioButton">Ship</label>
                       </div>
                       <div class="form-check">
                           <input v-model="adventureRadio" name = "et"
                           type="radio" id="adventureRadioButton" value="adventure">
                           <label for="adventureRadioButton">Adventure</label>
                       </div>
                       <p v-if="error" class="text-danger">You must choose entity type.</p>

                       <div class="p-2">
                           <div class="form-group col-md-6 ">
                               <label for="startDate" class="control-label">Start Date</label>
                               <input v-model="fromDate" type="date" value='' class="" id="startDate" required>
                           </div>

                           <div class="form-group col-md-6">
                               <label for="endDate" class="control-label">End Date</label>
                               <input v-model="toDate" type="date" value='' class="" id="endDate" required>
                           </div>
                       </div>

                       <div class="p-2">
                           <div class="form-group ">
                               <label for="country" class="control-label">Country</label>
                               <input type="text" value='' class="" id="country">
                           </div>

                           <div class="form-group">
                               <label for="city" class="control-label">City</label>
                               <input type="text" value='' class=" " id="city">
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
                           <button type="submit" class="float-right btn btn-primary" v-on:click="searchEntites">Search</button>
                       </div>
                   </form>
           </div>

           <div class="container col-8">
               <div class="container">
                    <div v-for="(c, i) in cottages" class="container card m-3">
                         <div class="row">
                              <div class="col-3 mt-2">
                                    <img src="https://picsum.photos/id/237/200/200" class="card-img rounded-3 mt-3" width="200" height="200"  alt="cottage image">
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

               <div class="container">
                    <div v-for="(s, i) in ships" class="container card m-3">
                         <div class="row">
                              <div class="col-3 mt-2">
                                   <img src="https://picsum.photos/id/80/200/300" class="card-img rounded-3 mt-3" width="200" height="200"  alt="ship image">
                                   <p class="ms-2 mt-3">{{ s.description }}</p>
                              </div>
                              <div class="col-4 card-body container">
                                   <h3 class="card-title mb-2">{{ s.name }} ({{ s.shipTypeStr }})</h3>
                                   <p class="card-text mt-2 mb-4 h5">{{ s.address.street }}, {{ s.address.city }}, {{ s.address.country }}</p>
                                   <p class="card-text mb-1">Price: {{ s.price }} EUR</p>
                                   <p class="card-text mb-1">Capacity: {{ s.capacity }} people</p>
                                   <p class="card-text mb-1">Length: {{ s.length }} m</p>
                                   <p class="card-text">Max speed: {{ s.maxSpeed }} km/h</p>
                                   <div class="d-flex flex-row mt-3">
                                         <a :href="'/#/updateShip/' + s.id" class="btn btn-primary me-3 mt-3">View</a>
                                         <a @click="setCurrentId(s.id)" class="btn btn-danger mt-3" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-expanded="false" aria-controls="confirm-delete">Delete</a>
                                   </div>
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
            axios.get("api/cottageOwner/getCottages/" + this.cottage_owner_id).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });

            axios.get("api/shipOwner/getShips/" + this.ship_owner_id).then(response => {
                 this.ships = response.data;
            }).catch(function (error) {
                 alert('An error occurred!');
            });
        },

        searchEntites() {
            if (this.fromDate && this.toDate && (this.cottageRadio || this.shipRadio || this.adventureRadio))
            {
                this.error = false;
                alert("Sve je uneseno kako treba. Filtriraj podatke ");
            }
            else {
            this.error = true;

            }
            }
        },
    });
