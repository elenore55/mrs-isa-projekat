Vue.component("edit-profile", {
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
                <div class="col-2 my-1 py-1">
                    Name:
                </div>
                <div class="col-3">
                    <input v-model="user.name" type="text"  class="form-control" placeholder={user.name} required>
                </div>
            </div>

            <div class="row">
                <div class="col-2 my-1 py-1">
                  Surname:
                </div>
                <div class="col-3">
                    <input v-model="user.surname" type="text"  class="form-control" placeholder={user.surname} required>
                </div>
              </div>

              <div class="row my-1">
                <div class="col-2">
                  Email:
                </div>
                <div class="col-3">
                  <label> {{user.email}}</label>
                </div>
              </div>

              <div class="row">
                <div class="col-2 my-1 py-1">
                  Street:
                </div>
                <div class="col-3">
                  <input v-model="user.street" type="text"  class="form-control" placeholder={user.street} required>
                </div>
              </div>

              <div class="row">
                <div class="col-2  my-1 py-1">
                  City:
                </div>
                <div class="col-3">
                  <input v-model="user.city" type="text"  class="form-control" placeholder={user.city} required>
                </div>
              </div>

              <div class="row">
                <div class="col-2 my-1 py-1">
                  Country:
                </div>
                <div class="col-3">
                  <input v-model="user.country" type="text"  class="form-control" placeholder={user.country} required>
                </div>
              </div>

              <div class="row">
                <div class="col-2 my-1 py-1">
                  Phone Number:
                </div>
                <div class="col-3">
                  <input v-model="user.phone" type="text"  class="form-control" placeholder={user.phone} required>
                </div>
              </div>

              <div class="row my-1">
                  <div class="col-2">
                       Category:
                  </div>
                  <div class="col-3">
                      <label> {{user.category}}</label>
                  </div>
              </div>

              <div class="row my-1">
                   <div class="col-2">
                        Penalties:
                   </div>
                   <div class="col-3">
                        <label> {{user.penalties}}</label>
                   </div>
              </div>

              <div class="row text-center">
                <div class="col-2">

                </div>
                <div class="col-2">
                    <button class="btn btn-primary m-3">Confirm</button>

                </div>

              </div>
          </div>
       </div>
   `,

});