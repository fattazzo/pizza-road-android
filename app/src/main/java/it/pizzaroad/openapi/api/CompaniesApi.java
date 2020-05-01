/*
 * Project: Pizza Road
 * File: CompaniesApi
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

import it.pizzaroad.openapi.models.Company;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface CompaniesApi {
  /**
   * Get a Company
   * Gets the details of a single instance of a &#x60;Company&#x60;.
   * @return Call&lt;Company&gt;
   */
  @GET("company")
  Call<Company> getCompany();
    

  /**
   * Company logo
   * Get a &#x60;Company&#x60; logo image
   * @return Call&lt;File&gt;
   */
  @GET("company/logo")
  Call<File> getLogo();
    

  /**
   * Update a Company
   * Updates an existing &#x60;Company&#x60;.
   * @param body Updated &#x60;Company&#x60; information. (required)
   * @return Call&lt;Company&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("company")
  Call<Company> updateCompany(
                    @retrofit2.http.Body Company body    
  );

  /**
   * Update a Company logo
   * Updates an existing &#x60;Company&#x60; logo.
   * @param file  (required)
   * @return Call&lt;Void&gt;
   */
  @retrofit2.http.Multipart
  @PUT("company/logo")
  Call<Void> updateLogo(
                        @retrofit2.http.Part("file\"; filename=\"file") RequestBody file
  );

}
