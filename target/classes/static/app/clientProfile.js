Vue.component("client-profile", {
   data: function() {
        return {
            user: {
                email: "email@gmail.com",
                name: "Pero",
                surname: "Peric",
                street: "Puskinova 6",
                city: "Novi Sad",
                country: "Srbija",
                phone: "123345",
                category: "Regular",
                penalties: "0",
                points: "0",
            }
        }
   },

   template: `

    <div class="w-100">
        <client-navbar></client-navbar>
        <div class = "d-flex align-items-center justify-content-center mt-5 mb-1">
            <div class="col-3 mt-5">
                <div class="row mt-5">
                    <div class="col-6">
                        Password:
                    </div>
                    <div class="col-6">
                        <a href="http://localhost:8000/#/changePassword/">Change password</a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        Name:
                    </div>
                    <div class="col-6">
                        <label> {{user.name}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Surname:
                    </div>
                    <div class="col-6">
                        <label> {{user.surname}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Email:
                    </div>
                    <div class="col-6">
                    <label> {{user.email}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Street:
                    </div>
                    <div class="col-6">
                    <label> {{user.street}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    City:
                    </div>
                    <div class="col-6">
                    <label> {{user.city}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Country:
                    </div>
                    <div class="col-6">
                    <label> {{user.country}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Phone Number:
                    </div>
                    <div class="col-6">
                    <label> {{user.phone}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        Category:
                    </div>
                    <div class="col-6">
                        <label> {{user.category}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                            Penalties:
                    </div>
                    <div class="col-2">
                            <label> {{user.penalties}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                            Points:
                    </div>
                    <div class="col-6">
                            <label> {{user.points}}</label>
                    </div>
                </div>
            </div>
        </div>
        <div>
        </div>
        <div class="row">
            <div class="col-6">

            </div>
            <div class="col-3">
                <button type="button" class="btn btn-primary mt-5" v-on:click="editProfile"  style="height:40px;width:100px;"> Edit </button>
            </div>

            <div class="col-2">
                <button class="btn btn-warning float-right mt-5" v-on:click="deleteProfile" style="height:40px;"> Delete profile  </button>
            </div>
        </div>
            </div>
   `,

    methods: {
        editProfile() {
            location.replace('http://localhost:8000/#/editProfile');
        },
        deleteProfile() {
                    location.replace('http://localhost:8000/#/deleteProfile');
                }
    }


});