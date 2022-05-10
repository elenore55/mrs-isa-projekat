Vue.component("delete-profile-message", {
   template: `
   <div class="w-100">
     <client-navbar></client-navbar>
     <section>
            <div class="container mt-5 pt-5">
                <div class="row">
                    <div class="col-lg-4 col-sm-8 col-md-6 m-auto">

                        <div class="card" style=" margin-top: 50px">
                            <div class="card-body text-center bg-warning">
                                <h3>
                                    Your request has been successfully submitted.
                                </h3>
                                <h3>
                                    We will send you a
                                    reply to your email address shortly.
                                </h3>
                                <h3>
                                    Thank you for your feedback!
                                </h3>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
     </section>
   </div>
   `,

});