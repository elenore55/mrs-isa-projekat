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
    <div style="background-color: #ddc8fb; height: 100%">
        <div class="d-flex justify-content-center">
            <div class="card px-3 py-2 m-5 shadow-lg" style="background-color: #fff9e8; border-radius: 15px; width: 33%">
                <div class="card-body m-3">
                    <h2 class="card-title d-flex justify-content-center mb-4 fw-bold">{{ owner.name }} {{ owner.surname }}</h2>
                    <div>
                        <i class="fa fa-envelope"></i>
                        <label class="h6 fw-bold">Email</label>
                    </div>
                    <p class="h5 ms-1 mb-4">{{ owner.email }}</p>
                    <div>
                        <i class="fa fa-phone"></i>
                        <label class="h6 fw-bold">Phone number</label>
                    </div>
                    <p class="ms-1 h5 mb-4">{{ owner.phoneNumber }}</p>
                    <div>
                        <i class="fa fa-home"></i>
                        <label class="fw-bold h6">Address</label>
                    </div>
                    <p class="ms-1 h5 mb-4">{{ owner.address.street }}, {{ owner.address.city }}, {{ owner.address.country }}</p>
                    <div style="font-size: 1.2em">
                        <label class="mt-2 ms-1 fw-bold h6">{{ owner.category }}</label> <label>user</label> <br/>
                        <label class="ms-1 fw-bold h6">{{ owner.numberOfPoints }}</label> <label>points</label>
                    </div>
                    <div class="mt-5 d-flex justify-content-evenly">
                        <a class="btn btn-success me-1" href="#" style="width: 50%">Edit</a>
                        <a class="btn btn-danger ms-1" href="#" style="width: 50%">Delete</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `
});