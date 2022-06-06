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
                                <div class="mb-2">
                                    <img :src="cottage.imagePaths[0]" style="width: 250px;height: 250px">
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
                            <a class="btn btn-primary btn-lg" :href="updateLink">Update</a>
                        </div>
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