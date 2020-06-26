/*
 * Project: Pizza Road
 * File: OrdersApi
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

import it.pizzaroad.openapi.models.OrderDetails;
import it.pizzaroad.openapi.models.OrderRequest;
import it.pizzaroad.openapi.models.OrderSearchParameters;
import it.pizzaroad.openapi.models.OrderSearchResult;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OrdersApi {
  /**
   * Create a Order
   * Creates a new instance of a &#x60;Order&#x60;.
   * @param body A new &#x60;Order&#x60; to be created. (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("orders")
  Call<Void> createOrder(
                    @retrofit2.http.Body OrderRequest body    
  );

  /**
   * Delete a Order
   * Deletes an existing &#x60;Order&#x60;.
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("orders/{orderId}")
  Call<Void> deleteOrder(
            @retrofit2.http.Path("orderId") Integer orderId            
  );

  /**
   * Get a Order
   * Gets the details of a single instance of a &#x60;OrderDetails&#x60;.
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @return Call&lt;OrderDetails&gt;
   */
  @GET("orders/{orderId}")
  Call<OrderDetails> getOrder(
            @retrofit2.http.Path("orderId") Integer orderId            
  );

  /**
   * 
   * 
   * @param body  (required)
   * @return Call&lt;List&lt;OrderSearchResult&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("orders/search")
  Call<List<OrderSearchResult>> searchOrders(
                    @retrofit2.http.Body OrderSearchParameters body    
  );

  /**
   * Update a Order
   * Updates an existing &#x60;OrderDetails&#x60;.
   * @param body Updated &#x60;OrderDetails&#x60; information. (required)
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @return Call&lt;OrderDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("orders/{orderId}")
  Call<OrderDetails> updateOrder(
                    @retrofit2.http.Body OrderDetails body    ,         @retrofit2.http.Path("orderId") Integer orderId            
  );

}
