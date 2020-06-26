/*
 * Project: Pizza Road
 * File: OrderslinesApi
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

import it.pizzaroad.openapi.models.OrderLine;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OrderslinesApi {
  /**
   * Create a OrderLine
   * Creates a new instance of a &#x60;OrderLine&#x60;.
   * @param body A new &#x60;OrderLine&#x60; to be created. (required)
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("orders/{orderId}/lines")
  Call<Void> createOrderLine(
                    @retrofit2.http.Body OrderLine body    ,         @retrofit2.http.Path("orderId") Integer orderId            
  );

  /**
   * Delete a OrderLine
   * Deletes an existing &#x60;OrderLine&#x60;.
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @param orderlineId A unique identifier for a &#x60;OrderLine&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("orders/{orderId}/lines/{orderlineId}")
  Call<Void> deleteOrderLine(
            @retrofit2.http.Path("orderId") Integer orderId            ,         @retrofit2.http.Path("orderlineId") Integer orderlineId            
  );

  /**
   * Get a OrderLine
   * Gets the details of a single instance of a &#x60;OrderLine&#x60;.
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @param orderlineId A unique identifier for a &#x60;OrderLine&#x60;. (required)
   * @return Call&lt;OrderLine&gt;
   */
  @GET("orders/{orderId}/lines/{orderlineId}")
  Call<OrderLine> getOrderLine(
            @retrofit2.http.Path("orderId") Integer orderId            ,         @retrofit2.http.Path("orderlineId") Integer orderlineId            
  );

  /**
   * List All OrderLines
   * Gets a list of all &#x60;OrderLine&#x60; entities.
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @return Call&lt;List&lt;OrderLine&gt;&gt;
   */
  @GET("orders/{orderId}/lines")
  Call<List<OrderLine>> getOrderLines(
            @retrofit2.http.Path("orderId") Integer orderId            
  );

  /**
   * Update a OrderLine
   * Updates an existing &#x60;OrderLine&#x60;.
   * @param body Updated &#x60;OrderLine&#x60; information. (required)
   * @param orderId A unique identifier for a &#x60;Order&#x60;. (required)
   * @param orderlineId A unique identifier for a &#x60;OrderLine&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("orders/{orderId}/lines/{orderlineId}")
  Call<Void> updateOrderLine(
                    @retrofit2.http.Body OrderLine body    ,         @retrofit2.http.Path("orderId") Integer orderId            ,         @retrofit2.http.Path("orderlineId") Integer orderlineId            
  );

}
