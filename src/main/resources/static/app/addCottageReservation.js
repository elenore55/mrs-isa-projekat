Vue.component('add-cottage-reservation', {
    data: function () {
        return {
            cottage_id: null,
            start: null, end: null,
            name: "Vikendica"
        }
    },

    template: `
    <div>
        <update-cottage-nav></update-cottage-nav>
        <div class="d-flex justify-content-center">
            <div class="container w-50 m-5 card">
                <div class="mt-4 ms-3 mb-2 card-title">
                    <h5>Reservation for {{ name }}</h5>
                </div>
                <div class="card-body">
                    <div class="row m-3">
                        <div class="col form-floating">
                            <input type="email" id="client-mail-input" class="form-control">
                            <label for="client-mail-input">Client email</label>
                        </div>
                    </div>
                    <div class="d-flex justify-content-around m-3">
                        <div class="mt-1 ms-3 me-4 mb-3">
                            <label for="start-date">Start date</label>
                            <vuejs-datepicker v-model="start" format="dd.MM." id="start-date"></vuejs-datepicker>
                        </div>
                        <div class="mt-1 mb-3 me-3">
                            <label for="end-date">End date</label>                
                            <vuejs-datepicker v-model="end" format="dd.MM." id="end-date"></vuejs-datepicker>
                        </div>
                    </div>
                    <div class="row mt-1 mb-3 me-3">
                        <div class="col text-end">
                            <button type="button" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `
});