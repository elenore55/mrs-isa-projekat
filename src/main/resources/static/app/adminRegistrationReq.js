Vue.component("admin-registrationreq",{
    data() {
        return {
            regreqs:[],
            apStatus:[],
            selectedStatus:[],
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
        this.loadRequests()
        // console.log(this.complaints)
        this.loadrequestPossibleStatuses()
    },
    template: `
        <div>
            <h2 class="text-center">All registration requests</h2>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th></th>
                      <th scope="col">Request id</th>
                      <th scope="col">Email</th>
                      <th scope="col">Type</th>
                      <th scope="col">Status</th>
                      <th scope="col">Respond</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="req in regreqs">>
                        <td> {{req.id}}  </td>   
                        <td> {{req.email}}  </td>
                        <td> {{req.type}}  </td>
                        <td> {{req.reason}}  </td>
                        <td> 
                            <select v-model="req.status">
                              <option v-for="status in apStatus">
                                {{ status }}
                              </option>
                            </select> 
                        </td>
                        <td> <input type="button" value="send" id="req.id" v-on:click="respondTo(req)"></td>
                    </tr>
                  </tbody>
                </table>
        </div>
    `,
    methods:{
        respondTo(req)
        {
            axios.post("api/registrationRequests/updateRegReqAdmin",{
                id: req.id,
                status:req.status,
                email:req.email,
                type:req.type,
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadRequests(){
            // axios.get("api/registrationRequests/allPending").then(response => {
            //     this.regreqs = response.data;
            //     console.log(this.regreqs[0])
            // })

            axios({
                method: 'get',
                url: "api/registrationRequests/allPending",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.regreqs = response.data;
                console.log(this.regreqs[0])
            })
        },
        loadrequestPossibleStatuses(){
            // axios.get("api/registrationRequests/approvalStatus").then(response => {
            //     this.apStatus = response.data;
            // })

            axios({
                method: 'get',
                url: "api/registrationRequests/approvalStatus",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.apStatus = response.data;
            })
        },
    }
});