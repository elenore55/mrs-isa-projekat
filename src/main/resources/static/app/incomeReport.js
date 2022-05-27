Vue.component('income-report', {
    data() {
        return {
            start_date: null,
            end_date: null,
            reports: [],
            disabled: {
                from: new Date()
            },
            ch: null
        }
    },

    mounted() {
        let chart = document.getElementById('bar-plot').getContext('2d');
        this.ch = new Chart(chart, {
            type: 'bar',
            data: {
                labels: [],
                datasets: [{
                    label: 'Income',
                    data: []
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    },

    template: `
    <div>
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-25 d-flex justify-content-evenly my-3">
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="start-date">Start date</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date" :disabled-dates="disabled"></vuejs-datepicker>     
                </div>
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="end-date">End date</label>
                    <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date" :disabled-dates="disabled"></vuejs-datepicker>
                </div>
                <div class="d-flex align-items-end">
                    <button class="btn btn-success px-3 mx-2 h-75 my-1" v-on:click="getReports">Display</button>
                </div>
            </div>
        </div>    
        <div class="container">
            <canvas id="bar-plot"></canvas>
        </div>
    </div>
    `,

    methods: {
        getReports() {
            axios.post("api/users/getIncomeReport/" + this.$route.params.id, {
                start: this.start_date,
                end: this.end_date
            }).then(response => {
                this.reports = response.data;
                this.ch.data.labels = this.getChartLabels();
                this.ch.data.datasets = [{
                   label: 'Income',
                   data: this.getChartData()
                }];
                this.ch.update();
                /*let chart = document.getElementById('bar-plot').getContext('2d');
                this.ch = new Chart(chart, {
                    type: 'bar',
                    data: {
                        labels: this.getChartLabels(),
                        datasets: [{
                            label: 'Income',
                            data: this.getChartData(),
                            /*backgroundColor: [
                                '#ff0000',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                '#ff0000',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1,
                            hoverBorderWidth: 3
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                }); */
            }).catch(error => {
                Swal.fire('Error', 'Something went wrong!', 'error');
            })
        },

        getChartLabels() {
            let labels = [];
            for (const report of this.reports) {
                labels.push(report.offerName);
            }
            return labels;
        },

        getChartData() {
            let chartData = [];
            for (const report of this.reports) {
                chartData.push(report.income);
            }
            return chartData;
        }
    }
});