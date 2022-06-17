Vue.component("client-actions", {
   data: function() {
       return {
           actions: [],
           offerId: "",
           old_price: 0,
       }
   },

   mounted() {
        main_image = $("body").css("background-image", "url('images/set2.png')");
        main_image = $("body").css("background-size", "100% 270%");
        this.reload();
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
        <div class="container mt-5 pt-5">
            <div v-for="(a, i) in actions" class="justify-content-center">
                <div class="card mt-3 ms-3 shadow col-6">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <h5>Start date: {{a.start.substring(0,10)}} </h5>
                                <h5>Duration: {{a.duration}} days </h5>
                            </div>
                            <div class="text-end me-1">
                                <h6 class="text-"> Old price: <s> {{old_price * a.duration}} EUR </s></h6>
                                <h5>New price: </h5> <h3 class="text-success">{{a.price}} EUR</h3>
                                <h5 class="text-secondary">{{a.maxPeople}} people</h5>
                            </div>
                        </div>

                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" style="width:3cm; font-size: 18px;" v-on:click="reserveAction(a, i)" class="btn btn-primary btn-sm">RESERVE</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   `,
    methods: {

        reload() {
            this.offerId = this.$route.params.id;
            axios.get("api/reservations/getOfferActions/" + this.offerId).then(response => {
                this.actions = response.data;
            }).catch(function (error) {
                alert('Greska u get actions');
            });
            axios.get("api/reservations/getOldPrice/" + this.offerId).then(response => {
                this.old_price = response.data;
            }).catch(function (error) {
                alert('Greska u get actions');
            });
           },

       reserveAction(a, i)
       {
         alert("Usla sam u ovo ");
         // treba poslati poruku da je uspjesno rezervisano
         // i onda jos napraviti novu rezervaciju
          axios.post("api/reservations/addNewFastReservation/" + this.offerId + "/5", {
                 id: a.id,
                 start: a.start,
                 duration: a.duration,
                 actionStart: a.actionStart,
                 actionDuration: a.actionDuration,
                 price: a.price,
                 maxPeople: a.maxPeople,
                 startStr: "",
                 actionStartStr: "",
                 }).catch(function (error) {
                     alert('Greska u kreiranju rezervacije!');
          });
          alert("Sve je proslo dobro");
          // sad bih ovdje jos mogla da obavijestim da je proslo dobro i da obrisem ovaj u kodu
          // i - to dugme u kodu da disableujes i pretvoris u reserved
          var buttons = document.getElementsByTagName('button');
          var myBtn = buttons[i+1];     // treba +1 zato sto imamo ovaj toglovani button
          myBtn.textContent = "RESERVED";
          myBtn.disabled = true;
       },



    },



});