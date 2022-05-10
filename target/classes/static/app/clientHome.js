Vue.component("client-home", {
   data: function() {
   return {
             entityType: "",
             cottages: [],
             ships: [],
             adventures: [],
             all_cities: [],
             all_countries: [],
             city: "",
             country: "",
             rate: "",
             sortBy: "",
             sortByList: ["...", "Name", "Rate", "Address", "Price"],
             direction: "",
             fromDate: "",
             toDate: "",
             rate: "",
             error: false,
             cottage_pictures: [],
             ship_pictures: [],
             adventure_pictures: [],
             default_cottage: "images/cottage_icon.jpg",
             default_ship: "images/ship_icon.png",
             default_adventure: "images/fishing_icon.jpg"
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
                   <form @submit.prevent class="p-3 bg-warning" style="border:1px solid rgb(156, 151, 151); border-radius: 5px;">
                       <h3> Search entities </h3>
                       <div class="form-check">
                           <input v-model="entityType" name = "et"
                           type="radio" id="cottageRadioButton" value="cottage">
                           <label for="cottageRadioButton">Cottage</label>
                       </div>
                       <div class="form-check ">
                           <input v-model="entityType" name = "et"
                           type="radio" id="shipRadioButton" value="ship">
                           <label for="shipRadioButton">Ship</label>
                       </div>
                       <div class="form-check">
                           <input v-model="entityType" name = "et"
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
                           <div class="form-group">
                               <label for="country" class="control-label">Country</label>
                               <input v-model="country" type="text" value='' class="" id="country">
                           </div>

                           <div class="form-group">
                               <label for="city" class="control-label">City</label>
                               <input v-model="city" type="text" value='' class=" " id="city">
                           </div>
                       </div>

                       <div class="row py-3">
                           <div class="">
                               <label for="rate"> Rate (from)</label>
                               <input v-model="rate" type="number" value='' min="0" max="10" class="col-md-4" id="rate">
                           </div>
                       </div>

                       <hr/>

                       <div class="row py-3">
                           <div class="">
                               <label for="sort"> Sort by</label>
                               <select v-model="sortBy" class="mdb-select md-form">
                                   <option value="" disabled selected>...</option>
                                   <option value="1">{{sortByList[1]}}</option>
                                   <option value="2">{{sortByList[2]}}</option>
                                   <option value="3">{{sortByList[3]}}</option>
                                   <option value="4">{{sortByList[4]}}</option>
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
                   <div v-for="(c, i) in cottages" class="container card m-3  border-warning border-2">
                        <div class="row pb-3">
                             <div class="col-3 mt-2">
                                   <img :src="cottage_pictures.at(i)" class="card-img rounded-3 mt-3" width="200" height="200"  alt="cottage image">
                                   <p class="ms-2 mt-3">{{ c.description }}</p>
                             </div>
                             <div class="col-5 card-body container">
                                  <h3 class="card-title mb-2">{{ c.name }}</h3>
                                  <p class="card-text mt-2 mb-4 h5">{{ c.address.street }}, {{ c.address.city }}, {{ c.address.country }}</p>
                                  <p class="card-text mb-2">Number of rooms: {{ c.rooms.length }}</p>
                                  <p class="card-text">Number of beds: {{ c.numberOfBeds }}</p>
                                  <div class="row ">
                                      <div class="col-2 my-auto">
                                          <p>Rate:  </p>
                                      </div>
                                      <div class="col-8">
                                          <h1> 9.9 </h1>
                                      </div>
                                  </div>
                             </div>
                             <div class="col-4 flex-column mt-auto mx-auto py-2>
                                 <p class="">Price per night: {{c.price}} EUR</p>
                                 <div class="text-center">
                                     <a :href="'/#/cottageDetailedView/' + c.id" class="btn btn-primary me-3 mt-3" style="height:40px;width:100px;">View</a>
                                 </div>
                            </div>
                        </div>
                   </div>
               </div>

              <div class="container">
                   <div v-for="(s, i) in ships" class="container card m-3 border-warning border-2">
                        <div class="row pb-3">
                             <div class="col-3 mt-2">
                                  <img :src="ship_pictures.at(i)" class="card-img rounded-3 mt-3" width="200" height="200"  alt="ship image">
                                  <p class="ms-2 mt-3">{{ s.description }}</p>
                             </div>
                             <div class="col-5 card-body container">
                                  <h3 class="card-title mb-2">{{ s.name }} ({{ s.shipTypeStr }})</h3>
                                  <p class="card-text mt-2 mb-4 h5">{{ s.address.street }}, {{ s.address.city }}, {{ s.address.country }}</p>
                                  <p class="card-text mb-1">Capacity: {{ s.capacity }} people</p>
                                  <p class="card-text mb-1">Length: {{ s.length }} m</p>
                                  <p class="card-text">Max speed: {{ s.maxSpeed }} km/h</p>
                                  <div class="row ">
                                        <div class="col-2 my-auto">
                                            <p>Rate:  </p>
                                        </div>
                                        <div class="col-8">
                                            <h1> 9.9 </h1>
                                        </div>
                                    </div>
                             </div>
                             <div class="col-4 flex-column mt-auto mx-auto py-2>
                                  <p class="" style="text-align:center;">Price: {{s.price}} EUR</p>
                                  <div class="text-center">
                                      <a :href="'/#/shipDetailedView/' + s.id" class="btn btn-primary me-3 mt-3" style="height:40px;width:100px;">View</a>
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
            axios.get("api/cottages/getCottagesWithRate/").then(response => {
                this.cottages = response.data;
                for (const c of this.cottages) {
                    if (!c.imagePaths || c.imagePaths.length === 0) {
                        this.cottage_pictures.push(this.default_cottage);
                    } else {
                        this.cottage_pictures.push(c.imagePaths.at(0));
                    }
                }
            }).catch(function (error) {
                alert('An error occurred!');
            });

            axios.get("api/ships/getShips/").then(response => {
                 this.ships = response.data;
                 for (const s of this.ships) {
                     if (!s.imagePaths || s.imagePaths.length === 0) {
                         this.ship_pictures.push(this.default_ship);
                     } else {
                         this.ship_pictures.push(s.imagePaths.at(0));
                     }
                 }
            }).catch(function (error) {
                 alert('An error occurred!');
            });
        },

        viewCottage() {
            // ovdje ce za sada ici samo preusmjeravanje na narednu stranicu, a poslije cemo vidjeti kako dalje
            //location.replace('http://localhost:8000/#/cottageDetailedView');
        },


        searchEntites() {
            if (this.fromDate && this.toDate && (this.entityType))
            {
                this.cottages = [];
                this.ships = [];
                this.adventures = [];
                this.error = false;
                if (this.entityType=="cottage")      // ako su odabrane vikendice, nuliracemo ostale i samo njih prikazati
                {
                    axios.post("api/cottages/filter", {
                         fromDate: this.fromDate,
                         toDate: this.toDate,
                         country: this.country,
                         city: this.city,
                         rate: this.rate,
                         sortByList: this.sortByList,
                         sortBy: this.sortBy,
                         direction: this.direction,
                    }).then(response => {
                    this.cottages = response.data;
                    }).catch(function (error) {
                         alert('An error occurred!');
                    });

                }
                else if (this.entityType=="ship")
                    {
                        axios.post("api/ships/filter", {
                             fromDate: this.fromDate,
                             toDate: this.toDate,
                             country: this.country,
                             city: this.city,
                             rate: this.rate,
                             sortByList: this.sortByList,
                             sortBy: this.sortBy,
                             direction: this.direction,
                        }).then(response => {
                        this.ships = response.data;
                        }).catch(function (error) {
                             alert('An error occurred!');
                        });
                    }
            }

            else {
            this.error = true;
            }
        }
          },
    });
