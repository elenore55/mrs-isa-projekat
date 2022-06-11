Vue.component('ship-profile', {
    data() {
        return {
            ship: {}
        }
    },

    mounted() {
        axios.get("api/ships/getShip/" + this.$route.params.id).then(response => {
            this.ship = response.data;
        }).catch(function (error) {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div style="background-color: #f2e488">
        <update-ship-nav></update-ship-nav>
        <div class="d-flex justify-content-center">
            <div class="card shadow-lg my-3">
                <div class="container card-body">
                
                    <h2 class="card-title mb-5 ms-5 mt-3">{{ ship.name }}</h2>

                    <div class="d-flex justify-content-evenly  mb-4">
                        <div class="w-50">
                            <div class="d-flex justify-content-start">
                                <div class="mb-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                    <img :src="ship.imagePaths[0]" style="width: 250px;height: 250px" data-bs-target="#carouselExample" data-bs-slide-to="0">
                                </div>
                                <div class="ms-4">   
                                    <h1 class="text-success ">{{ ship.price }} <i class="fa fa-eur"></i></h1>
                                    <p class="h6 my-2"> {{ ship.description }}</p>
                                    <h2 v-if="ship.rate != -1"><span class="badge bg-primary">{{ ship.rate }}</span></h2>
                                    <h6 v-if="ship.rate != -1">{{ ship.reviews.length }} reviews</h6>
                                    <h3 v-if="ship.rate == -1">No reviews</h3>
                                    <div class="mt-4">
                                        <p class="h6">Length: {{ ship.length }}m</p>
                                        <p class="h6">Capacity: {{ ship.capacity }} people</p>
                                        <p class="h6">Maximum speed: {{ ship.maxSpeed }}km/h</p>
                                    </div>
                                </div>
                            </div>
                            <div class="w-100 mt-1">
                                <p>{{ ship.additionalInfo }}</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <address-map my_style="width:370px;height:330px" :street_init="ship.address.street" :city_init="ship.address.city" :country_init="ship.address.country"></address-map>
                        </div>
                    </div>
                    
                    <div class="d-flex justify-content-between mb-4">
                        <div class="d-flex justify-content-left">
                            <div class="ms-5 ps-3">
                                <h4 class="mb-3"><i class="fa fa-check"></i> Rules</h4>
                                <p class="mb-1" v-for="(r, i) in ship.rules" style="font-size: 1.1em">{{ r }}</p>
                            </div>
                            <div class="ms-5">
                                <h4 class="mb-2"><i class="fa fa-compass"></i> Navigation equipment</h4>
                                <p class="ms-5 mb-1" v-for="(n, i) in ship.navigationEquipmentList">{{ n.name }}</p>
                            </div>
                            <div class="ms-5">
                                <h4 class="mb-2"><i class="fa fa-fish"></i> Fishing equipment</h4>
                                <p class="ms-5 mb-1" v-for="(f, i) in ship.fishingEquipmentList">{{ f.name }}</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end align-items-end me-4 mb-2">
                            <a v-if="!ship.editable" class="btn btn-primary btn-lg" role="link" aria-disabled="true" style="cursor: not-allowed; opacity: 50%">Update</a>
                            <a v-if="ship.editable" class="btn btn-primary btn-lg" :href="updateLink">Update</a>
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
                                <div v-for="(img, i) in ship.imagePaths">
                                    <button type="button" data-bs-target="#carouselExample" :data-bs-slide-to="i" class="active" aria-current="true"></button>
                                </div>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" :src="ship.imagePaths[0]" style="height: 450px">
                                </div>
                                <div class="carousel-item" v-for="(img, i) in ship.imagePaths.slice(1)">
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
            return "/#/updateShip/" + this.$route.params.id;
        }
    }
    
});