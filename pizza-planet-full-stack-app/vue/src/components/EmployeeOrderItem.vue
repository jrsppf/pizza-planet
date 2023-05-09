<template>
  <div class="order-form">
    <p class="orderID">
      <b>Order ID: {{ order.orderId }}</b>
    </p>
    <p>Order total: {{ total }}</p>
    <p>Order email: {{ order.email }}</p>
    <p>Order phone: {{ order.phoneNumber }}</p>
    <p>Order delivery info: {{ order.address.street }} {{ order.address.city }} {{ order.address.state }} {{ order.address.zip }} </p>
    <div v-for="(pizza, index) in order.pizzas" :key="index">
      <p>
        <b>Pizza ID: {{ pizza.pizzaId }}</b>
      </p>

      <!-- <p>Pizza {{ pizza.pizzaId }} Toppings: {{ pizza.toppings}}</p> -->
      <p>Toppings: {{ pizza.toppings.map((topping) => topping.name).join(", ")}}</p>


      <!-- <div v-for="(item, index) in pizza.toppings" :key="index"></div> -->
      <!-- <p>Toppings: {{ item.name }}</p> -->

      <!-- Pizza {{ pizza.pizzaId }} Premium Toppings: {{ pizza.premiumToppings }} -->
     <p> Premium Toppings: {{ pizza.premiumToppings.map((topping) => topping.name).join(", ") }}</p>

      <!-- <p>Pizza {{ pizza.pizzaId }} Sauce: {{ pizza.sauce }}</p> -->

      <p>Sauce: {{ pizza.sauce.name }}</p>
      <p>Crust: {{ pizza.crust.name }}</p>
      <!-- <p>Pizza {{ pizza.pizzaId }} Size: {{ pizza.size.name }}</p> -->
      <p>Size: {{ pizza.size.name }}</p>
    </div>

    <div v-for="(nonPizza, index) in order.appetizers" :key="index">
      <p><b>Appetizers</b></p>
      <p>{{ nonPizza.itemName }}</p>
    </div>

    <div v-for="(nonPizza, index) in order.salads" :key="index">
      <p><b>Salads</b></p>
      <p>{{ nonPizza.itemName }}</p>
    </div>

    <div v-for="(nonPizza, index) in order.sides" :key="index">
      <p><b>Sides</b></p>
      <p>{{ nonPizza.itemName }}</p>
    </div>

    <div v-for="(nonPizza, index) in order.drinks" :key="index">
      <p><b>Drinks</b></p>
      <p>{{ nonPizza.itemName }}</p>
    </div>

    <p>Order status:</p>
    <input
      type="radio"
      :name="'status' + order.orderId"
      @change="onChange($event)"
      value="PENDING"
      :checked="order.status == 'PENDING'"
    />Pending
    <input
      type="radio"
      :name="'status' + order.orderId"
      @change="onChange($event)"
      value="CANCELLED"
      :checked="order.status == 'CANCELLED'"
    />Cancelled
    <input
      type="radio"
      :name="'status' + order.orderId"
      @change="onChange($event)"
      value="COMPLETE"
      :checked="order.status == 'COMPLETE'"
    />Complete
    <input
      type="radio"
      :name="'status' + order.orderId"
      @change="onChange($event)"
      value="READY"
      :checked="order.status == 'READY'"
    />Ready
  </div>
</template>

<script>
import OrderServices from "../services/OrderServices";

export default {
  name: "employee-order-item",
  props: ["order"],
  computed: {
    total() {
      if (this.order.total) {
        return "$" + this.order.total.toFixed(2);
      } else {
        return "$0.00";
      }
    },
  },
  methods: {
    onChange(event) {
      let newStatus = event.target.value;
      let updatedOrder = {
        orderId: this.order.orderId,
        status: newStatus,
        total: this.order.total,
        address: this.order.address,
        email: this.order.email,
        cardInfo: this.order.cardInfo,
        phoneNumber: this.order.phoneNumber,
        pizzas: this.order.pizzas,
        appetizers: this.order.appetizers,
        salads: this.order.salads,
        sides: this.order.sides,
        drinks: this.order.drinks,
      };
      OrderServices.updateOrderStatus(this.order.orderId, updatedOrder);
    },
  },
};
</script>

<style scoped>
.order-form{
  margin: 10px;
}
.orderID{
  color: blue;
}
</style>