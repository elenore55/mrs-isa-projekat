Vue.component("ship-images", {
    data: function () {
        return {
            paths: ["https://picsum.photos/id/23/200/300", "https://picsum.photos/id/217/200/300", "https://picsum.photos/id/2/200/300",
                "https://picsum.photos/id/81/200/300", "https://picsum.photos/id/55/200/300", "https://picsum.photos/id/7/200/300",
                "https://picsum.photos/id/212/200/300", "https://picsum.photos/id/100/200/300"]
        }
    },

    template: `
    <div>
        <update-ship-nav></update-ship-nav>
        <div class="container px-2">
            <div class="row">
                <div class="col d-flex justify-content-center flex-wrap">
                    <div v-for="(img, i) in paths" class="m-2">
                        <img :src="img" class="rounded float-start m-2" width="240" height="240"> 
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
    </div>
    `,

    methods: {
        addImage(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) {
                this.paths.push(file.name);
            }
        },

        sendRequest() {
            axios.post("api/ships/updateShipImages", {
                id: 1,
                imagePaths: this.paths
            }).then(function(response) {
                alert('Ship successfully updated!');
            }).catch(function (error) {
                alert('An error occurred!');
            });
        }
    }
});