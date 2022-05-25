Vue.component("client-rate", {
   data: function() {
       return {
            reason: "",
            error: false,
            type: "",
            id: 1,
       }
   },

   mounted() {
        main_image = $("body").css("background-image", "");
        main_image = $("body").css("background-size", "100% 200%");
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
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
                                    <p v-if="error" class="text-danger">You must choose one option</p>
                                 <div class="rating">
                                    <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                                    <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
                                    <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                                    <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                                    <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
                                 </div>

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
            sendFeedback(){
            // prvo provjeri da li je sve poslano kako treba
                if (this.reason && this.type)
                {
                    this.error = false;
                    axios.post("api/complaint/add", {
                        content: this.reason,
                        id: this.id
                    }).then(function (response) {
                        alert("Poslala sam feedabck");
                    }).catch(function (error) {
                        alert("An ERROR occurred while sending feedback");
                    });
                }
                else
                {
                    this.error = true;
                }
            },

        },



});