Vue.component('reservation-confirmation', {
   mounted() {
       axios.post("api/reservations/confirmReservation/" + this.$route.params.id).then(function(response) {
           alert('Reservation confirmed!');
       }).catch(function (error) {
           alert('An error occurred');
       });
   }
});