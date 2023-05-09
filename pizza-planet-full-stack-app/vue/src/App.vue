<template>
  <v-app>
    <v-app-bar
      app
      color=#C67CF7
    >
    <!-- <v-app-bar-title>Pizza Planet</v-app-bar-title> -->
      <router-link to="/">
        <img src="../public/images/PizzaPlanetLogoMain.png" style="height: 80%; max-height: 64px"/>
      </router-link>
      <v-spacer></v-spacer>

      <v-spacer></v-spacer>
      <v-btn to="/" text>
        <v-icon small>fa-home</v-icon>
      </v-btn>
      <v-btn to="/login" text v-show="$store.state.token === ''">
        <v-icon small>fa-key</v-icon>
      </v-btn>
       <v-btn to="/logout" text v-show="$store.state.token != ''">
        <v-icon small>fa-lock</v-icon>
      </v-btn>
      <v-btn to="/order-cart" text v-show="$store.state.token === ''">
        <v-icon small>fa-shopping-cart</v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>
      <div id="app">
        <div id="nav">
          <!-- <router-link v-bind:to="{ name: 'logout' }" v-show="$store.state.token != ''">Logout</router-link> -->
        </div>
        <router-view />
      </div>
      </v-main>
    <div><FooterSection/></div>
  </v-app>
</template>

<script>
import FooterSection from './components/Footer.vue'
export default {
  name: "App",
  components: {
    FooterSection
  },

}
</script>

<style>
  #app {
    height: 100%;
    background: url('https://media.giphy.com/media/1kTUjHlD4SVf2lut1u/giphy.gif') no-repeat center center fixed;
    background-size: cover;
  }
  /* .footer{
    display: flex;
    margin-bottom: 0px;
    justify-items: flex-end;
    position: relative; 
    bottom: 0;
  } */
  .logo-button {
    margin: 0;
    display: block;
    overflow: hidden;
  }
</style>
