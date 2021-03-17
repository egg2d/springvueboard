<template>
  <v-app>
    <div id="app">
      <Header/>
      <router-view/>
      <Footer/>
      <vue-confirm-dialog></vue-confirm-dialog>

    </div>
  </v-app>
</template>

<script>

  import Header from "@/components/main/header/Header";
  import Footer from "@/components/main/footer/Footer";

  export default {
  name: 'App',

  components: {
    'Footer' : Footer,
    'Header' : Header
  },

  data: () => ({
    //
  }),
};
</script>
