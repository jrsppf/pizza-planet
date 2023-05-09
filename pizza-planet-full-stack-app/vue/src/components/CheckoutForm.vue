<template>
  <v-container class="mx-auto fill-height">
    <v-form @submit.prevent class="pizza-order-form">
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="payment.name"
            label="Name:"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="payment.cardNumber"
            label="Card Number:"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="3">
          <v-text-field
            v-model="payment.expirationDate"
            label="Expiration Date:"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="3">
          <v-text-field
            v-model="payment.securityCode"
            label="Security Code"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="3">
          <v-text-field
            v-model="payment.billingZip"
            label="Billing Zip Code"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn @click="sumbitPayment">Place Order</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import OrderServices from "../services/OrderServices";

export default {
  name: "checkout-form",
  data() {
    return {
      payment: {
        name: "",
        cardNumber: "",
        expirationDate: "",
        securityCode: "",
        billingZip: "",
      },
    };
  },
  methods: {
    sumbitPayment() {
      const paymentInfo = {
        name: this.payment.name,
        cardNumber: this.payment.cardNumber,
        expirationDate: this.payment.expirationDate,
        securityCode: this.payment.securityCode,
        billingZip: this.payment.billingZip,
      };
      this.$store.commit("SET_PAYMENT", paymentInfo);

      // this may fit somewhere else better, and need way to account if payment unsuccessful
      this.$store.commit("SET_ORDER_STATUS_TO_PENDING", this.$store.state.order);

      console.log("We made it this far, at least");
      console.log(this.$store.state.order)

      //POST THE ORDER TO DATABASE
      OrderServices.postAnOrder(this.$store.state.order).then((resp) => {
        if (resp.status === 201 || resp.status === 200) {
          this.$store.state.confirmationID = resp.data.orderId;
          //clear out cart
          this.$store.state.order = {
          orderId: '',
          status: '',
          total: '',
          address: 
            {
              name: '',
              street: '',
              city: '',
              state: '',
              zip: '',
            },
          email:"",
          cardInfo: {
            name: '',
            cardNumber: '',
            expirationDate: '',
            securityCode: '',
            billingZip: '',
          },
          phone: '',
          pizzas: [],
          // nonPizzaItems: 
          

          //REMOVE nonPizzaItems wrapper
          appetizers: [],
          salads: [],
          sides: [],
          drinks: []
          // name: '',
          // delivery: false,
    }

          this.$router.push("/order-complete");
        }
      });
    },
  },
};
</script>

<style>
.pizza-order-form {
  background: rgb(255, 255, 255);
  padding-bottom: 200px;
  border: 3px solid #fff;
  border-radius: 32px;
  padding: 30px;
  box-shadow: 0 0 3px #fff, 0 0 3px #fff, 0 0 3px #bc13fe, 0 0 12px #bc13fe,
    0 0 45px #bc13fe, inset 0 0 21px #bc13fe;
  margin-top: 2rem;
  margin-bottom: 6.5rem;
}
</style>