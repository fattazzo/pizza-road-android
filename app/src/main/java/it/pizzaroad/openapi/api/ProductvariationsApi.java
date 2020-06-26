/*
 * Project: Pizza Road
 * File: ProductvariationsApi
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

import it.pizzaroad.openapi.models.VariationProduct;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductvariationsApi {
  /**
   * Create a VariationProduct
   * Creates a new instance of a &#x60;VariationProduct&#x60;.
   * @param body A new &#x60;VariationProduct&#x60; to be created. (required)
   * @return Call&lt;VariationProduct&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("product/variations")
  Call<VariationProduct> createVariationProduct(
                    @retrofit2.http.Body VariationProduct body    
  );

  /**
   * Delete a VariationProduct
   * Deletes an existing &#x60;VariationProduct&#x60;.
   * @param variationId A unique identifier for a &#x60;VariationProduct&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("product/variations/{variationId}")
  Call<Void> deleteVariationProduct(
            @retrofit2.http.Path("variationId") Integer variationId            
  );

  /**
   * Get a VariationProduct
   * Gets the details of a single instance of a &#x60;VariationProduct&#x60;.
   * @param variationId A unique identifier for a &#x60;VariationProduct&#x60;. (required)
   * @return Call&lt;VariationProduct&gt;
   */
  @GET("product/variations/{variationId}")
  Call<VariationProduct> getVariationProduct(
            @retrofit2.http.Path("variationId") Integer variationId            
  );

  /**
   * List All VariationProducts
   * Gets a list of all &#x60;VariationProduct&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;VariationProduct&#x60; (optional)
   * @return Call&lt;List&lt;VariationProduct&gt;&gt;
   */
  @GET("product/variations")
  Call<List<VariationProduct>> getVariationProducts(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Update a VariationProduct
   * Updates an existing &#x60;VariationProduct&#x60;.
   * @param body Updated &#x60;VariationProduct&#x60; information. (required)
   * @param variationId A unique identifier for a &#x60;VariationProduct&#x60;. (required)
   * @return Call&lt;VariationProduct&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("product/variations/{variationId}")
  Call<VariationProduct> updateVariationProduct(
                    @retrofit2.http.Body VariationProduct body    ,         @retrofit2.http.Path("variationId") Integer variationId            
  );

}
