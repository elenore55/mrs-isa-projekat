Vue.component("client-profile", {
   data: function() {
        return {
            user: {
                email: "pera@peric",
                name: "Pera",
                surname: "Peric",
                street: "Zmaj Jovina",
                city: "Novi Sad",
                country: "Srbija",
                phone: "00381-256-552",
                category: "Regular",
                penalties: "0",

            }
        }
   },

   template: `

    <div class="w-100">
        <client-navbar></client-navbar>
        <div class="my-5 mx-5">

            <div class="row">
                <div class="col-2">
                    Name:
                </div>
                <div class="col-2">
                    <label> {{user.name}}</label>
                </div>
            </div>

            <div class="row">
                <div class="col-2">
                  Surname:
                </div>
                <div class="col-2">
                    <label> {{user.surname}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2">
                  Email:
                </div>
                <div class="col-2">
                  <label> {{user.email}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2">
                  Street:
                </div>
                <div class="col-2">
                  <label> {{user.street}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2">
                  City:
                </div>
                <div class="col-2">
                  <label> {{user.city}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2">
                  Country:
                </div>
                <div class="col-2">
                  <label> {{user.country}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2">
                  Phone Number:
                </div>
                <div class="col-2">
                  <label> {{user.phone}}</label>
                </div>
              </div>

              <div class="row">
                  <div class="col-2">
                       Category:
                  </div>
                  <div class="col-2">
                      <label> {{user.category}}</label>
                  </div>
              </div>

              <div class="row">
                   <div class="col-2">
                        Penalties:
                   </div>
                   <div class="col-2">
                        <label> {{user.penalties}}</label>
                   </div>
              </div>

              <div class="row text-center">
                <div class="col-2">

                </div>
                <div class="col-2">
                    <button type="button" class="btn btn-primary m-3" v-on:click="editProfile"> Edit </button>
                </div>

                <div class="col-8">
                  <button class="btn btn-warning m-3 float-right" v-on:click="deleteProfile"> Delete profile  </button>
                </div>
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