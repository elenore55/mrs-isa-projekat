Vue.component("add-admin",{
    data:function ()
    {
        return{
            form: {
                name: "",
                surname: "",
                email: "",
                phoneNumber: "",
                password: "",
                address: {
                    country: "",
                    city: "",
                    street: ""
                }
            },
            is_main:""
        }
    },
    // mounted: function (){
    //
    // },
    template: `
    <form style="width: 600px; margin: auto"  v-on:submit ="sendRequest">
        <h2 class="text-center">Add admin</h2>
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" v-model="form.name" class="form-control" pattern="[A-Z]([a-z]*)">   
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input type="text" class="form-control" v-model="form.surname" pattern="[A-Z]([a-z]*)">          
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" class="form-control" v-model="form.email" pattern="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$">          
            </div>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" class="form-control" v-model="form.phoneNumber" pattern="[0-9]{8,12}">          
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input type="text" class="form-control" v-model="form.address.country" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control" v-model="form.address.city" pattern="((([A-Z])([a-z]+) ?)+)$">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Street</label>
                    <input type="text" class="form-control" v-model="form.address.street">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Password</label>
                    <input type="text" class="form-control" v-model="form.password">          
                </div>
<!--                <div class="form-group">-->
<!--                    <label>Password repeat</label>-->
<!--                    <input type="text" class="form-control" v-model="passwordRE">          -->
<!--                </div>-->
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <input type="checkbox" id="checkbox" v-model="is_main" />
                <label> Is main</label>
<!--                <textarea class="form-control" style="height: 100px" v-model="adminInfo.is_main"></textarea>-->
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
<!--                <button type="submit" class="btn btn-primary btn-lg" v-on:submit="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>-->
                <button type="submit" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Add new admin</button>
            </div>
        </div>
    </form>
    `,
    methods:{
        sendRequest(){
            console.log(this.form);
            axios.post("api/admin/addAdmin", {
                is_main: this.is_main,
                profileDataDTO: this.form
            }).then(function (response) {
                alert("Successfully updated your personal information");
            }).catch(function (error) {
                alert("An ERROR occurred while updating your personal information");
            });
        }
    }
});