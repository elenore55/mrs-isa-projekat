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
                    <div class="col-3">
                        <img src="https://picsum.photos/id/81/200/300" class="card-img" width="250" height="250"  alt="cottage image">
                    </div>
                    <div class="col-7">
                        <div class="card-body">
                            <h5 class="card-title">{{ c.name }}</h5>
                            <p class="card-text">{{ c.description }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `
});