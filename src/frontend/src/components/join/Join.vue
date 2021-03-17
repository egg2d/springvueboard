<template>
    <div style="width: 30%; min-height: 600px; margin-top: 100px" class="mx-auto">

        <ValidationObserver ref="observer">
           <ValidationProvider v-slot="{errors}" name="id" rules="required|min:5|max:10|idDupCheck:@idCheck">
               <v-layout row class="mt-6">
                    <v-text-field
                            v-model="id"
                            :error-messages="errors"
                            label="Id"
                            :F="idDupCheck"
                    ></v-text-field>
                    <v-btn dark @click="idCheck" class="mr-2">중복 체크</v-btn>

                </v-layout>
            </ValidationProvider>
            <ValidationProvider name="idCheck">
                <input v-model="idDupCheck" type="hidden"/>

            </ValidationProvider>
            <ValidationProvider v-slot="{errors}" name="E-mail" rules="required|email">
                <v-layout row class="mt-6">
                <v-text-field
                        v-model="email"
                        :error-messages="errors"
                        label="E-mail"
                ></v-text-field>
                </v-layout>
            </ValidationProvider>
            <ValidationProvider v-slot="{errors}" name="Name" rules="required|max:10">
                <v-layout row class="mt-6">
                <v-text-field
                        v-model="name"
                        :counter="10"
                        :error-messages="errors"
                        label="Name"
                ></v-text-field>
                </v-layout>
            </ValidationProvider>
            <ValidationProvider v-slot="{errors}" name="Password" rules="required|password">
                <v-layout row class="mt-6">
                <v-text-field
                        v-model="password"
                        :type="'password'"
                        :error-messages="errors"
                        label="Password"
                ></v-text-field>
                </v-layout>
            </ValidationProvider>
            <ValidationProvider v-slot="{errors}" name="Password Confirm" rules="required|confirm:@Password">
                <v-layout row class="mt-6">
                <v-text-field
                        v-model="confirmation"
                        :type="'password'"
                        :error-messages="errors"
                        label="Password Confirmation"
                ></v-text-field>
                </v-layout>
            </ValidationProvider>

            <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
            >
                <template v-slot:activator="{ on, attrs }">
                    <ValidationProvider v-slot="{errors}" name="birthday" rules="required">
                        <v-layout row class="mt-6">
                        <v-text-field
                            v-model="birthday"
                            label="Birthday date"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            :error-messages="errors"
                    ></v-text-field>
                        </v-layout>
                    </ValidationProvider>
                </template>
                <v-date-picker
                        ref="picker"
                        v-model="birthday"
                        :max="new Date().toISOString().substr(0, 10)"
                        min="1930-01-01"
                        @change="save"
                ></v-date-picker>
            </v-menu>
            <v-layout row class="mt-6" >
            <v-btn dark @click="join" class="mr-2">가입</v-btn>
            <v-btn outlined @click="goBack">취소</v-btn>
            </v-layout>
        </ValidationObserver>
    </div>
</template>

<script>
    import axios from 'axios'
    import { required, email, max, min } from 'vee-validate/dist/rules'
    import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
    setInteractionMode('eager')


    extend('required', {
        ...required,
        message: '{_field_}는 필수항목입니다.'
    })

    extend('idDupCheck', {
        params: ['target'],
        validate(value, {target}) {
            console.log(this);
            console.log(target);

            return this== true;
        },
        message: '아이디 중복체크가 되지 않았습니다.',
    })

    extend('email', {
        ...email,
        message: '올바른 E-mail을 입력해주세요.'
    })

    extend('max', {
        ...max,
        message: '{_field_}은 {length}자 이하만 가능합니다.',
    })

    extend('min', {
        ...min,
        message: '{_field_}은 {length}자 이상만 가능합니다.',
    })

    extend('confirm', {
        params: ['target'],
        validate(value, {target}) {
            return value === target
        },
        message: 'Password가 일치하지 않습니다.',
    })

    extend('password', {
        validate: value => {
            return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value)
        },
        message: '{_field_}는 영대소문자, 숫자, 특수기호가 포함된 8자 이상만 가능합니다.'
    })

    export default {
        components: {
            ValidationProvider,
            ValidationObserver
        },
        data() {
            return {
                userId : '',
                email : '',
                password : '',
                name : '',
                confirmation: '',
                birthday: null,
                menu: false,
                idDupCheck : false
            }
        },
        watch: {
            menu (val) {
                val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            },
        },
        methods : {
            join() {
                this.$refs.observer.validate()
                    .then((res) => {
                        if(res) {
                            const that = this
                            axios.post('/api/user/join', {
                                id : this.id,
                                email : this.email,
                                password : this.password,
                                name : this.name,
                                birthday : this.birthday
                            }).then(function(res) {

                                if(res.data.status == "success") {
                                console.log(res)
                                window.alert("회원가입이 완료되었습니다.")
                                that.$router.push({path: '/login'})
                                }
                                else if(res.data.status != "success"){
                                    window.alert(res.data.message);
                                }

                            }).catch(function (err) {
                                window.alert(err)
                            })
                        }
                    })
                    .catch((err) => {
                        console.log(err)
                    })


            },
            goBack() {
                this.$router.replace('/')
            },
            save (date) {
                    this.$refs.menu.save(date)
            },
            // Id 중복 체크
            idCheck() {

                if(this.id.length ==0) {
                    alert('id를 입력해주세요.');
                    return;
                } else if(this.id.length <5 || this.id.length > 10) {
                    alert('id의 길이는 5~10자리 입니다.');
                }

                const that = this
                axios.post('/api/user/idCheck', {
                    id : this.id,
                }).then(function(res) {
                    if(res.data.status == "SUCCESS") {
                        console.log('success');
                        that.idDupCheck = true;
                        that.$confirm(
                            {
                                message: `해당 아이디를 사용하시겠습니까?`,
                                button: {
                                    no: '아니오',
                                    yes: '네'
                                },
                                callback: confirm => {
                                    if (confirm) {
                                        // ... do something
                                        that.idDupCheck=true;
                                    } else {
                                        that.idDupCheck=false;
                                    }
                                }
                            }
                        )
                    } else if(res.data.status =="ALREADY_REGISTERED") {
                        console.log('ALREADY_REGISTERED');

                    } else if(res.data.status != "SUCCESS"){
                        window.alert(res.data.message);
                    }

                }).catch(function (err) {
                    window.alert(err)
                })

            }

        }
    }
</script>

<style scoped>

</style>