Vue.component("city-filter", {
    props: ['cities', 'all_cities'],
    template: `
        <div class="d-flex flex-row m-2">
            <!-- Cities -->
            <div class="form-floating input-group w-25 me-5 pt-3">
                <input v-model="city" class="form-control" list="city-select" id="city-select-input">
                <datalist id="city-select">
                    <option v-for="c in all_cities">{{ c }}</option>
                </datalist>
                <button type="button" class="btn btn-secondary" v-on:click="addCity">Add</button>
                <label class="form-label mt-3" for="city-select-input">City</label>
            </div>
            <div class="d-flex flex-row mt-4">
                <span v-for="(c, i) in cities" class="input-group" style="width: 170px">
                    <span class="p-2"><label class="form-label ms-3">{{ c }}</label></span>
                    <span><button class="btn btn-outline-danger" type="button" v-on:click="cities.splice(i, 1)">X</button></span>
                </span>
            </div>
        </div>
    `,

    data: function () {
        return {
            city: ""
        }
    },

    methods: {
        addCity() {
            if (this.city && !this.cities.includes(this.city)) {
                this.cities.push(this.city);
            }
            this.city = "";
        },
    }
});