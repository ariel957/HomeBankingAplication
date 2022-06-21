Vue.createApp({
    data() {
        return{
          accounts:[],
        clients:[],
        loans:[],

        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        axios.get('http://localhost:8080/api/clients/current')
            .then(datos => {
                this.accounts = datos.data.accounts
                this.clients = datos.data
                this.loans = datos.data.loans
                console.log(this.clients)
               
            })
          
               
           
    },
    methods: {
        logout(){
            axios.post('/api/logout').then(response =>  window.location.href="http://localhost:8080/web/index.html") 
        },
        create(){
                Swal.fire({
                    title: 'Estás por crear una cuenta!',
                    text: "Recuerda que solo puedes crear hasta 3 cuentas",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Si,quiero una cuenta!'
                  }).then((result) => {
                    if (result.isConfirmed) {
                      Swal.fire(
                        'Nueva cuenta creada!',
                        'ya puedes empezar a usar tu nueva cuenta',
                        'success'
                      )
                      .then(response => {axios.post('http://localhost:8080/api/clients/current/accounts',
                      {headers:{'content-type':'application/x-www-form-urlencoded'}})
                      .then(response => location.reload())})

                    }
                    
                  })

                }
        

            

           


    },

}).mount('#app')