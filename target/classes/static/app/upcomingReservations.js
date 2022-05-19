Vue.component("upcoming-reservations", {
   data: function() {
       return {
           reservations: [],
           id: "",

       }
   },

   mounted() {
        this.id = this.$route.params.id;
        axios.get("api/reservations/getClientUpcomingReservations/" + this.id).then(response => {
            this.reservations = response.data;
        }).catch(function (error) {
            alert('Greska u get cottages');
        });
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
            <div class="container mt-5 pt-5">
                <div class="row p-3 mt-5">
                    <div class="col-2">
                        VIEW DETAILS
                    </div>

                    <div class="col-2">
                        START DATE
                    </div>

                    <div class="col-2">
                        END DATE
                    </div>

                    <div class="col-2">
                        STATUS
                    </div>

                    <div class="col-1">
                        PRICE (EUR)
                    </div>

                    <div class="col-3">

                    </div>
                </div>
                <div v-for="(r, i) in reservations" class="row p-2 m-3" style="border:1px solid rgb(156, 151, 151); border-radius: 5px;">
                    <div class="col-2">
                        <a v-on:click="viewDetails(r.link)" style="color:blue;"><u> View Details </u></a>
                    </div>

                    <div class="col-2">
                        {{r.startDate}}
                    </div>

                    <div class="col-2">
                         {{r.endDate}}
                    </div>

                    <div class="col-2">
                        {{r.status}}
                    </div>

                    <div class="col-1">
                        {{r.price}}
                    </div>

                    <div class="col-3">
                        <button v-if="notInThreeDays(r)" class="btn btn-danger"> Cancel reservation</button>
                    </div>
               </div>
            </div>
        </div>
   `,
    methods: {

        notInThreeDays(reservation)
        {
            return true;
            alert("U narednim danima" + reservation.isInThreeDays) ;
            return reservation.isInThreeDays;
        },

        findPaths(reservations)
        {
            paths = [];
            for (let i = 0; i < reservations.length; i++) {
                onePath = "#/cottageDetailedView/" + reservations[i].offerId + "/" + reservations[i].startDate + "/" + reservations[i].endDate;
                paths.push(onePath);
            }
            return paths;
        },

        viewDetails(link)
        {
            //location.replace('http://localhost:8000/' + link);
            window.location.href = 'http://localhost:8000/' + link;

        }
    },



});