/*
 * Project: Pizza Road
 * File: CollectionFormats
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

package it.pizzaroad.openapi;

import java.util.Arrays;
import java.util.List;

public class CollectionFormats {

    public static class CSVParams {

        protected List<String> params;

        public CSVParams() {
        }

        public CSVParams(List<String> params) {
            this.params = params;
        }

        public CSVParams(String... params) {
            this.params = Arrays.asList(params);
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }

        @Override
        public String toString() {
            return StringUtil.join(params.toArray(new String[0]), ",");
        }

    }

    public static class SSVParams extends CSVParams {

        public SSVParams() {
        }

        public SSVParams(List<String> params) {
            super(params);
        }

        public SSVParams(String... params) {
            super(params);
        }

        @Override
        public String toString() {
            return StringUtil.join(params.toArray(new String[0]), " ");
        }
    }

    public static class TSVParams extends CSVParams {

        public TSVParams() {
        }

        public TSVParams(List<String> params) {
            super(params);
        }

        public TSVParams(String... params) {
            super(params);
        }

        @Override
        public String toString() {
            return StringUtil.join( params.toArray(new String[0]), "\t");
        }
    }

    public static class PIPESParams extends CSVParams {

        public PIPESParams() {
        }

        public PIPESParams(List<String> params) {
            super(params);
        }

        public PIPESParams(String... params) {
            super(params);
        }

        @Override
        public String toString() {
            return StringUtil.join(params.toArray(new String[0]), "|");
        }
    }

}
