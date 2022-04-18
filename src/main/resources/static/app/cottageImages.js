Vue.component('cottage-images', {
   data: function() {
       return {
           paths: ["https://picsum.photos/200", "https://picsum.photos/200", "https://picsum.photos/200",
           "https://picsum.photos/200", "https://picsum.photos/200", "https://picsum.photos/200"]
       }
   },

    template: `
        <div>
            <update-cottage-nav></update-cottage-nav>
            <div class="container px-5">
                <span v-for="(img, i) in paths">
                    <img :src="img" class="rounded float-start m-2" width="240">       
                </span>
            </div>
        </div>
   `
});