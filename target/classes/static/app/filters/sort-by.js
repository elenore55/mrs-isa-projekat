Vue.component("sort-by", {
    props: ['sort_by_list'],

    template: `
        <div class="row mt-4 ms-1">
            <!-- Sort by -->
            <div class="col-2 form-floating input-group w-25">
                <select class="form-select" v-model="sort_by" aria-label="Default select example" 
                id="sort-by-select" @change="updateSortBy">
                    <option v-for="s in sort_by_list">{{ s }}</option>
                </select>
                <label for="sort-by-select">Sort by</label>
            </div>
            <div class="col-2 form-floating input-group w-25">
                <select class="form-select" v-model="direction" aria-label="Default select example" 
                id="direction-select" @change="updateDirection">
                    <option selected="selected">Ascending</option>
                    <option>Descending</option>
                </select>
                <label for="direction-select">Direction</label>
            </div>
        </div>
    `,

    data: function() {
        return {
            sort_by: "",
            direction: ""
        }
    },

    methods: {
        updateSortBy() {
            this.$emit('updateSortBy', this.sort_by);
        },

        updateDirection() {
            this.$emit('updateDirection', this.direction);
        }
    }
});