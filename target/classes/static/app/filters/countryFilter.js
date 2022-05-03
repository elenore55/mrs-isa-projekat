Vue.component("country-filter", {
    props: ['countries', 'all_countries'],

    template: `
        <div class="d-flex flex-row m-2">
            <!-- Countries -->
            <div class="form-floating input-group w-25 me-5">
                <input v-model="country" class="form-control" list="country-select" id="country-select-input">
                <datalist id="country-select">
                    <option v-for="c in all_countries">{{ c }}</option>
                </datalist>
                <button type="button" class="btn btn-secondary" v-on:click="addCountry">Add</button>
                <label for="country-select-input">Country</label>
            </div>
            <div class="d-flex flex-row mt-1">
                <span v-for="(c, i) in countries" class="input-group" style="width: 170px">
                    <span class="p-2"><label class="form-label ms-3">{{ c }}</label></span>
                    <span><button class="btn btn-outline-danger" type="button" v-on:click="countries.splice(i, 1)">X</button></span>
                </span>
            </div>
        </div>
    `,

    data: function () {
        return {
            country: ""
        }
    },

    methods: {
        addCountry() {
            if (this.country && !this.countries.includes(this.country)) {
                this.countries.push(this.country);
            }
            this.country = "";
        }
    }
});