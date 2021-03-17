<template>
    <div>

        <v-app-bar  app>
            <v-container class="mx-auto py-0">

                <v-row align="center">
                    <v-img
                            :src="require('@/assets/logo.png')"
                            class="mr-5"
                            contain
                            height="48"
                            width="48"
                            max-width="48"
                            @click="$vuetify.goTo(0)"
                    />
                    <v-spacer />

                    <v-toolbar-items>
                        <v-btn
                                depressed
                                v-if="isAuthenticated == null" to="/login" class="hidden-sm-and-down">로그인</v-btn>
                        <v-btn
                                depressed
                                v-if="isAuthenticated == null" to="/join" class="hidden-sm-and-down">회원가입</v-btn>

                        <v-btn text>로그아웃</v-btn>
                    </v-toolbar-items>
                </v-row>
            </v-container>
        </v-app-bar>

    </div>
</template>

<script>
    import store from "@/store"

    export default {
        computed: {
            isAuthenticated() {
                return store.getters.isAuthenticated
            },
        },
        methods: {
            logout() {

                //로그아웃 mutations 실행 후 리다이렉트
                store.dispatch("LOGOUT")
                    .then(() => this.$router.push("/")
                        .catch(err => {
                            if(err.name != "NavigationDuplicated") {
                                throw err;
                            }
                        })
                    )
            }
        }
    }
</script>

<style>

</style>



<!--  <v-spacer></v-spacer>
  <router-link v-if="isAuthenticated == null" to="/join" class="ma-2">회원가입</router-link>
  <router-link v-if="isAuthenticated == null" to="/login">로그인</router-link>
  <a href="" v-if="isAuthenticated != null" @click.prevent="logout" class="ma-2">로그아웃</a>
-->