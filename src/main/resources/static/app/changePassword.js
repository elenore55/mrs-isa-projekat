Vue.component("change-password", {
   data: function() {
       return {
           form: {
               old: "",
               newPass: "",
               confirm: "",
               id: "1",
               error: false,
           }
       }
   },
   template: `
    <section>
            <div class="container mt-5 pt-5">
                        <div class="row">
                            <div class="col-lg-4 col-sm-8 col-md-6 m-auto">
                                <h1 class="text-center">Change password</h1>
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
   `,
    methods: {

        sendData() {
            if (this.allFieldsAreFilled)
            {
                axios.post("api/users/changePassword", {
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