Vue.component("adventure-history",{
    data:function ()
    {
        return{
            availabilities: [],
            adventure:[],
            adventures:[],
            start:null,
            end:null
        }
    },
    mounted: function (){
        this.loadAllInstructorAvailability()
        this.loadInstructorsAdventures()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Instructors adventures</h2>
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
            <p> ovde prikazati rezervacije avantura</p>
<!--            <p v-for="ava in availabilities">{{ava}}</p>-->
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
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Load</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadAllInstructorAvailability(){
            axios.get("api/availability/all").then(response => {
                this.availabilities = response.data;
                // console.log(this.availabilities)
                // console.log("BBBB")
            })
        },
        loadInstructorsAdventures(){
            axios.get("api/adventures/all").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },

        sendRequest(){
            console.log("AAAAAAAAAAAAA")
            axios.get("api/availability/betweenDates").then(response => {
                this.availabilities = response.data;
                // console.log(this.availabilities)
                // console.log("BBBB")
            })
        }
    }
});