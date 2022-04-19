Vue.component("profile-page-instructorpi",{
    data:function ()
    {
        return{
            form: {
                name: "",
                surname: "",
                email: "",
                phoneNumber: "",
                address: {
                    country: "",
                    city: "",
                    street: ""
                },
                password: "",
                passwordRE: "",
                biography: ""
            },
            instruktorPI:""

        }
    },
    mounted: function (){
        this.loadInstructorProfile()
    },
    template: `
    <form style="width: 600px; margin: auto">
        <h2 class="text-center">Personal information instructor</h2>
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" v-model="form.name" class="form-control" value={{instruktorPI.biography}}>
                <p>{{instruktorPI.name}} NEKI dodatni tekst</p>          
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input type="text" class="form-control" v-model="form.surname">          
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" class="form-control" v-model="form.email">          
            </div>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" class="form-control" v-model="form.phoneNumber">          
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input type="text" class="form-control" v-model="form.address.country">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control" v-model="form.address.city">          
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
                <div class="form-group">
                    <label>Password repeat</label>
                    <input type="text" class="form-control" v-model="form.passwordRE">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Biography</label>
                <textarea class="form-control" style="height: 100px" v-model="form.passwordRE">{{instruktorPI.biography}}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
                <button type="button" class="btn btn-primary btn-lg" v-on:click="sendRequest" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>
            </div>
        </div>
        <div class="row">
            <p>Name: {{instruktorPI.name}}</p>
            <p>Surname: {{instruktorPI.surname}}</p>
            <p>Email: {{instruktorPI.email}}</p>
            <p>Phone number: {{instruktorPI.phoneNumber}}</p>
            <p>Address: Street-{{instruktorPI.address.street}}, City-{{instruktorPI.address.city}}, Country-{{instruktorPI.address.country}}</p>
            <p>Password: {{instruktorPI.password}}</p>
            <p>Biography: {{instruktorPI.biography}}</p>
            <p>ID: {{instruktorPI.id}}</p>
        </div> 
    </form>
    `,
    methods:{
        loadInstructorProfile(){
            axios.get("api/instructors/getInstructorData").then(response => {
                this.instruktorPI = response.data
                // console.log(this.instruktorPI)
            })
        },
        sendRequest(){
            axios.post("api/instructors/updateInstructorInfo", {
                name: this.form.name,
                surname: this.form.surname,
                email: this.form.email,
                phoneNumber: this.form.phoneNumber,
                address: this.form.address,
                password: this.form.password,
                biography: this.form.biography,
                id: 1
            }).then(function (response) {
                alert("Successfully updated your personal information");
            }).catch(function (error) {
                alert("An ERROR occurred while updating your personal information");
            });

        }
    }
})