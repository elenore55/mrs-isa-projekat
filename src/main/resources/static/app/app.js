const ReservationsCalendar = {template: '<reservations-calendar></reservations-calendar>'};
const UpdateOwnersProfile = {template: '<update-owners-profile></update-owners-profile>'};
const OwnersProfile = {template: '<owners-profile></owners-profile>'};
const OwnersRegistration = {template: '<owners-registration></owners-registration>'};
const ShipReservations = {template: '<ship-reservations></ship-reservations>'};
const ReservationConfirmation = {template: '<reservation-confirmation></reservation-confirmation>'};
const FastReservations = {template: '<fast-reservations></fast-reservations>'};
const AddReservation = {template: '<add-reservation></add-reservation>'};
const CottageReservations = {template: '<cottage-reservations></cottage-reservations>'};
const ShipsViewOwner = {template: '<ships-view-owner></ships-view-owner>'};
const CottagesViewOwner = {template: '<cottages-view-owner></cottages-view-owner>'};
const UpdateShipNav = {template: '<update-ship-nav></update-ship-nav>'};
const ShipImages = {template: '<ship-images></ship-images>'};
const UpdateShip = {template: '<update-ship></update-ship>'};
const AddShip = {template: '<add-ship></add-ship>'};
const ProfilePageInstructorPI = {template: '<profile-page-instructorpi></profile-page-instructorpi>'};
const AddAdventure = {template: '<add-adventure></add-adventure>'};
const CottageImages = {template: '<cottage-images></cottage-images>'};
const UpdateCottageNav = {template: '<update-cottage-nav></update-cottage-nav>'};
const UpdateCottage = {template: '<update-cottage></update-cottage>'};
const AddCottage = {template: '<add-cottage></add-cottage>'};
const Login = {template: '<login></login>'};
const Registration = {template: '<registration></registration>'};
const ClientNavbar = {template: '<client-navbar></client-navbar>'};
const ClientProfile = {template: '<client-profile></client-profile>'};
const EditProfile = {template: '<edit-profile></edit-profile>'};
const ClientHome = {template: '<client-home></client-home>'};
const DeleteProfile = {template: '<delete-profile></delete-profile>'};
const DeleteProfileMessage = {template: '<delete-profile-message></delete-profile-message>'};
const EditProfileMessage = {template: '<edit-profile-message></edit-profile-message>'};
const ChangePassword = {template: '<change-password></change-password>'};
const CottageDetailedView = {template: '<cottage-detailed-view></cottage-detailed-view>'};

Vue.component('vuejs-datepicker', vuejsDatepicker);

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: "/reservationsCalendar/:id/",
            component: ReservationsCalendar
        },
        {
            path: "/updateOwnersProfile/:id/",
            component: UpdateOwnersProfile
        },
        {
            path: "/ownersProfile/:id/",
            component: OwnersProfile
        },
        {
            path: "/ownersRegistration/",
            component: OwnersRegistration
        },
        {
            path: "/shipReservations/:id/",
            component: ShipReservations
        },
        {
            path: "/confirmReservation/:id/",
            component: ReservationConfirmation
        },
        {
            path: "/fastReservations/:id/",
            component: FastReservations
        },
        {
            path: "/addReservation/:id/",
            component: AddReservation
        },
        {
            path: "/cottageReservations/:id/",
            component: CottageReservations
        },
        {
            path: "/shipsViewOwner/",
            component: ShipsViewOwner
        },
        {
            path: "/cottagesViewOwner/",
            component: CottagesViewOwner
        },
        {
            path: "/updateShipNav/",
            component: UpdateShipNav
        },
        {
            path: "/shipImages/:id/",
            component: ShipImages
        },
        {
            path: "/updateShip/:id/",
            component: UpdateShip
        },
        {
            path: "/addShip/",
            component: AddShip
        },
        {
            path: "/clientHome/",
            component: ClientHome
        },
        {
            path: "/deleteProfile/",
            component: DeleteProfile
        },
        {
            path: "/cottageImages/:id/",
            component: CottageImages
        },
        {
            path: "/clientNavbar/",
            component: ClientNavbar
        },
        {
            path: "/clientProfile/",
            component: ClientProfile
        },
        {
            path: "/editProfile/",
            component: EditProfile
        },
        {
            path: "/changePassword/",
            component: ChangePassword
        },
        {
             path: "/registration/",
             component: Registration
        },
        {
            path: "/login/",
            component: Login
        },
        {
            path: "/cottageDetailedView/:id",
            component: CottageDetailedView
        },
        {
            path: "/updateCottageNav/",
            component: UpdateCottageNav
        },
        {
            path: "/updateCottage/:id/",
            component: UpdateCottage
        },
        {
            path: "/addCottage/",
            component: AddCottage
        },
        {
           path: "/addAdventure/",
           component: AddAdventure
        },
        {
            path: "/deleteProfileMessage/",
            component: DeleteProfileMessage
        },
        {
            path: "/editProfileMessage/",
            component: EditProfileMessage
        },
        {
           path: "/profilePageInstructorPI/",
           component: ProfilePageInstructorPI
        }


    ]
});

var app = new Vue({
    router,
    el: "#main"
});