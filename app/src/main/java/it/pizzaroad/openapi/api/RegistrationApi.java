/*
 * Project: Pizza Road
 * File: RegistrationApi
 *
 * Created by fattazzo
 * Copyright © 2020 Gianluca Fattarsi. All rights reserved.
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.pizzaroad.openapi.api;

import it.pizzaroad.openapi.models.UserRegistrationInfo;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegistrationApi {
  /**
   * Perform a confirm for a User registration
   * Complete &#x60;User&#x60; registration and change status to &#x27;Active&#x27;
   * @param username Username of &#x60;User&#x60; entity (required)
   * @param registrationToken Valid token required to confirm registration (required)
   * @return Call&lt;String&gt;
   */
  @POST("public/confirm-registration/{username}")
  Call<String> confirmRegistration(
            @retrofit2.http.Path("username") String username            ,     @retrofit2.http.Query("registrationToken") String registrationToken                
  );

  /**
   * Create a customer User
   * Create a new instance of &#x60;User&#x60; with type &#x27;CUSTOMER&#x27; and status \&quot;TO_CONFIRM\&quot;
   * @param body  (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("public/registration-customer")
  Call<Void> createCustomer(
                    @retrofit2.http.Body UserRegistrationInfo body    
  );

  /**
   * Resend email
   * Resend email with activation code to user
   * @param username username of &#x60;User&#x60; (required)
   * @return Call&lt;Void&gt;
   */
  @POST("public/resend-confirm-registration-mail")
  Call<Void> resendConfirmRegistrationMail(
        @retrofit2.http.Query("username") String username                
  );

}
