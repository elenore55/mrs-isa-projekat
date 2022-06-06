Vue.component('change-pw-owner', {
    data() {
        return {
            old_pass_type: "password",
            old_eye_class: "fa fa-eye",
            pass_type: "password",
            eye_class: "fa fa-eye",
            pass_type_confirm: "password",
            eye_class_confirm: "fa fa-eye",
            password: '',
            old_password: '',
            confirm_password: '',
            input_started: false
        }
    },

    template: `
    <div style="background-color: #ddc8fb">
        <owners-nav></owners-nav>
        <div class="d-flex justify-content-center">
            <div class="card shadow-lg my-5" style="background-color: #fff9e8; border-radius: 15px; width: 40%">
                <div class="card-body">
                    <h3 class="card-title d-flex justify-content-center mt-3 mb-5">Change password</h3>
                    <div class="m-4">
                        <div class="input-group">
                            <div class="form-floating flex-grow-1">
                                <input :type="old_pass_type" class="form-control rounded-0 rounded-start" id="old-pass-input" v-model="old_password" placeholder="Old password">
                                <label for="old-pass-input" style="color:#C0C0C0">Old password</label>
                            </div>
                            <span class="input-group-text">
                                <i :class="old_eye_class" style="cursor: pointer" v-on:click="toggleOldPass"></i>
                            </span>
                        </div>
                    </div>
                    <div class="m-4">
                        <div class="input-group">
                            <div class="form-floating flex-grow-1">
                                <input :type="pass_type" class="form-control rounded-0 rounded-start" id="pass-input" v-model="password" placeholder="New password">
                                <label for="pass-input" style="color:#C0C0C0">New password</label>
                            </div>
                            <span class="input-group-text">
                                <i :class="eye_class" style="cursor: pointer" v-on:click="togglePass"></i>
                            </span>
                        </div>
                        <p v-if="isWeakPassword" class="text-danger">Weak password</p>
                        <p v-if="isMediumPassword" class="text-warning">Medium strong password</p>
                        <p v-if="isStrongPassword" class="text-success">Strong password</p>
                    </div>
                    <div class="m-4">
                        <div class="input-group">
                            <div class="form-floating flex-grow-1">
                                <input :type="pass_type_confirm" class="form-control rounded-0 rounded-start" id="pass-confirm-input" v-model="confirm_password" placeholder="Confirm new password">
                                <label for="pass-confirm-input" style="color:#C0C0C0">Confirm new password</label>
                            </div>
                            <span class="input-group-text">
                                <i :class="eye_class_confirm" style="cursor: pointer" v-on:click="togglePassConfirm"></i>
                            </span>
                        </div>
                        <p v-if="!areValidPasswords" class="text-danger">Passwords are required and must be matching</p>
                    </div>
                    <div class="m-4 d-flex justify-content-end">
                        <button type="button" class="btn btn-success" v-on:click="saveChanges">Confirm</button>
                    </div>
                </div>        
            </div>
        </div>
    </div>
    `,

    methods: {
        toggleOldPass() {
            if (this.old_pass_type === "password") {
                this.old_pass_type = "text";
                this.old_eye_class = "fa fa-eye-slash"
            } else {
                this.old_pass_type = "password";
                this.old_eye_class = "fa fa-eye"
            }
        },

        togglePass() {
            if (this.pass_type === "password") {
                this.pass_type = "text";
                this.eye_class = "fa fa-eye-slash"
            } else {
                this.pass_type = "password";
                this.eye_class = "fa fa-eye"
            }
        },

        togglePassConfirm() {
            if (this.pass_type_confirm === "password") {
                this.pass_type_confirm = "text";
                this.eye_class_confirm = "fa fa-eye-slash"
            } else {
                this.pass_type_confirm = "password";
                this.eye_class_confirm = "fa fa-eye"
            }
        },

        saveChanges() {
            this.input_started = true;
            if (!this.isWeakPassword && this.areValidPasswords) {
                axios.post("api/users/changePassword", {
                    old: this.old_password,
                    newPass: this.password,
                    id: this.$route.params.id,

                }).then(function(response) {
                    if(response.data === "OK")
                    {

                    }
                }).catch(function (error) {
                    alert('An error occurred!');

                });
            }
        }


    },

    computed: {
        isStrongPassword() {
            const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
            return re.test(this.password);
        },

        isMediumPassword() {
            if (this.isStrongPassword) return false;
            const re = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
            return re.test(this.password);
        },

        isWeakPassword() {
            if (!this.input_started && this.password.length === 0) return false;
            return !this.isMediumPassword && !this.isStrongPassword;
        },

        areValidPasswords() {
            if (!this.input_started) return true;
            return this.password && this.confirm_password && this.password === this.confirm_password;
        },
    }

});