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
            phone: "",
            input_started: false,
            type: "",
            pass_type: "password",
            eye_class: "fa fa-eye",
            pass_type_confirm: "password",
            eye_class_confirm: "fa fa-eye",
            reason: ""
        }
    },

    template: `
    <div>
        <div class="d-flex justify-content-center bg-gradient" style="background-color: #ddc8fb">
            <div class="card my-5 px-4 shadow-lg" style="background-color: #fff9e8; border-radius: 15px">
                <h3 class="card-title d-flex justify-content-center mt-3">Create an account</h3>
                <div class="container card-body">
                    <div class="row mt-2 mb-3">
                        <div class="col form-floating">
                            <input type="text" class="form-control shadow-sm" v-model="name" id="name-input" placeholder="Name">
                            <label style="color:#C0C0C0" for="name-input" class="ms-2">Name</label>
                            <p v-if="!isValidName" class="text-danger">Name is required</p>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" v-model="surname" id="surname-input" placeholder="Surname">
                            <label style="color:#C0C0C0" for="surname-input" class="ms-2">Surname</label>
                            <p v-if="!isValidSurname" class="text-danger">Surname is required</p>
                        </div>
                    </div>
                    <div class="row mt-3 mb-2">
                        <div class="col form-floating">
                            <input type="email" class="form-control" id="email-input" v-model="email" placeholder="Email">
                            <label for="email-input" class="ms-2" style="color:#C0C0C0">Email</label>
                            <p v-if="!isValidEmail" class="text-danger">Invalid email address</p>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col">
                            <div class="input-group">
                                <div class="form-floating flex-grow-1">
                                    <input :type="pass_type" class="form-control rounded-0 rounded-start" id="pass-input" v-model="password" placeholder="Password">
                                    <label for="pass-input" style="color:#C0C0C0">Password</label>
                                </div>
                                <span class="input-group-text">
                                    <i :class="eye_class" id="togglePassword" style="cursor: pointer" v-on:click="togglePass"></i>
                                </span>
                            </div>
                            <p v-if="isWeakPassword" class="text-danger">Weak password</p>
                            <p v-if="isMediumPassword" class="text-warning">Medium strong password</p>
                            <p v-if="isStrongPassword" class="text-success">Strong password</p>
                        </div>
                    </div>
                    <div class="row mt-2 mb-3">
                        <div class="col">
                            <div class="input-group">
                                <div class="form-floating flex-grow-1">
                                    <input :type="pass_type_confirm" class="form-control rounded-0 rounded-start" id="pass-confirm-input" v-model="passwordConfirmation" placeholder="Confirm password">
                                    <label for="pass-confirm-input" style="color:#C0C0C0">Confirm password</label>
                                </div>
                                <span class="input-group-text">
                                    <i :class="eye_class_confirm" style="cursor: pointer" v-on:click="togglePassConfirm"></i>
                                </span>
                            </div>
                            <p v-if="!areValidPasswords" class="text-danger">Passwords are required and must be matching</p>
                        </div>
                    </div>
                    <div class="row mb-2 mt-3">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="street-input" v-model="street" placeholder="Street">
                            <label for="street-input" class="ms-2" style="color:#C0C0C0">Address</label>
                            <p v-if="!isValidStreet" class="text-danger">Invalid address</p>
                        </div>
                    </div>
                    <div class="row mt-2 mb-3">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="city-input" v-model="city" placeholder="City">
                            <label for="city-input" class="ms-2" style="color:#C0C0C0">City</label>
                            <p v-if="!isValidCity" class="text-danger">Invalid city</p>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="country-input" v-model="country" placeholder="Country">
                            <label for="country-input" class="ms-2" style="color:#C0C0C0">City</label>
                            <p v-if="!isValidCountry" class="text-danger">Invalid country</p>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col form-floating">
                            <input type="tel" id="phone-input" class="form-control" v-model="phone" placeholder="Phone number" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3,4}"/>
                            <label for="phone-input" class="form-label ms-2" style="color:#C0C0C0">Phone number</label>
                            <p v-if="!isValidPhone" class="text-danger">Invalid phone number</p>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col form-floating">
                            <select class="form-select form-control" id="type-select" v-model="type">
                                <option selected>Cottage owner</option>
                                <option>Ship owner</option>
                            </select>
                            <label for="type-select" class="form-label ms-2" style="color:#C0C0C0">Type</label>
                            <p v-if="!isValidType" class="text-danger">Invalid type</p>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col form-floating">
                            <textarea id="reason-input" v-model="reason" class="form-control" placeholder="Reason for registration" style="height: 100px"></textarea>
                            <label for="reason-input" class="form-label ms-2" style="color:#C0C0C0">Reason for registration</label>
                            <p v-if="!isValidReason" class="text-danger">Reason for registration is required</p>
                        </div>
                    </div>
                    <div class="mt-4 mb-2 d-flex flex-column">
                        <button type="button" class="btn btn-success btn-lg bg-gradient text-center" v-on:click="sendRequest">Submit</button>                    
                    </div>
                    <p class="text-center text-muted my-3">Already have an account? <a href="/#/login" class="fw-bold text-body"><u>Login here</u></a></p>
                </div>    
            </div>
        </div>
    </div>
    `,

    methods: {
        togglePass() {
            if (this.pass_type === "password") {
                this.pass_type = "text";
                this.eye_class = "fa fa-eye-slash"
            } else {
                this.pass_type = "password";
                this.eye_class = "fa fa-eye"
            }
        },

        togglePassConfirm() {
            if (this.pass_type_confirm === "password") {
                this.pass_type_confirm = "text";
                this.eye_class_confirm = "fa fa-eye-slash"
            } else {
                this.pass_type_confirm = "password";
                this.eye_class_confirm = "fa fa-eye"
            }
        },

        sendRequest() {
            this.input_started = true;
            alert(this.country);
            if (this.isValidName && this.isValidSurname && this.isValidEmail && this.areValidPasswords && !this.isWeakPassword
                && this.isValidStreet && this.isValidCity && this.isValidCountry && this.isValidPhone && this.isValidType && this.isValidReason) {
                axios.post("api/registrationRequests/addRequest", {
                    name: this.name,
                    surname: this.surname,
                    email: this.email,
                    password: this.password,
                    address: {
                        street: this.street,
                        city: this.city,
                        country: this.country
                    },
                    type: this.type,
                    phoneNumber: this.phone,
                    reason: this.reason
                }).then(function (response) {
                    Swal.fire('Success', 'Registration request sent!', 'success');
                }).catch(function (error) {
                    Swal.fire('Error', 'Email already taken!', 'error');
                });
            }
        }
    },

    computed: {
        isValidName() {
            if (!this.input_started) return true;
            return !!(this.name);
        },

        isValidSurname() {
            if (!this.input_started) return true;
            return !!(this.surname);
        },

        isValidEmail() {
            if (!this.input_started) return true;
            const re = new RegExp(/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/);
            return this.email && re.test(this.email);
        },

        isStrongPassword() {
            const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
            return re.test(this.password);
        },

        isMediumPassword() {
            if (this.isStrongPassword) return false;
            const re = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
            return re.test(this.password);
        },

        isWeakPassword() {
            if (!this.input_started && this.password.length === 0) return false;
            return !this.isMediumPassword && !this.isStrongPassword;
        },

        areValidPasswords() {
            if (!this.input_started) return true;
            return this.password && this.passwordConfirmation && this.password === this.passwordConfirmation;
        },

        isValidStreet() {
            if (!this.input_started) return true;
            return !!(this.street);
        },

        isValidCity() {
            if (!this.input_started) return true;
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.city && re.test(this.city);
        },

        isValidCountry() {
            if (!this.input_started) return true;
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.country && re.test(this.country);
        },

        isValidType() {
            if (!this.input_started) return true;
            return !!(this.type);
        },

        isValidPhone() {
            if (!this.input_started) return true;
            return !!(this.phone);
        },

        isValidReason() {
            if (!this.input_started) return true;
            return !!(this.reason);
        }
    }

});