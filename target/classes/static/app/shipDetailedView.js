Vue.component("ship-detailed-view", {
   data: function() {
       return {
                id: "",
                name: "",
                description: "",
                street: "",
                city: "",
                country: "",
                num_people: "",
                rate: "",
                images: [],
                image: "",
                price: "",
                image_number: 0,
                additionalInfo: "",

                length: "",
                numberOfEngines: "",
                powerOfEngine: "",
                maxSpeed: "",
                cancellationConditions: "",
                fishingEquipmentList: [],
                navigationEquipmentList: [],
                shipTypeStr: "",
                rules: [],

                clientEmail: "",
                fromDate: "",
                toDate: "",
                offerId: "",


           }
   },

   mounted() {
           this.id = this.$route.params.id;
           this.fromDate = this.$route.params.fromDate;
           this.toDate = this.$route.params.toDate;
           // prvo cemo dobaviti slike
           axios.get("api/ships/getShipImages/" + this.id ).then(response => {
                 this.images = response.data;

            }).catch(function (error) {
                  alert('An error occurred!');
            });

           // sada dobavljamo ostale atribute
           axios.get("api/ships/getShip/" + this.id).then(response => {
               this.name = response.data.name;
               this.description = response.data.description;
               this.street = response.data.address.street;
               this.city = response.data.address.city;
               this.country = response.data.address.country;
               this.additionalInfo = response.data.additionalInfo;
               this.shipType = response.data.shipType;
               this.length = response.data.length;
               this.num_people = response.data.capacity;
               this.numberOfEngines = response.data.numberOfEngines;
               this.powerOfEngine = response.data.powerOfEngine;
               this.maxSpeed = response.data.maxSpeed;
               this.cancellationConditions = response.data.cancellationConditions;
               this.rules = response.data.rules;
               this.fishingEquipmentList = response.data.fishingEquipmentList;
               this.navigationEquipmentList = response.data.navigationEquipmentList;
               this.shipTypeStr = response.data.shipTypeStr;


               this.rate = this.getRate(this.id);
               this.price = response.data.price;
               this.image=this.images[this.image_number];
               main_image = $("#main_image").attr("src",this.image);

           }).catch(function (error) {
               alert('An error occurred!');
           });


       },

   template: `
   <div class="w-100">
       <client-navbar></client-navbar>
        <div class="row mx-3 mt-5 ">
                <div class = "d-flex align-items-center justify-content-center">
                    <div class="col-7">
                        <div class= "d-flex align-items-center justify-content-center mt-5 mb-1">
                            <h1>{{name}} {{shipTypeStr}}</h1>
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

                        <div class = "row m-3">
                            <div class = "col-5 border mb-3 border-secondary rounded" style="font-size: 18px;">
                                Rules:
                                <div v-for="(r, i) in rules" class="mb-2">
                                    <p> {{r}} </p>
                                </div>
                                <hr/>
                                Cancellation conditions:
                                <table style="border-collapse:separate; border-spacing:1em; ">
                                    <tr> <td> {{cancellationConditions}}</td> </tr>
                                </table>
                            </div>
                            <div class="col-1">

                            </div>

                            <div class = "col-6 border mb-3 border-secondary rounded" style="font-size: 18px;">
                                Navigation equipment:
                                <table style="border-collapse:separate; border-spacing:1em; ">
                                    <tr> <td> - Kompas</td> </tr>
                                    <tr> <td>- I jos nesto</td> </tr>
                                </table>
                                <hr/>
                                Fishing equipment:
                                <table style="border-collapse:separate; border-spacing:1em; ">
                                    <tr> <td> - Udica</td> </tr>
                                    <tr> <td> - Jos nesto od opreme</td> </tr>
                                    <tr> <td> - Stap za pecanje</td> </tr>
                                </table>
                            </div>
                        </div>


                        <div class = "row m-3">

                            <div class = "col-5 border mb-3 border-secondary rounded" style="font-size: 18px;">
                                General info
                                <table style="border-collapse:separate; border-spacing:1em; ">
                                    <tr> <td>Capacity:</td> <td>{{num_people}}</td> </tr>
                                    <tr> <td> Length:</td> <td>{{length}} m</td> </tr>
                                    <tr> <td>Max speed:</td> <td> {{maxSpeed}} kmph </td> </tr>
                                </table>
                            </div>

                            <div class="col-1">

                            </div>

                            <div class = "col-6 border mb-3 border-secondary rounded" style="font-size: 18px;">
                                General info
                                <table style="border-collapse:separate; border-spacing:1em; ">
                                    <tr> <td>Rate:</td> <td> {{rate}} </td> </tr>
                                    <tr> <td> Price:</td> <td> {{price}} EUR</td> </tr>
                                </table>
                                <div class="row">
                                    <div class="col-6">
                                    </div>
                                    <div class="col-5 mb-2">
                                        <button v-on:click="makeReservation" class="btn btn-primary btn-lg"> Reserve </button>
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

        makeReservation()  {
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
                     alert("Sve je proslo dobro");
                     location.replace('http://localhost:8000/#/clientHome');
                     }).catch(function (error) {
                         alert('Greska u rezervisanju broda');
                     });
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

            getRate(id){
                   return 9.8;
            },

        },



});