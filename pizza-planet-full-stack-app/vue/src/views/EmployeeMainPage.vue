<template>
  <div class="employee-main-page">
    <v-btn
      class="order-screen-button"
      @click="
        $router.push({
          name: 'view-orders-by-status',
          params: { status: 'pending' },
        })
      "
      >View Pending Orders
    </v-btn>
    <v-btn
      class="order-screen-button"
      @click="
        $router.push({
          name: 'view-orders-by-status',
          params: { status: 'complete' },
        })
      "
      >View Completed Orders</v-btn
    >
    <v-btn
      class="order-screen-button"
      @click="
        $router.push({
          name: 'view-orders-by-status',
          params: { status: 'cancelled' },
        })
      "
      >View Cancelled Orders</v-btn
    >
    <v-btn
      class="order-screen-button"
      @click="
        $router.push({
          name: 'view-orders-by-status',
          params: { status: 'ready' },
        })
      "
      >View Ready Orders</v-btn
    >
    <v-btn
      class="order-screen-button"
      @click="
        $router.push({
          name: 'view-orders',
        })
      "
      >View All Orders</v-btn
    >
    <v-btn
      class="order-screen-button"
      @click="$router.push('/menu')"
      >Edit Menu</v-btn
    >
    <p class="giphy">
      <img src=https://media.giphy.com/media/l3q2Dh5BA4zRFSwak/giphy.gif
      alt="happy dancing pizza">
    </p>
  </div>
</template>

<script>
export default {};
</script>

<style>
.order-screen-button {
  /* align-self: flex-start; */
  /* margin-left: 500px; */
  margin-bottom: 30px;
  /* align-items: center; */
}
.employee-main-page {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>