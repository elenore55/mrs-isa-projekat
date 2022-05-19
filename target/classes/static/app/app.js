const ReservationConfirmation = {template: '<reservation-confirmation></reservation-confirmation>'};
const FastReservations = {template: '<fast-reservations></fast-reservations>'};
const AddCottageReservation = {template: '<add-cottage-reservation></add-cottage-reservation>'};
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
const ShipDetailedView = {template: '<ship-detailed-view></ship-detailed-view>'};
const UnregisteredNavbar = {template: '<unregistered-navbar></unregistered-navbar>'};
const InstructorsAdventures = {template: '<instructors-adventures></instructors-adventures>'};
const AddAdmin = {template: '<add-admin></add-admin>'};
const UpdateAdminInfo = {template: '<update-admin-info></update-admin-info>'};
const AvailabilityInstructor = {template: '<availability-instructor></availability-instructor>'};
const UpcomingReservations = {template: '<upcoming-reservations></upcoming-reservations>'};

Vue.component('vuejs-datepicker', vuejsDatepicker);

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: "/confirmReservation/:id/",
            component: ReservationConfirmation
        },
        {
            path: "/fastReservations/:id/",
            component: FastReservations
        },
        {
            path: "/addCottageReservation/:id/",
            component: AddCottageReservation
        },
        {
            path: "/cottageReservations/:id/",
            component: CottageReservations
        },
        {
            path: "/instructorsAdventures/",
            component: InstructorsAdventures
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
            path: "/unregisteredNavbar/",
            component: UnregisteredNavbar
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
            path: "/cottageDetailedView/:id/:fromDate/:toDate",
            component: CottageDetailedView
        },
        {
            path: "/upcomingReservations/:id",
            component: UpcomingReservations
        },
        {
            path: "/shipDetailedView/:id",
            component: ShipDetailedView
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
        },
        {
            path: "/addAdmin/",
            component: AddAdmin
        },
        {
            path: "/updateAdminInfo/",
            component: UpdateAdminInfo
        },
        {
            path: "/availabilityInstructor/",
            component: AvailabilityInstructor
        }


    ]
});

var app = new Vue({
    router,
    el: "#main"
});