/*
 * Project: Pizza Road
 * File: ProductcategoriesApi
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

import it.pizzaroad.openapi.models.ProductCategory;
import it.pizzaroad.openapi.models.ProductCategoryDetails;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductcategoriesApi {
  /**
   * Create a ProductCategory
   * Creates a new instance of a &#x60;ProductCategory&#x60;.
   * @param body A new &#x60;ProductCategory&#x60; to be created. (required)
   * @return Call&lt;ProductCategoryDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("productcategories")
  Call<ProductCategoryDetails> createProductCategory(
                    @retrofit2.http.Body ProductCategoryDetails body    
  );

  /**
   * Delete a ProductCategory
   * Deletes an existing &#x60;ProductCategory&#x60;.
   * @param productcategoryId A unique identifier for a &#x60;ProductCategory&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("productcategories/{productcategoryId}")
  Call<Void> deleteProductCategory(
            @retrofit2.http.Path("productcategoryId") Integer productcategoryId            
  );

  /**
   * List All productcategories
   * Gets a list of all &#x60;ProductCategory&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;ProductCategory&#x60; (optional)
   * @return Call&lt;List&lt;ProductCategory&gt;&gt;
   */
  @GET("productcategories")
  Call<List<ProductCategory>> getProductCategories(
        @retrofit2.http.Query("includeDisabled") Boolean includeDisabled                
  );

  /**
   * Get a ProductCategory
   * Gets the details of a single instance of a &#x60;ProductCategory&#x60;.
   * @param productcategoryId A unique identifier for a &#x60;ProductCategory&#x60;. (required)
   * @return Call&lt;ProductCategoryDetails&gt;
   */
  @GET("productcategories/{productcategoryId}")
  Call<ProductCategoryDetails> getProductCategory(
            @retrofit2.http.Path("productcategoryId") Integer productcategoryId            
  );

  /**
   * Update a ProductCategory
   * Updates an existing &#x60;ProductCategory&#x60;.
   * @param body Updated &#x60;ProductCategory&#x60; information. (required)
   * @param productcategoryId A unique identifier for a &#x60;ProductCategory&#x60;. (required)
   * @return Call&lt;ProductCategoryDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("productcategories/{productcategoryId}")
  Call<ProductCategoryDetails> updateProductCategory(
                    @retrofit2.http.Body ProductCategoryDetails body    ,         @retrofit2.http.Path("productcategoryId") Integer productcategoryId            
  );

}
