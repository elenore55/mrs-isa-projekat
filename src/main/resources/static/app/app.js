const CottageImages = {template: '<cottage-images></cottage-images>'};
const UpdateCottageNav = {template: '<update-cottage-nav></update-cottage-nav>'};
const UpdateCottage = {template: '<update-cottage></update-cottage>'};
const AddCottage = {template: '<add-cottage></add-cottage>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: "/cottageImages/",
            component: CottageImages
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
        }
    ]
});

var app = new Vue({
    router,
    el: "#main",
});