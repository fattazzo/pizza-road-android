/*
 * Project: Pizza Road
 * File: BranchesApi
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

import it.pizzaroad.openapi.models.Branch;
import it.pizzaroad.openapi.models.BranchDetails;
import it.pizzaroad.openapi.models.ShippingZone;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BranchesApi {
  /**
   * Create a Branch for company
   * Creates a new instance of a &#x60;Branch&#x60;.
   * @param body A new &#x60;Branch&#x60; to be created. (required)
   * @return Call&lt;BranchDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("branches")
  Call<BranchDetails> createBranch(
                    @retrofit2.http.Body BranchDetails body    
  );

  /**
   * Delete a Branch
   * Deletes an existing &#x60;Branch&#x60;.
   * @param branchId A unique identifier for a &#x60;Branch&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("branches/{branchId}")
  Call<Void> deleteBranch(
            @retrofit2.http.Path("branchId") Integer branchId            
  );

  /**
   * Get a Branch
   * Gets the details of a single instance of a &#x60;Branch&#x60;.
   * @param branchId A unique identifier for a &#x60;Branch&#x60;. (required)
   * @return Call&lt;BranchDetails&gt;
   */
  @GET("branches/{branchId}")
  Call<BranchDetails> getBranch(
            @retrofit2.http.Path("branchId") Integer branchId            
  );

  /**
   * List All branches of company
   * Gets a list of all &#x60;Branch&#x60; entities.
   * @return Call&lt;List&lt;Branch&gt;&gt;
   */
  @GET("branches")
  Call<List<Branch>> getBranches();
    

  /**
   * List All shippingzones
   * Gets a list of all &#x60;ShippingZone&#x60; entities.
   * @param branchId A unique identifier for a &#x60;Branch&#x60;. (required)
   * @return Call&lt;List&lt;ShippingZone&gt;&gt;
   */
  @GET("branches/{branchId}/shippingzones")
  Call<List<ShippingZone>> getShippingZones(
            @retrofit2.http.Path("branchId") Integer branchId            
  );

  /**
   * Update a Branch
   * Updates an existing &#x60;Branch&#x60;.
   * @param body Updated &#x60;Branch&#x60; information. (required)
   * @param branchId A unique identifier for a &#x60;Branch&#x60;. (required)
   * @return Call&lt;BranchDetails&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("branches/{branchId}")
  Call<BranchDetails> updateBranch(
                    @retrofit2.http.Body BranchDetails body    ,         @retrofit2.http.Path("branchId") Integer branchId            
  );

}
