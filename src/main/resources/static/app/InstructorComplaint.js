Vue.component("instructor-complaint",{
    data:function ()
    {
        return{
            adventure:[],
            adventures:[],
            reservations:[],
            reservation:[],
            offer:[],
            review:[],
            brojac:0,
            penalty:false,
            client_email:[]

        }
    },
    mounted: function (){
        this.loadInstructorsAdventures()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Reservation with client</h2>
        <div class="row">
        </div>
        <div class = "row">
            <h5 class="text-center">Instructors adventures</h5>
                <select class="select form-control" v-model="adventure" v-on:change="loadAdventuresReservations()" style="height: 50px">
                    <option v-for="adv in adventures" v-bind:value="adv"> {{adv.name}}</option>
                </select>
        </div>
        <div class = "row">
            <h5 class="text-center">Adventures reservations</h5>
                <select class="select form-control" v-model="reservation" style="height: 50px">
                    <option v-for="reserv in reservations" v-bind:value="reserv"> {{reserv.id}}</option>
                </select>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Client's email</label>
                <textarea v-model="client_email" class="form-control" style="height: 40px"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="form-group; col">
                <label>Review</label>
                <textarea v-model="review" class="form-control" style="height: 40px"></textarea>
            </div>
            <div class="form-group; col">
                <label>Request penalty</label>
                <input type="checkbox" value="true" v-on:click="reqPen()">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Send</button>
            </div>
        </div>
    </form>   
    `,
    methods:{
        reqPen()
        {
            this.penalty = !this.penalty
        },
        loadInstructorsAdventures(){ // ZA SAD OSTAVLJENO DA BUDE 3 ZA INSTRUKTORA
            axios.get("api/adventures/allInstructorsAdventures/"+"3").then(response => {
                this.adventures = response.data;
                // console.log(this.adventures)
            })
        },
        loadAdventuresReservations(){       // ZA SAD ZA INSTRUKTORA POSTAVLJENO DA JE 3
            axios.get("api/reservations/advreser/"+this.adventure.id+"/"+"3").then(response => {
                this.reservations = response.data;
                console.log(this.adventure)
            })
        },
        sendRequest() {
            if (this.review) {
                console.log(this.review)
                console.log(this.penalty)
                console.log(this.client_email)
                console.log(this.reservation.id)
                axios.put("api/clientReviews/addReview", {
                    content: this.review,
                    penaltyRequested: this.penalty,
                    clientEmail: this.client_email,
                    ownerId: 3,
                    reservationId: this.reservation.id
                })
            }
        }
    }
});