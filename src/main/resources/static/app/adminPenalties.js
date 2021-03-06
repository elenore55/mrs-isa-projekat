Vue.component("admin-penalties",{
    data() {
        return {
            penalties:[],
            apStatus:[],
            selectedStatus:[],
            reason:[],
            id: [],
            token: {}
        }
    },
    mounted: function (){
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;
        alert("Trenutni id je " + this.id);
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 210%");
        this.loadPenalties()
    },
    template: `
        <div>
            <h2 class="text-center">All penalty requirements</h2>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th></th>
                      <th scope="col">Complaint id</th>
                      <th scope="col">Clients email</th>
                      <th scope="col">Owner/Instructor id</th>
                      <th scope="col">Reason</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="pen in penalties">>
                        <td> {{pen.id}}  </td>   
                        <td> {{pen.clientEmail}}  </td>
                        <td> {{pen.ownerId}}  </td>
<!--                        <td> <input type="text" v-model="reason[pen.id]" id="pen.id" class="form-control">  </td>-->
                        <td> {{pen.content}}  </td>
                        <td> <input type="button" value="accept" id="pen.id" v-on:click="respondTo(pen)"></td>
                        <td> <input type="button" value="ignore" id="pen.id" v-on:click="respondToDelete(pen)"></td>
                    </tr>
                  </tbody>
                </table>
        </div>
    `,
    methods:{
        respondTo(pen)
        {
            axios.post("api/clientReviews/updatePenaltyAdmin",{
                id: pen.id,
                penaltyRequested: true,
                clientEmail:pen.clientEmail,
                reservationId:pen.reservationId,
                dateTime:pen.dateTime,
                content:pen.content
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },
        respondToDelete(pen)
        {
            axios.post("api/clientReviews/updatePenaltyAdminDelete",{
                id: pen.id,
                penaltyRequested: true,
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadPenalties(){
            // axios.get("api/clientReviews/allPending").then(response => {
            //     this.penalties = response.data;
            //     console.log(this.penalties[0])
            // })

            axios({
                method: 'get',
                url: "api/clientReviews/allPending",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.penalties = response.data;
                console.log(this.penalties[0])
            })
        },
    }
});