Vue.component('owners-profile', {
    data() {
        return {
            id: null,
            owner: {},
            reason: ""
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
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ owner.email }}</p>
                    <div>
                        <i class="fa fa-phone"></i>
                        <label class="h6 fw-bold">Phone number</label>
                    </div>
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ owner.phoneNumber }}</p>
                    <div>
                        <i class="fa fa-home"></i>
                        <label class="fw-bold h6">Address</label>
                    </div>
                    <p class="ms-1 mb-4" style="font-size: 1.2em">{{ owner.address.street }}, {{ owner.address.city }}, {{ owner.address.country }}</p>
                    <div style="font-size: 1.2em">
                        <label class="mt-2 ms-1 fw-bold h6">{{ owner.category }}</label> <label>user</label> <br/>
                        <label class="ms-1 fw-bold h6">{{ owner.numberOfPoints }}</label> <label>points</label>
                    </div>
                    <div class="mt-5 d-flex justify-content-evenly">
                        <a class="btn btn-success me-1" href="javascript:void(0)" @click="$router.push({path: '/updateOwnersProfile/' + $route.params.id})" style="width: 50%">Edit</a>
                        <a @click="window.scrollTo(0, document.body.scrollHeight);" class="btn btn-danger ms-1" data-bs-toggle="collapse" href="#confirm-delete" 
                        role="button" aria-expanded="false" aria-controls="confirm-delete" style="width: 50%">Delete</a>
                    </div>
                    <div class="collapse shadow rounded mt-3 form-floating" id="confirm-delete">
                        <textarea v-model="reason" class="form-control" id="reason-textarea" style="height: 150px"></textarea>
                        <label for="reason-textarea">Reason for deletion</label>
                        <div class="d-flex justify-content-evenly">
                            <a v-on:click="deleteAccount" class="btn btn-outline-secondary my-3" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">Confirm</a>
                            <a class="btn btn-outline-secondary my-3" data-bs-toggle="collapse" href="#confirm-delete" role="button" aria-controls="confirm-delete">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,

    methods: {
        deleteAccount() {
            axios.post('api/deletionRequests/deleteProfile', {
               id: this.$route.params.id,
               reason: this.reason
            }).then(response => {
                alert('Request for account deletion sent!');
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    }
});