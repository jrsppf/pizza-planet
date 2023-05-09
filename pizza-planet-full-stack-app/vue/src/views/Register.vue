<template>
  <div id="register" class="pa-4 text-center">
    <v-sheet width="300" class="mx-auto glow-border">
      <v-form ref="form" @submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group" >
          <v-text-field
            v-model="user.username"
            id="username"
            :spellcheck="false"
            :rules="usernameValidLength"
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
            :rules="passwordValidLength"
            label="Password"
            required
            ></v-text-field>
        </div>
        <div class="form-input-group">
            <v-text-field
            v-model="user.confirmPassword"
            type="password"
            id="confirmPassword"
            label="Confirm Password"
            required
            ></v-text-field>
        </div>
        <v-btn type="submit">Create Account</v-btn>
        <p><router-link :to="{ name: 'login' }" class="already-have">Already have an account? Log in.</router-link></p>
      </v-form>
    </v-sheet>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      usernameValidLength: [
        username => ( username.length >= 8) || 'Name must be at least 8 characters',
      ],
      //ADD Password validation
      passwordValidLength: [
        password => ( password.length >= 8) || 'Password must be at least 8 characters',
        password => ( password.includes('*') || password.includes('$') || password.includes('!')||password.includes('~')) || 'Password must be at least one of these characters: *, $, !, ~',
        password => ( password.toLowerCase() != password) || 'Password must contain at least 1 capitalized letter',
      ],

    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

.mx-auto {
  display: flex;
  flex-direction: column;
  /* margin-top: 275px; */
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

.already-have {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}



</style>

