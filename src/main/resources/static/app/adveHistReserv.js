Vue.component("adventure-history",{
    data:function ()
    {
        return{
            availabilities: [],
            adventure:[],
            adventures:[],
            start:null,
            end:null,
            reservations:[]
        }
    },
    mounted: function (){
        this.loadInstructorsAdventures()
        //this.loadAllReservationHistory()
        //this.loadReservationHistory()
    },
    template: ` 
    <form style="width: 900px; margin: auto">
<!--        <form style="width: 900px; margin: auto"  v-on:submit ="loadReservationHistory">-->
        <h2 class="text-center">Instructors adventures</h2>
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
            <div class="col">
                <h3 class="text-center">Choose your adventure</h3>
                    <select class="select form-control" @change="loadReservationHistory()" v-model="adventure" style="height: 50px">
                        <option v-for="adv in adventures" v-bind:value="adv"> {{adv.name}}</option>
                    </select>
            </div>
        </div>
        <div class = "row">
            <table class="table table-striped" style="width: 1000px" v-if="this.adventure">
              <thead>
                <tr>
                  <th scope="col">Clients email</th>
                  <th scope="col">Start date</th>
                  <th scope="col">End date</th>
                  <th scope="col">Reservation status</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="reservation in reservations">
                    <td> {{reservation.clientEmail}}  </td>
                    <td> {{reservation.startDate}}  </td>
                    <td> {{reservation.endDate}}  </td>
                    <td> {{reservation.status}}  </td>
                </tr>
              </tbody>
            </table>
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

        loadAllReservationHistory(){
            axios.get("api/reservations/all").then(response => {
                this.reservations = response.data;
                console.log(this.reservations)
            })
        },

        // msm sta da ti kazem ti trazi pomoc :)
        loadReservationHistory(){   //onde nam 1 predstavlja id instruktora zasad ne znam o kojem je rec
            axios.get("api/reservations/advreser/"+this.adventure.id+"/3").then(response => {
                this.reservations = response.data;
                console.log(this.reservations)
            })
        },

        zeroPad(num, places) {
            var zero = places - num.toString().length + 1;
            return Array(+(zero > 0 && zero)).join("0") + num;
        },

        dateForBackend(date){
            return date.getFullYear()+'-'+this.zeroPad(date.getMonth()+1, 2)+'-'+this.zeroPad(date.getDate(), 2)+' '+this.zeroPad(date.getHours(), 2)+':'+this.zeroPad(date.getMinutes(), 2)+':'+this.zeroPad(date.getSeconds(), 2)
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