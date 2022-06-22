Vue.component("client-profile", {
   data: function() {
        return {
            user: {
                email: "email@gmail.com",
                name: "Pero",
                surname: "Peric",
                address: {
                    street: "Puskinova 6",
                    city: "Novi Sad",
                    country: "Srbija",
                },
                phone: "123345",
                category: "Regular",
                penalties: "0",
                points: "0",
            },
            token: {},
            id: 0,
        }
   },
   mounted() {
           this.token = JSON.parse(localStorage.getItem("jwt"));
           this.id = this.token.userId;
           main_image = $("body").css("background-image", "url('images/set.webp')");
           main_image = $("body").css("background-size", "100% 210%");
           this.reload();
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
                        <a href="http://localhost:8000/index.html#/changePassword/">Change password</a>
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
                    <label> {{user.address.street}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    City:
                    </div>
                    <div class="col-6">
                    <label> {{user.address.city}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Country:
                    </div>
                    <div class="col-6">
                    <label> {{user.address.country}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                    Phone Number:
                    </div>
                    <div class="col-6">
                    <label> {{user.phoneNumber}}</label>
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
                            <label> {{0}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                            Points:
                    </div>
                    <div class="col-6">
                            <label> {{user.numberOfPoints}}</label>
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
        reload()
        {
            axios({
               method: 'get',
               url: "api/users/getById/" + this.id,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.user = response.data;
           }).catch(function (error) {
               if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });

        },
        editProfile() {
            location.replace('http://localhost:8000/index.html#/editProfile');
        },
        deleteProfile() {
             location.replace('http://localhost:8000/index.html#/deleteProfile');
             }
    }


});