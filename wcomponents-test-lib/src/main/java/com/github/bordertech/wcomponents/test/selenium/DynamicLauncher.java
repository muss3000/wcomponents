package com.github.bordertech.wcomponents.test.selenium;

import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.registry.UIRegistry;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;

/**
 * This class extends PlainLauncher to allow the launcher UI to be determined
 * (and reconfigured) at runtime via the setUI method.
 *
 * @author Joshua Barclay
 * @since 1.2.0
 */
public class DynamicLauncher extends SeleniumLauncher {

	/**
	 * The name of the class to launch for the UI.
	 */
	private String uiRegistryKey = null;

	/**
	 * <p>
	 * Set the instance for the UI to launch.</p>
	 * <p>
	 * A null value will revert to the Default PlainLauncher parameter behavior.
	 * </p>
	 *
	 * @param componentToLaunch the WComponent to launch, or null for default
	 * behavior.
	 */
	public void setComponentToLaunch(final WComponent componentToLaunch) {
		if (componentToLaunch == null) {
			uiRegistryKey = null;
		} else {
			uiRegistryKey = UUID.randomUUID().toString();
			UIRegistry.getInstance().register(uiRegistryKey, componentToLaunch);
		}
	}

	/**
	 * Override to return the UI Registry Key - the class name is not needed
	 * because the instance has been added to the UIRegistry in advance.
	 *
	 * @return the UI Registry Key, or the super class value if the key is
	 * blank.
	 */
	@Override
	protected String getComponentToLaunchClassName() {
		if (StringUtils.isBlank(uiRegistryKey)) {
			return super.getComponentToLaunchClassName();
		} else {
			return uiRegistryKey;
		}
	}
}
