Vue.component("advreserv-with-client",{
    data:function ()
    {
        return{
            start:null,
            end:null,
            adventure:[],
            adventures:[],
            offer:[],
            price:[],
            client_email:[]

        }
    },
    mounted: function (){
        // this.loadAllInstructorAvailability()
        this.loadInstructorsAdventures()
        // this.getOfferFromAdventure()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Reservation with client</h2>
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
                <label>start date</label>  
                <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date" class="form-control"></vuejs-datepicker> 
            </div>
            <div class="col">
                <label>end date</label>
                <vuejs-datepicker v-model="end" format="dd.MM.yyyy." id="end-date" class="form-control"></vuejs-datepicker>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Client's email</label>
                <textarea v-model="client_email" class="form-control" style="height: 40px"></textarea>
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
                axios.post("api/adventures/addReservationClient", {
                    // availabilityDTO: this.availabilityDTO
                    start: this.start,
                    end: this.duration,
                    adventure: this.adventure.id,
                    email: this.client_email
                }).then(function (response) {
                    alert("Successfully updated your personal information");
                }).catch(function (error) {
                    alert("An ERROR occurred while updating your personal information");
                });
            })
        }
    }
});