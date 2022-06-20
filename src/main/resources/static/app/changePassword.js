Vue.component("change-password", {
   data: function() {
       return {
           form: {
               old: "",
               newPass: "",
               confirm: "",
               id: "",
               error: false,
               token: {}
           }
       }
   },

   mounted() {
           this.token = JSON.parse(localStorage.getItem("jwt"));
           this.form.id = this.token.userId;
           main_image = $("body").css("background-image", "url('images/set.webp')");
           main_image = $("body").css("background-size", "100% 210%");

          },
   template: `
   <div class="w-100">
        <client-navbar></client-navbar>
        <section>
            <div class="container mt-5 pt-5">
                        <div class="row mt-5">
                            <div class="col-lg-4 col-sm-8 col-md-6 m-auto mt-3">
                                <h1 class="text-center mb-5">Change password</h1>
                                <div class="card">
                                    <div class="card-body">
                                        <form @submit.prevent >

                                            <input v-model="form.old" type="password"  class="form-control my-3 py-2" placeholder="Enter current password" required>

                                            <input v-model="form.newPass" type="password" class="form-control my-3 py-2" placeholder="Enter new password" required>

                                            <input v-model="form.confirm" type="password" class="form-control my-3 py-2" placeholder="Confirm new password" required>
                                            <p v-if="!isValidConfirmationPassword" class="text-danger">Passwords do not match.</p>

                                            <p v-if="form.error" class="text-danger">Invalid password.</p>
                                            <div class="text-center mt-3">
                                                <button type="submit" class="btn btn-primary" v-on:click="sendData">Confirm</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        </section>
   </div>
   `,
    methods: {

        sendData() {
            if (this.allFieldsAreFilled)
            {
                /*axios.post("api/users/changePassword", {
                     old: this.form.old,
                     newPass: this.form.newPass,
                     id: this.form.id,

                     }).then(function(response) {
                     if(response.data=="OK")
                     {
                        location.replace('http://localhost:8000/#/editProfileMessage');
                     }
                         }).catch(function (error) {
                             alert('An error occurred!');

                         });*/
                axios({
                   method: 'post',
                   url: "api/users/changePassword", data: {
                        old: this.form.old,
                         newPass: this.form.newPass,
                         id: this.form.id,
                    },
                   headers: {
                       Authorization: "Bearer " + this.token.accessToken
                   }
               }).then(response => {
               if(response.data=="OK")
                {
                   Swal.fire({
                  title: 'Success!',
                  text: 'Your changes are saved',
                  icon: 'success',
                  confirmButtonText: 'OK'
                })
                }

               }).catch(function (error) {
                   if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                   else Swal.fire('Error', 'Something went wrong!', 'error');
               });
                    this.form.error = true;
            }
          }
        },

    computed: {
            isValidConfirmationPassword() {
                 return this.form.newPass == this.form.confirm;
                   },
            allFieldsAreFilled() {
                return this.form.newPass && this.form.old && this.form.confirm;

            }
        }

});