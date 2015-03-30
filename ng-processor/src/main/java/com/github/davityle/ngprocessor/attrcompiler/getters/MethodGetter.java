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


import java.util.List;

/**
* Created by davityle on 1/24/15.
*/
public class MethodGetter implements Getter {

    private final String methodName;
    private final List<Getter> parameters;
    private String parametersSource;

    public MethodGetter(String source, List<Getter> parameters) {
        this.methodName = source;
        this.parameters = parameters;
    }

    @Override
    public String getSource() {
        if(parametersSource == null){
            StringBuilder parametersSourceBuilder = new StringBuilder("(");
            for(int index = 0; index < parameters.size(); index++){
                Getter getter = parameters.get(index);
                parametersSourceBuilder.append(getter.getSource());
                if(index != parameters.size() -1)
                    parametersSourceBuilder.append(",");
            }
            parametersSourceBuilder.append(")");
            parametersSource = parametersSourceBuilder.toString();
        }
        return methodName + parametersSource;

    }
}
