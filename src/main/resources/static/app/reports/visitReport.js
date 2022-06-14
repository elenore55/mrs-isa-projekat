Vue.component('visit-report', {
    data() {
        return {
            start_date: null,
            end_date: null,
            reports: [],
            offer_type: "",
            disabled: {
                from: new Date()
            },
            ch: null,
            colors: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'],
            border_colors: ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
            period_select: 'Monthly'
        }
    },

    mounted() {
        axios.get("api/users/getOfferType/" + this.$route.params.id).then(response => {
            this.offer_type = response.data;
        }).catch(error => {
            Swal.fire('Error', 'Owner not found!', 'error');
        });
        let chart = document.getElementById('bar-plot').getContext('2d');
        this.ch = new Chart(chart, {
            type: 'bar',
            data: {
                labels: [],
                datasets: [{
                    label: 'Visits',
                    data: []
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                title: {
                    display: true,
                    text: "Visit frequency during the chosen period"
                }
            }
        });
    },

    template: `
    <div>
        <owners-nav :offer="offer_type"></owners-nav>
        <div class="d-flex justify-content-center" style="background-color: #ddc8fb">
            <div class="w-25 d-flex justify-content-evenly my-3">
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="start-date">Start date</label>
                    <vuejs-datepicker v-model="start_date" format="dd.MM.yyyy." id="start-date" :disabled-dates="disabled" monday-first="true"></vuejs-datepicker>     
                </div>
                <div class="mt-1 ms-3 me-4 mb-1">
                    <label for="end-date">End date</label>
                    <vuejs-datepicker v-model="end_date" format="dd.MM.yyyy." id="end-date" :disabled-dates="disabled" monday-first="true"></vuejs-datepicker>
                </div>
                <div class="mt-1 ms-3 me-4 mb-1 d-flex align-items-end">
                    <select v-model="period_select" class="px-2 py-1">
                        <option selected>Monthly</option>
                        <option>Weekly</option>
                        <option>Daily</option>
                        <option>By offer</option>
                    </select>
                </div>
                <div class="d-flex align-items-end">
                    <button class="btn btn-success px-3 mx-2 h-75 my-1" v-on:click="getReports">Display</button>
                </div>
            </div>
        </div>    
        <div class="container w-75">
            <canvas id="bar-plot"></canvas>
        </div>
    </div>
    `,

    methods: {
        getReports() {
            axios.post("api/users/getVisitReport/" + this.$route.params.id + "/" + this.period_select, {
                start: this.start_date,
                end: this.end_date
            }).then(response => {
                this.reports = response.data;
                this.ch.data.labels = this.getChartLabels();
                this.ch.data.datasets = [{
                    label: 'Visits',
                    data: this.getChartData(),
                    backgroundColor: this.getBgColors(),
                    borderColor: this.getBorderColors(),
                    borderWidth: 1,
                    hoverBorderWidth: 3
                }];
                this.ch.options.title = {
                    display: true,
                    text: "Visit frequency during the chosen period"
                };
                this.ch.update();
            }).catch(error => {
                Swal.fire('Error', 'Something went wrong!', 'error');
            })
        },

        getChartLabels() {
            let labels = [];
            for (const report of this.reports) {
                labels.push(report.x);
            }
            return labels;
        },

        getChartData() {
            let chartData = [];
            for (const report of this.reports) {
                chartData.push(report.y);
            }
            return chartData;
        },

        getBgColors() {
            let bgColors = [];
            const n = this.colors.length;
            if (n === 0) return [];
            for (let i in this.reports) {
                bgColors.push(this.colors[i % n]);
            }
            return bgColors;
        },

        getBorderColors() {
            let borderColors = [];
            const n = this.border_colors.length;
            if (n === 0) return [];
            for (let i in this.reports) {
                borderColors.push(this.border_colors[i % n]);
            }
            return borderColors;
        }
    }
});