Vue.component("cottages-view-owner", {
    data: function () {
        return {
            cottages: [],
            sort_by_list: ["Name", "Price", "Number of rooms", "Rating", "City", "Country"],
            search_criterion: "",
            all_cities: [],
            all_countries: [],
            cities: [],
            countries: [],
            country: "",
            low_price: null,
            high_price: null,
            sort_by: "",
            direction: "",
            price_error: false,
            owner_id: 2,
            current_id: null,
            default_image: "images/cottage_icon.jpg",
            profilePictures: [],
            token: {}
        }
    },

    mounted() {
        $("body").css("background-image", "url('images/set2.png')");
        $("body").css("background-size", "100% 200%");
        this.token = JSON.parse(localStorage.getItem("jwt"));
        this.reload();

        axios.get("api/addresses/getCities").then(response => {
            this.all_cities = response.data;
        }).catch(function (error) {

            Swal.fire('Error', 'Something went wrong!', 'error');
        });

        axios.get("api/addresses/getCountries").then(response => {
            this.all_countries = response.data;
        }).catch(function (error) {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
        <div>
            <owners-nav offer="cottages"></owners-nav>
            <div class="container">
                <div class="d-flex justify-content-center">
                    <div class="collapse bg-light shadow rounded w-50 mt-3" id="confirm-delete">
                        <p class=" d-flex justify-content-center mt-5 mb-3">Are you sure you want to delete the cottage?</p>
                        <div class="d-flex justify-content-center">
                            <a v-on:click="deleteCottage" class="btn btn-lg btn-outline-secondary m-4" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">Yes</a>
                            <a class="btn btn-lg btn-outline-secondary m-4" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">No</a>
                        </div>
                    </div>
                </div>
                <div class="mt-3">
                    <div class="d-flex justify-content-end">
                        <div class="input-group me-1 w-25">
                             <input v-model="search_criterion" type="search" id="search-input" class="form-control" placeholder="Search"/>
                             <button type="button" class="btn btn-primary" v-on:click="search">
                                <i class="fa fa-search"></i>
                             </button>
                        </div>
                        <a type="button" class="btn btn-outline-primary" data-bs-toggle="collapse" href="#filter-div" role="button" 
                        aria-expanded="false" aria-controls="filter-div" style="background-color: white;">Filter</a>
                    </div>
                    <a type="button" class="btn btn-primary shadow" href="/index.html#/addCottage/">Add cottage</a>
                </div>
                <div class="collapse bg-light shadow-sm rounded" id="filter-div">
                    <div class="container mt-3">
                        <city-filter :all_cities="all_cities" :cities="cities"></city-filter>
                        <country-filter :all_countries="all_countries" :countries="countries"></country-filter>
                        <low-high-filter title="Price" :error="price_error" @updateLowPrice="updateLowPrice($event)" @updateHighPrice="updateHighPrice($event)" @undoErrorPrice="undoErrorPrice"></low-high-filter>
                        <sort-by :sort_by_list="sort_by_list" @updateSortBy="sort_by=$event" @updateDirection="direction=$event"></sort-by>
                        <div class="row">
                            <div class="col">
                                <button type="button" class="btn btn-primary float-end mb-3 mt-2" v-on:click="filter">Fetch</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3 v-if="cottages.length == 0" class="text-info ms-5 mt-3">No cottages to show</h3>
            <div class="container">
                <div v-for="(c, i) in cottages">
                    <div class="container card my-5 ms-2 me-5 shadow" :id="cardId(i)" style="border-radius: 10px" 
                            v-on:mouseover="cardMouseOver(i)" v-on:mouseleave="cardMouseLeave(i)">
                        <div class="row">
                            <div class="col-3 mt-2">
                                <img :src="profilePictures.at(i)" class="card-img rounded-3 mt-3" width="200" height="200" alt="cottage image">
                                <p class="ms-2 mt-3">{{ c.description }}</p>
                            </div>
                            <div class="col-4 card-body container">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h3 class="card-title mb-2">{{ c.name }}</h3>
                                        <p class="card-text mt-2 mb-4 h5">{{ c.address.street }}, {{ c.address.city }}, {{ c.address.country }}</p>
                                    </div>
                                    <div class="me-4">
                                        <span v-if="c.rate != -1" class="badge bg-primary my-1">
                                            <div class="d-flex justify-content-start">
                                                <h6 class="d-flex align-items-center"><i class="fa fa-star"></i></h6>
                                                <h3>&nbsp;{{ c.rate.toFixed(1) }}</h3>
                                            </div>
                                        </span>
                                        <h6 v-if="c.rate != -1">{{ c.reviews.length }} reviews</h6>
                                        <h3 v-if="c.rate == -1">No reviews</h3>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-start">
                                    <h1 class="text-success ">{{ c.price }} <i class="fa fa-eur"></i></h1>
                                    <h5 class="d-flex align-items-end">&nbsp; /day</h5>
                                </div>
                                <p class="card-text mb-2">Number of rooms: {{ c.rooms.length }}</p>
                                <p class="card-text">Number of beds: {{ c.numberOfBeds }}</p>
                                <div class="d-flex flex-row mt-3">
                                    <a :href="'/index.html#/cottageProfile/' + c.id" class="btn btn-primary me-3 mt-3">View</a>
                                    <button type="button" class="btn btn-danger mt-3" v-on:click="setCurrentId(c.id)">Delete</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,

    methods: {
        prepareDelete(id) {
            this.setCurrentId(id);
            window.scrollTo(0, 0);
        },

        cardId(i) {
            return "card-div-" + i;
        },

        cardMouseOver(i) {
            $("#card-div-" + i).css('transform', 'scale(1.06)');
        },

        cardMouseLeave(i) {
            $("#card-div-" + i).css('transform', 'scale(1)');
        },

        setCurrentId(id) {
            this.current_id = id;
            Swal.fire({
                title: 'Are you sure you want to delete the cottage?',
                showDenyButton: true,
                confirmButtonText: 'Yes, delete it',
                denyButtonText: `Cancel`,
                icon: 'warning'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.deleteCottage();
                }
            });
        },

        getCurrentCottageId() {
            return this.current_id;
        },

        deleteCottage() {
            axios({
                method: "delete",
                url: "api/cottages/deleteCottage/" + this.current_id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                Swal.fire('Success', 'Cottage deleted!', 'success');
                this.reload();
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'It is not possible to delete the cottage!', 'error');
            });
            this.of = "auto";
        },

        search() {
            axios({
                method: 'get',
                url: "api/cottageOwner/getCottages/" + this.owner_id + "/" + this.search_criterion,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        reload() {
            axios({
                method: 'get',
                url: "api/cottageOwner/getCottages/" + this.owner_id,
                headers: {
                    Authorization: "Bearer " + this.token.accessToken
                }
            }).then(response => {
                this.cottages = response.data;
                for (const c of this.cottages) {
                    if (!c.imagePaths || c.imagePaths.length === 0) {
                        this.profilePictures.push(this.default_image);
                    } else {
                        this.profilePictures.push(c.imagePaths.at(0));
                    }
                }
            }).catch(function (error) {
                if (error.response.status === 401){
                    this.$router.push({path: '/unauthorized'});
                }
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        filter() {
            if (this.areValidPrices) {
                axios({
                    method: "post",
                    url: "api/cottageOwner/filterCottages/" + this.owner_id,
                    data: {
                        cities: this.cities,
                        countries: this.countries,
                        low: this.low_price,
                        high: this.high_price,
                        sortParam: this.sort_by,
                        sortDir: this.direction
                    },
                    headers: {
                        Authorization: "Bearer " + this.token.accessToken
                    }
                }).then(response => {
                    this.cottages = response.data;
                }).catch(function (error) {
                    if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            } else {
                this.price_error = true;
            }
        },

        updateLowPrice(price) {
            this.low_price = price;
        },

        updateHighPrice(price) {
            this.high_price = price;
        },

        undoErrorPrice() {
            this.price_error = false;
        }
    },

    computed: {
        areValidPrices() {
            if (this.low_price && this.high_price) {
                return this.low_price <= this.high_price;
            }
            return true;
        }
    }
});