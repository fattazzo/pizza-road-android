/*
 * Project: Pizza Road
 * File: ShippingmethodsApi
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

import it.pizzaroad.openapi.models.ShippingMethod;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ShippingmethodsApi {
  /**
   * Create a ShippingMethod
   * Creates a new instance of a &#x60;ShippingMethod&#x60;.
   * @param body A new &#x60;ShippingMethod&#x60; to be created. (required)
   * @return Call&lt;ShippingMethod&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("shippingmethods")
  Call<ShippingMethod> createShippingMethod(
                    @retrofit2.http.Body ShippingMethod body    
  );

  /**
   * Delete a ShippingMethod
   * Deletes an existing &#x60;ShippingMethod&#x60;.
   * @param shippingmethodId A unique identifier for a &#x60;ShippingMethod&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("shippingmethods/{shippingmethodId}")
  Call<Void> deleteShippingMethod(
            @retrofit2.http.Path("shippingmethodId") Integer shippingmethodId            
  );

  /**
   * Get a ShippingMethod
   * Gets the details of a single instance of a &#x60;ShippingMethod&#x60;.
   * @param shippingmethodId A unique identifier for a &#x60;ShippingMethod&#x60;. (required)
   * @return Call&lt;ShippingMethod&gt;
   */
  @GET("shippingmethods/{shippingmethodId}")
  Call<ShippingMethod> getShippingMethod(
            @retrofit2.http.Path("shippingmethodId") Integer shippingmethodId            
  );

  /**
   * List All shippingmethods
   * Gets a list of all &#x60;ShippingMethod&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Size&#x60; (optional, default to false)
   * @return Call&lt;List&lt;ShippingMethod&gt;&gt;
   */
  @GET("shippingmethods")
  Call<List<ShippingMethod>> getShippingMethods(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Update a ShippingMethod
   * Updates an existing &#x60;ShippingMethod&#x60;.
   * @param body Updated &#x60;ShippingMethod&#x60; information. (required)
   * @param shippingmethodId A unique identifier for a &#x60;ShippingMethod&#x60;. (required)
   * @return Call&lt;ShippingMethod&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("shippingmethods/{shippingmethodId}")
  Call<ShippingMethod> updateShippingMethod(
                    @retrofit2.http.Body ShippingMethod body    ,         @retrofit2.http.Path("shippingmethodId") Integer shippingmethodId            
  );

}
