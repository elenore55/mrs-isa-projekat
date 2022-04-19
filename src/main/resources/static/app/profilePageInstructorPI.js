Vue.component("profile-page-instructorpi",{
    data:function ()
    {
        return{
            name:"",
            surname:"",
            email:"",
            phoneNumber:"",
            address:"",
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
                <input type="text" class="form-control" value="{{instruktorPI.biography}}">
                <p>{{instruktorPI.biography}} NEKI dodatni tekst</p>          
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input type="text" class="form-control">          
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" class="form-control">          
            </div>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" class="form-control">          
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input type="text" class="form-control">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>City</label>
                    <input type="text" class="form-control">          
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Street</label>
                    <input type="text" class="form-control">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Password</label>
                    <input type="text" class="form-control">          
                </div>
                <div class="form-group">
                    <label>Password repeat</label>
                    <input type="text" class="form-control">          
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label>Biography</label>
                <textarea class="form-control" style="height: 100px"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <br>
                <button type="button" class="btn btn-primary btn-lg" style="width: 200px; position: relative; bottom: 0px; right: -400px">Update your info</button>
            </div>
        </div> 
    </form>
    `,
    methods:{
        loadInstructorProfile(){
            axios.get("api/instructors/getInstructorData").then(response => {
                this.instruktorPI = response.data
                console.log(this.instruktorPI)
            })
        }
    }
})