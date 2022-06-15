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
            selectedUsers: [],
            selectedAdventures: [],
            selectedShips: [],
            selectedCottages: [],
            users:[]
        }
    },
    mounted: function (){
        this.loadInstructorsAdventures()
        this.loadUsers()
        this.loadShips()
        this.loadCottages()
        console.log(this.users)
        console.log(this.ships)
        console.log(this.cottages)
        console.log(this.adventures)
    },
    template: `
<!--        <form style="width: 900px; margin: auto"  v-on:submit ="loadReservationHistory">   mozes da obrises formu-->
        <div>
            <h2 class="text-center">All entitiesss</h2>
            <div class="col">
                <label>Search entities</label>
                <input type="text" class="form-control" v-model="search" style="width: 360px;">
                <button class="btn btn-primary" v-on:click="deleteSelected()" style="position: relative; right: 0px;bottom: -5px">Delete selected</button>
                <br>
                <br>
                <br>    
            </div>
            <div class="row">
                <div class = "col">
                    <label>Users</label>
                    <table class="table table-striped" style="width: 800px">
                      <thead>
                        <tr> 
                            <th></th> 
                            <th scope="col">Email</th>
                            <th scope="col">Name</th>
                            <th scope="col">Surname</th>
                            <th scope="col">Phone number</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(user,index) in users">
                            <td> <input type="checkbox" id="user.id" value="user.id" v-on:click="enterValuesInLists(user.id,selectedUsers)"></td>
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
                          <th></th>
                          <th scope="col">Name</th>
                          <th scope="col">Price</th>
                          <th scope="col">Max People</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(adventure,index) in adventures">
                            <td> <input type="checkbox" id="adventure.id" value="adventure.id" v-on:click="enterValuesInLists(adventure.id,selectedAdventures)"></td>
                            <td> {{adventure.name}}  </td>
                            <td> {{adventure.price}}  </td>
                            <td> {{adventure.maxPeople}}  </td>
                        </tr>
                      </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class = "col">
                    <label>Ships</label>
                    <table class="table table-striped" style="width: 800px">
                      <thead>
                        <tr>
                          <th></th>
                          <th scope="col">Name</th>
                          <th scope="col">Capacity</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(ship,index) in ships">
                            <td> <input type="checkbox" id="ship.id" value="ship.id" v-on:click="enterValuesInLists(ship,selectedShips)" ></td>
                            <td> {{ship.name}}  </td>
                            <td> {{ship.capacity}}  </td>
                            <td> {{ship.price}}  </td>
                        </tr>
                      </tbody>
                    </table>
                </div>
                <div class = "col">
                    <label>Cottages</label>
                    <table class="table table-striped" style="width: 800px">
                      <thead>
                        <tr>
                          <th></th>
                          <th scope="col">Name</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(cottage,index) in cottages">
                            <td> <input type="checkbox" id="cottage.id" value="cottage.id" v-on:click="enterValuesInLists(cottage.id,selectedCottages)"></td>
                            <td> {{cottage.name}}  </td>
                            <td> {{cottage.price}}  </td>
                        </tr>
                      </tbody>
                    </table>
                </div>
            </div>
        </div>
    `,
    methods:{
        enterValuesInLists(element,list)
        {
            if(list.includes(element))
            {
                const index = list.indexOf(element)
                if(index>-1)
                    list.splice(index,1)
            }
            else
            {
                list.push(element)
            }
        },
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
        loadShips(){ //get ships metoda u ShipControlleru
            axios.get("api/ships/getShips").then(response => {
                this.ships = response.data;
                // console.log(this.adventures)
            })
        },
        loadCottages(){ // get cottages metoda u CottageControleru
            axios.get("api/cottages/getCottages").then(response => {
                this.cottages = response.data;
                // console.log(this.adventures)
            })
        },
        deleteSelected() {
            for(let element of this.selectedShips)
            {
                // axios.delete("api/offers/deleteOffer/"+element.id+"/"+element.ownerId)
                axios.delete("api/shipOwner/deleteTheShip/"+element.id+"/"+element.ownerId)
            }
            console.log("AAAAAAAAAAAAAAAA")

        },
        sendRequest(){
            axios.get("api/TBD").then(response => {
                this.users = response.data;
                // console.log(this.adventures)
            })
        }
    }
});