Vue.component("login", {
   data: function() {
       return {
           user: {
               email: "",
               password: "",
               error: false,
           }
       }
   },
   template: `
    <section>
            <div class="container mt-5 pt-5">
                <div class="row">
                    <div class="col-lg-4 col-sm-8 col-md-6 m-auto">
                        <h1 class="text-center">Login</h1>
                        <div class="card">
                            <div class="card-body">
                                <form @submit.prevent >

                                    <input v-model="user.email" type="email"  class="form-control my-3 py-2" placeholder="Email" required>

                                    <input v-model="user.password" type="password" name="" id="" class="form-control my-3 py-2" placeholder="Password" required>

                                    <p v-if="user.error" class="text-danger">Invalid email or password.</p>
                                    <div class="text-center mt-3">
                                        <button type="submit" class="btn btn-primary" v-on:click="sendLoginRequest">Login</button>
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

        sendLoginRequest() {
            if (this.user.email && this.user.password)
            {
                axios.post("api/users/login", {
                     email: this.user.email,
                     password: this.user.password,

                     }).then(function(response) {
                     if(response.data=="")
                     {
                        // preusmjeri na stranicu za login sa greskom
                        //console.log("trebalo je da se ispsie");
                        //location.replace('http://localhost:8000/#/registration');
                     } else
                     {
                        console.log("trebalo je da se ispise");
                        location.replace('http://localhost:8000/#/clientHome');
                     }
                         }).catch(function (error) {
                             alert('An error occurred!');
                             // preusmjeri na stranicu za login sa greskom
                         });
                    this.user.error = true;
            }
          }
        },

    computed: {

    }

});