/*
 * Project: Pizza Road
 * File: PizzacategoriesApi
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

import it.pizzaroad.openapi.models.Category;
import it.pizzaroad.openapi.models.CategoryDetails;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PizzacategoriesApi {
  /**
   * Create a pizza Category
   * Creates a new instance of a pizza &#x60;Category&#x60;.
   * @param body A new pizza &#x60;Category&#x60; to be created. (required)
   * @return Call&lt;CategoryDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("pizza/categories")
  Call<CategoryDetails> createPizzaCategory(
                    @retrofit2.http.Body CategoryDetails body    
  );

  /**
   * Delete a pizza Category
   * Deletes an existing pizza &#x60;Category&#x60;.
   * @param categoryId A unique identifier for a &#x60;Category&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("pizza/categories/{categoryId}")
  Call<Void> deletePizzaCategory(
            @retrofit2.http.Path("categoryId") Integer categoryId            
  );

  /**
   * List All pizza categories
   * Gets a list of all pizza &#x60;Category&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Category&#x60; (optional)
   * @return Call&lt;List&lt;Category&gt;&gt;
   */
  @GET("pizza/categories")
  Call<List<Category>> getPizzaCategories(
                @retrofit2.http.Header("includeDisabled") Boolean includeDisabled        
  );

  /**
   * Get a pizza Category
   * Gets the details of a single instance of a pizza &#x60;Category&#x60;.
   * @param categoryId A unique identifier for a &#x60;Category&#x60;. (required)
   * @return Call&lt;CategoryDetails&gt;
   */
  @GET("pizza/categories/{categoryId}")
  Call<CategoryDetails> getPizzaCategory(
            @retrofit2.http.Path("categoryId") Integer categoryId            
  );

  /**
   * Update a pizza Category
   * Updates an existing pizza &#x60;Category&#x60;.
   * @param body Updated &#x60;Category&#x60; information. (required)
   * @param categoryId A unique identifier for a &#x60;Category&#x60;. (required)
   * @return Call&lt;CategoryDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("pizza/categories/{categoryId}")
  Call<CategoryDetails> updatePizzaCategory(
                    @retrofit2.http.Body CategoryDetails body    ,         @retrofit2.http.Path("categoryId") Integer categoryId            
  );

}
