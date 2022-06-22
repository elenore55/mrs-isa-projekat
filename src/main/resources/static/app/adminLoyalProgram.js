Vue.component("admin-loyalprogram",{
    data:function ()
    {
        return{
            coefficients:[],
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
        this.loadLoyalProgram()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <div style="align-content: center">
            <h2 class="text-center">Personal information instructor</h2>
            <div class="row" style="width: 500px">
                <label style="font-size: x-large">Required points</label>
                <div class="col">
                    <div class="form-group">
                        <label>Silver</label>
                        <input type="number" step="1" min="1" v-model="coefficients.requiredPointsSilver" class="form-control">   
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Gold</label>
                        <input type="number" step="1" min="1" v-model="coefficients.requiredPointsGold" class="form-control">   
                    </div>
                </div>
            </div>
<!--            /////////////////////////////////////////////////////////////////-->
            <br><br><br>
            <div class="row" style="width: 500px">
                <label style="font-size: x-large">Reservation points</label>
                <div class="col">
                    <div class="form-group">
                        <label>User</label>
                        <input type="number" step="1" min="1" v-model="coefficients.userReservationPoints" class="form-control">   
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Owner</label>
                        <input type="number" step="1" min="1" v-model="coefficients.ownerReservationPoints" class="form-control">   
                    </div>
                </div>
            </div>
            <!--            /////////////////////////////////////////////////////////////////-->
            <br><br><br>
            <div class="row" style="width: 500px">
                <label style="font-size: x-large">Percentages</label>
                <div class="col">
                    <div class="form-group">
                        <label>Client silver</label>
                        <input type="number" step="0.1" min="0.1" v-model="coefficients.percentageClientSilver" class="form-control" >   
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Client gold </label>
                        <input type="number" step="0.1" min="0.1" v-model="coefficients.percentageClientGold" class="form-control" >   
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Owner silver</label>
                        <input type="number" step="0.1" min="0.1" v-model="coefficients.percentageOwnerSilver" class="form-control" >   
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label>Owner gold</label>
                        <input type="number" step="0.1" min="0.1" v-model="coefficients.percentageOwnerGold" class="form-control" >   
                    </div>
                </div>
                
                <div class="form-group" style="column-span: 2">
                    <label>Reservation</label>
                    <input type="number" step="0.1" min="0.1" v-model="coefficients.reservationPercentage" class="form-control">   
                </div>
            </div>
            <div class="row" style="width: 500px">
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update loyalty values</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadLoyalProgram(){
            // axios.get("api/coefficients/getCoefs").then(response => {
            //     this.coefficients = response.data;
            //     console.log(this.coefficients)
            // })

            axios({
                method: 'get',
                url: "api/coefficients/getCoefs",
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.coefficients = response.data;
                console.log(this.coefficients)
            })
        },
        sendRequest(){
            console.log(this.coefficients);
            axios.post("api/coefficients/updateCoeffs", {
            requiredPointsSilver:this.coefficients.requiredPointsSilver,
            requiredPointsGold:this.coefficients.requiredPointsGold,
            userReservationPoints:this.coefficients.userReservationPoints,
            ownerReservationPoints:this.coefficients.ownerReservationPoints,
            percentageClientSilver:this.coefficients.percentageClientSilver,
            percentageClientGold:this.coefficients.percentageClientGold,
            percentageOwnerSilver:this.coefficients.percentageOwnerSilver,
            percentageOwnerGold:this.coefficients.percentageOwnerGold,
            reservationPercentage:this.coefficients.reservationPercentage
            },{
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(function (response) {
                this.$router.go();
                alert("Successfully updated coeffs");
            }).catch(function (error) {
                alert("An ERROR occurred while updating");
            });
        }
    }
});