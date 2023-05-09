<template>
    <v-sheet class="naming-conventions-who">
        <h2>Checkout</h2>
        <checkout-form />
    </v-sheet>
</template>

<script>
import CheckoutForm from '../components/CheckoutForm.vue'
export default {
    name: "checkout",
    components: {
        CheckoutForm
    }

}
</script>

<style>
    .naming-conventions-who {
        margin: auto;
    }
</style>