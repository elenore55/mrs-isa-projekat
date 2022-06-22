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
             numberOfPeople: "",
             sortBy: "",
             sortByList: ["...", "Name", "Rate", "Country", "City", "Price"],
             direction: "",
             fromDate: "",
             toDate: "",
             error_date: false,
             error_radio: false,
             cottage_pictures: [],
             ship_pictures: [],
             adventure_pictures: [],
             default_cottage: "images/cottage_icon.jpg",
             default_ship: "images/ship_icon.png",
             default_adventure: "images/fishing_icon.jpg",
             token: {},
             id: 0,
             canShow: false,
             disabled: {
                 to: new Date()
             },

           }
        },

mounted() {
        main_image = $("body").css("background-image", "url('images/set.webp')");
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = JSON.parse(localStorage.getItem("jwt")).userId,
        this.reload();
    },

   template: `
   <div class="w-100">
       <client-navbar></client-navbar>
       <div class="d-sm-flex align-items-center" style=" margin-top: 80px">
           <div id="outer" class ="vertical-left col-md-3 fixed-top h-100 d-inline-block pb-2" style="margin-top: 80px; margin-left: 10px">
               <form @submit.prevent class="p-2 bg-warning h-100 d-inline-block" style="border:1px solid rgb(156, 151, 151); border-radius: 5px;">
                   <h3> Search entities </h3>
                   <div class="form-check form-check-inline">
                       <input v-model="entityType" name = "et"
                       type="radio" id="cottageRadioButton" value="cottage">
                       <label for="cottageRadioButton">Cottage</label>
                   </div>
                   <div class="form-check form-check-inline">
                       <input v-model="entityType" name = "et"
                       type="radio" id="shipRadioButton" value="ship">
                       <label for="shipRadioButton">Ship</label>
                   </div>
                   <div class="form-check form-check-inline">
                       <input v-model="entityType" name = "et"
                       type="radio" id="adventureRadioButton" value="adventure">
                       <label for="adventureRadioButton">Adventure</label>
                   </div>
                   <p v-if="error_radio" class="text-danger">You must choose entity type.</p>

                   <div class="py-1">
                       <div class="form-group col-md-5 ">
                           <label for="start-date">Start date</label>
                           <vuejs-datepicker v-model="fromDate" id="start-date" :disabled-dates="disabled"></vuejs-datepicker>
                       </div>

                       <div class="form-group col-md-5">
                           <label for="end-date">End date</label>

                           <vuejs-datepicker v-model="toDate" id="end-date" :disabled-dates="disabled"></vuejs-datepicker>
                       </div>
                   </div>
                   <p v-if="error_date" class="text-danger">You must choose start and end date.</p>

                   <div class="row py-1">
                       <div class="form-group col-md-6">
                           <label for="country" class="control-label">Country</label>
                           <input v-model="country" type="text" value='' class="col-md-10" id="country">
                       </div>

                       <div class="form-group col-md-6">
                           <label for="city" class="control-label">City</label>
                           <input v-model="city" type="text" value='' class="col-md-10" id="city">
                       </div>
                   </div>

                   <div class="row py-1">
                       <div class="">
                           <label for="rate"> Rate (from)</label>
                           <input v-model="rate" type="number" value='' min="0" max="10" class="col-md-2" id="rate">
                       </div>
                   </div>

                   <div class="row py-1">
                       <div class="">
                           <label for="people"> Number of people</label>
                           <input v-model="numberOfPeople" type="number" value='' min="0"  class="col-md-2" id="people">
                       </div>
                   </div>
                   <hr/>

                   <div class="row py-1">
                       <div class="">
                           <label for="sort"> Sort by</label>
                           <select v-model="sortBy" class="mdb-select md-form">
                               <option value="" disabled selected>{{sortByList[0]}}</option>
                               <option value="1">{{sortByList[1]}}</option>
                               <option value="2">{{sortByList[2]}}</option>
                               <option value="3">{{sortByList[3]}}</option>
                               <option value="4">{{sortByList[4]}}</option>
                               <option value="5">{{sortByList[5]}}</option>
                             </select>
                       </div>
                   </div>
                   <hr/>
                   <div class="row mx-3">
                       <button type="submit" class="float-right btn btn-primary" v-on:click="searchEntites">Search</button>
                   </div>
               </form>
           </div>

                   <div style="padding-left:370px">
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
                                                      <h3> {{adjustRate(c.rate)}} </h3>
                                                  </div>
                                              </div>
                                         </div>
                                         <div class="col-4 flex-column mt-auto mx-auto py-2">
                                             <p style="font-size:20px;" class="">Price: {{c.price}} EUR</p>
                                             <div v-if="canShow" class="text-center"  >
                                                 <a :href="'/index.html#/cottageDetailedView2/' + c.id + '/' + fromDate + '/' + toDate" class="btn btn-primary me-3 mt-3" style="height:40px;width:100px;">View</a>
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
                                              <div class="row">
                                                    <div class="col-3 my-auto">
                                                        <p>Rate:</p>
                                                    </div>
                                                    <div class="col-8">
                                                        <h3> {{adjustRate(s.rate)}}  </h3>
                                                    </div>
                                              </div>
                                         </div>
                                         <div class="col-4 flex-column mt-auto mx-auto py-2">
                                              <p style="font-size:20px;" class="">Price: {{s.price}} EUR</p>
                                              <div v-if="canShow" class="text-center">
                                                  <a :href="'/index.html#/shipDetailedView2/' + s.id + '/' + fromDate + '/' + toDate" class="btn btn-primary me-3 mt-3" style="height:40px;width:100px;">View</a>
                                              </div>
                                         </div>
                                    </div>
                               </div>
                           </div>


                           <div class="container">
                                 <div v-for="(a, i) in adventures" class="container card m-3 border-warning border-2">
                                     <div class="row pb-3">
                                         <div class="col-3 mt-2">
                                             <img :src="adventure_pictures.at(i)" class="card-img rounded-3 mt-3" width="200" height="200">
                                              <p class="ms-2 mt-3">{{a.description}}
                                              </p>
                                         </div>
                                         <div class="col-5 card-body container">
                                              <h3 class="card-title mb-2">{{a.name}}</h3>
                                              <p class="card-text mt-2 mb-4 h5">{{a.address.street}}, {{a.address.city}}, {{a.address.country}}</p>
                                              <p class="card-text mb-2">Max number of people: {{a.maxPeople}}</p>
                                              <div class="row ">
                                                   <div class="col-3 my-auto">
                                                       <p> Rate:  </p>
                                                   </div>
                                                   <div class="col-8">
                                                       <h3> {{adjustRate(a.rate)}}  </h3>
                                                   </div>
                                              </div>
                                         </div>
                                         <div class="col-4 flex-column mt-auto mx-auto py-2">
                                              <p style="font-size:20px;">Price: {{a.price}} EUR</p>
                                              <div v-if="canShow" class="text-center">
                                                  <a :href="'/index.html#/adventureDetailedView/' + a.id + '/' + fromDate + '/' + toDate" class="btn btn-primary me-3 mt-3" style="height:40px;width:100px;">View</a>
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
            /*axios.get("api/cottages/getCottagesWithRate/").then(response => {
                this.cottages = response.data;
                for (const c of this.cottages) {
                    if (!c.imagePaths || c.imagePaths.length === 0) {
                        this.cottage_pictures.push(this.default_cottage);
                    } else {
                        this.cottage_pictures.push(c.imagePaths.at(0));
                    }
                }
            }).catch(function (error) {
                alert("Greska u get cottages");
                if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });*/
            axios({
                    method: 'get',
                    url: "api/cottages/getCottagesWithRate/",
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(response => {
                //alert("Duzina dobavljenih vikendica je " + response.data.length);
                    this.cottages = response.data;
                        for (const c of this.cottages) {
                            if (!c.imagePaths || c.imagePaths.length === 0) {
                                this.cottage_pictures.push(this.default_cottage);
                            } else {
                                this.cottage_pictures.push(c.imagePaths.at(0));
                            }
                        }
                }).catch(function (error) {
                    // alert("Greskaaa u get cottages");
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });

            /*axios.get("api/ships/getShipsWithRate/").then(response => {
                 this.ships = response.data;
                 for (const s of this.ships) {
                     if (!s.imagePaths || s.imagePaths.length === 0) {
                         this.ship_pictures.push(this.default_ship);
                     } else {
                         this.ship_pictures.push(s.imagePaths.at(0));
                     }
                 }
            }).catch(function (error) {
             alert("Greska u get ships");
             if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
             else Swal.fire('Error', 'Something went wrong!', 'error');
            });*/

            axios({
                method: 'get',
                url: "api/ships/getShipsWithRate/",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                //alert("Duzina dobavljenih brodova je " + response.data.length);
                this.ships = response.data;
                for (const s of this.ships) {
                     if (!s.imagePaths || s.imagePaths.length === 0) {
                         this.ship_pictures.push(this.default_ship);
                     } else {
                         this.ship_pictures.push(s.imagePaths.at(0));
                     }
                 }
            }).catch(error => {
                // alert("Greskaaa u get ships");
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });


            /*axios.get("api/adventures/all/").then(response => {
                 this.adventures = response.data;
                 for (const a of this.adventures) {
                     if (!a.imagePaths || a.imagePaths.length === 0) {
                         this.adventure_pictures.push(this.default_adventure);
                     } else {
                         this.adventure_pictures.push(a.imagePaths.at(0));
                     }
                 }
            }).catch(function (error) {
                 alert('An error occurred!');
            });*/

            axios({
                method: 'get',
                url: "api/adventures/all/",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                //alert("Duzina dobavljenih avantura je " + response.data.length);
                this.adventures = response.data;
                for (const a of this.adventures) {
                     if (!a.imagePaths || a.imagePaths.length === 0) {
                         this.adventure_pictures.push(this.default_adventure);
                     } else {
                         this.adventure_pictures.push(a.imagePaths.at(0));
                     }
                 }
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });

        },

        adjustRate(rate)
        {
            if (rate==-1)
            return "Not rated";
            return rate.toFixed(1);
        },

        searchEntites() {

            if (!this.entityType)
            {
                this.error_radio = true;
            }
            if (!this.fromDate || !this.toDate)
            {
                this.error_date = true;
            }
            if (this.fromDate && this.toDate && (this.entityType))
            {
                this.canShow = true;
                this.cottages = [];
                this.ships = [];
                this.adventures = [];
                this.error_date = false;
                this.error_radio = false;
                if (this.entityType=="cottage")      // ako su odabrane vikendice, nuliracemo ostale i samo njih prikazati
                {
                    /*axios.post("api/cottages/filter", {
                         fromDate: this.fromDate,
                         toDate: this.toDate,
                         country: this.country,
                         city: this.city,
                         rate: this.rate,
                         numberOfPeople: this.numberOfPeople,
                         sortByList: this.sortByList,
                         sortBy: this.sortBy,
                         direction: this.direction,
                    }).then(response => {
                    this.cottages = response.data;
                    }).catch(function (error) {
                         alert('An error occurred!');
                    });*/

                    axios({
                       method: 'post',
                       url: "api/cottages/filter/", data: {
                            fromDate: this.fromDate,
                             toDate: this.toDate,
                             country: this.country,
                             city: this.city,
                             rate: this.rate,
                             numberOfPeople: this.numberOfPeople,
                             sortByList: this.sortByList,
                             sortBy: this.sortBy,
                             direction: this.direction,
                        },
                       headers: {
                           Authorization: "Bearer " + this.token.accessToken
                       }
                   }).then(response => {
                       this.cottages = response.data;
                   }).catch(function (error) {
                       if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                       else Swal.fire('Error', 'Something went wrong!', 'error');
                   });

                }
                else if (this.entityType=="ship")
                    {
                        /*axios.post("api/ships/filter", {
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
                        });*/
                        axios({
                           method: 'post',
                           url: "api/ships/filter/", data: {
                                fromDate: this.fromDate,
                                 toDate: this.toDate,
                                 country: this.country,
                                 city: this.city,
                                 rate: this.rate,
                                 numberOfPeople: this.numberOfPeople,
                                 sortByList: this.sortByList,
                                 sortBy: this.sortBy,
                                 direction: this.direction,
                            },
                           headers: {
                               Authorization: "Bearer " + this.token.accessToken
                           }
                       }).then(response => {
                           this.ships = response.data;
                       }).catch(function (error) {
                           if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                           else Swal.fire('Error', 'Something went wrong!', 'error');
                       });
                    }

                else if (this.entityType=="adventure")
                    {
                        /*axios.post("api/ships/filter", {
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
                        });*/
                        axios({
                           method: 'post',
                           url: "api/adventures/filter/", data: {
                                fromDate: this.fromDate,
                                 toDate: this.toDate,
                                 country: this.country,
                                 city: this.city,
                                 rate: this.rate,
                                 numberOfPeople: this.numberOfPeople,
                                 sortByList: this.sortByList,
                                 sortBy: this.sortBy,
                                 direction: this.direction,
                            },
                           headers: {
                               Authorization: "Bearer " + this.token.accessToken
                           }
                       }).then(response => {
                           this.adventures = response.data;
                       }).catch(function (error) {
                           if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                           else Swal.fire('Error', 'Something went wrong!', 'error');
                       });
                    }
                }
            }
        },
    });
