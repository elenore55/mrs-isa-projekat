Vue.component("instructor-calendar",{
    data:function ()
    {
        return{
            reservations: [],
            start:null,
            end:null
        }
    },
    mounted: function (){
        this.loadAllInstructorsReservations()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Instructorsss calendar</h2>
        <div class="row">
        </div>
        <div class="row">
            <div class="col">   
<!--            ostavljamo zbog potreba sortiranja vremena-->
                <vuejs-datepicker v-model="start" format="dd.MM.yyyy." id="start-date"></vuejs-datepicker>
                <label>start date</label>   
            </div>
            <div class="col">
                <vuejs-datepicker v-model="end" format="dd.MM.yyyy." id="end-date"></vuejs-datepicker>
                <label>end date</label>
            </div>
        </div>
        <div class = "row">
            <p> ovde prikazati rezervacije u zadatom opsegu</p>
<!--            <p v-for="ava in availabilities">{{ava}}</p>-->
        </div>
        <div class="row">
            <div class="col">
                <br>
<!--                <button type="submit" class="btn btn-primary btn-lg" v-on:submit="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>-->
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Load</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadAllInstructorsReservations(){
            axios.get("api/reservations/all").then(response => {
                this.reservations = response.data;
                // console.log(this.availabilities)
                // console.log("BBBB")
            })
        },
        sendRequest(){
            console.log("AAAAAAAAAAAAA")
            axios.get("api/reservations/allBetween").then(response => {
                this.reservations = response.data;
                // console.log(this.availabilities)
                // console.log("BBBB")
            })
        }
    }
});