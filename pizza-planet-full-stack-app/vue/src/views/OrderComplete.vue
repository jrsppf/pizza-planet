<template class="orderComplete">
    <v-sheet>
    <div class="center">
        <h1>Order Complete</h1>
        <h3>Confirmation Number: {{this.$store.state.confirmationID}}</h3>
        <h4>You can pick up your order in 25 min or expect to be delivered in 45 min</h4>
    </div>

     <!-- <p class="giphy">
      <img src=https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZDJkY2U0MjAzODQ4MTgzNDQ0YzYxY2VhODA0OWExNTFmOGNiOGQ0ZiZlcD12MV9pbnRlcm5hbF9naWZzX2dpZklkJmN0PWc/GFKZLvfBiyrN6/giphy.gif
      alt="building pizza">
    </p> -->
    </v-sheet>
</template>

<script>
export default {
    name: "order-complete"

}
</script>

<style>
    .orderComplete{
        /* background: white !important; */
    }
    .center {
        
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-content: center;
        height: 100%;
    }
</style>