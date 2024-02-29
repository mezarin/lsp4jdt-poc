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
package org.eclipse.lsp4jdt.commons;

import org.eclipse.lsp4j.Position;

/**
 * MicroProfile Java hover parameters.
 *
 * @author Angelo ZERR
 *
 */
public class JavaHoverParams {

    private String uri;
    private Position position;
    private DocumentFormat documentFormat;
    private boolean surroundEqualsWithSpaces;

    public JavaHoverParams() {

    }

    public JavaHoverParams(String uri, Position position, DocumentFormat documentFormat, boolean surroundEqualsWithSpaces) {
        this();
        setUri(uri);
        setPosition(position);
        setDocumentFormat(documentFormat);
        setSurroundEqualsWithSpaces(surroundEqualsWithSpaces);
    }

    /**
     * Returns the java file uri.
     *
     * @return the java file uri.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the java file uri.
     *
     * @param uri the java file uri.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Returns the hover position
     *
     * @return the hover position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the hover position
     *
     * @param position the hover position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    public DocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    /**
     * Returns true if equals should be surrounded with spaces in hover and false otherwise
     *
     * @return true if equals should be surrounded with spaces in hover and false otherwise
     */
    public boolean isSurroundEqualsWithSpaces() {
        return surroundEqualsWithSpaces;
    }

    /**
     * Set whether or not equals should be surrounded with spaces in hover
     *
     * @param surroundEqualsWithSpaces whether or not equals should be surrounded with spaces in hover
     */
    public void setSurroundEqualsWithSpaces(boolean surroundEqualsWithSpaces) {
        this.surroundEqualsWithSpaces = surroundEqualsWithSpaces;
    }
}