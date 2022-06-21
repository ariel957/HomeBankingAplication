Vue.createApp({
    data() {
        return{
        loans:[],
        paymentsLoan:[],
        paymentNumber:[],
        paymentsAvailable:0,
        amount:0,
        destinationAccount:"",
        accounts:"",
        clients:[]
        }

    },
    created() {
        axios.get('http://localhost:8080/api/loans')
            .then(datos => {
                this.loans = datos.data
                console.log(this.loans)
               
            }),
            axios.get('http://localhost:8080/api/clients/current')
            .then(datos => {
                this.accounts = datos.data.accounts
                this.clients = datos.data
                
               
               
            })
           
          
               
           
    },
    methods: {
        logout(){
            axios.post('/api/logout').then(response =>  window.location.href="http://localhost:8080/web/index.html") 
        },
        createLoan(){ 
            let aplyLoan = {
                "loanId" : this.typeLoan,
                "amount" : this.amount,
                "payment" : this.paymentsAvailable,
                "accountNumber" : this.destinationAccount
            }
            axios.post('/api/loans',aplyLoan) 
          
             },
         paymentsFilter(){
             this.paymentsLoan = this.loans.filter((loans)=> loans.id == this.typeLoan)
             this.paymentNumber = this.paymentsLoan[0].payments
            }
        

            

           


    },

}).mount('#app')