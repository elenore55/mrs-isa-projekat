new Vue({
   el: '#app_addAdventure',       // imamo kontrolu nad divom i sad mozemo da saljemo odnosno prikazujemo podatke na njeme
   data:{
      title:'AddAdventure',
      style_addAdventure:''
   },
   methods:{
      changeTitle(){
         this.title='ChangedAgain';
      }
   }
});