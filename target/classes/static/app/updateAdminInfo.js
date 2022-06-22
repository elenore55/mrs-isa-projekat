Vue.component("update-admin-info",{
    data:function ()
    {
        return{
            adminData:[],
            passwordRe: "",
            ogpassword:"",
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
        this.loadAdminsInfo()
    },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Update your info</h2>
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" v-model="adminData.profileDataDTO.name" class="form-control" pattern="[A-Z]([a-z]*)">   
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input type="text" class="form-control" v-model="adminData.profileDataDTO.surname" pattern="[A-Z]([a-z]*)">          
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" class="form-control" v-model="adminData.profileDataDTO.email" pattern="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$">          
            </div>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" class="form-control" v-model="adminData.profileDataDTO.phoneNumber" pattern="[0-9]{8,12}">          
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input type="text" class="form-control" v-model="adminData.profileDataDTO.address.country" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control" v-model="adminData.profileDataDTO.address.city" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Street</label>
                    <input type="text" class="form-control" v-model="adminData.profileDataDTO.address.street">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Password</label>
                    <input type="text" class="form-control" v-model="adminData.profileDataDTO.password">          
                </div>
                <div class="form-group">
                    <label>Password repeat</label>
                    <input type="text" class="form-control" v-model="passwordRe">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <input type="checkbox" id="checkbox" v-model="adminData.is_main" />
                <label> Is main</label>
<!--                <textarea class="form-control" style="height: 100px" v-model="adminInfo.is_main"></textarea>-->
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
<!--                <button type="submit" class="btn btn-primary btn-lg" v-on:submit="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>-->
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        loadAdminsInfo(){
            // axios.get("api/admin/getAdminData/"+this.id).then(response => {
            //     this.adminData = response.data
            //     this.ogpassword = this.adminData.profileDataDTO.password;
            //     console.log(this.adminData)
            // })
            axios({
                method: 'get',
                url: "api/admin/getAdminData/"+this.id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.adminData = response.data
                this.ogpassword = this.adminData.profileDataDTO.password;
                console.log(this.adminData)
            })
        },
        sendRequest(){
            console.log(this.adminData);
            if(this.adminData.profileDataDTO.password == this.passwordRe || (this.passwordRe=="" && this.adminData.profileDataDTO.password == this.ogpassword)) {
                axios.post("api/admin/updateAdminInfo", {
                    is_main: this.adminData.is_main,
                    profileDataDTO: this.adminData.profileDataDTO,
                    id: this.adminData.id
                },
                {
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