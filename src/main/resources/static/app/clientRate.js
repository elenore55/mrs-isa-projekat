Vue.component("client-rate", {
   data: function() {
       return {
            reason: "",
            errorRadio: false,
            errorRate: false,
            type: "",
            clientId: 6,
            reservationId: "",
            rate: "",
       }
   },

   mounted() {
        this.reservationId = this.$route.params.id;
        main_image = $("body").css("background-image", "");
        main_image = $("body").css("background-size", "100% 200%");
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
        <div class="d-flex justify-content-center mt-5">
           <div class="collapse bg-light shadow rounded w-50 mt-5" id="confirm-cancel">
               <p class=" d-flex justify-content-center mt-5 mb-3"> Your feedback has been saved! </p>
               <div class="d-flex justify-content-center">
                   <a class="btn btn-lg btn-outline-secondary m-4" role="button" v-on:click="hideItself">OK</a>
               </div>
           </div>
               </div>
            <div class="container mt-5 pt-5">
                <div my-5 mx-5 pt-5 align-items-center>
                    <div class = "my-5 d-flex align-items-center justify-content-center">
                         <form @submit.prevent>
                              <h4 class="text-center"> What do You want to rate? </h4>
                                <div class = "d-flex align-items-center justify-content-center">
                                  <div class="form-check form-check-inline">
                                    <input v-model="type" type="radio" id="owner" name="for-complain" value="Owner">
                                    <label for="owner">Owner</label><br>
                                  </div>

                                  <div class="form-check form-check-inline">
                                    <input v-model="type" type="radio" id="offer" name="for-complain" value="Offer">
                                    <label for="offer">Offer</label><br>
                                  </div>
                                </div>
                                <p v-if="errorRadio" class="text-danger">You must choose one option</p>
                                 <div class="rating">
                                    <input type="radio" v-model="rate" name="rating" value="5" id="5"><label for="5">☆</label>
                                    <input type="radio" v-model="rate" name="rating" value="4" id="4"><label for="4">☆</label>
                                    <input type="radio" v-model="rate" name="rating" value="3" id="3"><label for="3">☆</label>
                                    <input type="radio" v-model="rate" name="rating" value="2" id="2"><label for="2">☆</label>
                                    <input type="radio" v-model="rate" name="rating" value="1" id="1"><label for="1">☆</label>
                                 </div>
                              <p v-if="errorRate" class="text-danger">You must input rate</p>

                              <h4 class="text-center my-3"> Write a review: </h4>
                              <textarea v-model="reason" class = "" name="textarea" rows="10" cols="70"></textarea>
                              <br>

                              <button v-on:click="sendFeedback" class="btn btn-primary float-end">Submit</button>
                         </form>
                    </div>
                </div>
            </div>
    </div>
   `,
    methods: {

            hideItself()
                {
                    $("#confirm-cancel").hide(200);
                },

            sendFeedback(){
            // mora unijeti koga ocjenjuje i broj zvjezdica
            // ne mora razlog
                if (this.rate && this.type)
                {
                    this.errorRate = false;
                    this.errorRadio = false;
                    axios.post("api/feedback/add", {
                        comment: this.reason,
                        rating: this.rate,
                        reservationId: this.reservationId,
                    }).then(function (response) {
                        //alert("Poslala sam feedback");
                        $("#confirm-cancel").show(200);
                    }).catch(function (error) {
                        alert("An ERROR occurred while sending feedback");
                    });
                }
                else
                {
                    if (!this.rate)
                    {
                        this.errorRate = true;
                    }
                    else {
                        this.errorRate = false;
                    }

                    if (!this.type)
                    {
                        this.errorRadio = true;
                    }
                    else
                    {
                        this.errorRadio = false;
                    }

                }
            },

        },



});