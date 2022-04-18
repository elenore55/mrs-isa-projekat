const Login = {template: '<login></login>'}
const Registration = {template: '<registration></registration>'}
const UpdateCottage = {template: '<update-cottage></update-cottage>'};
const AddCottage = {template: '<add-cottage></add-cottage>'};


const router = new VueRouter({
    mode: 'hash',
    routes: [
            {
                path: "/login/",
                component: Login
            },
            {
                path: "/registration/",
                component: Registration
            },
        {
            path: "/updateCottage/",
            component: UpdateCottage
        },
        {
            path: "/addCottage/",
            component: AddCottage
        }

    ]
});

var app = new Vue({
    router,
    el: "#main",
});