Vue.component('update-owners-profile', {
    data() {
        return {
            owner: {},
            input_started: false
        }
    },

    mounted() {
        $("body").css("background-image", "url('images/gradient_purple.jpg')");
        $("body").css("background-size", "100% 200%");
        axios({
            method: "get",
            url: "api/users/getOwner/" + JSON.parse(localStorage.getItem("jwt")).userId,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.owner = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div>
        <div class="d-flex justify-content-center">
            <div class="card my-5 px-4 shadow-lg" style="background-color: #fff9e8; border-radius: 15px">
                <div class="container card-body">
                    <h3 class="card-title d-flex justify-content-center mt-3 mb-4">Update profile data</h3>
                    <div class="row my-3">
                        <div class="col form-floating">
                            <input type="text" class="form-control shadow-sm" v-model="owner.name" id="name-input" placeholder="Name">
                            <label style="color:#C0C0C0" for="name-input" class="ms-2">Name</label>
                            <p v-if="!isValidName" class="text-danger">Name is required</p>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" v-model="owner.surname" id="surname-input" placeholder="Surname">
                            <label style="color:#C0C0C0" for="surname-input" class="ms-2">Surname</label>
                            <p v-if="!isValidSurname" class="text-danger">Surname is required</p>
                        </div>
                    </div>
                    <div class="row mt-3 mb-2">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="street-input" v-model="owner.address.street" placeholder="Street">
                            <label for="street-input" class="ms-2" style="color:#C0C0C0">Address</label>
                            <p v-if="!isValidStreet" class="text-danger">Invalid address</p>
                        </div>
                    </div>
                    <div class="row mb-3 mt-2">
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="city-input" v-model="owner.address.city" placeholder="City">
                            <label for="city-input" class="ms-2" style="color:#C0C0C0">City</label>
                            <p v-if="!isValidCity" class="text-danger">Invalid city</p>
                        </div>
                        <div class="col form-floating">
                            <input type="text" class="form-control" id="country-input" v-model="owner.address.country" placeholder="Country">
                            <label for="country-input" class="form-label ms-2" style="color:#C0C0C0">Country</label>
                            <p v-if="!isValidCountry" class="text-danger">Invalid country</p>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col form-floating">
                            <input type="tel" id="phone-input" class="form-control" v-model="owner.phoneNumber" placeholder="Phone number" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3,4}"/>
                            <label for="phone-input" class="form-label ms-2" style="color:#C0C0C0">Phone number</label>
                            <p v-if="!isValidPhone" class="text-danger">Invalid phone number</p>
                        </div>
                    </div>
                    <div class="mt-4 mb-2 d-flex flex-column">
                        <button type="button" class="btn btn-success btn-lg bg-gradient text-center" v-on:click="updateData">Save changes</button>                    
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        updateData() {
            this.input_started = true;
            if (this.isValidName && this.isValidSurname && this.isValidStreet && this.isValidCity &&
                this.isValidCountry && this.isValidPhone) {
                axios.post('api/users/updateUser/' + JSON.parse(localStorage.getItem("jwt")).userId, this.owner, {
                        headers: {
                            Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                        }
                    }).then(response => {
                    this.owner = response.data;
                    Swal.fire('Success', 'Profile data updated!', 'success');
                    this.$router.push({path: '/ownersProfile'});
                }).catch(function (error) {
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            }
        }
    },


    computed: {
        isValidName() {
            if (!this.input_started) return true;
            return !!(this.owner.name);
        },

        isValidSurname() {
            if (!this.input_started) return true;
            return !!(this.owner.surname);
        },

        isValidStreet() {
            if (!this.input_started) return true;
            return !!(this.owner.address.street);
        },

        isValidCity() {
            if (!this.input_started) return true;
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.owner.address.city && re.test(this.owner.address.city);
        },

        isValidCountry() {
            if (!this.input_started) return true;
            const re = new RegExp(/([A-Z])([A-Za-z]+)$/);
            return !!this.owner.address.country && re.test(this.owner.address.country);
        },

        isValidPhone() {
            if (!this.input_started) return true;
            return !!(this.owner.phoneNumber);
        },
    }

});