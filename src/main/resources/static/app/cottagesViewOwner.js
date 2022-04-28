Vue.component("cottages-view-owner", {
    data: function () {
        return {
            cottages: []
        }
    },

    mounted() {
        axios.get("api/cottageOwner/getCottages/2").then(response => {
            this.cottages = response.data;
            alert(this.cottages);
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
        <div>VIEW</div>
    `
});