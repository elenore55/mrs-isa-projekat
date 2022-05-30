Vue.component("adventure-quick",{
    data:function ()
    {
        return{
            start:null,
            duration:null,
            action_start:null,
            action_duration:null,
            maxPeople:[],
            adventure:[],
            adventures:[],
            offer:[],
            price:[],
            additional_services:[],
            address_id:[],
            type:[]
        }
    },
    mounted: function (){
        // this.loadAllInstructorAvailability()
        this.loadInstructorsAdventures()
        // this.getOfferFromAdventure()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Fast adventure reservation</h2>
        <div class="row">
        </div>
        <div class = "row">
            <h5 class="text-center">Instructors adventures</h5>
                <select class="select form-control" v-model="adventure" style="height: 50px">
                    <option v-for="adv in adventures" v-bind:value="adv"> {{adv.name}}</option>
                </select>
        </div>
        <div class="row">
            <div class="col">
                <label>Start date</label>  
                <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date" class="form-control"></vuejs-datepicker> 
            </div>
            <div class="col">
                <label>Duration</label>
                <input v-model="duration" type="number" step="1" min="0" class="form-control"/>
            </div>
            
            <div class="col">
                <label>Action start date</label>  
                <vuejs-datepicker v-model="action_start" format="dd.MM.yyyy." id="start-date" class="form-control"></vuejs-datepicker> 
            </div>
            <div class="col">
                <label>Action duration</label>
                <input v-model="action_duration" type="number" step="1" min="0" class="form-control"/>
            </div>
            
        </div>
        <div class="row">
            <div class="col">
                <label>Max people</label>
                <input v-model="maxPeople" type="number" step="1" min="0" class="form-control"/>
            </div>
            <div class="col">
                <label>Price in EUR</label>
                <input v-model="price" type="number" step="1" min="0" class="form-control"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Additional services</label>
                <textarea v-model="additional_services" class="form-control" style="height: 115px"></textarea>
            </div>
            <div class="form-group">
                <label>Type</label>
                <textarea v-model="type" class="form-control" style="height: 50px"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Add</button>
            </div>
        </div>
    </form>   
    `,
    methods:{
        loadInstructorsAdventures(){
            axios.get("api/adventures/all").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        sendRequest(){
            axios.get("api/offers/getOffer/" + this.adventure.id).then(response => {
                this.offer = response.data;
                axios.post("api/adventures/addFastReservation", {
                    // availabilityDTO: this.availabilityDTO
                    start: this.start,
                    duration: this.duration,

                    action_start: this.action_start,
                    action_duration: this.action_duration,

                    maxPeople: this.maxPeople,
                    price: this.price,
                    adventure: this.adventure.id
                }).then(function (response) {
                    alert("Successfully updated your personal information");
                }).catch(function (error) {
                    alert("An ERROR occurred while updating your personal information");
                });
            })
        }
    }
});