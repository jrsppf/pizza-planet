<template>
    <v-form @submit.prevent class="glow-border">
    <v-text-field v-model="address.name" label="Name" required></v-text-field>
    <v-text-field v-model="address.street" label="Street Address" required></v-text-field>
    <v-row>
        <v-col cols="12" md="6">
            <v-text-field v-model="address.city" label="City" required></v-text-field>
        </v-col>
        <v-col cols="12" md="3">
            <v-text-field v-model="address.state" label="State" required></v-text-field>
        </v-col>
        <v-col cols="12" md="3">
            <v-text-field v-model="address.zip" label="Zip Code" required></v-text-field>
        </v-col>
    </v-row>
    <v-btn @click="submitAddress">Submit</v-btn>
    </v-form>
</template>

<script>
export default {
    name: "delivery-address",
    data() {
        return {
            address: {
                name: '',
                street: '',
                city: '',
                state: '',
                zip: '',
            }
        }
    },
    methods: {
        submitAddress() {
            const deliveryAddress = {
                name: this.address.name,
                street: this.address.street,
                city: this.address.city,
                state: this.address.state,
                zip: this.address.zip,
                };
            this.$store.commit("SET_ADDRESS", deliveryAddress);

            this.$store.commit("SET_CUSTOMER_NAME", this.address.name);

            this.$store.commit("SET_DELIVERY");

            this.$router.push('/menu')
        }
    }
}

</script>

<style>

</style>