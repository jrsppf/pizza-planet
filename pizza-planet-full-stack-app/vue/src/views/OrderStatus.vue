<template>
  <v-container class="order-status">
    <v-sheet>
        <v-row>
            <v-col cols="12">
                <h1>{{ pageHeader}}</h1> 
                <v-row>
                    <v-col cols="12" class="employee-order-items">
                        <employee-order-item v-for="order in this.orders" v-bind:key="order.orderId" v-bind:order="order"/>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-sheet>
  </v-container>
</template>

<script>
import EmployeeOrderItem from "../components/EmployeeOrderItem.vue";
import OrderServices from "../services/OrderServices"

export default {
  name: "order-status",
  data() {
      return {
          orders: [],
      }
  },
  components: {
    EmployeeOrderItem,
  },
  computed: {
      pageHeader() {
          if(this.$route.params.status) {
              return this.$route.params.status.toUpperCase() + " ORDERS";
          } else {
              return "ALL ORDERS"
          }
      }
  },
  created(){
      if(this.$route.params.status) {
        OrderServices.getOrderListByStatus(this.$route.params.status.toUpperCase()).then(resp => {
            this.orders = resp.data;
        })
      } else {
          OrderServices.getOrderList().then(resp => {
              this.orders = resp.data;
          })
      }
    }
};
</script>

<style>
.employee-order-items{
    display:flex;
    height: 100%;
    flex-direction: column;
    justify-content: space-between;
}
</style>