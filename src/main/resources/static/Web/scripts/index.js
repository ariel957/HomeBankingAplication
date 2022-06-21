Vue.createApp({
    data() {
        return{
   
            newUserPassword:"",
            newUserMail:"",
            newUserLastName:"",
            newUserName:"",
        }
    },
    created() {
           
        

               
    },
    methods: {
        login (){
            axios.post('/api/login',`email=${this.newUserMail}&password=${this.newUserPassword}`,
            {headers:{'content-type':'application/x-www-form-urlencoded'}}).then(response => window.location.href="http://localhost:8080/web/accounts.html")
            },
            register (){
                axios.post('/api/clients',`firstName=${this.newUserName}&lastName=${this.newUserLastName}&mail=${this.newUserMail}&password=${this.newUserPassword}`,
                {headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    response.status
                    this.login()
                })

                }
            

    },


}).mount('#app')