Vue.component('cottage-profile', {
    data() {
        return {
            cottage: {}
        }
    },

    mounted() {
        axios.get("api/cottages/getCottage/" + this.$route.params.id).then(response => {
            this.cottage = response.data;
        }).catch(function (error) {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div style="background-color: #f2e488">
        <update-cottage-nav></update-cottage-nav>
        <div class="d-flex justify-content-center">
            <div class="card shadow-lg my-3">
                <div class="container card-body">
                
                    <h2 class="card-title mb-5 ms-5 mt-3">{{ cottage.name }}</h2>

                    <div class="d-flex justify-content-evenly  mb-4">
                        <div class="w-50">
                            <div class="d-flex justify-content-start">
                                <div class="mb-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    <img :src="cottage.imagePaths[0]" style="width: 250px;height: 250px" data-bs-target="#carouselExample" data-bs-slide-to="0">
                                </div>
                                <div class="ms-4">   
                                    <h1 class="text-success ">{{ cottage.price }} <i class="fa fa-eur"></i></h1>
                                    <h2>{{ cottage.rate }}</h2>
                                    <p class="h6"> {{ cottage.description }}</p>
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
                            <a v-if="!cottage.editable" class="btn btn-primary btn-lg" role="link" aria-disabled="true" style="cursor: not-allowed; opacity: 50%">Update</a>
                            <a v-if="cottage.editable" class="btn btn-primary btn-lg" :href="updateLink">Update</a>
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
    </div>
    `,

    computed: {
        updateLink() {
            return "/#/updateCottage/" + this.$route.params.id;
        },

        numberOfBeds() {
            let cnt = 0;
            for (let room of this.cottage.rooms) {
                cnt += room.numberOfBeds;
            }
            return cnt;
        }

    }
});