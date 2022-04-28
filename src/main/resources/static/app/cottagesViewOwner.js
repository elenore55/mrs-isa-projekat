Vue.component("cottages-view-owner", {
    data: function () {
        return {
            cottages: [],
            sort_by_list: ["Name", "Price", "Number of rooms", "Rating"],
            search_criterion: "",
            all_cities: ["Novi Sad", "Beograd", "Banja Luka"],
            all_countries: ["Srbija", "BiH"],
            cities: [],
            city: "",
            countries: [],
            country: "",
            low_price: null,
            high_price: null,
            sort_by: "",
            direction: ""
        }
    },

    mounted() {
        axios.get("api/cottageOwner/getCottages/2").then(response => {
            this.cottages = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
        <div>
            <div class="container">
                <div class="d-flex justify-content-end">
                    <div class="input-group me-1 w-25">
                         <input v-model="search_criterion" type="search" id="search-input" class="form-control" placeholder="Search"/>
                         <button type="button" class="btn btn-primary">
                            <i class="fas fa-search"></i>
                         </button>
                    </div>
                    <a type="button" class="btn btn-outline-primary" data-bs-toggle="collapse" href="#filter-div" role="button" aria-expanded="false" aria-controls="filter-div">Filter</a>
                </div>
                <div class="collapse bg-light shadow-sm rounded" id="filter-div">
                    <div class="container mt-3">
                        <div class="d-flex flex-row m-2">
                            <!-- Cities -->
                            <div class="form-floating input-group w-25 me-5 pt-3">
                                <select class="form-select" v-model="city" aria-label="Default select example" id="city-select">
                                    <option v-for="c in all_cities">{{ c }}</option>
                                </select>
                                <button type="button" class="btn btn-secondary" v-on:click="addCity">Add</button>
                                <label class="form-label mt-3" for="city-select">City</label>
                            </div>
                            <div class="d-flex flex-row mt-4">
                                <span v-for="(c, i) in cities" class="input-group" style="width: 170px">
                                    <span class="p-2"><label class="form-label ms-3">{{ c }}</label></span>
                                    <span><button class="btn btn-outline-danger" type="button" v-on:click="cities.splice(i, 1)">X</button></span>
                                </span>
                            </div>
                        </div>
                        <div class="d-flex flex-row m-2">
                            <!-- Countries -->
                            <div class="form-floating input-group w-25 me-5">
                                <select class="form-select" v-model="country" aria-label="Default select example" id="country-select">
                                    <option v-for="c in all_countries">{{ c }}</option>
                                </select>
                                <button type="button" class="btn btn-secondary" v-on:click="addCountry">Add</button>
                                <label for="country-select">Country</label>
                            </div>
                            <div class="d-flex flex-row mt-1">
                                <span v-for="(c, i) in countries" class="input-group" style="width: 170px">
                                    <span class="p-2"><label class="form-label ms-3">{{ c }}</label></span>
                                    <span><button class="btn btn-outline-danger" type="button" v-on:click="countries.splice(i, 1)">X</button></span>
                                </span>
                            </div>
                        </div>
                        <div class="row mt-4 ms-1">
                            <!-- Price -->
                            <p class="col-1 pt-2">Price</p>
                            <div class="col-2 form-floating">
                                <input v-model="low_price" type="number" min="0" class="form-control" id="low-price-input"/>
                                <label for="low-price-input">Low</label>
                            </div>
                            <div class="col-2 form-floating">
                                <input v-model="high_price" type="number" min="0" class="form-control" id="high-price-input"/>
                                <label for="high-price-input">High</label>
                            </div>
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
                                    <option selected>Ascending</option>
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
            <div class="container">
                <div v-for="(c, i) in cottages" class="container card m-3">
                    <div class="row">
                        <div class="col-3 mb-2">
                            <img src="https://picsum.photos/id/81/200/300" class="card-img rounded-3 mt-3" width="200" height="200"  alt="cottage image">
                        </div>
                        <div class="col-4 card-body container">
                            <h5 class="card-title">{{ c.name }}</h5>
                            <p class="card-text mt-3">{{ c.address.street }}, {{ c.address.city }}, {{ c.address.country }}</p>
                            <p class="card-text">Price: {{ c.price }} EUR</p>
                            <div class="d-flex flex-row mt-3">
                                <a href="/#/updateCottage" class="btn btn-primary me-3 mt-3">View</a>
                                <button v-on:click="deleteCottage" class="btn btn-danger mt-3">Delete</button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <p class="m-3">{{ c.description }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,

    methods: {
        deleteCottage() {

        },

        search() {

        },

        filter() {

        },

        addCity() {
            if (this.city && !this.cities.includes(this.city)) {
                this.cities.push(this.city);
            }
            this.city = "";
        },

        addCountry() {
            if (this.country && !this.countries.includes(this.country)) {
                this.countries.push(this.country);
            }
            this.country = "";
        }
    }
});