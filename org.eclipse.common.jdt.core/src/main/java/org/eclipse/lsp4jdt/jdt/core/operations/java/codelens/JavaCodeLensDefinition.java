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
package org.eclipse.lsp4jdt.jdt.core.operations.java.codelens;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.lsp4j.CodeLens;
import org.eclipse.lsp4jdt.jdt.core.java.AbstractJavaFeatureDefinition;
import org.eclipse.lsp4jdt.jdt.core.java.codelens.IJavaCodeLensParticipant;
import org.eclipse.lsp4jdt.jdt.core.java.codelens.JavaCodeLensContext;

/**
 * Wrapper class around java participants {@link IJavaCodeLensParticipant}.
 */
public class JavaCodeLensDefinition extends AbstractJavaFeatureDefinition<IJavaCodeLensParticipant> implements IJavaCodeLensParticipant {
    private static final Logger LOGGER = Logger.getLogger(JavaCodeLensDefinition.class.getName());

    public JavaCodeLensDefinition(IConfigurationElement element) {
        super(element);
    }

    // -------------- CodeLens

    @Override
    public boolean isAdaptedForCodeLens(JavaCodeLensContext context, IProgressMonitor monitor) {
        try {
            return getParticipant().isAdaptedForCodeLens(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling isAdaptedForCodeLens", e);
            return false;
        }
    }

    @Override
    public void beginCodeLens(JavaCodeLensContext context, IProgressMonitor monitor) {
        try {
            getParticipant().beginCodeLens(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling beginCodeLens", e);
        }
    }

    @Override
    public List<CodeLens> collectCodeLens(JavaCodeLensContext context, IProgressMonitor monitor) {
        try {
            return getParticipant().collectCodeLens(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while collecting codeLens", e);
            return null;
        }
    }

    @Override
    public void endCodeLens(JavaCodeLensContext context, IProgressMonitor monitor) {
        try {
            getParticipant().endCodeLens(context, monitor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calling endCodeLens", e);
        }
    }

}
