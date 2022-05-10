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
                shipType: "",
                length: "",
                numberOfEngines: "",
                powerOfEngine: "",
                maxSpeed: "",
                cancellationConditions: "",
                fishingEquipmentList: [],
                navigationEquipmentList: [],
                shipTypeStr: ""

           }
   },

   mounted() {
           this.id = this.$route.params.id;

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
               this.num_people = response.data.maxPeople;
               this.numberOfEngines = response.data.numberOfEngines;
               this.powerOfEngine = response.data.powerOfEngine;
               this.maxSpeed = response.data.maxSpeed;
               this.cancellationConditions = response.data.cancellationConditions;
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
                <div class= "d-flex align-items-center justify-content-center mt-5 mb-1">
                    <h1>{{name}}</h1>
                </div>
                <div class="col-1">
                </div>
                <div class = "col-4">
                    <div class = "" style="font-size: 18px;">
                        <table style="border-collapse:separate; border-spacing:1em;">
                            <tr> <th style="width: 37%"></th> </tr>
                            <tr> <td >Description: </td> <td> {{description}}</td> </tr>
                            <tr> <td>Address: </td> <td>{{street}}, {{city}}, {{country}}</td> </tr>
                            <tr> <td> Capacity: </td> <td>{{num_people}}</td> </tr>
                            <tr> <td>Rate:</td> <td> {{rate}}</td> </tr>
                            <tr> <td> Price:</td> <td> {{price}}</td> </tr>
                            <tr> <td></td> <td> <button class="btn btn-primary"> Reserve </button></td> </tr>
                        </table>
                    </div>
                </div>

                <div class="col-7 mt-3">
                    <div class= "d-flex align-items-center justify-content-center mt-3">
                        <div class="row">
                            <button v-on:click="getPrevious" class="btn btn-secondary rounded-0" style="height:400px;width:50px;"><</button>
                            <img id="main_image" class="mx-0 px-0" style="height:400px;width:500px;">
                            <button v-on:click="getNext" class="btn btn-secondary rounded-0" style="height:400px;width:50px;"> > </button>
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

            getRate(id){
                   return 9.8;
            },

        },



});