/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.async.loader;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.SwitchType;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialSwitch;

public class DefaultSwitchLoader implements AsyncDisplayLoader<Boolean> {

    private MaterialSwitch materialSwitch;
    private MaterialLoader loader;

    protected DefaultSwitchLoader() {
    }

    public DefaultSwitchLoader(MaterialSwitch materialSwitch) {
        this.materialSwitch = materialSwitch;

        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(materialSwitch);
    }

    @Override
    public void loading() {
        materialSwitch.setEnabled(false);
        materialSwitch.getLabel().setVisibility(Style.Visibility.HIDDEN);
        materialSwitch.clearErrorText();
        loader.show();
    }

    @Override
    public void success(Boolean result) {
        materialSwitch.setValue(result);
    }

    @Override
    public void failure(String error) {
        if (materialSwitch.getType().equals(SwitchType.DEFAULT)) {
            materialSwitch.setErrorText(error);
        }
    }

    @Override
    public void finalize() {
        materialSwitch.setEnabled(true);
        materialSwitch.getLabel().setVisibility(Style.Visibility.VISIBLE);
        loader.hide();
    }
}
