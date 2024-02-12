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
package org.eclipse.lsp4jdt.jdt.core;

import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

/**
 * Project label provider API
 *
 * @author dakwon
 *
 */
public interface IProjectLabelProvider {

	/**
	 * Returns a list of project labels ("maven", "microprofile", etc.) for the given project
	 * @param project the project to get labels for
	 * @return a list of project labels for the given project
	 * @throws JavaModelException
	 */
	List<String> getProjectLabels(IJavaProject project) throws JavaModelException;
}
