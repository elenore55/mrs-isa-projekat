Vue.component("delete-profile", {
data: function() {
        return {
            user: {

            }
        }
   },

   template: `
   <div class="w-100">
   <client-navbar></client-navbar>
     <div class = "m-3 col-4">
            <form action="" method="post">
              <h4> Enter the reasons for deleting Your profile</h4>
              <textarea class = "m-4" name="textarea" rows="6" cols="50" required></textarea>
              <br>
              <button type="submit" class="btn btn-primary float-end mx-5">Submit</button>
            </form>
          </div>
      </div>
   `,

});