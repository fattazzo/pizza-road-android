/*
 * Project: Pizza Road
 * File: VariationsApi
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

import it.pizzaroad.openapi.models.Topping;
import it.pizzaroad.openapi.models.VariationDough;
import it.pizzaroad.openapi.models.VariationSize;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface VariationsApi {
  /**
   * Create a Dough
   * Creates a new instance of a &#x60;Dough&#x60;.
   * @param body A new &#x60;Dough&#x60; to be created. (required)
   * @return Call&lt;VariationDough&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("variations/doughs")
  Call<VariationDough> createDough(
                    @retrofit2.http.Body VariationDough body    
  );

  /**
   * Create a Size
   * Creates a new instance of a &#x60;Size&#x60;.
   * @param body A new &#x60;Size&#x60; to be created. (required)
   * @return Call&lt;VariationSize&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("variations/sizes")
  Call<VariationSize> createSize(
                    @retrofit2.http.Body VariationSize body    
  );

  /**
   * Create a Topping
   * Creates a new instance of a &#x60;Topping&#x60;.
   * @param body A new &#x60;Topping&#x60; to be created. (required)
   * @return Call&lt;Topping&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("variations/toppings")
  Call<Topping> createTopping(
                    @retrofit2.http.Body Topping body    
  );

  /**
   * Delete a Dough
   * Deletes an existing &#x60;Dough&#x60;.
   * @param doughId A unique identifier for a &#x60;Dough&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("variations/doughs/{doughId}")
  Call<Void> deleteDough(
            @retrofit2.http.Path("doughId") Integer doughId            
  );

  /**
   * Delete a Size
   * Deletes an existing &#x60;Size&#x60;.
   * @param sizeId A unique identifier for a &#x60;Size&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("variations/sizes/{sizeId}")
  Call<Void> deleteSize(
            @retrofit2.http.Path("sizeId") Integer sizeId            
  );

  /**
   * Delete a Topping
   * Deletes an existing &#x60;Topping&#x60;.
   * @param toppingId A unique identifier for a &#x60;Topping&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("variations/toppings/{toppingId}")
  Call<Void> deleteTopping(
            @retrofit2.http.Path("toppingId") Integer toppingId            
  );

  /**
   * Get a Dough
   * Gets the details of a single instance of a &#x60;Dough&#x60;.
   * @param doughId A unique identifier for a &#x60;Dough&#x60;. (required)
   * @return Call&lt;VariationDough&gt;
   */
  @GET("variations/doughs/{doughId}")
  Call<VariationDough> getDough(
            @retrofit2.http.Path("doughId") Integer doughId            
  );

  /**
   * List All doughs
   * Gets a list of all &#x60;Dough&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Dough&#x60; (optional)
   * @return Call&lt;List&lt;VariationDough&gt;&gt;
   */
  @GET("variations/doughs")
  Call<List<VariationDough>> getDoughs(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Get a Size
   * Gets the details of a single instance of a &#x60;Size&#x60;.
   * @param sizeId A unique identifier for a &#x60;Size&#x60;. (required)
   * @return Call&lt;VariationSize&gt;
   */
  @GET("variations/sizes/{sizeId}")
  Call<VariationSize> getSize(
            @retrofit2.http.Path("sizeId") Integer sizeId            
  );

  /**
   * List All sizes
   * Gets a list of all &#x60;Size&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Size&#x60; (optional)
   * @return Call&lt;List&lt;VariationSize&gt;&gt;
   */
  @GET("variations/sizes")
  Call<List<VariationSize>> getSizes(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Get a Topping
   * Gets the details of a single instance of a &#x60;Topping&#x60;.
   * @param toppingId A unique identifier for a &#x60;Topping&#x60;. (required)
   * @return Call&lt;Topping&gt;
   */
  @GET("variations/toppings/{toppingId}")
  Call<Topping> getTopping(
            @retrofit2.http.Path("toppingId") Integer toppingId            
  );

  /**
   * List All toppings
   * Gets a list of all &#x60;Topping&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Topping&#x60; (optional)
   * @return Call&lt;List&lt;Topping&gt;&gt;
   */
  @GET("variations/toppings")
  Call<List<Topping>> getToppings(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Update a Dough
   * Updates an existing &#x60;Dough&#x60;.
   * @param body Updated &#x60;Dough&#x60; information. (required)
   * @param doughId A unique identifier for a &#x60;Dough&#x60;. (required)
   * @return Call&lt;VariationDough&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("variations/doughs/{doughId}")
  Call<VariationDough> updateDough(
                    @retrofit2.http.Body VariationDough body    ,         @retrofit2.http.Path("doughId") Integer doughId            
  );

  /**
   * Update a Size
   * Updates an existing &#x60;Size&#x60;.
   * @param body Updated &#x60;Size&#x60; information. (required)
   * @param sizeId A unique identifier for a &#x60;Size&#x60;. (required)
   * @return Call&lt;VariationSize&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("variations/sizes/{sizeId}")
  Call<VariationSize> updateSize(
                    @retrofit2.http.Body VariationSize body    ,         @retrofit2.http.Path("sizeId") Integer sizeId            
  );

  /**
   * Update a Topping
   * Updates an existing &#x60;Topping&#x60;.
   * @param body Updated &#x60;Topping&#x60; information. (required)
   * @param toppingId A unique identifier for a &#x60;Topping&#x60;. (required)
   * @return Call&lt;Topping&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("variations/toppings/{toppingId}")
  Call<Topping> updateTopping(
                    @retrofit2.http.Body Topping body    ,         @retrofit2.http.Path("toppingId") Integer toppingId            
  );

}
