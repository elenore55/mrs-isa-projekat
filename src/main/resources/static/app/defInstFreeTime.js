Vue.component("availability-instructor",{
    data:function ()
    {
        return{
            availabilities: [],
            start_date:null,
            end_date:null,
            calendar:[],
            inst_id:[]
        }
    },
    mounted: function (){
        this.loadAllInstructorAvailability()
    },
    // ako hoces custom ponasanja ti stavi button i v-onclick inace ako se koristi forma idemo submit i onsubmit jer nam daje sam mogucnost provere unosa podataka
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Add your available time</h2>
        <div class="row">
        </div>
        <div class="row">
            <div class="col">
                <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>
                <label>start date</label>   
            </div>
            <div class="col">
                <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date"></vuejs-datepicker>
                <label>end date</label>
            </div>
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
        loadAllInstructorAvailability(){
            axios.get("api/availability/all").then(response => {
                this.availabilities = response.data;
                console.log(this.availabilities)
            })
        },
        sendRequest(){
            console.log(this.start_date)
            console.log(this.end_date)
            axios.post("api/availability/addAvailability", {
                availabilityDTO: this.availabilities,
                start_date: this.start_date,
                end_date:this.end_date
            }).then(function (response) {
                alert("Successfully updated your personal information");
            }).catch(function (error) {
                alert("An ERROR occurred while updating your personal information");
            });
        }
    }
});