Vue.component('low-high-filter', {
    props: ['title', 'error'],

    template: `
        <div class="row mt-4 ms-1">
            <p class="col-1 pt-2">{{ title }}</p>
            <div class="col-2 form-floating">
                <input v-model="low" type="number" min="0" class="form-control" id="low-input" 
                oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null" 
                v-on:focus="undoError" @input="updateLow" />
                <label for="low-input">Low</label>
            </div>
            <div class="col-2 form-floating">
                <input v-model="high" type="number" min="0" class="form-control" id="high-input"
                oninput="this.value = !!this.value && Math.abs(this.value) >= 0 ? Math.abs(this.value) : null" 
                v-on:focus="undoError" @input="updateHigh"/>
                <label for="high-input">High</label>
            </div>
            <p v-if="error" class="text-danger">Low must not be greater than high.</p>
        </div>
    `,

    data: function() {
        return {
            low: null,
            high: null
        }
    },

    methods: {
        updateLow() {
            this.$emit('updateLow' + this.title, this.low);
        },

        updateHigh() {
            this.$emit('updateHigh' + this.title, this.high);
        },

        undoError() {
            this.$emit('undoError' + this.title, this.high);
        }
    },

});