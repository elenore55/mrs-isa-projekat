Vue.component("client-actions", {
   data: function() {
       return {
           actions: [],
           offerId: "",
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
                <div class="justify-content-center">

                    <div class="card mt-3 ms-3 shadow col-6">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <h5>Start date:  5.5.2022 </h5>
                                <h5>Duration: 7 days </h5>
                            </div>
                            <div class="text-end me-1">
                                <h6 class="text-"> Old price: <s> 500 EUR </s></h6>
                                <h5>New price: </h5> <h3 class="text-success">300 EUR</h3>
                                <h5 class="text-secondary">3 people</h5>
                            </div>
                        </div>

                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" style="width:3cm; font-size: 18px;" v-on:click="deleteAction(i)" class="btn btn-primary btn-sm">RESERVE</button>
                        </div>
                    </div>
                </div>

                <div class="justify-content-center">
                    <div class="card mt-3 ms-3 shadow col-6">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <h5>Start date:  7.6.2022 </h5>
                                <h5>Duration: 2 days </h5>
                            </div>
                            <div class="text-end me-1">
                                <h6 class="text-"> Old price: <s> 40 EUR </s></h6>
                                <h5>New price: </h5> <h3 class="text-success">10 EUR</h3>
                                <h5 class="text-secondary">1 people</h5>
                            </div>
                        </div>

                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" style="width:3cm; font-size: 18px;" v-on:click="deleteAction(i)" class="btn btn-primary btn-sm">RESERVE</button>
                        </div>
                    </div>
                </div>

                <div class="justify-content-center">
                    <div class="card mt-3 ms-3 shadow col-6">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <h5>Start date: 11.8.2022 </h5>
                                <h5>Duration: 4 days </h5>
                            </div>
                            <div class="text-end me-1">
                                <h6 class="text-"> Old price: <s> 150 EUR </s></h6>
                                <h5>New price: </h5> <h3 class="text-success">100 EUR</h3>
                                <h5 class="text-secondary">2 people</h5>
                            </div>
                        </div>

                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" style="width:3cm; font-size: 18px;" v-on:click="deleteAction(i)" class="btn btn-primary btn-sm">RESERVE</button>
                        </div>
                    </div>
                </div>

                <div class="justify-content-center">
                    <div class="card mt-3 ms-3 shadow col-6">
                    <div class="card-body ms-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <h5>Start date: 8.8.2022 </h5>
                                <h5>Duration: 8 days </h5>
                            </div>
                            <div class="text-end me-1">
                                <h6 class="text-"> Old price: <s> 500 EUR </s></h6>
                                <h5>New price: </h5> <h3 class="text-success">400 EUR</h3>
                                <h5 class="text-secondary">3 people</h5>
                            </div>
                        </div>

                        <div class="text-end mb-1 me-1 mt-1">
                            <button type="button" style="width:3cm; font-size: 18px;" v-on:click="deleteAction(i)" class="btn btn-primary btn-sm">RESERVE</button>
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
           },



    },



});