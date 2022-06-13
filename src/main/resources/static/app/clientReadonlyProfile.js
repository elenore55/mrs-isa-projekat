Vue.component('client-readonly-profile', {
    data() {
        return {
            client: {}
        }
    },

    mounted() {
        axios.get("api/users/getClientByEmail/" + this.$route.params.email).then(response => {
            this.client = response.data;
        }).catch(error => {
            Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div style="background-color: #ddc8fb; height: 100%">
        <div class="d-flex justify-content-center">
            <div class="card px-3 py-2 m-5 shadow-lg" style="background-color: #fff9e8; border-radius: 15px; width: 40%">
                <div class="card-body m-3">
                    <h2 class="card-title d-flex justify-content-center mb-4 fw-bold">{{ client.name }} {{ client.surname }}</h2>
                    <div>
                        <i class="fa fa-envelope"></i>
                        <label class="h6 fw-bold">Email</label>
                    </div>
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ client.email }}</p>
                    <div>
                        <i class="fa fa-phone"></i>
                        <label class="h6 fw-bold">Phone number</label>
                    </div>
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ client.phoneNumber }}</p>
                    <div>
                        <i class="fa fa-home"></i>
                        <label class="fw-bold h6">Address</label>
                    </div>
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ client.address.street }}, {{ client.address.city }}, {{ client.address.country }}</p>
                    <hr>
                    <h4 class="text-success mb-4">Loyalty program</h4>
                    <div style="font-size: 1.2em">
                        <div class="d-flex justify-content-left">
                            <span><i class="fa fa-medal mx-2"></i></span><p>Status: &nbsp</p> <p class="ms-1 fw-bold">{{ client.category }}</p>
                        </div>
                        <div class="d-flex justify-content-left">
                            <span><i class="fa fa-star mx-2"></i></span><p>Points: &nbsp</p> <p class="ms-1 fw-bold">{{ client.numberOfPoints }}</p>
                        </div>
                        <div class="d-flex justify-content-left">
                            <span><i class="fa fa-ban mx-2"></i></span><p>Penalties: &nbsp</p> <p class="ms-1 fw-bold">{{ client.numberOfPenalties }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,
});