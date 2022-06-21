Vue.createApp({
    data() {
        return{
            clients : [],
            jsonClient : [],
            firstName : "",
            lastName : "",
            mail : "",
        }
    },
    created() {
        axios.get('http://localhost:8080/api/clients/')
            .then()
            .then(datos => {
                this.clients = datos.clients
                this.jsonClient= clients
            })
    },
    methods: {
        addClient (){
            axios.post('http://localhost:8080/clients',{
                firstName : this.firstName,
                lastName : this.lastName,
                mail : this.mail,
            })
            .then(response =>(console.log(response)) )  
              },


              deleteClient(client){
                  axios.delete(client._links.client.href)
                  .then(location.reload())
              },

              editClient(client){
              Swal.fire({
                title: 'Edit',
                html:
                  `<input id="swal-input1" class="swal2-input" value="${client.firstName}">` +
                  `<input id="swal-input2" class="swal2-input" value="${client.lastName}">`+
                  `<input id="swal-input3" class="swal2-input" value="${client.mail}">`,
                focusConfirm: false,
                preConfirm: () => {
    
                 let newName = document.getElementById('swal-input1').value;
                 let newLastName =  document.getElementById('swal-input2').value;
                 let newMail =  document.getElementById('swal-input3').value;

                 let editClient = {
                        firstName : newName,
                        lastName : newLastName,
                        mail : newMail,
                    };
                    axios.put(client._links.client.href , editClient)
                    .then(location.reload())
                }
              })
            },
    },

}).mount('#app')