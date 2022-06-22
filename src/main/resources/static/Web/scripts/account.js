Vue.createApp({
    data() {
        return {
            transactions: [],
            accounts: [],


        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        axios.get('http://localhost:8080/api/accounts/' + id)
            .then(datos => {
                this.transactions = datos.data.transactions
                this.transactions.sort((a, b) => a.id - b.id)
                this.accounts = datos.data
                console.log(this.transactions)


            })


    },
    methods: {
        logout(){
            axios.post('/api/logout').then(response =>  window.location.href="http://localhost:8080/web/index.html") 


    },
    }
}).mount('#app')