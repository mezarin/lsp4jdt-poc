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

import java.util.List;

/**
 * MicroProfile Java diagnostics parameters.
 *
 * @author Angelo ZERR
 *
 */
public class JavaDiagnosticsParams {

    private List<String> uris;

    private DocumentFormat documentFormat;

    private JavaDiagnosticsSettings settings;

    public JavaDiagnosticsParams() {
        this(null);
    }

    public JavaDiagnosticsParams(List<String> uris) {
        this(uris, null);
    }

    public JavaDiagnosticsParams(List<String> uris, JavaDiagnosticsSettings settings) {
        setUris(uris);
        this.settings = settings;
    }

    /**
     * Returns the java file uris list.
     *
     * @return the java file uris list.
     */
    public List<String> getUris() {
        return uris;
    }

    /**
     * Set the java file uris list.
     *
     * @param uris the java file uris list.
     */
    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public DocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    /**
     * Returns the diagnostics settings.
     *
     * @return the diagnostics settings
     */
    public JavaDiagnosticsSettings getSettings() {
        return this.settings;
    }

    /**
     * Sets the diagnostics settings.
     *
     * @param settings the new value for the diagnostics settings
     */
    public void setSettings(JavaDiagnosticsSettings settings) {
        this.settings = settings;
    }
}