Vue.component('owners-profile', {
    data() {
        return {
            owner: {},
            reason: ""
        }
    },

    mounted() {
        $("body").css("background-image", "url('images/gradient_purple.jpg')");
        $("body").css("background-size", "100% 200%");
        axios({
            method: "get",
            url: "api/users/getOwner/" + JSON.parse(localStorage.getItem("jwt")).userId,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.owner = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
    <div>
        <owners-nav></owners-nav>
        <div class="d-flex justify-content-center">
            <div class="card px-3 py-2 m-5 shadow-lg" style="background-color: #fff9e8; border-radius: 15px; width: 40%">
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
                    <div class="d-flex justify-content-start ms-1 mb-4">
                        <i class="fa fa-key mt-1 me-1"></i>
                        <a href="javascript:void(0)" @click="$router.push({path: '/changePwOwner'})">Change password</a>
                    </div>
                    <hr>
                    <h4 class="text-success mb-5">Loyalty program</h4>
                    <div style="font-size: 1.2em">
                        <div class="d-flex justify-content-left">
                            <span><i class="fa fa-medal mx-2"></i></span><p>Status: &nbsp</p> <p class="ms-1 fw-bold">{{ owner.category }}</p>
                        </div>
                        <div class="d-flex justify-content-left">
                            <span><i class="fa fa-star mx-2"></i></span><p>Points: &nbsp</p> <p class="ms-1 fw-bold">{{ owner.numberOfPoints }}</p>
                        </div>
                    </div>

                    <div class="mt-5 d-flex justify-content-evenly">
                        <a class="btn btn-success me-1" href="javascript:void(0)" @click="$router.push({path: '/updateOwnersProfile'})" style="width: 30%">Edit</a>
                        <a data-bs-target="#confirm-delete" class="btn btn-danger ms-1" data-bs-toggle="collapse" href="#confirm-delete" 
                        role="button" aria-expanded="false" aria-controls="confirm-delete" style="width: 30%">Delete</a>
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
               id: JSON.parse(localStorage.getItem("jwt")).userId,
               reason: this.reason
            }, {
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(response => {
                Swal.fire('Success', 'Request for account deletion sent!', 'success');
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else
                    Swal.fire({
                        icon: 'info',
                        title: 'Deletion request already sent'
                    });
            });
        }
    }

});