const AddAdventure = {template: '<add-adventure></add-adventure>'};

const router = new VueRouter({
   mode: 'hash',
   routes: [
      {
         path: "/addAdventure/",
         component: AddAdventure
      }
   ]
});

var app = new Vue({
   router,
   el: "#main",
});