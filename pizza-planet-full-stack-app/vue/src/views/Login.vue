<template>
  <div id="login" class="pa-4 text-center"> 
    <v-sheet width="300" class="mx-auto glow-border">
      <v-form ref="form" @submit.prevent="login">
        <h1 >Please Sign In</h1>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <v-text-field
            v-model="user.username"
            id="username"
            :spellcheck="false"
            label="Username"
            required
            autofocused
            ></v-text-field>
        </div>
        <div class="form-input-group">
            <v-text-field
            v-model="user.password"
            type="password"
            id="password"
            label="Password"
            required
            ></v-text-field>
        </div>
        
        <v-btn type="submit">Sign in</v-btn>
        <p>
        <router-link :to="{ name: 'register' }" class="need-account">Need an account? Sign up.</router-link></p>
      </v-form>
    </v-sheet>
  </div>
</template>


<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/employee-main-page");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  
}



.mx-auto {
  display: flex;
  flex-direction: column;
  /* border: 5px solid #5c5858;
  border-radius: 25px;
  padding: 25px; */
  border: 3px solid #fff;
  border-radius: 32px;
  padding: 30px;
  box-shadow: 
  0 0 3px #fff,
  0 0 3px #fff,
  0 0 3px #bc13fe,
  0 0 12px #bc13fe,
  0 0 45px #bc13fe,
  inset 0 0 21px #bc13fe;

} 

.need-account {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

main {
background-image: "../images/laser-background.jpg";
}


</style>