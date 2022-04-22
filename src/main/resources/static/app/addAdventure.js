Vue.component("add-adventure",{
    data:function ()
    {
        return{
            allEquipments: [],
            //instructorBio_data: '',
            form:{
                name: "",
                address:{
                    country: "",
                    city: "",
                    street: ""
                },
                description: "",
                price: null,
                additionalInfo: "",
                fInstructorBio: "",
                imagePaths: [],
                rules: [],
                fishingEquipmentList: [],
                maxPeople: 1,
                fInstructorId: 1
            }
        }
    },
    mounted: function (){
        this.loadEquipment()
        // this.loadInstructorBio_data()
    },
    template: `
    <form style="width: 1000px; margin: auto" v-on:submit="sendRequest">
        <h2 class="text-center">Add Adventure</h2>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Adventure name</label>
                    <input v-model="form.name" type="text" class="form-control">          
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea v-model="form.description" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>Rules</label>
                    <select class="select form-control" multiple v-model="form.rules">
                      <option value="Rule1">Rule1</option>
                      <option value="Rule2">Rule2</option>
                      <option value="Rule3">Rule3</option>
                      <option value="Rule4">Rule4</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input v-model="form.address.country" type="text" class="form-control" pattern="([A-Z])([A-Za-z]+)$">
                </div>
                <div class="form-group">
                    <label>City</label>
                    <input v-model="form.address.city" type="text" class="form-control" pattern="([A-Z])([A-Za-z]+)$">
                </div>
                <div class="form-group">
                    <label>Street</label>
                    <input v-model="form.address.street" type="text" class="form-control">
                </div>
            </div>
            <div class="col-2">
                <div class="form-group">
                    <label>Fishing equipment</label>
                        <select class="select form-control" multiple v-model="form.fishingEquipmentList" style="height: 250px">
                            <option v-for="equipment in allEquipments">{{equipment.name}}</option>
                        </select>
                </div>                
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Instructor biography</label>
                    <textarea v-model="form.fInstructorBio" class="form-control" style="height: 110px"></textarea>
<!--                    <p>{{instructorBio_data}}  dasdsadsa</p>-->
                </div>
                <div class="form-group">
                    <label>Additional information</label>
                    <textarea v-model="form.additionalInfo" class="form-control" style="height: 115px"></textarea>
                </div>
            </div> 
        </div>
        <div class="row">
            <div class="col-2">
                <div class="form-group">
                    <label>Price</label>
                    <input v-model="form.price" type="number" step="0.01" min="0" class="form-control"/>
                </div>
            </div>
            <div class="col-3">
                <div class="form-group">
                        <label>Maximum number of people</label>
                        <input v-model="form.maxPeople" class="form-control" style="width: 100px" pattern="[0-9]{0,4}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                <label>Pictures</label>
                <input accept="image/*" type="file" class="form-control" multiple/>
            </div>
            <div class="col-4"></div>
            <div class="col-2">
                <div class="form-group">
                   <br>
<!--                   <input type="button" class="btn btn-primary form-control" value="Submit">-->
                   <button type="submit" class="btn btn-primary btn-lg" style="position: relative; right: -220px;bottom: 0px">Submit</button>
                </div>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadEquipment(){
          axios.get("api/fishingEquipment/all").then(response => {
            this.allEquipments = response.data
              console.log(this.allEquipments)
          })
        },
        // loadInstructorBio_data(){
        //     axios.get("api/instructors/getInstructorData").then(response => {
        //         this.instructorBio_data = response.data
        //         console.log(this.instructorBio_data)
        //     })
        // },
        sendRequest(){
            console.log(this.form.fishingEquipmentList)
            axios.post("api/adventures/addAdventure", {
                name: this.form.name,
                description: this.form.description,
                rules: this.form.rules,
                price: this.form.price,
                maxPeople: this.form.maxPeople,
                fishingEquipmentList: this.form.fishingEquipmentList,
                fInstructorBio: this.form.fInstructorBio,
                imagePaths: this.form.imagePaths,
                // country: this.form.country,
                // city: this.form.city,
                // street: this.form.street,
                address: this.form.address,
                additionalInfo: this.form.additionalInfo,
                fInstructorId: 1
            }).then(function (response) {
                alert("Successfully added an adventure");
            }).catch(function (error) {
                alert("An ERROR occurred while adding an adventure");
            });

        }
    }
})