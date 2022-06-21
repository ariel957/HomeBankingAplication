Vue.createApp({
    data() {
        return{
            account:[],
            cuentaDestino:"",
            cuentaOrigen:"",
            description:"",
            amount:"",
         
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        axios.get('http://localhost:8080/api/clients/current')
            .then(datos => {
                this.accounts = datos.data.accounts
                
               
            })
               
    },
    methods: {
        createTransaction(){ 
            
            axios.post('/api/transactions',`amount=${this.amount}&description=${this.description}&originAccountNumber=${this.cuentaOrigen}&targetAccountNumber=${this.cuentaDestino}`,
            {headers:{'content-type': 'application/x-www-form-urlencoded'}})
            .then(response => window.location.href="http://localhost:8080/web/accounts.html")
            .then(response => console.log("create"))
            
         }


    },


}).mount('#app')