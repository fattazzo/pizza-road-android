/*
 * Project: Pizza Road
 * File: GroupsApi
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

import it.pizzaroad.openapi.models.Group;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GroupsApi {
  /**
   * Create a Group
   * Creates a new instance of a &#x60;Group&#x60;.
   * @param body A new &#x60;Group&#x60; to be created. (required)
   * @return Call&lt;Group&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("groups")
  Call<Group> createGroup(
                    @retrofit2.http.Body Group body    
  );

  /**
   * Delete a Group
   * Deletes an existing &#x60;Group&#x60;.
   * @param groupId A unique identifier for a &#x60;Company&#x60;. (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("groups/{groupId}")
  Call<Void> deleteGroup(
            @retrofit2.http.Path("groupId") Integer groupId            
  );

  /**
   * Get a Group
   * Gets the details of a single instance of a &#x60;Group&#x60;.
   * @param groupId A unique identifier for a &#x60;Company&#x60;. (required)
   * @return Call&lt;Group&gt;
   */
  @GET("groups/{groupId}")
  Call<Group> getGroup(
            @retrofit2.http.Path("groupId") Integer groupId            
  );

  /**
   * List All groups
   * Gets a list of all &#x60;Group&#x60; entities.
   * @return Call&lt;List&lt;Group&gt;&gt;
   */
  @GET("groups")
  Call<List<Group>> getGroups();
    

  /**
   * Update a Group
   * Updates an existing &#x60;Group&#x60;.
   * @param body Updated &#x60;Group&#x60; information. (required)
   * @param groupId A unique identifier for a &#x60;Company&#x60;. (required)
   * @return Call&lt;Group&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("groups/{groupId}")
  Call<Group> updateGroup(
                    @retrofit2.http.Body Group body    ,         @retrofit2.http.Path("groupId") Integer groupId            
  );

}
