Vue.component("past-reservations", {
   data: function() {
       return {
           reservations: [],
           id: 6,
           sortEntity: "",
           sortBy: "",
           token: {},

       }
   },

   mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 210%");
        this.reload();
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
            <div class="container mt-5 pt-5">

                <div class="row">
                    <div class="col-md-2 col-sm-6">
                        <label> Select entity </label>
                        <select v-model="sortEntity" class="mdb-select md-form">
                            <option value="" disabled selected>...</option>
                            <option value="1">Cottages</option>
                            <option value="2">Ships</option>
                            <option value="3">Adventures</option>
                        </select>
                    </div>

                    <div class="col-md-2 col-sm-6">
                        <label> Sort by </label>
                        <select v-model="sortBy" class="mdb-select md-form">
                            <option value="" disabled selected>...</option>
                            <option value="1">Start date</option>
                            <option value="2">Duration</option>
                            <option value="3">Price</option>
                        </select>
                    </div>

                    <div class="col-md-2 col-sm-6">
                        <button class="btn btn-primary" v-on:click="filter">Search</button>
                    </div>
                </div>



                <div class="row p-3 mt-5" style="background-color: white">

                    <div class="col-2">
                        VIEW DETAILS
                    </div>

                    <div class="col-2">
                        START DATE
                    </div>

                    <div class="col-2">
                        END DATE
                    </div>

                    <div class="col-2">
                        PRICE (EUR)
                    </div>

                    <div class="col-4">

                    </div>
                </div>
                <div v-for="(r, i) in reservations" class="row p-3 my-2" style="border:1px solid rgb(156, 151, 151); border-radius: 5px; background-color: white" >
                    <div class="col-2">
                        <a v-on:click="viewDetails(r.link)" style="color:blue;"><u> {{r.name}} </u></a>
                    </div>

                    <div class="col-2">
                        {{r.startDate}}
                    </div>

                    <div class="col-2">
                         {{r.endDate}}
                    </div>

                    <div class="col-2">
                        {{r.price}}
                    </div>

                    <div class="col-2">
                        <button class="btn btn-primary" v-on:click="rate(r)" style="width:2cm;"> Rate </button>
                    </div>

                    <div class="col-2">
                        <button class="btn btn-primary" v-on:click="complain(r)" > Complain  </button>
                    </div>
               </div>
            </div>
        </div>
   `,
    methods: {

        reload() {
            /*axios.post("api/reservations/getClientPastReservations/", {
                sortEntity: this.sortEntity,
                sortBy: this.sortBy,
                id: this.id,
            }).then(response => {
            this.reservations = response.data;
            }).catch(function (error) {
                alert('Greska u get past reservations');
            });*/
            axios({
               method: 'post',
               url: "api/reservations/getClientPastReservations/", data: {
                    sortEntity: this.sortEntity,
                    sortBy: this.sortBy,
                    id: this.id,
                },
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.reservations = response.data;
           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
           },


        rate(reservation)
            {
                //location.replace('http://localhost:8000/index.html#/clientRate/' + reservation.id);
                this.$router.push({path: '/clientRate/' + reservation.id});
            },

        complain(reservation)
                {
                    //location.replace('http://localhost:8000/index.html#/clientComplain/' + reservation.id);
                    this.$router.push({path: '/clientComplain/' + reservation.id});
                },

        viewDetails(link)
        {
            //window.location.href = 'http://localhost:8000/' + link;
            this.$router.push({path: '/' + link});
        },

        filter()
        {
            this.reload();
        }
    },



});