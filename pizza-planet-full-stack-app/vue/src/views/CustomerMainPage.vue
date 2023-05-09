<template>
  <div class="home">
    <div class="slide">
      <v-btn class="order-screen-button" @click="showDeliveryDialog = true"
        >Delivery</v-btn
      >
      <v-btn class="order-screen-button" to="/menu">Carry-Out</v-btn>

      <div><slideShow /></div>
    </div>

    <v-dialog class="delivery-address" v-model="showDeliveryDialog">
      <v-card center >
        <v-card-title>Delivery Address</v-card-title>
        <v-card-text>
          <delivery-address />
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import slideShow from "../components/Carousel";
import DeliveryAddress from "../components/DeliveryAddress";

export default {
  name: "customer-main-page",
  components: {
    slideShow,
    DeliveryAddress,
  },
  data() {
    return {
      showDeliveryDialog: false,
    };
  },
//   computed: {
//     checkEmployeeLogIn() {
//       let user = true;
//       if (this.$store.state.token != "") {
//         user = false;
//       }
//       return user;
//     },
//   },
};
</script>

<style>
.slide {
  /* display: flex; */
  height: 800px;
  width: 1200px;
  padding-top: 50px;
  /* margin-left: 800px; */
  /* align-items: center;
    justify-content: center; */
}
.order-screen-button {
  margin-right: 10px;
  padding-right: 10px;
}
.home {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.giphy {
  display: flex;
  justify-content: center;
}

  .delivery-address-container {
    width: 100px;
    align-self: flex-end;
  }
</style>