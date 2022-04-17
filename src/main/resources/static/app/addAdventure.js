Vue.component("add-adventure",{
    data:function ()
    {
        return{
            form:{
                name: "",
                address:{
                    country: "",
                    city: "",
                    street: ""
                },
                description: "",
                price: null,
                additionalInfo: "",
                fInstructorBio: "",
                imagePaths: [],
                rules: [],
                fishingEquipmentList: [],
                maxPeople: 1
            }
        }  //ovde se nalaze podaci koji ce se iskoristiti na komponenti
    },
    template: `
    <form style="width: 800px; margin: auto">
        <h2 class="text-center">Add Adventure</h2>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label>Adventure name</label>
                    <input v-model="form.name" type="text" class="form-control"">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea v-model="form.description" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>Rules</label>
                    <select class="select" multiple v-model="form.rules" class="form-control">
                      <option value="1">Rule 1.</option>
                      <option value="2">Rule 2.</option>
                      <option value="3">Rule 3.</option>
                      <option value="4">Rule 4.</option>
                      <option value="5">Rule 5.</option>
                      <option value="6">Rule 6.</option>
                      <option value="7">Rule 7.</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Country</label>
                    <input v-model="form.country" type="text" class="form-control"">
                </div>
                <div class="form-group">
                    <label>City</label>
                    <input v-model="form.city" type="text" class="form-control"">
                </div>
                <div class="form-group">
                    <label>Street</label>
                    <input v-model="form.street" type="text" class="form-control"">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Fishing equipment</label>
                    <select class="select" multiple v-model="form.fishingEquipmentList" class="form-control">
                      <option value="1">Fishing Rod</option>
                      <option value="2">Fishing Line</option>
                      <option value="3">Hooks</option>
                      <option value="4">Bait</option>
                      <option value="5">Lures</option>
                      <option value="6">Bobbers</option>
                      <option value="7">Sinkers</option>
                    </select>
                </div>                
            </div>
            <div class="col">
                <div class="form-group">
                    <label>Instructor biography</label>
                    <textarea v-model="form.fInstructorBio" class="form-control"></textarea>
                </div>
            </div> 
        </div>
        <div class="row">
            <div class="col-2">
                <div class="form-group">
                    <label>Price</label>
                    <input v-model="form.price" type="number" step="0.01" min="0" class="form-control"/>
                </div>
            </div>
            <div class="col-4">
                <div class="form-group">
                        <label>Maximum number of people</label>
                        <input v-model="form.maxPeople" class="form-control">
                </div>
            </div>
        </div>
    </form>
    `,
    methods:{

    }
})