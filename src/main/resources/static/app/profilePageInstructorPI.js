Vue.component("profile-page-instructorpi",{
    data:function ()
    {
        return{
            instruktorPI: {},
            passwordRE:"",
            ogpassword:[],
            reason: "",
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
        this.loadInstructorProfile()
    },
    // ako hoces custom ponasanja ti stavi button i v-onclick inace ako se koristi forma idemo submit i onsubmit jer nam daje sam mogucnost provere unosa podataka
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Personal information instructor</h2>
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" v-model="instruktorPI.profileDataDTO.name" class="form-control" pattern="[A-Z]([a-z]*)">   
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.surname" pattern="[A-Z]([a-z]*)">          
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.email" pattern="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$">          
            </div>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.phoneNumber" pattern="[0-9]{8,12}">          
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.address.country" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.address.city" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Street</label>
                    <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.address.street">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Password</label>
                    <input type="text" class="form-control" v-model="instruktorPI.profileDataDTO.password">          
                </div>
                <div class="form-group">
                    <label>Password repeat</label>
                    <input type="text" class="form-control" v-model="passwordRE">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Biography</label>
                <textarea class="form-control" style="height: 100px" v-model="instruktorPI.biography"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
<!--                <button type="submit" class="btn btn-primary btn-lg" v-on:submit="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>-->
                <input type="button" class="btn btn-primary btn-lg" value="Delete request" v-on:click="sendDeleteReq()" style="width: 200px;"/>
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -200px">Update your info</button>
            </div>
        </div>
        <div class="row">
            <textarea v-model="reason" class = "" name="textarea" rows="10" cols="70" required></textarea>
        </div>
    </form>
    `,
    methods:{
        sendDeleteReq()
        {
            if(this.reason) {
                axios.post("api/deletionRequests/deleteProfile", {
                    id: this.instruktorPI.id,
                    reason: this.reason,
                },{
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(function (response) {
                    if (response.data == "OK") {
                        //location.replace('http://localhost:8000/#/deleteProfileMessage');
                        this.$router.push({path: '/deleteProfileMessage/'});
                    }
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            }
        },
        loadInstructorProfile(){
            // axios.get("api/instructors/getInstructorData").then(response => {
            //     this.instruktorPI = response.data;
            //     this.ogpassword = this.instruktorPI.profileDataDTO.password;
            //     console.log(this.instruktorPI)
            // })

            axios({
                method: 'get',
                url: "api/instructors/getInstructorData/"+this.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.instruktorPI = response.data;
                this.ogpassword = this.instruktorPI.profileDataDTO.password;
                console.log(this.instruktorPI)
            })
        },
        sendRequest(){
            console.log(this.instruktorPI);
            if(this.instruktorPI.profileDataDTO.password == this.passwordRE || (this.passwordRE=="" && this.instruktorPI.profileDataDTO.password == this.ogpassword)) {
                axios.post("api/instructors/updateInstructorInfo", {
                    biography: this.instruktorPI.biography,
                    profileDataDTO: this.instruktorPI.profileDataDTO,
                    id: this.id
                },{
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(function (response) {
                    alert("Successfully updated your personal information");
                }).catch(function (error) {
                    alert("An ERROR occurred while updating your personal information");
                });
            }
            else
                alert("New password doesnt match the old password");
        }
    }
});