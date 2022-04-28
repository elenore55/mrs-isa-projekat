Vue.component("client-home", {
   data: function() {
   return {
        form: {
             entityType: "",
             name: "Pera",
             surname: "Peric",
             street: "Zmaj Jovina",
             city: "Novi Sad",
             country: "Srbija",
             phone: "00381-256-552",
             category: "Regular",
               }
           }
   },
   template: `
   <div class="w-100">
   <client-navbar></client-navbar>
   <div class ="vertical-left col-md-2 m-2" style="border:1px solid rgb(156, 151, 151); border-radius: 25px">
           <form class="p-3">
               <h3> Search entities </h3>
               <div class="form-check">
                   <input v-model="form.entityType" name = "et"
                   type="radio" id="cottageRadioButton" value="cottage">
                   <label for="cottageRadioButton">Cottage</label>
               </div>
               <div class="form-check ">
                   <input v-model="form.entityType" name = "et"
                   type="radio" id="shipRadioButton" value="ship">
                   <label for="shipRadioButton">Ship</label>
               </div>
               <div class="form-check">
                   <input v-model="form.entityType" name = "et"
                   type="radio" id="adventureRadioButton" value="adventure">
                   <label for="adventureRadioButton">Adventure</label>
               </div>

               <div class="p-2">
                   <div class="form-group col-md-6 ">
                       <label for="startDate" class="control-label">Start Date</label>
                       <input type="date" value='' class="" id="startDate" required>
                   </div>

                   <div class="form-group col-md-6">
                       <label for="endDate" class="control-label">End Date</label>
                       <input type="date" value='' class="" id="endDate" required>
                   </div>
               </div>

               <div class="p-2">
                   <div class="form-group ">
                       <label for="country" class="control-label">Country</label>
                       <input type="text" value='' class="" id="country" required>
                   </div>

                   <div class="form-group">
                       <label for="city" class="control-label">City</label>
                       <input type="text" value='' class=" " id="city" required>
                   </div>
               </div>

               <div class="row py-3">
                   <div class="">
                       <label for="rate"> Rate (from)</label>
                       <input type="number" value='' min="0" max="10" class="col-md-4" id="rate">
                   </div>
               </div>

               <hr/>

               <div class="row py-3">
                   <div class="">
                       <label for="sort"> Sort by</label>
                       <select class="mdb-select md-form">
                           <option value="" disabled selected>...</option>
                           <option value="1">Name</option>
                           <option value="2">Rate</option>
                           <option value="3">Address</option>
                           <option value="4">Price</option>
                         </select>
                   </div>
               </div>
               <hr/>

               <div class="row mx-5">
                   <button type="submit" class="float-right btn btn-primary">Search</button>
               </div>
           </form>
       </div>
   </div>
   `,

});