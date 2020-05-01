/*
 * Project: Pizza Road
 * File: UsersApi
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

import java.util.List;

import it.pizzaroad.openapi.models.User;
import it.pizzaroad.openapi.models.UserDetails;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsersApi {
  /**
   * Create a User
   * Creates a new instance of a &#x60;User&#x60;. Only &#x27;WORKER&#x27; type can be created
   * @param body A new &#x60;User&#x60; to be created. (required)
   * @return Call&lt;UserDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("users")
  Call<UserDetails> createUser(
                    @retrofit2.http.Body UserDetails body    
  );

  /**
   * Delete a User
   * Deletes an existing &#x60;User&#x60;.
   * @param userName A unique identifier for a &#x60;User&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("users/{userName}")
  Call<Void> deleteUser(
            @retrofit2.http.Path("userName") String userName            
  );

  /**
   * Get a User
   * Gets the details of a single instance of a &#x60;User&#x60;.
   * @param userName A unique identifier for a &#x60;User&#x60;. (required)
   * @return Call&lt;UserDetails&gt;
   */
  @GET("users/{userName}")
  Call<UserDetails> getUser(
            @retrofit2.http.Path("userName") String userName            
  );

  /**
   * List All users
   * Gets a list of all &#x60;User&#x60; entities.
   * @return Call&lt;List&lt;User&gt;&gt;
   */
  @GET("users")
  Call<List<User>> getUsers();
    

  /**
   * Update a User
   * Updates an existing &#x60;User&#x60;.
   * @param body Updated &#x60;User&#x60; information. (required)
   * @param userName A unique identifier for a &#x60;User&#x60;. (required)
   * @return Call&lt;UserDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("users/{userName}")
  Call<UserDetails> updateUser(
                    @retrofit2.http.Body UserDetails body    ,         @retrofit2.http.Path("userName") String userName            
  );

}
