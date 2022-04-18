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

                                    <input type="email"  class="form-control my-3 py-2" placeholder="Email" required>

                                    <input type="password" name="" id="" class="form-control my-3 py-2" placeholder="Password" required>

                                    <div class="text-center mt-3">
                                        <button class="btn btn-primary">Login</button>
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
            if (this.isValidData) {
                axios.post("api/client/login", {
                    email: this.user.email,
                    password: this.user.password,

                }).then(function(response) {
                    alert('Cottage successfully added!');
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            } else {
                this.cottage.errors.name = true;
                this.cottage.errors.description = true;
                this.cottage.errors.price = true;
                this.cottage.errors.street = true;
                this.cottage.errors.city = true;
                this.cottage.errors.country = true;
            }
        },
    },

    computed: {

    }

});