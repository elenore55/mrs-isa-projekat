Vue.component("client-subs", {
   data: function() {
       return {
           subs: [],
           id: "",
           token: {}
       }
   },

   mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;
        main_image = $("body").css("background-image", "url('images/set2.png')");
        main_image = $("body").css("background-size", "100% 270%");
        this.reload();
       },

   template: `
    <div>
        <client-navbar> </client-navbar>

            <div class="container mt-5 pt-5">

                <div v-for="(s, i) in subs" class="row p-3 my-2" style="border:1px solid rgb(156, 151, 151); border-radius: 5px; background-color: white">
                    <div class="col-3">
                         <img :src="s.image" class="card-img rounded-3 my-3" width="100" height="150"  alt="cottage image">
                    </div>

                   <div class="col-5 card-body container">
                        <h3 class="card-title mb-2">{{s.name}}</h3>
                        <div class="my-2">
                        </div>
                        <p class="card-text mt-2 mb-4 h5">{{s.address.street}}, {{s.address.city}}, {{s.address.country}}</p>
                   </div>
                   <div class="col-4 mt-5">

                        <div class="my-3">
                        </div>
                        <div class="text-center">

                            <button class="btn btn-primary" v-on:click="viewActions(s)" style="height:40px;width:130px;"> View Actions</button>
                            <button class="btn btn-danger" v-on:click="unfollow(s)" style="height:40px;width:130px;"> Unsubscribe</button>
                        </div>
                   </div>
               </div>
            </div>
        </div>
   `,
    methods: {

        reload() {
            //this.id = this.$route.params.id;
            /*axios.get("api/reservations/getClientsSubs/" + this.id).then(response => {
                this.subs = response.data;
            }).catch(function (error) {
                alert('Greska u get subs');
            });*/
            axios({
               method: 'get',
               url: "api/reservations/getClientsSubs/" + this.id,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.subs = response.data;
           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
           },


        viewActions(sub)
        {
            //window.location.href = 'http://localhost:8000/index.html#/clientActions/' + sub.id;
            this.$router.push({path: '/clientActions/' + sub.id});
        },

        unfollow(sub)
        {
            /*axios.post("api/reservations/unfollow/" + this.id + "/" + sub.id).then(response => {
                this.reload();
            }).catch(function (error) {
                alert('Greska u get subs');
            });*/
            axios({
               method: 'post',
               url: "api/reservations/unfollow/" + this.id + "/" + sub.id,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.reload();
           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
        },
    },
});