/*
 * Copyright 2015 Tyler Davis
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.davityle.ngprocessor.attrcompiler.getters;

/**
 * Created by tyler on 2/2/15.
 */
public class TernaryGetter implements Getter {

    private final Getter booleanGetter;
    private final Getter valTrue, valFalse;

    public TernaryGetter(Getter booleanGetter, Getter valTrue, Getter valFalse) {
        this.booleanGetter = booleanGetter;
        this.valTrue = valTrue;
        this.valFalse = valFalse;
    }

    @Override
    public String getSource() {
        return booleanGetter.getSource() + '?' + valTrue.getSource() + ':' + valFalse.getSource();
    }
}
