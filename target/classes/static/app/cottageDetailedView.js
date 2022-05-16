Vue.component("cottage-detailed-view", {
   data: function() {
       return {
                id: "",
                fromDate: "",
                toDate: "",
                name: "",
                description: "",
                street: "",
                city: "",
                country: "",
                num_rooms: "",
                num_bed: "",
                rate: "",
                images: [],
                image: "",
                price: "",
                rate: "",
                image_number: 0,
                rules: []

           }
   },

   mounted() {
           this.id = this.$route.params.id;
           alert("Id ovdje glasi " + this.id);
           alert("Duzina parametara je " + this.$route.params.length)
           this.fromDate = this.$route.params.fromDate;
           alert("From date ovdje glasi ", this.fromDate);
           this.toDate = this.$route.params.dto;
           alert("To date ovdje glasi ", this.toDate);
           axios.get("api/cottages/getCottageImages/" + this.id ).then(response => {
                 this.images = response.data;

            }).catch(function (error) {
                  alert('An error occurred!');
            });

           // sada dobavljamo ostale atrinute
           axios.get("api/cottages/getCottageWithRate/" + this.id).then(response => {
               this.name = response.data.name;
               this.description = response.data.description;
               this.street = response.data.address.street;
               this.city = response.data.address.city;
               this.country = response.data.address.country;
               this.num_rooms = response.data.rooms.length;
               this.num_bed = response.data.numberOfBeds;
               this.rate = response.data.rate;
               this.price = response.data.price;
               this.image=this.images[this.image_number];
               this.rules = response.data.rules;
               main_image = $("#main_image").attr("src",this.image);

           }).catch(function (error) {
               alert('An error occurred!');
           });


       },

   template: `
   <div class="w-100">
       <client-navbar></client-navbar>
        <div class = "d-flex align-items-center justify-content-center mt-5">
            <div class="col-7">
                <div class= "d-flex align-items-center justify-content-center mt-5 mb-1">
                    <h1>{{name}}</h1>
                </div>

                <div class= "d-flex mb-1">
                    <i class="fa fa-fw fa-map-marker"></i>
                    {{street}}, {{city}}, {{country}}
                </div>

                <div class="my-3">
                    <div class= "d-flex align-items-center justify-content-center mt-3">
                        <div class="d-flex align-items-left justify-content-left mt-3">
                            <button v-on:click="getPrevious" class="btn btn-white rounded-0 border-secondary" style="height:500px;width:50px;"><</button>
                            <img id="main_image" class="mx-0 px-0" style="height:500px;width:770px;">
                            <button v-on:click="getNext" class="btn btn-secondary rounded-0" style="height:500px;width:50px;"> > </button>
                        </div>
                    </div>
                </div>

                <div class= "d-flex mt-3 mb-5">
                    {{description}}
                </div>

                <div class = "row mx-3">
                    <div class="col-5 border border-secondary rounded bg-warning p-3 mb-3" style="font-size: 18px;">
                        <label class="form-label h5">Rules</label>
                        <div v-for="(r, i) in rules" class="mb-2">
                            <p> {{r}} </p>
                        </div>
                    </div>
                    <div class="col-1">
                    </div>
                    <div class = "col-6 border border-secondary rounded bg-warning mb-3" style="font-size: 18px;">
                        <table style="border-collapse:separate; border-spacing:1em; ">
                            <tr> <td>Number of rooms:</td> <td> {{num_rooms}} </td> </tr>
                            <tr> <td> Number of beds:</td> <td>{{num_bed}}</td> </tr>
                            <tr> <td>Rate:</td> <td> {{adjustRate(rate)}} </td> </tr>
                            <tr> <td> Price:</td> <td>  {{price}} EUR</td> </tr>
                            <tr> <td></td> <td> <button v-on:click="makeReservation" class="btn btn-primary btn-lg"> Reserve </button></td> </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>

   </div>
   `,

methods: {

        getNext() {
            // ovdje bi trebalo staviti narednu sliku
            this.image_number++;
            if (this.image_number==this.images.length)
            {
                this.image_number = 0;
            }
            this.image = this.images[this.image_number];
            main_image = $("#main_image").attr("src",this.image);
            },

        getPrevious() {
            // ovdje bi trebalo staviti narednu sliku
            this.image_number--;
            if (this.image_number<0)
            {
                this.image_number += this.images.length
            }
            this.image = this.images[this.image_number];
            main_image = $("#main_image").attr("src",this.image);
            },

        adjustRate(rate)
        {
            if (rate==-1) return "Not rated yet";
            return rate;
        },

        makeReservation()
        {
        // ovdje treba poslati zahtjev da se napravi nova rezervacija
        // trenutno mi od podataka fali: id korisnika, start date, end date
        // client id - napisan da fali
        // offer id - imam
        // recenzija id - prazno
        // rezervacija - bice automatski
            axios.get("api/cottages/makeReservation/" + this.id ).then(response => {
                 alert("Rezervacija usojesno napravljena");

            }).catch(function (error) {
                  alert('An error occurred!');
            });

        }


    },



});