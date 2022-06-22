Vue.component("admin-feedback",{
    data() {
        return {
            feedbacks:[],
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
        this.loadFeedbacks()
        this.loadFeedbacksPossibleStatuses()
    },
    template: `
        <div>
            <h2 class="text-center">All feedbacks</h2>
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
                    <tr v-for="fed in feedbacks">>
                        <td> {{fed.id}}  </td>   
                        <td> {{fed.comment}}  </td>
                        <td> {{fed.status}}  </td>
                        <td> 
                            <select v-model="fed.status">
                              <option v-for="status in apStatus">
                                {{ status }}
                              </option>
                            </select> 
                        </td>
                        <td> <input type="button" value="send" id="fed.id" v-on:click="respondTo(fed)"></td>
                    </tr>
                  </tbody>
                </table>
        </div>
    `,
    methods:{
        respondTo(fed)
        {
            axios.post("api/feedback/updateFeedbackAdmin",{
                id: fed.id,
                status:fed.status,
                reservationId:fed.reservationId
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadFeedbacks(){
            // axios.get("api/feedback/allPending").then(response => {
            //     this.feedbacks = response.data;
            //     console.log(this.feedbacks[0])
            // })

            axios({
                method: 'get',
                url: "api/feedback/allPending",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.feedbacks = response.data;
                console.log(this.feedbacks[0])
            })
        },
        loadFeedbacksPossibleStatuses(){
            // axios.get("api/feedback/approvalStatus").then(response => {
            //     this.apStatus = response.data;
            // })

            axios({
                method: 'get',
                url: "api/feedback/approvalStatus",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.apStatus = response.data;
            })
        },
    }
});