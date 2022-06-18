Vue.component("admin-registrationreq",{
    data() {
        return {
            complaints:[],
            apStatus:[],
            selectedStatus:[]
        }
    },
    mounted: function (){
        this.loadRequests()
        // console.log(this.complaints)
        this.loadrequestPossibleStatuses()
    },
    template: `
        <div>
            <h2 class="text-center">All registration requests</h2>
<!--                <div v-for="comp in complaints">-->
<!--                    <div> {{comp.id}} {{comp.content}} {{comp.dateTime}} {{comp.adminApprovalStatus}}</div>-->
<!--                </div>-->
            
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th></th>
                      <th scope="col">Complaint id</th>
                      <th scope="col">Content</th>
                      <th scope="col">Status</th>
                      <th scope="col">Respond</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="comp in complaints">>
                        <td> {{comp.id}}  </td>   
                        <td> {{comp.content}}  </td>
                        <td> {{comp.adminApprovalStatus}}  </td>
                        <td> 
                            <select v-model="comp.adminApprovalStatus">
                              <option v-for="status in apStatus">
                                {{ status }}
                              </option>
                            </select> 
                        </td>
                        <td> <input type="button" value="send" id="comp.id" v-on:click="respondTo(comp)"></td>
                    </tr>
                  </tbody>
                </table>
        </div>
    `,
    methods:{
        respondTo(comp)
        {
            axios.post("api/complaint/updateComplaintAdmin",{
                content : comp.content,
                clientDTO: comp.clientDTO,
                id: comp.id,
                adminApprovalStatus:comp.adminApprovalStatus,
                dateTime: comp.dateTime
                // }).then(this.$router.go()).catch(console.log("Nesto nije valjano"))
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadRequests(){
            axios.get("api/complaint/allPending").then(response => {
                this.complaints = response.data;
                console.log(this.complaints[0])
            })
        },
        loadrequestPossibleStatuses(){
            axios.get("api/complaint/approvalStatus").then(response => {
                this.apStatus = response.data;
            })
        },
    }
});