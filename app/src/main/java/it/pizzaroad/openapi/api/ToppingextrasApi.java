/*
 * Project: Pizza Road
 * File: ToppingextrasApi
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

import it.pizzaroad.openapi.models.ToppingExtra;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface ToppingextrasApi {
  /**
   * Get a ToppingExtra
   * Gets the details of a single instance of a &#x60;ToppingExtra&#x60;.
   * @param toppingextraId A unique identifier for a &#x60;ToppingExtra&#x60;. (required)
   * @return Call&lt;ToppingExtra&gt;
   */
  @GET("toppingextras/{toppingextraId}")
  Call<ToppingExtra> getToppingExtra(
            @retrofit2.http.Path("toppingextraId") Integer toppingextraId            
  );

  /**
   * List All ToppingExtras
   * Gets a list of all &#x60;ToppingExtra&#x60; entities.
   * @param doughId Work with &#x60;sizeId&#x60; parameter (optional)
   * @param sizeId Work with &#x60;doughId&#x60; parameter (optional)
   * @return Call&lt;List&lt;ToppingExtra&gt;&gt;
   */
  @GET("toppingextras")
  Call<List<ToppingExtra>> getToppingExtras(
                @retrofit2.http.Header("doughId") Integer doughId        ,             @retrofit2.http.Header("sizeId") Integer sizeId        
  );

  /**
   * Update a ToppingExtra
   * Updates an existing &#x60;ToppingExtra&#x60;.
   * @param body Updated &#x60;ToppingExtra&#x60; information. (required)
   * @param toppingextraId A unique identifier for a &#x60;ToppingExtra&#x60;. (required)
   * @return Call&lt;ToppingExtra&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("toppingextras/{toppingextraId}")
  Call<ToppingExtra> updateToppingExtra(
                    @retrofit2.http.Body ToppingExtra body    ,         @retrofit2.http.Path("toppingextraId") Integer toppingextraId            
  );

  /**
   * Update All ToppingExtras
   * Updates existing &#x60;ToppingExtra&#x60;.
   * @param body  (required)
   * @return Call&lt;List&lt;ToppingExtra&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("toppingextras")
  Call<List<ToppingExtra>> updateToppingExtras(
                    @retrofit2.http.Body List<ToppingExtra> body    
  );

}
