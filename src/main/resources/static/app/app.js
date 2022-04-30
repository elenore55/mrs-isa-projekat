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

const router = new VueRouter({
    mode: 'hash',
    routes: [
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
            path: "/cottageImages/",
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
             path: "/registration/",
             component: Registration
        },
        {
            path: "/login/",
            component: Login
        },
        {
            path: "/updateCottageNav/",
            component: UpdateCottageNav
        },
        {
            path: "/updateCottage/",
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
           path: "/profilePageInstructorPI/",
           component: ProfilePageInstructorPI
        }


    ]
});

var app = new Vue({
    router,
    el: "#main",
});