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

import com.github.davityle.ngprocessor.attrcompiler.TokenType;

/**
 * Created by tyler on 2/6/15.
 */
public class BinaryOperatorGetter implements Getter {

    protected final Getter leftSide;
    protected final Getter rightSide;
    protected final TokenType.BinaryOperator operator;

    public BinaryOperatorGetter(Getter leftSide, Getter rightSide, TokenType.BinaryOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    public static  BinaryOperatorGetter getOperator(Getter leftSide, Getter rightSide, TokenType.BinaryOperator operator){
        return new BinaryOperatorGetter(leftSide, rightSide, operator);
    }


    @Override
    public String getSource() {
        return '(' + leftSide.getSource() + operator.toString() + rightSide.getSource() + ')';
    }
}
