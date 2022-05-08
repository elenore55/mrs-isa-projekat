Vue.component("instructors-adventures",{
    data:function ()
    {
        return{
            adventures: [],
            adventureInfo: {},
            allEquipments:[],
            addressInfo:{
                countryInfo: "",
                cityInfo: "",
                streetInfo: ""
            },
            fishingEquipmentListInfo: [],
            adventure: [],
            form: {
                id: [],
                name: [],
                address: [],
                description: [],
                price: [],
                additionalInfo: [],
                fInstructorId: [],
                imagePaths: [],
                rules: [],
                fishingEquipmentList: [],
                maxPeople: []
            }
        }
    },
    mounted: function (){
        this.loadInstructorsAdventures()
        this.loadEquipment()
    },
    // ako hoces custom ponasanja ti stavi button i v-onclick inace ako se koristi forma idemo submit i onsubmit jer nam daje sam mogucnost provere unosa podataka
    template: `
    <div class="row" style="width: 800px; margin:auto">
        <div class="col">
            <h2 class="text-center">Instructors adventures</h2>
                <select class="select form-control" v-model="adventure" style="height: 50px">
                    <option v-for="adv in adventures">{{adv.id}} - {{adv.name}}</option>
                </select>
                <button type="button" v-on:click="loadAdventureInfo" class="btn btn-secondary my-1"> Press to edit adventure</button>
                <button type="button" v-on:click="deleteAdventure" class="btn btn-secondary my-1"> Press to delete adventure</button>
        </div>
        <div class="col">
            <form style="width: 400px; margin: auto"  v-on:submit ="sendRequest">
                <h2 class="text-center">Update instructor's selected adventure</h2>
                <div class="form-group">
                    <label>Adventure name</label>
                    <input type="text" v-model="adventureInfo.name" class="form-control">    
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea v-model="adventureInfo.description" style="height: 100px" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>Rules</label>
                    <select class="select form-control" multiple v-model="adventureInfo.rules">
                      <option value="Rule1">Rule1</option>
                      <option value="Rule2">Rule2</option>
                      <option value="Rule3">Rule3</option>
                      <option value="Rule4">Rule4</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Country</label>
                    <input v-model="addressInfo.countryInfo" type="text" class="form-control" pattern="([A-Z])([A-Za-z]+)$">
                </div>
                <div class="form-group">
                    <label>City</label>
                    <input v-model="addressInfo.cityInfo" type="text" class="form-control" pattern="([A-Z])([A-Za-z]+)$">
                </div>
                <div class="form-group">
                    <label>Street</label>
                    <input v-model="addressInfo.streetInfo" type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fishing equipment</label>
                        <select class="select form-control" multiple v-model="fishingEquipmentListInfo" style="height: 250px">
                            <option v-for="equipment in adventureInfo.fishingEquipmentList">{{equipment.name}}</option>
                        </select>
                </div>
                <div class="form-group">
                    <label>Additional information</label>
                    <textarea v-model="adventureInfo.additionalInfo" class="form-control" style="height: 115px"></textarea>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input v-model="adventureInfo.price" type="number" step="0.01" min="0" class="form-control"/>
                </div>
                <div class="form-group">
                        <label>Maximum number of people</label>
                        <input v-model="adventureInfo.maxPeople" class="form-control" style="width: 100px" pattern="[0-9]{0,4}">
                </div>
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your adventure's info</button>
            </form>
        </div>
    </div>
    `,
    methods:{
        loadAdventureInfo(){
            axios.get("api/adventures/"+this.adventure.split(' - ')[0]).then(response => {
                this.adventureInfo = response.data;
                this.addressInfo.cityInfo=this.adventureInfo.address.city;
                this.addressInfo.streetInfo=this.adventureInfo.address.street;
                this.addressInfo.countryInfo=this.adventureInfo.address.country;
            })
        },
        // loadEquipment(){
        //     axios.get("api/fishingEquipment/all").then(response => {
        //         this.allEquipments = response.data
        //         console.log(this.allEquipments)
        //     })
        // },
        deleteAdventure(){
            console.log(this.adventure.split(' - ')[0])
            axios.get("api/adventures/deleteAdventure/"+this.adventure.split(' - ')[0]).then(response => {
                alert('Adventure successfully deleted');
                this.loadInstructorsAdventures();
            }).catch(function (error) {
                alert('It is not possible to delete this adventure');
            });
        },
        loadInstructorsAdventures(){
            axios.get("api/adventures/all").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        sendRequest(){
            // console.log(this.adventureInfo) // se nalazi ceo objekat fishingequipmenta
            // console.log("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
            // console.log("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
            // console.log(this.fishingEquipmentListInfo)  // ovde ne
            console.log(this.addressInfo)
            this.adventureInfo.address.city = this.addressInfo.cityInfo;
            this.adventureInfo.address.country = this.addressInfo.countryInfo;
            this.adventureInfo.address.street = this.addressInfo.streetInfo;
            axios.post("api/adventures/updateAdventureInfo", {
                // ovde se nalaze podaci iz dto-a
                id: this.adventureInfo.id,
                name: this.adventureInfo.name,
                description: this.adventureInfo.description,
                rules: this.adventureInfo.rules,
                price: this.adventureInfo.price,
                maxPeople: this.adventureInfo.maxPeople,
                // fishingEquipmentList: this.fishingEquipmentListInfo,
                fishingEquipmentList: this.adventureInfo.fishingEquipmentList,
                address: this.adventureInfo.address,
                additionalInfo: this.adventureInfo.additionalInfo,
                fInstructorId: this.adventureInfo.fInstructorId,
                imagePaths: this.adventureInfo.imagePaths
            }).then(function (response) {
                alert("Successfully updated your adventure's information");
            }).catch(function (error) {
                alert("An ERROR occurred while updating your adventure's information");
            });

        }
    }
});