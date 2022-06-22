Vue.component("edit-profile", {
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
            id: 0,
            token: "",
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
                      <input v-model="user.address.street" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6  my-1 py-1">
                      City:
                    </div>
                    <div class="col-6">
                      <input v-model="user.address.city" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Country:
                    </div>
                    <div class="col-6">
                      <input v-model="user.address.country" type="text"  class="form-control" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 my-1 py-1">
                      Phone Number:
                    </div>
                    <div class="col-6">
                      <input v-model="user.phoneNumber" type="text"  class="form-control" required>
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
                            <label> {{0}}</label>
                       </div>
                </div>

                <div class="row my-1">
                       <div class="col-6">
                            Points:
                       </div>
                       <div class="col-6">
                            <label> {{user.numberOfPoints}}</label>
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

                    /*axios.post("api/users/edit", {
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
                    });*/

                    axios({
                       method: 'post',
                       url: "api/users/edit", data: {
                            email: this.user.email,
                            name: this.user.name,
                            surname: this.user.surname,
                            street: this.user.address.street,
                            city: this.user.address.city,
                            country: this.user.address.country,
                            phone: this.user.phoneNumber,
                        },
                       headers: {
                           Authorization: "Bearer " + this.token.accessToken
                       }
                   }).then(response => {
                       Swal.fire({
                        title: 'Success!',
                        text: 'Your changes are saved',
                        icon: 'success',
                        confirmButtonText: 'OK'
                      })
                   }).catch(function (error) {
                       if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                       else Swal.fire('Error', 'Something went wrong!', 'error');
                   });
               }
           },

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
                  if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                  else Swal.fire('Error', 'Something went wrong!', 'error');
              });

           },

           allFieldAreFilled()
           {


                return this.user.email && this.user.name && this.user.surname && this.user.address.street
                && this.user.address.city && this.user.address.country && this.user.phoneNumber;
           }
       }

});