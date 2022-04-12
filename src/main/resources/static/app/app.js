const AddCottage = {template: '<add-cottage></add-cottage>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
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