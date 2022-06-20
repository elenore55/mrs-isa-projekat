Vue.component("registration", {
   data: function() {
       return {
           user: {
               email: "",
               password: "",
               passwordConfirmation: "",
               name: "",
               surname: "",
               street:"",
               city:"",
               country:"",
               phone:"",
               error: false,
           }
       }
   },
   template: `
   <div class="w-100">
        <unregistered-navbar> </unregistered-navbar>
        <section>
            <div class="container mt-5 pt-5">
                <div class="row">
                    <div class="col-lg-4 col-sm-8 col-md-6 m-auto">
                        <h1 class="text-center">Registration</h1>
                        <div class="card">
                            <div class="card-body">
                                <form @submit.prevent action="">
                                    <div class="form-floating has-validation">
                                        <input v-model="user.email"  type="email"  class="form-control my-3 py-2" placeholder="Email"required>
                                        <label style="color:#C0C0C0" for="email-input">Email</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.password" type="password" name="" id="" class="form-control my-3 py-2" placeholder="Password" required>
                                        <label style="color:#C0C0C0" for="password-input">Password</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.passwordConfirmation" type="password" class="form-control my-3 py-2" placeholder="Confirm password" required>
                                        <p v-if="!isValidConfirmationPassword" class="text-danger">Passwords do not match.</p>
                                        <label style="color:#C0C0C0" for="passwordConfirmation-input">Potvrda lozinke</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.name" type="text" name="" id="" class="form-control my-3 py-2" placeholder="Name" required>
                                        <label style="color:#C0C0C0" for="name-input">Name</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.surname" type="text" name="" id="" class="form-control my-3 py-2" placeholder="Last name" required>
                                        <label style="color:#C0C0C0" for="surname-input">Surname</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.street" type="text" name="" id="" class="form-control my-3 py-2" placeholder="Address" required>
                                        <label style="color:#C0C0C0" for="street-input">Address</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.city" type="text" name="" id="" class="form-control my-3 py-2" placeholder="City" required>
                                        <label style="color:#C0C0C0" for="city-input">City</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.country" type="text" name="" id="" class="form-control my-3 py-2" placeholder="Country" required>
                                        <label style="color:#C0C0C0" for="country-input">Country</label>
                                    </div>

                                    <div class="form-floating has-validation">
                                        <input v-model="user.phone" type="tel" name="" id="" class="form-control my-3 py-2" placeholder="Phone number" required>
                                        <label style="color:#C0C0C0" for="phone-input">Phone</label>
                                    </div>
                                    <p v-if="user.error" class="text-danger">User with this email is already registered.</p>
                                    <div class="text-center mt-3">
                                        <button class="btn btn-primary" v-on:click="sendRequest">Submit</button>
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

        sendRequest() {
        if (this.isValidConfirmationPassword && this.allFieldsAreFilled) {
                axios.post("api/users/registration", {
                    email: this.user.email,
                    password: this.user.password,
                    passwordConfirmation: this.user.passwordConfirmation,
                    name: this.user.name,
                    surname: this.user.surname,
                    street: this.user.street,
                    city: this.user.city,
                    country: this.user.country,
                    phone: this.user.phone
                }).then(function(response) {
                    if (response.data == "OK")
                    {
                        alert("User succesfully added");
                        window.location = 'http://localhost:8000/#/login';
                    }

                }).catch(function (error) {
                    alert('An error occurred!');
                });
                this.user.error = true;
            }
        }
    },

    computed: {
        isValidConfirmationPassword() {
                   return this.user.password == this.user.passwordConfirmation;
               },
        allFieldsAreFilled() {
            return this.user.email && this.user.password && this.user.passwordConfirmation && this.user.name &&
            this.user.surname && this.user.city && this.user.country && this.user.street && this.user.phone;
        }
    }

});