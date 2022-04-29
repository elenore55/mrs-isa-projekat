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
    <div>SHIPS VIEW</div>
    `,

    methods: {
        reload() {
            axios.get("api/shipOwner/getShips/" + this.owner_id).then(response => {
                this.cottages = response.data;
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    }
});