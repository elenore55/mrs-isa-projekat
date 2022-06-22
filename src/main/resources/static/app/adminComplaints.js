Vue.component("admin-complaint",{
    data() {
        return {
            complaints:[],
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
        this.loadComplaints()
            // console.log(this.complaints)
        this.loadComplaintPossibleStatuses()
    },
    template: `
        <div>
            <h2 class="text-center">All complaints</h2>
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
            },
            {
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadComplaints(){
            // axios.get("api/complaint/allPending").then(response => {
            //     this.complaints = response.data;
            //     console.log(this.complaints[0])
            // })
            axios({
                method: 'get',
                url: "api/complaint/allPending",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.complaints = response.data;
                console.log(this.complaints[0])
            })
        },
        loadComplaintPossibleStatuses(){
            // axios.get("api/complaint/approvalStatus").then(response => {
            //     this.apStatus = response.data;
            // })

            axios({
                method: 'get',
                url: "api/complaint/approvalStatus",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.apStatus = response.data;
            })
        },
    }
});