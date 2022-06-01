Vue.component("admin-entities",{
    data() {
        return {
            reservations: [],
            adventures:[],
            ships:[],
            cottages:[],
            start:null,
            end:null,
            entity:[],
            choice:[],
            users:[]
        }
    },
    mounted: function (){
    this.loadInstructorsAdventures()
    this.loadUsers()
    console.log(this.users)
},
    template: `
    <form style="width: 1800px; margin: auto">
<!--        <form style="width: 900px; margin: auto"  v-on:submit ="loadReservationHistory">-->
        <h2 class="text-center">All entities</h2>
        <div class="row">
            <div class = "col">
                <label>Users</label>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th scope="col">Email</th>
                      <th scope="col">Name</th>
                      <th scope="col">Surname</th>
                      <th scope="col">Phone number</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="user in users">
                        <td> {{user.email}}  </td>
                        <td> {{user.name}}  </td>
                        <td> {{user.surname}}  </td>
                        <td> {{user.phoneNumber}}  </td>
                    </tr>
                  </tbody>
                </table>
            </div>
            <div class = "col">
                <label>Adventures</label>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                      <th scope="col">Name</th>
                      <th scope="col">Price</th>
                      <th scope="col">Max People</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="adventure in adventures">
                        <td> {{adventure.name}}  </td>
                        <td> {{adventure.price}}  </td>
                        <td> {{adventure.maxPeople}}  </td>
                    </tr>
                  </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Enter entity to delete</label>
                <input type="text" class="form-control" v-model="entity" style="width: 360px;">          
            
                <label for="one">User</label>
                <input type="radio" id="one" value="One" v-model="choice" style="width: 30px;" />
                
                <label for="two">| Adventure</label>
                <input type="radio" id="two" value="Two" v-model="choice" style="width: 30px"/>
                
                <label for="three">| Ship</label>
                <input type="radio" id="three" value="Three" v-model="choice" style="width: 30px;" />
                
                <label for="four">| Cottage</label>
                <input type="radio" id="four" value="Four" v-model="choice" style="width: 30px"/>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadUsers(){
            axios.get("api/users/allUsers").then(response => {
                this.users = response.data;
                // console.log(this.adventures)
            })
        },

        loadInstructorsAdventures(){
            axios.get("api/adventures/all").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        loadShips(){
            axios.get("api/TBD").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        loadCottages(){
            axios.get("api/TBD").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        }
    }
});