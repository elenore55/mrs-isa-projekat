Vue.component('cottage-images', {
   data: function() {
       return {
           id: null,
           paths: []
       }
   },

    mounted() {
        this.id = this.$route.params.id;

        axios({
            method: "get",
            url: "api/cottages/getCottageImages/" + this.$route.params.id,
            headers: {
                Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
            }
        }).then(response => {
            this.paths = response.data;
        }).catch(function (error) {
            if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
            else Swal.fire('Error', 'Something went wrong!', 'error');
        });
    },

    template: `
        <div>
            <update-cottage-nav></update-cottage-nav>
            <h3 v-if="paths.length == 0" class="text-info">No images to show</h3>
            <div class="container px-2">
                <div class="row">
                    <div class="col d-flex justify-content-center flex-wrap" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        <div v-for="(img, i) in paths" class="m-2">
                            <img :src="img" class="rounded float-start m-2" width="240" height="240" data-bs-target="#carouselExample" :data-bs-slide-to="i" style="cursor: pointer">
                            <div class="text-end">
                                <button type="button" class="btn btn-sm btn-danger me-3" v-on:click="paths.splice(i, 1)">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col form-group ms-5">
                        <label class="form-label h5">Add image</label> <br />
                        <input type="file" class="form-control-file" id="img" name="img" accept="image/*" @change="addImage($event)" multiple>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col text-end">
                        <button class="btn btn-primary" v-on:click="sendRequest">Save changes</button>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div id="carouselExample" class="carousel slide" data-bs-interval="false">
                                <div class="carousel-indicators">
                                    <div v-for="(img, i) in paths">
                                        <button type="button" data-bs-target="#carouselExample" :data-bs-slide-to="i" class="active" aria-current="true"></button>
                                    </div>
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img class="d-block w-100" :src="paths[0]" style="height: 450px">
                                    </div>
                                    <div class="carousel-item" v-for="(img, i) in paths.slice(1)">
                                        <img class="d-block w-100" :src="img" style="height: 450px">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" data-bs-target="#carouselExample" type="button" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" data-bs-target="#carouselExample" role="button" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   `,

    methods: {
        addImage(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) {
                this.paths.push("images/" + file.name);
            }
        },

        imageHovered() {
            alert('Hover');
        },

        sendRequest() {
            axios.post("api/cottages/updateCottageImages", {
                id: this.id,
                imagePaths: this.paths
            }, {
                headers: {
                    Authorization: "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken
                }
            }).then(function(response) {
                Swal.fire('Success', 'Cottage updated!', 'success');
            }).catch(function (error) {
                if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
                else Swal.fire('Error', 'Something went wrong!', 'error');
            });
        }
    }
});