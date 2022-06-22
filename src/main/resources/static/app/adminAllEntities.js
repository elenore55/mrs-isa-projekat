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
            search:[],
            selected:[],
            selectAllUsers: false,
            selectAllAdventures: false,
            selectAllShips: false,
            selectAllCottages: false,
            users:[]
        }
    },
    mounted: function (){
    this.loadInstructorsAdventures()
    this.loadUsers()
    console.log(this.users)
},
    template: `
    <form style="width: 1800px; margin: auto" v-on:submit="sendRequest">
<!--        <form style="width: 900px; margin: auto"  v-on:submit ="loadReservationHistory">-->
        <h2 class="text-center">All entitiess</h2>
        <div class="row">
            <div class="row">
                <div class="col">
                    <label>Search entities</label>
                    <input type="text" class="form-control" v-model="search" style="width: 360px;">
                    <button type="submit" class="btn btn-primary" style="position: relative; right: 0px;bottom: -5px">Search</button>
                    <button type="submit" class="btn btn-primary" style="position: relative; right: 0px;bottom: -5px">Delete selected</button>
                    <br>
                    <br>
                    <br>    
                </div>
                <div class="col" style="color: #0a53be">
<!--                    <label>Enter entity to delete</label>-->
<!--                    <input type="text" class="form-control" v-model="entity" style="width: 160px;">          -->
<!--                -->
                    <label style="font-size: large">User</label>
                    <input type="checkbox" id="one" value="One" v-model="choice" style="width: 30px;" />
                    
                    <label style="font-size: large">| Adventure</label>
                    <input type="checkbox" id="two" value="Two" v-model="choice" style="width: 30px"/>
                    
                   <label style="font-size: large">| Ship</label>
                    <input type="checkbox" id="three" value="Three" v-model="choice" style="width: 30px;" />
                    
                    <label style="font-size: large">| Cottage</label>
                    <input type="checkbox" id="four" value="Four" v-model="choice" style="width: 30px"/>
                </div>
            </div>  
            <div class = "col">
                <label>Users</label>
                <table class="table table-striped" style="width: 800px">
                  <thead>
                    <tr>
                        <th scope="col"><input type="checkbox" v-model="selectAllUsers" @click="selectUsers"></th>  
                        <th scope="col">Email</th>
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Phone number</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="user in users">
                        <td> <input type="checkbox" :value="user.email" v-model="selected"></td>
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
                      <th scope="col"><input type="checkbox" v-model="selectAllAdventures"></th>  
                      <th scope="col">Name</th>
                      <th scope="col">Price</th>
                      <th scope="col">Max People</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="adventure in adventures">
                        <td> <input type="checkbox" :value="adventure.id" v-model="selected"></td>
                        <td> {{adventure.name}}  </td>
                        <td> {{adventure.price}}  </td>
                        <td> {{adventure.maxPeople}}  </td>
                    </tr>
                  </tbody>
                </table>
            </div>
        </div>
    </form>
    `,
    methods:{
        selectUsers() {
            // this.selected = [];
            if (!this.selectAllUsers) {
                for (let i in this.users) {
                    this.selected.push(this.user[i].email);
                }
            }
        },

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
        },
        sendRequest(){
            axios.get("api/TBD").then(response => {
                this.users = response.data;
                // console.log(this.adventures)
            })
        }
    }
});