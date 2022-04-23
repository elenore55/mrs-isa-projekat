Vue.component("login", {
   data: function() {
       return {
           user: {
               email: "",
               password: "",
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
                                <form action="">

                                    <input v-model="user.email" type="email"  class="form-control my-3 py-2" placeholder="Email" required>

                                    <input v-model="user.password" type="password" name="" id="" class="form-control my-3 py-2" placeholder="Password" required>

                                    <div class="text-center mt-3">
                                        <button class="btn btn-primary" v-on:click="sendRequest">Login</button>
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

        sendRequest() {
                axios.post("api/users/login", {
                    email: this.user.email,
                    password: this.user.password,

                }).then(function(response) {
                    alert('Cottage successfully added!');
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            }
        },

    computed: {

    }

});