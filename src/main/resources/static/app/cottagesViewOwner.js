Vue.component("cottages-view-owner", {
    data: function () {
        return {
            cottages: [],
            sort_by_list: ["Name", "Price", "Number of rooms", "Rating"],
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
        }
    },

    mounted() {
        this.reload();

        axios.get("api/addresses/getCities").then(response => {
            this.all_cities = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });

        axios.get("api/addresses/getCountries").then(response => {
            this.all_countries = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
        <div>
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
                <div class="d-flex justify-content-end">
                    <div class="input-group me-1 w-25">
                         <input v-model="search_criterion" type="search" id="search-input" class="form-control" placeholder="Search"/>
                         <button type="button" class="btn btn-primary" v-on:click="search">
                            <i class="fas fa-search"></i>
                         </button>
                    </div>
                    <a type="button" class="btn btn-outline-primary" data-bs-toggle="collapse" href="#filter-div" role="button" aria-expanded="false" aria-controls="filter-div">Filter</a>
                </div>
                <div class="collapse bg-light shadow-sm rounded" id="filter-div">
                    <div class="container mt-3">
                        <city-filter :all_cities="all_cities" :cities="cities"></city-filter>
                        <country-filter :all_countries="all_countries" :countries="countries"></country-filter>
                        <div class="row mt-4 ms-1">
                            <!-- Price -->
                            <p class="col-1 pt-2">Price</p>
                            <div class="col-2 form-floating">
                                <input v-model="low_price" type="number" min="0" class="form-control" id="low-price-input" 
                                    oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null" 
                                    v-on:focus="price_error = false" />
                                <label for="low-price-input">Low</label>
                            </div>
                            <div class="col-2 form-floating">
                                <input v-model="high_price" type="number" min="0" class="form-control" id="high-price-input"
                                oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null" 
                                v-on:focus="price_error = false" />
                                <label for="high-price-input">High</label>
                            </div>
                            <p v-if="price_error" class="text-danger">Low must not be greater than high.</p>
                        </div>
                        <div class="row mt-4 ms-1">
                            <!-- Sort by -->
                            <div class="col-2 form-floating input-group w-25">
                                <select class="form-select" v-model="sort_by" aria-label="Default select example" id="sort-by-select">
                                    <option v-for="s in sort_by_list">{{ s }}</option>
                                </select>
                                <label for="sort-by-select">Sort by</label>
                            </div>
                            <div class="col-2 form-floating input-group w-25">
                                <select class="form-select" v-model="direction" aria-label="Default select example" id="direction-select">
                                    <option selected="selected">Ascending</option>
                                    <option>Descending</option>
                                </select>
                                <label for="direction-select">Direction</label>
                            </div>
                        </div>
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
                <div v-for="(c, i) in cottages" class="container card m-3">
                    <div class="row">
                        <div class="col-3 mt-2">
                            <img src="https://picsum.photos/id/81/200/300" class="card-img rounded-3 mt-3" width="200" height="200"  alt="cottage image">
                            <p class="ms-2 mt-3">{{ c.description }}</p>
                        </div>
                        <div class="col-4 card-body container">
                            <h3 class="card-title mb-2">{{ c.name }}</h3>
                            <p class="card-text mt-2 mb-4 h5">{{ c.address.street }}, {{ c.address.city }}, {{ c.address.country }}</p>
                            <p class="card-text mb-2">Price: {{ c.price }} EUR</p>
                            <p class="card-text mb-2">Number of rooms: {{ c.rooms.length }}</p>
                            <p class="card-text">Number of beds: {{ c.numberOfBeds }}</p>
                            <div class="d-flex flex-row mt-3">
                                <a :href="'/#/updateCottage/' + c.id" class="btn btn-primary me-3 mt-3">View</a>
                                <a @click="prepareDelete(c.id)" class="btn btn-danger mt-3" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-expanded="false" aria-controls="confirm-delete">Delete</a>
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

        setCurrentId(id) {
            this.current_id = id;
        },

        deleteCottage() {
            axios.delete("api/cottages/deleteCottage/" + this.current_id).then(response => {
                alert('Cottage successfully deleted');
                this.reload();
            }).catch(function (error) {
                alert('It is not possible to delete the cottage!');
            });
            this.of = "auto";
        },

        search() {
            axios.get("api/cottageOwner/getCottages/" + this.owner_id + "/" + this.search_criterion).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        },

        reload() {
            axios.get("api/cottageOwner/getCottages/" + this.owner_id).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        },

        filter() {
            if (this.areValidPrices) {
                axios.post("api/cottageOwner/filterCottages/" + this.owner_id, {
                    cities: this.cities,
                    countries: this.countries,
                    low: this.low_price,
                    high: this.high_price,
                    sortParam: this.sort_by,
                    sortDir: this.direction
                }).then(response => {
                    this.cottages = response.data;
                }).catch(function (error) {
                    alert('An error occurred!');
                });
            } else {
                this.price_error = true;
            }
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