/*
 * Project: Pizza Road
 * File: PizzasApi
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

import it.pizzaroad.openapi.models.Item;
import it.pizzaroad.openapi.models.ItemPizza;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PizzasApi {
  /**
   * Create a ItemPizza
   * Creates a new instance of a &#x60;ItemPizza&#x60;.
   * @param body A new &#x60;ItemPizza&#x60; to be created. (required)
   * @return Call&lt;ItemPizza&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("pizza/items")
  Call<ItemPizza> createItemPizza(
                    @retrofit2.http.Body ItemPizza body    
  );

  /**
   * Delete a ItemPizza
   * Deletes an existing &#x60;ItemPizza&#x60;.
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("pizza/items/{itemId}")
  Call<Void> deleteItemPizza(
            @retrofit2.http.Path("itemId") Integer itemId            
  );

  /**
   * Delete a ItemPizza image
   * Deletes an existing &#x60;ItemPizza&#x60; image.
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("pizza/items/{itemId}/image")
  Call<Void> deleteItemPizzaImage(
            @retrofit2.http.Path("itemId") Integer itemId            
  );

  /**
   * Get a ItemPizza
   * Gets the details of a single instance of a &#x60;ItemPizza&#x60;.
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @param includeInvalidPrices Include prices from disabled categories and prices equal to zero (optional, default to false)
   * @return Call&lt;ItemPizza&gt;
   */
  @GET("pizza/items/{itemId}")
  Call<ItemPizza> getItemPizza(
            @retrofit2.http.Path("itemId") Integer itemId            ,     @retrofit2.http.Query("includeInvalidPrices") Boolean includeInvalidPrices                
  );

  /**
   * Get a ItemPizza image
   * Gets a &#x60;ItemPizza&#x60; image.
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @return Call&lt;File&gt;
   */
  @GET("pizza/items/{itemId}/image")
  Call<File> getItemPizzaImage(
            @retrofit2.http.Path("itemId") Integer itemId            
  );

  /**
   * List All ItemPizzas
   * Gets a list of all &#x60;ItemPizza&#x60; entities.
   * @param includeDisabled If true, the list of all entities include enabled and disabled &#x60;Product&#x60; (optional)
   * @param categoryId Filter all products by the category if present (optional)
   * @return Call&lt;List&lt;Item&gt;&gt;
   */
  @GET("pizza/items")
  Call<List<Item>> getItemPizzas(
                @retrofit2.http.Header("includeDisabled") Boolean includeDisabled        ,             @retrofit2.http.Header("categoryId") Integer categoryId        
  );

  /**
   * Update a ItemPizza
   * Updates an existing &#x60;ItemPizza&#x60;.
   * @param body Updated &#x60;ItemPizza&#x60; information. (required)
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @return Call&lt;ItemPizza&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("pizza/items/{itemId}")
  Call<ItemPizza> updateItemPizza(
                    @retrofit2.http.Body ItemPizza body    ,         @retrofit2.http.Path("itemId") Integer itemId            
  );

  /**
   * Update a ItemPizza image
   * Updates an existing &#x60;ItemPizza&#x60; image.
   * @param file  (required)
   * @param itemId A unique identifier for a &#x60;ItemPizza&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @retrofit2.http.Multipart
  @PUT("pizza/items/{itemId}/image")
  Call<Void> updateItemPizzaImage(
                        @retrofit2.http.Part("file\"; filename=\"file") RequestBody file,         @retrofit2.http.Path("itemId") Integer itemId            
  );

}
