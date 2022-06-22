Vue.component("availability-instructor",{
    data:function ()
    {
        return{
            // availabilities: [],
            start:null,
            end:null,
            calendar:[],
            adventure:[],
            adventures:[],
            offer:[],
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
        // this.loadAllInstructorAvailability()
        this.loadInstructorsAdventures()
        // this.getOfferFromAdventure()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Add adventure available time</h2>
        <div class="row">
        </div>
        <div class="row">
            <div class="col">
                <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>
                <label>start date</label>   
            </div>
            <div class="col">
                <vuejs-datepicker v-model="end" format="dd.MM.yyyy." id="end-date"></vuejs-datepicker>
                <label>end date</label>
            </div>
        </div>
        <div class = "row">
            <h2 class="text-center">Instructors adventures</h2>
                <select class="select form-control" v-model="adventure" style="height: 50px">
                    <option v-for="adv in adventures" v-bind:value="adv"> {{adv.name}}</option>
                </select>
        </div>
        <div class="row">
            <div class="col">
                <br>
<!--                <button type="submit" class="btn btn-primary btn-lg" v-on:submit="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>-->
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Add</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        // loadAllInstructorAvailability(){
        //     axios.get("api/availability/all").then(response => {
        //         this.availabilities = response.data;
        //         // console.log(this.availabilities)
        //         // console.log("BBBB")
        //     })
        // },
        loadInstructorsAdventures(){
            // axios.get("api/adventures/all").then(response => {
            //     this.adventures = response.data;
            //     // console.log(this.adventures)
            // })
            axios({
                method: 'get',
                url: "api/adventures/all/"+this.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        sendRequest(){
            // axios.get("api/offers/getOffer/" + this.adventure.id).then(response => {
            //     this.offer = response.data;
            //     axios.post("api/availability/addAvailability", {
            //         // availabilityDTO: this.availabilityDTO
            //         start: this.start,
            //         end:this.end,
            //         offer: this.offer
            //     }).then(function (response) {
            //         alert("Successfully added availability period");
            //     }).catch(function (error) {
            //         alert("An ERROR occurred while adding an availability");
            //     });
            // })

            axios({
                method: 'get',
                url: "api/offers/getOffer/"+this.adventure.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.offer = response.data;
                axios.post("api/availability/addAvailability", {
                    // availabilityDTO: this.availabilityDTO
                    start: this.start,
                    end:this.end,
                    offer: this.offer
                },{
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(function (response) {
                    alert("Successfully added availability period");
                }).catch(function (error) {
                    alert("An ERROR occurred while adding an availability");
                });
            })
        }
    }
});