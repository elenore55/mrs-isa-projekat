Vue.component('reservation-confirmation', {
   mounted() {
       axios.post("api/reservations/confirmReservation/" + this.$route.params.id).then(function(response) {
           Swal.fire('Success', 'Reservation confirmed!', 'success');
       }).catch(function (error) {
           Swal.fire('Error', 'Something went wrong!', 'error');
       });
   }
});