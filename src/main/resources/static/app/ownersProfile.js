Vue.component('owners-profile', {
    data() {
        return {
            id: null,
            owner: {}
        }
    },

    mounted() {
        this.id = this.$route.params.id;
        axios.get("api/users/getOwner/" + this.$route.params.id).then(response => {
            this.owner = response.data;
        }).catch(function (error) {
            alert('An error occurred!');
        });
    },

    template: `
    <div>Owner</div>
    `
});