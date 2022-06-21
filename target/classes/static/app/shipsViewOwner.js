Vue.component("ships-view-owner", {
    data: function() {
        return {
            ships: [],
            sort_by_list: ["Name", "Price", "Length", "Capacity", "Type", "Maximum speed", "Rating", "City", "Country"],
            search_criterion: "",
            all_cities: [], all_countries: [], cities: [], countries: [],
            low_price: null, high_price: null, low_len: null, high_len: null, low_cap: null, high_cap: null, low_speed: null, high_speed: null,
            sort_by: "", direction: "",
            price_error: false, length_error: false, capacity_error: false, speed_error: false,
            owner_id: 1, current_id: null,
            default_image: "images/ship_icon.png",
            profilePictures: []
        }
    },

    mounted() {
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
        <div style="background-color: #fff9e8">
            <owners-nav offer="ships"></owners-nav>
            <div class="container">
                <div class="d-flex justify-content-center">
                <div class="collapse bg-light shadow rounded w-50 mt-3" id="confirm-delete">
                    <p class=" d-flex justify-content-center mt-5 mb-3">Are you sure you want to delete the ship?</p>
                    <div class="d-flex justify-content-center">
                        <a v-on:click="deleteShip" class="btn btn-lg btn-outline-secondary m-4" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">Yes</a>
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
                        aria-expanded="false" aria-controls="filter-div" style="background-color: white">Filter</a>
                    </div>
                    <a type="button" class="btn btn-primary shadow" href="/#/addShip/">Add ship</a>
                </div>
                <div id="filter-div" class="collapse bg-light shadow-sm rounded">
                    <div class="container mt-3">
                        <city-filter :cities="cities" :all_cities="all_cities"></city-filter>
                        <country-filter :all_countries="all_countries" :countries="countries"></country-filter>
                        <low-high-filter title="Price" :error="price_error" @updateLowPrice="low_price=$event" @updateHighPrice="high_price=$event" @undoErrorPrice="price_error=false"></low-high-filter>
                        <low-high-filter title="Length" :error="length_error" @updateLowLength="low_len=$event" @updateHighLength="high_len=$event" @undoErrorLength="length_error=false"></low-high-filter>
                        <low-high-filter title="Capacity" :error="capacity_error" @updateLowCapacity="low_cap=$event" @updateHighCapacity="high_cap=$event" @undoErrorCapacity="capacity_error=false"></low-high-filter>
                        <low-high-filter title="Speed" :error="speed_error" @updateLowSpeed="low_speed=$event" @updateHighSpeed="high_speed=$event" @undoErrorSpeed="speed_error=false"></low-high-filter>
                        <sort-by :sort_by_list="sort_by_list" @updateSortBy="sort_by=$event" @updateDirection="direction=$event"></sort-by>
                        <div class="row">
                            <div class="col">
                                <button type="button" class="btn btn-primary float-end mb-3 mt-2" v-on:click="filter">Fetch</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3 v-if="ships.length == 0" class="text-info ms-5 mt-3">No ships to show</h3>
            <div class="container">
                <div v-for="(s, i) in ships">
                    <div class="container card my-5 ms-2 me-5 shadow" :id="cardId(i)" style="border-radius: 10px"
                            v-on:mouseover="cardMouseOver(i)" v-on:mouseleave="cardMouseLeave(i)">
                        <div class="row">
                            <div class="col-3 mt-2">
                                <img :src="profilePictures.at(i)" class="card-img rounded-3 mt-3" width="200" height="200"  alt="ship image">
                                <p class="ms-2 mt-3">{{ s.description }}</p>
                            </div>
                            <div class="col-4 card-body container">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <h3 class="card-title mb-2">{{ s.name }} ({{ s.shipTypeStr }})</h3>
                                        <p class="card-text mt-2 mb-4 h5">{{ s.address.street }}, {{ s.address.city }}, {{ s.address.country }}</p>
                                    </div>
                                    <div class="me-4">
                                        <h2 v-if="s.rate != -1"><span class="badge bg-primary">{{ s.rate }}</span></h2>
                                        <h6 v-if="s.rate != -1">{{ s.reviews.length }} reviews</h6>
                                        <h3 v-if="s.rate == -1">No reviews</h3>
                                    </div>
                                </div>
                                <p class="card-text mb-1">Price: {{ s.price }} EUR</p>
                                <p class="card-text mb-1">Capacity: {{ s.capacity }} people</p>
                                <p class="card-text mb-1">Length: {{ s.length }} m</p>
                                <p class="card-text">Max speed: {{ s.maxSpeed }} km/h</p>
                                <div class="d-flex flex-row mt-3">
                                    <a :href="'/index.html#/shipProfile/' + s.id" class="btn btn-primary me-3 mt-3">View</a>
                                    <button type="button" class="btn btn-danger mt-3" v-on:click="setCurrentId(s.id)">Delete</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,

    methods: {
        reload() {
            axios({
                method: "get",
                url: "api/shipOwner/getShips/" + JSON.parse(localStorage.getItem("jwt")).userId,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.ships = response.data;
                for (const s of this.ships) {
                    if (!s.imagePaths || s.imagePaths.length === 0) {
                        this.profilePictures.push(this.default_image);
                    } else {
                        this.profilePictures.push(s.imagePaths.at(0));
                    }
                }
            }).catch(function (error) {
                if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        setCurrentId(id) {
            this.current_id = id;
            Swal.fire({
                title: 'Are you sure you want to delete the ship?',
                showDenyButton: true,
                confirmButtonText: 'Yes, delete it',
                denyButtonText: `Cancel`,
                icon: 'warning'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.deleteShip();
                }
            });
        },

        deleteShip() {
            axios({
                method: "delete",
                url: "api/ships/deleteShip/" + this.current_id,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                Swal.fire('Success', 'Ship deleted!', 'success');
                this.reload();
            }).catch(function (error) {
                if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                else Swal.fire('Error', 'It is not possible to delete the ship!', 'error');
            });
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

        search() {
            axios({
                method: "get",
                url: "api/shipOwner/getShips/" + JSON.parse(localStorage.getItem("jwt")).userId + "/" + this.search_criterion,
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                this.ships = response.data;
            }).catch(function (error) {
                if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        },

        filter() {
            if (this.areValidPrices && this.areValidCapacities && this.areValidLengths && this.areValidSpeeds) {
                axios.post("api/shipOwner/filterShips/" + this.owner_id, {
                    cities: this.cities,
                    countries: this.countries,
                    lowPrice: this.low_price,
                    highPrice: this.high_price,
                    lowLength: this.low_len,
                    highLength: this.high_len,
                    lowCapacity: this.low_cap,
                    highCapacity: this.high_cap,
                    lowSpeed: this.low_speed,
                    highSpeed: this.high_speed,
                    sortParam: this.sort_by,
                    sortDir: this.direction
                }, {
                    headers: {
                        Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                    }
                }).then(response => {
                    this.ships = response.data;
                }).catch(function (error) {
                    if (error.response.status === 401) location.replace('http://localhost:8000/index.html#/unauthorized/');
                    else Swal.fire('Error', 'Something went wrong!', 'error');
                });
            }
        },
    },

    computed: {
        areValidPrices() {
            if (this.low_price > 0 && this.high_price > 0 && this.low_price > this.high_price) {
                this.price_error = true;
                return false;
            }
            return true;
        },

        areValidLengths() {
            if (this.low_len && this.high_len && this.low_len > this.high_len) {
                this.length_error = true;
                return false;
            }
            return true;
        },

        areValidCapacities() {
            if (this.low_cap && this.high_cap && this.low_cap > this.high_cap) {
                this.capacity_error = true;
                return false;
            }
            return true;
        },

        areValidSpeeds() {
            if (this.low_speed && this.high_speed && this.low_speed > this.high_speed) {
                this.speed_error = true;
                return false;
            }
            return true;
        }
    }
});