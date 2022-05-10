Vue.component("edit-profile", {
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
            <div class="col-3 my-5 mx-5 pt-5 py-5">
            <form @submit.prevent>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                        Name:
                    </div>
                    <div class="col-6">
                        <input v-model="user.name" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Surname:
                    </div>
                    <div class="col-6">
                        <input v-model="user.surname" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row my-1">
                    <div class="col-6">
                      Email:
                    </div>
                    <div class="col-6">
                      <label> {{user.email}}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Street:
                    </div>
                    <div class="col-6">
                      <input v-model="user.street" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6  my-1 py-1">
                      City:
                    </div>
                    <div class="col-6">
                      <input v-model="user.city" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Country:
                    </div>
                    <div class="col-6">
                      <input v-model="user.country" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Phone Number:
                    </div>
                    <div class="col-6">
                      <input v-model="user.phone" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row my-1">
                      <div class="col-6">
                           Category:
                      </div>
                      <div class="col-6">
                          <label> {{user.category}}</label>
                      </div>
                </div>

                <div class="row my-1">
                       <div class="col-6">
                            Penalties:
                       </div>
                       <div class="col-6">
                            <label> {{user.penalties}}</label>
                       </div>
                </div>

                <div class="row my-1">
                       <div class="col-6">
                            Points:
                       </div>
                       <div class="col-6">
                            <label> {{user.points}}</label>
                       </div>
                </div>

                <div class="row text-center">
                    <div class="col-6">

                    </div>
                    <div class="col-6">
                        <button type="submit" class="btn btn-primary m-3" v-on:click="sendData">Confirm</button>
                    </div>
                </div>
            </form>
         </div>
     </div>
    </div>
   `,

   methods: {
           sendData() {
               if (this.allFieldAreFilled())
               {
                    axios.post("api/users/edit", {
                    email: this.user.email,
                    name: this.user.name,
                    surname: this.user.surname,
                    street: this.user.street,
                    city: this.user.city,
                    country: this.user.country,
                    phone: this.user.phone,


                    }).then(function(response) {
                    if(response.data=="OK")
                    {
                        location.replace('http://localhost:8000/#/editProfileMessage');
                    }
                    }).catch(function (error) {
                         alert('An error occurred!');
                    // preusmjeri na stranicu za login sa greskom
                    });
               }
           },

           allFieldAreFilled()
           {
                return this.user.email && this.user.name && this.user.surname && this.user.street && this.user.street
                && this.user.city && this.user.country && this.user.phone;
           }
       }

});