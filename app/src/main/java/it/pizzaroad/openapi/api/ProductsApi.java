/*
 * Project: Pizza Road
 * File: ProductsApi
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

import java.io.File;
import java.util.List;

import it.pizzaroad.openapi.models.Product;
import it.pizzaroad.openapi.models.ProductDetails;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductsApi {
  /**
   * Create a Product
   * Creates a new instance of a &#x60;Product&#x60;.
   * @param body A new &#x60;Product&#x60; to be created. (required)
   * @return Call&lt;ProductDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("products")
  Call<ProductDetails> createProduct(
                    @retrofit2.http.Body ProductDetails body    
  );

  /**
   * Delete a Product
   * Deletes an existing &#x60;Product&#x60;.
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("products/{productId}")
  Call<Void> deleteProduct(
            @retrofit2.http.Path("productId") Integer productId            
  );

  /**
   * Delete a Product image
   * Deletes an existing &#x60;Product&#x60; image.
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("products/{productId}/image")
  Call<Void> deleteProductImage(
            @retrofit2.http.Path("productId") Integer productId            
  );

  /**
   * Get a Product
   * Gets the details of a single instance of a &#x60;Product&#x60;.
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;ProductDetails&gt;
   */
  @GET("products/{productId}")
  Call<ProductDetails> getProduct(
            @retrofit2.http.Path("productId") Integer productId            
  );

  /**
   * Get a Product image
   * Gets a &#x60;Product&#x60; image.
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;File&gt;
   */
  @GET("products/{productId}/image")
  Call<File> getProductImage(
            @retrofit2.http.Path("productId") Integer productId            
  );

  /**
   * List All products
   * Gets a list of all &#x60;Product&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Product&#x60; (optional)
   * @return Call&lt;List&lt;Product&gt;&gt;
   */
  @GET("products")
  Call<List<Product>> getProducts(
                @retrofit2.http.Header("includeDisabled") Boolean includeDisabled        
  );

  /**
   * Update a Product
   * Updates an existing &#x60;Product&#x60;.
   * @param body Updated &#x60;Product&#x60; information. (required)
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;ProductDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("products/{productId}")
  Call<ProductDetails> updateProduct(
                    @retrofit2.http.Body ProductDetails body    ,         @retrofit2.http.Path("productId") Integer productId            
  );

  /**
   * Update a Product image
   * Updates an existing &#x60;Product&#x60; image.
   * @param file  (required)
   * @param productId A unique identifier for a &#x60;Product&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @retrofit2.http.Multipart
  @PUT("products/{productId}/image")
  Call<Void> updateProductImage(
                        @retrofit2.http.Part("file\"; filename=\"file") RequestBody file,         @retrofit2.http.Path("productId") Integer productId            
  );

}
