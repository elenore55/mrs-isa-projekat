Vue.component("client-complain", {
   data: function() {
       return {
            reason: "",
            error: false,
            type: "",
            id: 0,
            token: {}
       }
   },

   mounted() {
           this.token = JSON.parse(localStorage.getItem("jwt"));
           this.id = this.token.userId;
        //main_image = $("body").css("background-image", "url('images/login3.jpg')");
        //main_image = $("body").css("background-size", "100% 200%");
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
        <div class="d-flex justify-content-center mt-5">
           <div class="collapse bg-light shadow rounded w-50 mt-5" id="confirm-cancel">
               <p class=" d-flex justify-content-center mt-5 mb-3"> Your complaint has been saved!</p>
               <div class="d-flex justify-content-center">
                   <a class="btn btn-lg btn-outline-secondary m-4" role="button" v-on:click="hideItself">OK</a>
               </div>
           </div>
       </div>

            <div class="container mt-5 pt-5">
                <div my-5 mx-5 pt-5 align-items-center>
                    <div class = "my-5 d-flex align-items-center justify-content-center">
                         <form @submit.prevent>
                              <h4 class="text-center">What do You want to complain about? </h4>
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
                              <h4 class="text-center my-3">  Elaborate Your complaint </h4>
                              <textarea v-model="reason" class = "" name="textarea" rows="10" cols="70"></textarea>
                              <br>
                              <p v-if="error" class="text-danger">You must fill all form fields.</p>
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
            // prvo provjeri da li je sve poslano kako treba
                if (this.reason && this.type)
                {
                    this.error = false;
                    /*axios.post("api/complaint/add", {
                        content: this.reason,
                        id: this.id
                    }).then(function (response) {
                        //alert("Poslala sam feedback");
                        $("#confirm-cancel").show(200);
                    }).catch(function (error) {
                        alert("An ERROR occurred while sending feedback");
                    });*/

                    axios({
                       method: 'post',
                       url: "api/complaint/add", data: {
                            content: this.reason,
                            id: this.id,
                        },
                       headers: {
                           Authorization: "Bearer " + this.token.accessToken
                       }
                   }).then(response => {
                       $("#confirm-cancel").show(200);
                   }).catch(function (error) {
                       if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                       else Swal.fire('Error', 'Something went wrong!', 'error');
                   });
                }
                else
                {
                    this.error = true;
                }
            },

        },



});