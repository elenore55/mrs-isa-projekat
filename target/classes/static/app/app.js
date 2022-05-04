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

Vue.component('vuejs-datepicker', vuejsDatepicker);

const router = new VueRouter({
    mode: 'hash',
    routes: [
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
            path: "/login/",
            component: Login
        },
        {
            path: "/registration/",
            component: Registration
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
           path: "/profilePageInstructorPI/",
           component: ProfilePageInstructorPI
        }


    ]
});

var app = new Vue({
    router,
    el: "#main"
});