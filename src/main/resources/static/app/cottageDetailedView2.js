Vue.component('cottage-detailed-view2', {
    data() {
        return {
            cottage: {},
            my_modal: null,
            clientEmail: "",
            token: {},
            offerId: 0,
            clientEmail: "",
            fromDate: "",
            toDate: "",
            id: 0,
        }
    },

    mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.id = this.token.userId;

        this.offerId = this.$route.params.id;
        this.fromDate = this.$route.params.fromDate;
        this.toDate = this.$route.params.toDate;

        axios({
            method: "get",
            url: "api/cottages/getCottage/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.cottage = response.data;
        }).catch(function (error) {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
        this.my_modal =  new bootstrap.Modal(document.getElementById("reviewsModal"), {});
    },

    template: `
    <div style="background-color: #f2e488">
        <client-navbar></client-navbar>
        <div class="d-flex justify-content-center mt-5">
            <div class="card shadow-lg my-3">
                <div class="container card-body">
                    <h2 class="card-title mb-5 ms-5 mt-3">{{ cottage.name }}</h2>
                    <div class="d-flex justify-content-evenly  mb-4">
                        <div class="w-50">
                            <div class="d-flex justify-content-start">
                                <div class="mb-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    <img :src="cottage.imagePaths[0]" style="width: 250px;height: 250px; cursor: pointer" data-bs-target="#carouselExample" data-bs-slide-to="0">
                                </div>
                                <div class="ms-4">
                                    <h1 class="text-success ">{{ cottage.price }} <i class="fa fa-eur"></i></h1>
                                    <p class="h6 my-2"> {{ cottage.description }}</p>
                                    <h2 v-if="cottage.rate != -1"><span class="badge bg-primary">{{ cottage.rate }}</span></h2>
                                    <u><a data-bs-toggle="modal" href="javascript:void(0)" @click="displayReviews" style="cursor: pointer; color: #0a53be"><h6 v-if="cottage.rate != -1">{{ cottage.reviews.length }} reviews</h6></a></u>
                                    <h3 v-if="cottage.rate == -1">No reviews</h3>
                                </div>
                            </div>
                            <div class="w-100 mt-1">
                                <p>{{ cottage.additionalInfo }}</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <address-map my_style="width:370px;height:330px" :street_init="cottage.address.street" :city_init="cottage.address.city" :country_init="cottage.address.country"></address-map>
                        </div>
                    </div>

                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-left">
                            <div class="mx-5 ps-3">
                                <!-- Rules -->
                                <h4 class="mb-3"><i class="fa fa-check"></i> Rules</h4>
                                <p v-for="(r, i) in cottage.rules" style="font-size: 1.1em">{{ r }}</p>
                            </div>
                            <div class="ms-5">
                                <!-- Rooms -->
                                <h4 class="mb-2"><i class="fa fa-bed"></i> Accommodation</h4>
                                <p class="h5">{{ cottage.rooms.length }} rooms</p>
                                <p class="h5">{{ numberOfBeds }} beds</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end align-items-end me-4 mb-2">
                            <button class="btn btn-primary btn-lg m-1" v-on:click="subscribe">Subscribe</button>
                            <button class="btn btn-primary btn-lg m-1" v-on:click="reserve">Reserve</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id="carouselExample" class="carousel slide" data-bs-interval="false">
                            <div class="carousel-indicators">
                                <div v-for="(img, i) in cottage.imagePaths">
                                    <button type="button" data-bs-target="#carouselExample" :data-bs-slide-to="i" class="active" aria-current="true"></button>
                                </div>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" :src="cottage.imagePaths[0]" style="height: 450px">
                                </div>
                                <div class="carousel-item" v-for="(img, i) in cottage.imagePaths.slice(1)">
                                    <img class="d-block w-100" :src="img" style="height: 450px">
                                </div>
                            </div>
                            <button class="carousel-control-prev" data-bs-target="#carouselExample" type="button" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" data-bs-target="#carouselExample" role="button" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="reviewsModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div v-for="(r, i) in cottage.reviews">
                            <div class="d-flex justify-content-start my-3">
                                <a href="javascript:void(0)" @click="displayClientProfile(r.client.email)" style="text-decoration: none; color: #002a80; cursor: pointer">
                                    <h3><i class="fa fa-user mx-3"></i>{{ r.client.name }} {{ r.client.surname }}</h3>
                                </a>
                            </div>
                            <div class="d-flex justify-content-start ms-5 my-2">
                                <h4 class="mt-2 me-2"><i :class="getEmoji(r.rating)" style="color: #fd7e14"></i></h4>
                                <h2><span class="badge bg-primary ms-3">{{ r.rating }}</span></h2> <h5 class="mt-3">&nbsp; / 10</h5>
                            </div>
                            <div class="ms-5 my-2">{{ r.comment }}</div>
                            <hr>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        getEmoji(rating) {
            if (rating >= 8.5) return "fa fa-grin-beam";
            if (rating >= 6.) return "fa fa-smile";
            if (rating >= 4.5) return "fa fa-meh";
            return "fa fa-frown";
        },

        displayReviews() {
            this.my_modal =  new bootstrap.Modal(document.getElementById("reviewsModal"), {});
            this.my_modal.show();
        },

        displayClientProfile(email) {
            this.my_modal.hide();
            this.$router.push({path: '/clientReadonlyProfile/' + email});
        },

        reserve() {
            this.clientEmail = "";
            this.fromDate = String(this.fromDate);
            this.toDate = String(this.toDate);
            /*axios.post("api/reservations/addReservationStringDate", {
                 id: 6,
                 clientEmail: this.clientEmail,
                 offerId: this.id,
                 fromDate: this.fromDate,
                 toDate: this.toDate,
                 }).then(function(response) {
                     $("#confirm-cancel").show(200);
                     }).catch(function (error) {
                         alert('Greska u cottage detailed');
                     });*/
            axios({
               method: 'post',
               url: "api/reservations/addReservationStringDate/", data: {
                    id: this.id,
                    clientEmail: this.clientEmail,
                    offerId: this.offerId,
                    fromDate: this.fromDate,
                    toDate: this.toDate
                },
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               Swal.fire('Success', 'Reservation added! We have sent You an email', 'success');
           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
        },

        subscribe() {
            axios({
               method: 'post',
               url: "api/reservations/subscribe/" + this.id + "/" + this.offerId,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               Swal.fire('Success', 'You have succesfully subscribed to this entity', 'success');
           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
        },

    },

    computed: {
        /*updateLink() {
            return "/index.html#/otherInformationCottage/" + this.$route.params.id;
        },*/


        numberOfBeds() {
            let cnt = 0;
            for (let room of this.cottage.rooms) {
                cnt += room.numberOfBeds;
            }
            return cnt;
        }

    }
});