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
            owner_id: 1, current_id: null
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
                    <p class=" d-flex justify-content-center mt-5 mb-3">Are you sure you want to delete the ship?</p>
                    <div class="d-flex justify-content-center">
                        <a v-on:click="deleteShip" class="btn btn-lg btn-outline-secondary m-4" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">Yes</a>
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
                <div v-for="(s, i) in ships" class="container card m-3">
                    <div class="row">
                        <div class="col-3 mt-2">
                            <img src="https://picsum.photos/id/80/200/300" class="card-img rounded-3 mt-3" width="200" height="200"  alt="ship image">
                            <p class="ms-2 mt-3">{{ s.description }}</p>
                        </div>
                        <div class="col-4 card-body container">
                            <h3 class="card-title mb-2">{{ s.name }} ({{ s.shipTypeStr }})</h3>
                            <p class="card-text mt-2 mb-4 h5">{{ s.address.street }}, {{ s.address.city }}, {{ s.address.country }}</p>
                            <p class="card-text mb-1">Price: {{ s.price }} EUR</p>
                            <p class="card-text mb-1">Capacity: {{ s.capacity }} people</p>
                            <p class="card-text mb-1">Length: {{ s.length }} m</p>
                            <p class="card-text">Max speed: {{ s.maxSpeed }} km/h</p>
                            <div class="d-flex flex-row mt-3">
                                <a :href="'/#/updateShip/' + s.id" class="btn btn-primary me-3 mt-3">View</a>
                                <a @click="setCurrentId(s.id)" class="btn btn-danger mt-3" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-expanded="false" aria-controls="confirm-delete">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,

    methods: {
        reload() {
            axios.get("api/shipOwner/getShips/" + this.owner_id).then(response => {
                this.ships = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        },

        setCurrentId(id) {
            this.current_id = id;
        },

        deleteShip() {
            axios.delete("api/ships/deleteShip/" + this.current_id).then(response => {
                alert('Ship successfully deleted');
                this.reload();
            }).catch(function (error) {
                alert('It is not possible to delete the ship!');
            });
        },

        search() {
            axios.get("api/shipOwner/getShips/" + this.owner_id + "/" + this.search_criterion).then(response => {
                this.ships = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
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
                }).then(response => {
                    this.ships = response.data;
                }).catch(function (error) {
                    alert('An error occurred!');
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