const PriceHistoryReport = {template: '<price-history-report></price-history-report>'};
const VisitReport = {template: '<visit-report></visit-report>'};
const ChangePasswordOwner = {template: '<change-pw-owner></change-pw-owner>'};
const ShipProfile = {template: '<ship-profile></ship-profile>'};
const CottageProfile = {template: '<cottage-profile></cottage-profile>'};
const SearchMap = {template: '<search-map></search-map>'};
const MyMap = {template: '<my-map></my-map>'};
const IncomeReport = {template: '<income-report></income-report>'};
const OwnersReservationsCalendar = {template: '<owners-reservations-calendar></owners-reservations-calendar>'};
const ClientReadonlyProfile = {template: '<client-readonly-profile></client-readonly-profile>'};
const ReservationsHistory = {template: '<reservations-history></reservations-history>'};
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
const ShipDetailedView = {template: '<ship-detailed-view></ship-detailed-view>'};
const UnregisteredNavbar = {template: '<unregistered-navbar></unregistered-navbar>'};
const InstructorsAdventures = {template: '<instructors-adventures></instructors-adventures>'};
const AddAdmin = {template: '<add-admin></add-admin>'};
const UpdateAdminInfo = {template: '<update-admin-info></update-admin-info>'};
const AvailabilityInstructor = {template: '<availability-instructor></availability-instructor>'};
const UpcomingReservations = {template: '<upcoming-reservations></upcoming-reservations>'};
const PastReservations = {template: '<past-reservations></past-reservations>'};
const ClientComplain = {template: '<client-complain></client-complain>'}
const ClientRate = {template: '<client-rate></client-rate>'}
const ClientSubs = {template: '<client-subs></client-subs>'}
const ClientActions = {template: '<client-actions></client-actions>'}
const InstructorsCalendar = {template: '<instructor-calendar></instructor-calendar>'};
const AdventureHistory = {template: '<adventure-history></adventure-history>'};
const AdventureQuickReserv = {template: '<adventure-quick></adventure-quick>'};
const AdveReservWithClient = {template: '<advreserv-with-client></advreserv-with-client>'};
const AdminEntities = {template: '<admin-entities></admin-entities>'};
const AdminComplaint = {template: '<admin-complaint></admin-complaint>'};
const AdminDeletionReq = {template: '<admin-deletreq></admin-deletreq>'};
const InstructorComplaint = {template: '<instructor-complaint></instructor-complaint>'};
const AdminPenalties = {template: '<admin-penalties></admin-penalties>'};
const AdminFeedback = {template: '<admin-feedback></admin-feedback>'};
const AdminRegREq = {template: '<admin-registrationreq></admin-registrationreq>'};
const AdminLoyalProg = {template: '<admin-loyalprogram></admin-loyalprogram>'};

Vue.component('vuejs-datepicker', vuejsDatepicker);

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: "/priceHistoryReport/:id",
            component: PriceHistoryReport
        },
        {
            path: "/visitReport/:id",
            component: VisitReport
        },
        {
            path: "/changePwOwner/:id",
            component: ChangePasswordOwner
        },
        {
            path: "/shipProfile/:id",
            component: ShipProfile
        },
        {
            path: "/cottageProfile/:id",
            component: CottageProfile
        },
        {
            path: "/searchMap",
            component: SearchMap
        },
        {
            path: "/myMap",
            component: MyMap
        },
        {
            path: "/incomeReport/:id",
            component: IncomeReport
        },
        {
            path: "/ownersReservationsCalendar/:id",
            component: OwnersReservationsCalendar
        },
        {
            path: "/clientReadonlyProfile/:email/",
            component: ClientReadonlyProfile
        },
        {
            path: "/reservationsHistory/:id/",
            component: ReservationsHistory
        },
        {
            path: "/reservationsCalendar/",
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
            path: "/instructorsAdventures/",
            component: InstructorsAdventures
        },
        {
            path: "/shipsViewOwner/:id/",
            component: ShipsViewOwner
        },
        {
            path: "/cottagesViewOwner/:id/",
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
            path: "/clientComplain/:id",
            component: ClientComplain
        },
        {
            path: "/clientRate/:id",
            component: ClientRate
        },
        {
            path: "/clientSubs/:id",
            component: ClientSubs
        },
        {
            path: "/clientActions/:id",
            component: ClientActions
        },
        {
            path: "/pastReservations/",
            component: PastReservations
        },
        {
            path: "/shipDetailedView/:id/:fromDate/:toDate",
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
        },
        {
            path: "/instructorsCalendar/",
            component: InstructorsCalendar
        },
        {
            path: "/adventureHistory/",
            component: AdventureHistory
        },
        {
            path: "/adventureQuickReserv/",
            component: AdventureQuickReserv
        },
        {
            path: "/advReservWithClient/",
            component: AdveReservWithClient
        },
        {
            path: "/adminEntities/",
            component: AdminEntities
        },
        {
            path: "/adminComplaint/",
            component: AdminComplaint
        },
        {
            path: "/adminDeletionReqs/",
            component: AdminDeletionReq
        },
        {
            path: "/instructorComplaint/",
            component: InstructorComplaint
        },
        {
            path: "/adminPenalties/",
            component: AdminPenalties
        },
        {
            path: "/adminFeedbacks/",
            component: AdminFeedback
        },
        {
            path: "/adminRegReq/",
            component: AdminRegREq
        },
        {
            path: "/adminLoyal/",
            component: AdminLoyalProg
        }
    ]
});

var app = new Vue({
    router,
    el: "#main"
});