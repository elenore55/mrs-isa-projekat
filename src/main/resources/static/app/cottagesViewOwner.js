Vue.component("cottages-view-owner", {
    data: function () {
        return {
            cottages: []
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
    `,

    methods: {
        deleteCottage() {

        }
    }
});