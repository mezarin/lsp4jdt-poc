/*******************************************************************************
* Copyright (c) 2020 Red Hat Inc. and others.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License v. 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
* which is available at https://www.apache.org/licenses/LICENSE-2.0.
*
* SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package org.eclipse.lsp4jdt.jdt.core.operations.java.hover;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4jdt.jdt.core.java.AbstractJavaFeatureDefinition;
import org.eclipse.lsp4jdt.jdt.core.java.hover.IJavaHoverParticipant;
import org.eclipse.lsp4jdt.jdt.core.java.hover.JavaHoverContext;

/**
 * Wrapper class around java participants {@link IJavaHoverParticipant}.
 */
public class JavaHoverDefinition extends AbstractJavaFeatureDefinition<IJavaHoverParticipant> implements IJavaHoverParticipant {
    private static final Logger LOGGER = Logger.getLogger(JavaHoverDefinition.class.getName());

    public JavaHoverDefinition(IConfigurationElement element) {
        super(element);
    }

    // -------------- Hover

    @Override
    public boolean isAdaptedForHover(JavaHoverContext context, IProgressMonitor monitor) {
        try {
            return getParticipant().isAdaptedForHover(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling isAdaptedForHover", e);
            return false;
        }
    }

    @Override
    public void beginHover(JavaHoverContext context, IProgressMonitor monitor) {
        try {
            getParticipant().beginHover(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling beginHover", e);
        }
    }

    @Override
    public Hover collectHover(JavaHoverContext context, IProgressMonitor monitor) {
        try {
            return getParticipant().collectHover(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while collecting hover", e);
            return null;
        }
    }

    @Override
    public void endHover(JavaHoverContext context, IProgressMonitor monitor) {
        try {
            getParticipant().endHover(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling endHover", e);
        }
    }

}
