Vue.component("admin-deletreq",{
    data() {
        return {
            reqs:[],
            apStatus:[],
            selectedStatus:[]
        }
    },
    mounted: function (){
        this.loadDeletionReqs()
        this.loadReqsPossibleStatuses()
    },
    template: `
        <div>
            <h2 class="text-center">All deletion reqs</h2>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th></th>
                      <th scope="col">Request id</th>
                      <th scope="col">User email</th>
                      <th scope="col">Respond</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="req in reqs">>
                        <td> {{req.id}}  </td>
                        <td> {{req.userDTO.email}}  </td>
                        <td> {{req.status}}  </td>
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
            axios.post("api/deletionRequests/updateDeletionReqAdmin",{
                userDTO: req.clientDTO,
                id: req.id,
                status:req.status,
            }).then(this.$router.go()).catch(console.log("Nesto nije valjano"))
        },

        loadDeletionReqs(){
            axios.get("api/deletionRequests/allPending").then(response => {
                this.reqs = response.data;
                console.log(this.reqs[0])
            })
        },
        loadReqsPossibleStatuses(){
            axios.get("api/deletionRequests/approvalStatus").then(response => {
                this.apStatus = response.data;
            })
        },
    }
});