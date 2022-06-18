Vue.component("admin-penalties",{
    data() {
        return {
            penalties:[],
            apStatus:[],
            selectedStatus:[],
            reason:[]
        }
    },
    mounted: function (){
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
                        <td> <input type="text" v-model="reason[pen.id]" id="pen.id" class="form-control">  </td>
                        <td> <input type="button" value="send" id="pen.id" v-on:click="respondTo(pen)"></td>
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
                penaltyRequested: true
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },

        loadPenalties(){
            axios.get("api/clientReviews/allPending").then(response => {
                this.penalties = response.data;
                console.log(this.penalties[0])
            })
        },
    }
});