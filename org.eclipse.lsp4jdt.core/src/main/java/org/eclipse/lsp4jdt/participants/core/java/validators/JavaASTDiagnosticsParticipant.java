/*******************************************************************************
* Copyright (c) 2021 Red Hat Inc. and others.
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
package org.eclipse.lsp4jdt.participants.core.java.validators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4jdt.core.java.diagnostics.IJavaDiagnosticsParticipant;
import org.eclipse.lsp4jdt.core.java.diagnostics.JavaDiagnosticsContext;
import org.eclipse.lsp4jdt.core.java.validators.JavaASTValidator;

/**
 * The java diagnostic participant which visit one time a given AST compilation
 * unit and loops for each {@link JavaASTValidator} registered with
 * "org.eclipse.lsp4jdt.jdt.core.javaASTValidators" extension point to report LSP
 * {@link Diagnostic}.
 * 
 * @author Angelo ZERR
 *
 */
public class JavaASTDiagnosticsParticipant implements IJavaDiagnosticsParticipant {

    @Override
    public List<Diagnostic> collectDiagnostics(JavaDiagnosticsContext context, IProgressMonitor monitor) throws CoreException {
        List<Diagnostic> diagnostics = new ArrayList<>();
        collectDiagnosticsInFile(context, diagnostics, monitor);
        return diagnostics;
    }

    private static void collectDiagnosticsInFile(JavaDiagnosticsContext context, List<Diagnostic> diagnostics,
                                                 IProgressMonitor monitor) throws JavaModelException {
        // Collect the list of JavaASTValidator which are adapted for the current AST
        // compilation unit to validate.
        Collection<ASTVisitor> validators = JavaASTValidatorRegistry.getInstance().getValidators(context, diagnostics,
                                                                                                 monitor);
        if (!validators.isEmpty()) {
            // Visit the AST compilation unit and process each validator.
            CompilationUnit ast = context.getASTRoot();
            ast.accept(new MultiASTVisitor(validators));
        }
    }
}