Vue.component("delete-profile", {
data: function() {
        return {
            form: {
                reason: "",
                id: "1",
            },
            token:{},
        }
   },

   mounted() {
           this.token = JSON.parse(localStorage.getItem("jwt"));
           this.form.id = this.token.userId;
          },

   template: `
   <div class="w-100">
        <client-navbar></client-navbar>
        <div my-5 mx-5 pt-5 align-items-center>
            <div> <h1> .</h1> </div>
            <div class = "my-5 d-flex align-items-center justify-content-center">
                 <form @submit.prevent>
                      <h4 class="text-center my-3"> Enter the reasons for deleting Your profile</h4>
                      <textarea v-model="form.reason" class = "" name="textarea" rows="10" cols="70" required></textarea>
                      <br>
                      <button v-on:click="sendFeedback" class="btn btn-primary float-end">Submit</button>
                 </form>
            </div>
        </div>
   </div>
   `,

methods: {
        sendFeedback() {
        if (this.form.reason)
        {
            /*axios.post("api/deletionRequests/deleteProfile", {
                 reason: this.form.reason,
                 id: this.form.id,

                 }).then(function(response) {
                 if(response.data=="OK")
                      {
                           location.replace('http://localhost:8000/#/deleteProfileMessage');
                      }
                           }).catch(function (error) {
                                alert('An error occurred!');
                                // preusmjeri na stranicu za login sa greskom
                           });*/
            axios({
               method: 'post',
               url: "api/deletionRequests/deleteProfile", data: {
                    reason: this.form.reason,
                    id: this.form.id,
                },
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               // neka poruka da je dobro proslo
               Swal.fire({
                 title: 'Success!',
                 text: 'Your request has been sucesfully sent',
                 icon: 'success',
                 confirmButtonText: 'OK'
               })
           }).catch(function (error) {
               if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
        }
        },
    }



});