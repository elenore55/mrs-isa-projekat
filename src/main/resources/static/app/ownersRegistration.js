Vue.component("owners-registration", {
    data() {
        return {
            email: "",
            password: "",
            passwordConfirmation: "",
            name: "",
            surname: "",
            street: "",
            city: "",
            country: "",
            phone: ""
        }
    },

    template: `
    <div>
        <div class="d-flex justify-content-center">
            <div class="card my-5 px-4">
                <h3 class="card-title d-flex justify-content-center mt-3">Registration</h3>
                <div class="container card-body">
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="text" class="form-control" v-model="name" id="name-input" placeholder="Name">
                            <label style="color:#C0C0C0" for="name-input" class="ms-2">Name</label>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" v-model="surname" id="surname-input" placeholder="Surname">
                            <label style="color:#C0C0C0" for="surname-input" class="ms-2">Surname</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="email" class="form-control" id="email-input" v-model="email" placeholder="Email">
                            <label for="email-input" class="ms-2" style="color:#C0C0C0">Email</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="password" class="form-control" id="pass-input" v-model="password" placeholder="Password">
                            <label for="pass-input" class="ms-2" style="color:#C0C0C0">Password</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="password" class="form-control" id="pass-confirm-input" v-model="passwordConfirmation" placeholder="Confirm password">
                            <label for="pass-confirm-input" class="ms-2" style="color:#C0C0C0">Confirm password</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="street-input" v-model="street" placeholder="Street">
                            <label for="street-input" class="ms-2" style="color:#C0C0C0">Address</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="city-input" v-model="city" placeholder="City">
                            <label for="city-input" class="ms-2" style="color:#C0C0C0">City</label>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="country-input" v-model="country" placeholder="Country">
                            <label for="country-input" class="form-label ms-2" style="color:#C0C0C0">Country</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <input type="text" id="phone-input" class="form-control" v-model="phone" placeholder="Phone number"/>
                            <label for="phone-input" class="form-label ms-2" style="color:#C0C0C0">Phone number</label>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col form-floating">
                            <select class="form-select form-control" id="type-select" v-model="type">
                                <option selected>Cottage owner</option>
                                <option>Ship owner</option>
                            </select>
                            <label for="type-select" class="form-label ms-2" style="color:#C0C0C0">Type</label>
                        </div>
                    </div>
                    <div class="my-4 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary btn-lg">Submit</button>                    
                    </div>
                </div>    
            </div>
        </div>
    </div>
    `
});