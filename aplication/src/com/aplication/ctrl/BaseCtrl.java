package com.aplication.ctrl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;


/**
 * class: BaseCtrl
 * Responsibility: Abstract class to instace base caracteristics of controllers of application.
 * autor: lg
 */
public abstract class BaseCtrl extends GenericForwardComposer {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		//Method to execute actions after to render a component
		super.doAfterCompose(comp);
	}

}
