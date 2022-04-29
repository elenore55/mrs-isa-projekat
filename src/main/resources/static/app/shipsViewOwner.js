Vue.component("ships-view-owner", {
    data: function() {
        return {
            ships: [],
            sort_by_list: ["Name", "Price", "Length", "Capacity", "Type", "Maximum speed", "Rating", "City", "Country"],
            search_criterion: "",
            all_cities: [],
            all_countries: [],
            cities: [],
            city: "",
            countries: [],
            country: "",
            low_price: null,
            high_price: null,
            sort_by: "",
            direction: "",
            price_error: false,
            owner_id: 1,
            current_id: null
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
                            <a href="/#/updateShip" class="btn btn-primary me-3 mt-3">View</a>
                            <a @click="setCurrentId(s.id)" class="btn btn-danger mt-3">Delete</a>
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
        }
    }
});