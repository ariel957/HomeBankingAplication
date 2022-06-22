Vue.createApp({
    data() {
        return{
            cardColor:"",
            cardType:"",
   
        }
    },
    created() {
           
        

               
    },
    methods: {
        logout(){
            axios.post('/api/logout').then(response =>  window.location.href="http://localhost:8080/web/index.html") 
        },
        createCard(){ 
        axios.post('/api/clients/current/cards',`type=${this.cardType}&color=${this.cardColor}`,
        {headers:{'content-type': 'application/x-www-form-urlencoded'}})
        .then(response => window.location.href="http://localhost:8080/web/cards.html")
        .then(response => console.log("create"))
        
        .catch(error => Swal.fire({
            icon: 'error',
            text: error.message,
            
           
          }))
     }
    },


}).mount('#app')