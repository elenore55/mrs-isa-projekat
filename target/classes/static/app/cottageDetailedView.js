Vue.component("cottage-detailed-view", {
   data: function() {
       return {
                id: "",
                fromDate: "",
                toDate: "",
                name: "",
                description: "",
                additionalInfo: "",
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
           this.fromDate = this.$route.params.fromDate;
           this.toDate = this.$route.params.toDate;

           main_image = $("body").css("background-image", "");
           //main_image = $("body").css("background-size", "100% 210%");

           axios.get("api/cottages/getCottageImages/" + this.id ).then(response => {
                 this.images = response.data;

            }).catch(function (error) {
                  alert('An error occurred!');
            });

           // sada dobavljamo ostale atrinute
           axios.get("api/cottages/getCottageWithRate/" + this.id).then(response => {
               this.name = response.data.name;
               this.description = response.data.description;
               this.additionalInfo = response.data.additionalInfo;
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

       <div class="d-flex justify-content-center mt-5">
           <div class="collapse bg-light shadow rounded w-50 mt-5" id="confirm-cancel">
               <p class=" d-flex justify-content-center mt-5 mb-3"> Reservation successfully added</p>
               <div class="d-flex justify-content-center">
                   <a class="btn btn-lg btn-outline-secondary m-4" role="button" v-on:click="hideItself">OK</a>
               </div>
           </div>
       </div>


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
                            <button v-on:click="getPrevious" class="btn btn-secondary rounded-0 border-secondary" style="height:500px;width:50px;"><</button>
                            <img id="main_image" class="mx-0 px-0" style="height:500px;width:770px;">
                            <button v-on:click="getNext" class="btn btn-secondary rounded-0" style="height:500px;width:50px;"> > </button>
                        </div>
                    </div>
                </div>

                <div class= "d-flex mt-3 mb-5">
                    {{description}}
                </div>
                <div class= "d-flex mt-3 mb-5">
                    {{additionalInfo}}
                </div>

                <div class = "row mx-3">
                    <div class="col-5 border border-secondary rounded p-3 mb-3 shadow-lg" style="font-size: 18px;">
                        <label class="form-label h5">Rules</label>
                        <div v-for="(r, i) in rules" class="mb-2">
                            <p> {{r}} </p>
                        </div>
                    </div>
                    <div class="col-1">
                    </div>
                    <div class = "col-6 border border-secondary rounded shadow-lg mb-3" style="font-size: 18px;">
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

        hideItself()
            {
                $("#confirm-cancel").hide(200);
            },

        makeReservation()
        {
            this.clientEmail = "jelenababic142@gmail.com";
            //this.clientEmail = "milica.popovic55@hotmail.com";
            this.offerId = 6;
            this.fromDate = String(this.fromDate);
            this.toDate = String(this.toDate);
            axios.post("api/reservations/addReservationStringDate", {
                 id: 6,
                 clientEmail: this.clientEmail,
                 offerId: this.id,
                 fromDate: this.fromDate,
                 toDate: this.toDate,

                 }).then(function(response) {
                     //alert("Sve je proslo dobro");
                     $("#confirm-cancel").show(200);
                     //location.replace('http://localhost:8000/#/clientHome');
                     }).catch(function (error) {
                         alert('Greska u cottage detailed');
                         // preusmjeri na stranicu za login sa greskom
                     });
        }


    },



});