/*
 * Project: Pizza Road
 * File: SessionApi
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

import it.pizzaroad.openapi.models.Session;
import it.pizzaroad.openapi.models.UserLogin;
import it.pizzaroad.openapi.models.UserSocialLogin;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SessionApi {
  /**
   * Create a session
   * Create a &#x60;Session&#x60; information
   * @param body Login user information (required)
   * @return Call&lt;Session&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("public/session")
  Call<Session> login(
                    @retrofit2.http.Body UserLogin body    
  );

  /**
   * Create a session
   * Create a &#x60;Session&#x60; information
   * @param body Login user information (required)
   * @return Call&lt;Session&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("public/session/social")
  Call<Session> loginSocial(
                    @retrofit2.http.Body UserSocialLogin body    
  );

  /**
   * Create new session
   * Create new &#x60;Session&#x60; with a valid access token
   * @param refreshToken Refresh token used for retrieve new updated session information (required)
   * @return Call&lt;Session&gt;
   */
  @POST("public/session/refresh/{refreshToken}")
  Call<Session> refreshToken(
            @retrofit2.http.Path("refreshToken") String refreshToken            
  );

}
