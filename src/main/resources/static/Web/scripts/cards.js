Vue.createApp({
    data() {
        return{
          cards:[],
        clients:[],
        
         
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        console.log(id)
        axios.get('http://localhost:8080/api/clients/current/')
            .then(datos => {
                this.cards = datos.data.cards
                this.clients = datos.data
                console.log(this.cards.thruDate)
              
               
            })
               
    },
    methods: {
        formatoFecha(fecha){
            fecha = fecha.substring(0,10)
            fecha = fecha.split("-")
            fecha[0] = fecha[0].substring(2,4)

            return fecha[1] + "/" +fecha[0]
        },
        logout(){
            axios.post('/api/logout').then(response =>  window.location.href="http://localhost:8080/web/index.html") 
        },
    }

}).mount('#app')