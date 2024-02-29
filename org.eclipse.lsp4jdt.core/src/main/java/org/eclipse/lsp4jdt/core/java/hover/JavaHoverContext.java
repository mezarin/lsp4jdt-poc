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
package org.eclipse.lsp4jdt.core.java.hover;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4jdt.commons.DocumentFormat;
import org.eclipse.lsp4jdt.core.java.AbtractJavaContext;
import org.eclipse.lsp4jdt.core.utils.IJDTUtils;

/**
 * Java hover context for a given compilation unit.
 *
 * @author Angelo ZERR
 *
 */
public class JavaHoverContext extends AbtractJavaContext {

    private final Position hoverPosition;

    private final IJavaElement hoverElement;

    private final DocumentFormat documentFormat;

    private final boolean surroundEqualsWithSpaces;

    public JavaHoverContext(String uri, ITypeRoot typeRoot, IJDTUtils utils, IJavaElement hoverElement,
                            Position hoverPosition, DocumentFormat documentFormat, boolean surroundEqualsWithSpaces) {
        super(uri, typeRoot, utils);
        this.hoverElement = hoverElement;
        this.hoverPosition = hoverPosition;
        this.documentFormat = documentFormat;
        this.surroundEqualsWithSpaces = surroundEqualsWithSpaces;
    }

    public DocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public IJavaElement getHoverElement() {
        return hoverElement;
    }

    public Position getHoverPosition() {
        return hoverPosition;
    }

    /**
     * Returns true if `=` should be surrounded with spaces in hover items, and false otherwise
     *
     * @return true if `=` should be surrounded with spaces in hover items, and false otherwise
     */
    public boolean isSurroundEqualsWithSpaces() {
        return surroundEqualsWithSpaces;
    }
}