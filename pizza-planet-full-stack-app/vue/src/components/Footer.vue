<template>

    <v-footer relative color="#C67CF7" height="auto">
        <!-- <img src="..\public\images\PizzaPlanetLogoMain.png" height="50px" class="footer-image" z-index="1"/> -->
            <v-card class="flex" flat tile outlined color="transparent">
            <v-card-actions class="justify-center flex-column">
                <span><v-icon small left>fa-phone</v-icon>+1-888-888-8888</span>
                <span><v-icon small left>fa-map-marker</v-icon>30 S 17th St Suite 217, Philadelphia, PA 19103</span>
            </v-card-actions>
            </v-card>
    </v-footer>
</template>

<script>
export default {
  name: "footerSection",

};
</script>
<style scoped>
.footer-image{
    margin-left: 20px;
}

</style>


