const AddAdventure = {template: '<add-adventure></add-adventure>'};
const ProfilePageInstructorPI = {template: '<profile-page-instructorpi></profile-page-instructorpi>'};

const router = new VueRouter({
   mode: 'hash',
   routes: [
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
   el: "#main",
});