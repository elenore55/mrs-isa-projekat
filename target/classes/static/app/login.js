Vue.component("login", {
   data: function() {
       return {
           user: {
               email: "",
               password: "",
               error: false,
           },
           token: {}
       }
   },

   mounted() {
        main_image = $("body").css("background-image", "url('images/login3.jpg')");
        main_image = $("body").css("background-size", "100% 200%");
   },

   template: `
    <div>
        <unregistered-navbar> </unregistered-navbar>
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
        </div>
   `,
    methods: {


        sendLoginRequest() {
            if (this.user.email && this.user.password)
            {
                axios.post("api/users/login", {
                     email: this.user.email,
                     password: this.user.password
                     }).then(function(response) {
                         this.token = response.data;
                         localStorage.setItem("jwt", JSON.stringify(this.token));
                         if (this.token.userRole === "ROLE_COTTAGE")
                            location.replace('http://localhost:8000/index.html#/cottagesViewOwner/' + this.token.userId);
                         else if (this.token.userRole === "ROLE_SHIP")
                             location.replace('http://localhost:8000/index.html#/shipsViewOwner/' + this.token.userId);
                         else if (this.token.userRole === "ROLE_CLIENT")
                             location.replace('http://localhost:8000/index.html#/clientHome');
                     }).catch(function (error) {
                         this.user.email = "";
                         this.user.password = "";
                         this.user.error = true;
                     });

            }
          }
        },



});