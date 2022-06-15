Vue.component("admin-complaint",{
    data() {
        return {
            complaints:[]
        }
    },
    mounted: function (){
        this.loadComplaints()
            console.log(this.complaints)
    },
    template: `
        <div>
            <h2 class="text-center">All complaints</h2>
                <div v-for="comp in complaints">
                    <div> {{comp.id}} {{comp.content}} {{comp.dateTime}} {{comp.adminApprovalStatus}}</div>
                </div>
        </div>
    `,
    methods:{
        loadComplaints(){
            axios.get("api/complaint/all").then(response => {
                this.complaints = response.data;
            })
        }
    }
});