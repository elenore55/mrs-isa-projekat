Vue.component("upcoming-reservations", {
   data: function() {
       return {
           reservations: [],
           id: "",
           token: {}
       }
   },

   mounted() {
        this.token = JSON.parse(localStorage.getItem("jwt"));
        main_image = $("body").css("background-image", "url('images/set.webp')");
        main_image = $("body").css("background-size", "100% 250%");
        this.reload();
       },

   template: `
    <div>
        <client-navbar> </client-navbar>
            <div class="container mt-5 pt-5">

                <div class="d-flex justify-content-center">
                    <div class="collapse bg-light shadow rounded w-50 mt-3" id="confirm-cancel">
                        <p class=" d-flex justify-content-center mt-5 mb-3"> Reservation successfully cancelled</p>
                        <div class="d-flex justify-content-center">
                            <a class="btn btn-lg btn-outline-secondary m-4" role="button" v-on:click="hideItself">OK</a>
                        </div>
                    </div>
                </div>

                <div class="row p-3 mt-5" style="background-color: white">
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
                <div v-for="(r, i) in reservations" class="row p-3 my-2" style="border:1px solid rgb(156, 151, 151); border-radius: 5px; background-color: white">
                    <div class="col-2">
                        <a v-on:click="viewDetails(r.link)" style="color:blue;"><u> {{r.name}} </u></a>
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
                        <button v-if="canCancel(r)" class="btn btn-danger" v-on:click="cancel(r)"> Cancel reservation</button>
                        <button v-if="cantCancel(r)" class="btn btn-danger" disabled> Cancel reservation</button>
                    </div>
               </div>
            </div>
        </div>
   `,
    methods: {

        reload() {
            /*this.id = this.$route.params.id;
            axios.get("api/reservations/getClientUpcomingReservations/" + this.id).then(response => {
                this.reservations = response.data;
            }).catch(function (error) {
                alert('Greska u get cottages');
            });*/
            this.id = JSON.parse(localStorage.getItem("jwt")).userId;
            axios({
               method: 'get',
               url: "api/reservations/getClientUpcomingReservations/" + this.id,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.reservations = response.data;

           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
        },

        canCancel(reservation)
        {
            //alert("Trenutno stanje rezervacije je " + reservation.reservationStatus);
            if (!reservation.inThreeDays && reservation.status!="CANCELLED" ) return true;
            return false;
        },

        cantCancel(reservation)
        {
            return !this.canCancel(reservation);
        },

        cancel(reservation)
        {
            /*axios.post("api/reservations/cancelReservation/" + reservation.id).then(response => {
                this.reload();
                $("#confirm-cancel").show(200);
            }).catch(function (error) {
                alert('Greska u brisanju rezervacija');
            });*/

            axios({
               method: 'post',
               url: "api/reservations/cancelReservation/" + reservation.id,
               headers: {
                   Authorization: "Bearer " + this.token.accessToken
               }
           }).then(response => {
               this.reload();
               $("#confirm-cancel").show(200);

           }).catch(function (error) {
               if (error.response.status === 401) this.$router.push({path: '/unauthorized'});
               else Swal.fire('Error', 'Something went wrong!', 'error');
           });
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

        hideItself()
        {
            $("#confirm-cancel").hide(200);
        },

        viewDetails(link)
        {
            //location.replace('http://localhost:8000/' + link);
            window.location.href = 'http://localhost:8000/' + link;
            this.$router.push({path: '/' + link});

        }
    },



});