Vue.component("past-reservations", {
   data: function() {
       return {
           reservations: [],
           id: "",
       }
   },

   mounted() {
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 210%");
        this.reload();
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
            <div class="container mt-5 pt-5">
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
                <div v-for="(r, i) in reservations" class="row p-3 my-2" style="border:1px solid rgb(156, 151, 151); border-radius: 5px;" style="background-color: white">
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
            this.id = this.$route.params.id;
            axios.get("api/reservations/getClientPastReservations/" + this.id).then(response => {
            this.reservations = response.data;
            }).catch(function (error) {
                alert('Greska u get past reservations');
            });
           },


        rate(reservation)
            {
                // treba da ga usmjerim na novu stranicu i proslijedim
                location.replace('http://localhost:8000/#/clientRate/1');
            },

        complain(reservation)
                {
                    location.replace('http://localhost:8000/#/clientComplain/1');
                },

        viewDetails(link)
        {
            window.location.href = 'http://localhost:8000/' + link;
        }
    },



});