Vue.component("instadv-reserv",{
    data:function ()
    {
        return{
            adventures: [],
            reservations:[],
            apStatus:[],
            selectedStatus:[],
            adventure: [],
            checked:false,
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
        this.loadInstructorsAdventures()
        this.loadReservationsPossibleStatuses()
    },
    // ako hoces custom ponasanja ti stavi button i v-onclick inace ako se koristi forma idemo submit i onsubmit jer nam daje sam mogucnost provere unosa podataka
    template: `
    <div class="row" style="width: 800px; margin:auto">
        <div class="col">
            <h2 class="text-center">Instructors adventures</h2>
                <select class="select form-control" v-model="adventure" style="height: 50px">
                    <option v-for="adv in adventures">{{adv.id}} - {{adv.name}}</option>
                </select>
                <button type="button" v-on:click="loadReservations" class="btn btn-secondary my-1"> Press to load adventures reservations</button>
                <label>Show all</label>
                <input type="checkbox" v-model="checked">
        </div>
        <div class = "row">
            <table class="table table-striped" style="width: 800px">
              <thead>
                <tr>
                  <th></th>
                  <th scope="col">Adventure</th>
                  <th scope="col">Start date</th>
                  <th scope="col">End date</th>
                  <th scope="col">Clients email</th>
                  <th scope="col">Respond</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="comp in reservations">>
                    <td> {{comp.offerName}}  </td>   
                    <td> {{comp.startDate}}  </td>
                    <td> {{comp.endDate}}  </td>
                    <td> {{comp.clientEmail}}  </td>
                    <td> 
                        <select v-model="comp.status">
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
            
    </div>
    `,
    methods:{
        loadInstructorsAdventures(){
            // axios.get("api/adventures/all").then(response => {
            //     this.adventures = response.data;
            //     // console.log(this.adventures)
            // })

            axios({
                method: 'get',
                url: "api/adventures/allInstructorsAdventures"+this.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        loadReservations(){ // zasad id od instruktora ostaje na 3
            if(this.checked)
            {
                // axios.get("api/reservations/allForInstructor/"+3+"/"+this.adventure.split(' - ')[0]).then(response => {
                //     this.reservations = response.data;
                //     console.log(this.reservations[0])
                // })

                axios({
                    method: 'get',
                    url: "api/reservations/allForInstructor/"+this.id+"/"+this.adventure.split(' - ')[0],
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(response => {
                    this.reservations = response.data;
                    console.log(this.reservations[0])
                })

            }
            else
            {
                // axios.get("api/reservations/allPendingForInstructor/"+3+"/"+this.adventure.split(' - ')[0]).then(response => {
                //     this.reservations = response.data;
                //     console.log(this.reservations[0])
                // })

                axios({
                    method: 'get',
                    url: "api/reservations/allPendingForInstructor/"+this.id+"/"+this.adventure.split(' - ')[0],
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(response => {
                    this.reservations = response.data;
                    console.log(this.reservations[0])
                })
            }
        },
        loadReservationsPossibleStatuses(){
            // axios.get("api/adventures/approvalStatus").then(response => {
            //     this.apStatus = response.data;
            //     console.log(this.apStatus)
            // })

            axios({
                method: 'get',
                url: "api/adventures/approvalStatus",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.apStatus = response.data;
                console.log(this.apStatus)
            })
        },
        respondTo(comp)
        {   // traba da se naporavi
            axios.post("api/reservations/updateAdventuresreservation",{
                id: comp.id,
                status:comp.status,
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(setTimeout(()=>this.$router.go(),100)).catch(console.log("Nesto nije valjano"))
        },
    }
});